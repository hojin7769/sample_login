package com.example.sample_login.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasicResponse {
    private Integer code;
    private HttpStatus httpStatus;
    private String message;
    private Integer count;
    private List<?> result;
}
