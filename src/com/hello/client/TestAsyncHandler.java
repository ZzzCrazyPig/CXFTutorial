package com.hello.client;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

public class TestAsyncHandler implements AsyncHandler<String> {

    private String reply;

    public void handleResponse(Response<String> response) {
        try {
            System.out.println("handleResponse called");
            reply = response.get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getResponse() {
        return reply;
    }

}