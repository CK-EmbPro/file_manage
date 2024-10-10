package com.manage_file.file_handling.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse<T> {
    private T data;
    private HttpStatus code;

    public ApiResponse(T data, HttpStatus code){
        this.data = data;
        this.code=code;
    }
}
