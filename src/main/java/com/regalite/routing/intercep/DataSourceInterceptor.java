package com.regalite.routing.intercep;


import com.regalite.routing.domain.BranchEnum;
import com.regalite.routing.config.BranchContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class DataSourceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                              HttpServletResponse response, Object handler) {

        String branch = request.getHeader("branch");
        if (BranchEnum.BANGALORE.toString().equalsIgnoreCase(branch))
            BranchContextHolder.setBranchContext(BranchEnum.BANGALORE);
        else
            BranchContextHolder.setBranchContext(BranchEnum.NOIDA);
        return true;
    }
}
