package com.hello.client;

import java.util.concurrent.Future;


import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.hello.HelloService;


public final class HelloClient2 {

    public static void main(String args[]) throws Exception {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        factory.setServiceClass(HelloService.class);
        factory.setAddress("http://192.168.16.52:8080/CXFTutorial/services/helloService?wsdl");
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        HelloService client = (HelloService) factory.create();

       
        // callback method
        TestAsyncHandler testAsyncHandler = new TestAsyncHandler();
        System.out.println("Invoking changeStudentAsync using callback object...");
        Future<?> response = client.sayHelloAsync(
                "CrazyPig", testAsyncHandler);
        while (!response.isDone()) {
            Thread.sleep(100);
        }
        
        String resp = testAsyncHandler.getResponse();
        System.out.println("Server responded through callback with: " + resp);

        System.exit(0);
    }
}