package com.regalite.routing.routing;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import com.regalite.routing.domain.BranchEnum;
import com.regalite.routing.config.BranchContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceRouting extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return BranchContextHolder.getBranchContext();
    }

    public void initDatasource(DataSource bangaloreDataSource,
                               DataSource noidaDataSource) {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(BranchEnum.NOIDA, noidaDataSource);
        dataSourceMap.put(BranchEnum.BANGALORE, bangaloreDataSource);
        this.setTargetDataSources(dataSourceMap);

        // Here we have to provide default datasource details.
        this.setDefaultTargetDataSource(noidaDataSource);
    }
}