package com.regalite.routing.service;


import com.regalite.routing.domain.Intern;
import com.regalite.routing.repository.InternRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class InternService {

    private final InternRepository internRepository;

    public List<Intern> getInterns() {
        return internRepository.findAll();
    }

    public String save() {
        Intern intern = Intern.builder()
                .name(UUID.randomUUID().toString())
                .address("Address")
                .dob("2000-01-01")
                .branch("Branch")
                .build();
        internRepository.save(intern);
        return "Success";
    }
}
