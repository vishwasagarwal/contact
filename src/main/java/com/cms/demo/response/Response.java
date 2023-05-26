package com.cms.demo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private String message;
    private String code;
    private boolean success;
    private T data;
    private long serverTime = System.currentTimeMillis() / 1000;

    public Response(T data) {
        this.message = "success";
        this.code = "200";
        this.success = true;
        this.data = data;
    }

    public Response(boolean success, String code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
