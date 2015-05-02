package com.databrigade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Response {
	private String status;
	private String result;

    public Response() {
        // Jackson deserialization
    }

    public Response(String status, String result) {
        this.status = status;
    	this.result = result;
    }

    @JsonProperty
    public String getStatus() {
        return status;
    }

    @JsonProperty
    public String getResult() {
        return result;
    }
}