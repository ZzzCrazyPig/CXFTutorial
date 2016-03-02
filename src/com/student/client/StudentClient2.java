package com.student.client;

import java.util.concurrent.Future;

//import javax.xml.ws.Response;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.student.ChangeStudentDetails;
//import com.student.Student;


public final class StudentClient2 {

    public static void main(String args[]) throws Exception {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        factory.setServiceClass(ChangeStudentDetails.class);
        factory.setAddress("http://localhost:8080/CXFTutorial/ChangeStudent?wsdl");
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        ChangeStudentDetails client = (ChangeStudentDetails) factory.create();

       
        // callback method
        TestAsyncHandler testAsyncHandler = new TestAsyncHandler();
        System.out.println("Invoking changeStudentAsync using callback object...");
        Future<?> response = client.changeStudentAsync(
                "CrazyPig", testAsyncHandler);
        while (!response.isDone()) {
            Thread.sleep(100);
        }
        
//        Student student = (Student) response.get();
//        System.out.println(student.getName());
        
        String resp = testAsyncHandler.getResponse();
        System.out.println("Server responded through callback with: " + resp);

        System.exit(0);
    }
}