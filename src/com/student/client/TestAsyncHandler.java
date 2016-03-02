package com.student.client;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

import com.student.Student;

public class TestAsyncHandler implements AsyncHandler<Student> {

    private Student    reply;

    public void handleResponse(Response<Student> response) {
        try {
            System.out.println("handleResponse called");
            reply = response.get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getResponse() {
        return reply.getName();
    }

}