package com.example.sample_login.api;

import com.example.sample_login.Response.BasicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {


    @PostMapping("/test")
    public ResponseEntity<BasicResponse> test(@RequestBody(required = false) Map<String, Object> map) {
        BasicResponse basicResponse = BasicResponse.builder().build();
        return new ResponseEntity<>(basicResponse, HttpStatus.OK);
    }


}
