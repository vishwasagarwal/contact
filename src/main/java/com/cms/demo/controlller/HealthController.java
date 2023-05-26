package com.cms.demo.controlller;

import com.cms.demo.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("health")
public class HealthController {

    @GetMapping
    public ResponseEntity<Response<String>> health() {
        return ResponseEntity.ok(new Response<>("healthy"));
    }
}
