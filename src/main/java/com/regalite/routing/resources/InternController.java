package com.regalite.routing.resources;

import com.regalite.routing.domain.Intern;
import com.regalite.routing.service.InternService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InternController {


    private final InternService internService;

    @GetMapping(value = "/intern")
    public ResponseEntity<List<Intern>> getInterns() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(internService.getInterns());
    }

    @GetMapping(value = "/save")
    public ResponseEntity<String> save() {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(internService.save());
    }
}
