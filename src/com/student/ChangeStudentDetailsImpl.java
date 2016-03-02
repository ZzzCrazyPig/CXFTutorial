package com.student;

import java.util.concurrent.Future;

import javax.jws.WebService;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

import org.apache.cxf.annotations.UseAsyncMethod;
import org.apache.cxf.jaxws.ServerAsyncResponse;

@WebService(endpointInterface = "com.student.ChangeStudentDetails")
public class ChangeStudentDetailsImpl implements ChangeStudentDetails {

    // synchronous method, as before
    @UseAsyncMethod
    public String changeStudent(String name) {
        System.out.println("Executing operation changeStudent synchronously\n");
        return "How are you " + name;
    }

    // async callback Method (new method)
    public Future<?> changeStudentAsync(final String name, final AsyncHandler<Student> asyncHandler) {
        System.out.println("Executing operation changeStudentAsync asynchronously\n");
        final ServerAsyncResponse<Student> asyncResponse = new ServerAsyncResponse<Student>();
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Student resp = new Student();
                resp.setName("How are you " + name);
                asyncResponse.set(resp);
                System.out.println("Responding on background thread\n");
                asyncHandler.handleResponse(asyncResponse);
            }
        }.start();

        return asyncResponse;
    }

    // async polling Method (new method)
    public Response<Student> changeStudentAsync(String requestType) {
    	System.out.println("Executing operation changeStudentAsync asynchronously\n");
        final ServerAsyncResponse<Student> asyncResponse = new ServerAsyncResponse<Student>();
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Student resp = new Student();
                resp.setName("How are you " + requestType);
                asyncResponse.set(resp);
                System.out.println("Responding on background thread\n");
            }
        }.start();

        return asyncResponse;
    }
}