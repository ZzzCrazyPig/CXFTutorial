package com.student;

import java.util.concurrent.Future;

import javax.jws.WebService;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "ChangeStudentDetails")
public interface ChangeStudentDetails {

    public Response<Student> changeStudentAsync(String name);

   
    @ResponseWrapper(localName = "changeStudentResponse", className = "com.student.Student")
    public Future<?> changeStudentAsync(String requestType,AsyncHandler<Student> asyncHandler);

    @ResponseWrapper(localName = "changeStudentResponse", className = "com.student.Student")
    public String changeStudent(String name);
}