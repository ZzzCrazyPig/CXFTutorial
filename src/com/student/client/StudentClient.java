package com.student.client;

import javax.xml.ws.Response;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.student.ChangeStudentDetails;
import com.student.Student;


public final class StudentClient {

    public static void main(String args[]) throws Exception {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        factory.setServiceClass(ChangeStudentDetails.class);
        factory.setAddress("http://localhost:8080/CXFTutorial/ChangeStudent?wsdl");
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        ChangeStudentDetails client = (ChangeStudentDetails) factory.create();
        // polling method
        System.out.println("Invoking changeStudentAsync using polling...");
        Response<Student> changeStudentResp = client.changeStudentAsync(System.getProperty("user.name"));
        while (true) {
            if (changeStudentResp.isDone()) {
                Student reply = changeStudentResp.get();
                System.out.println("Server responded through polling with: "
                        + reply.getName());
                break;
            } else {
                Thread.sleep(1000);
                System.out.println("Polling %%%%%%%%%%%%%%%");
            }

        }


        System.exit(0);
    }
}