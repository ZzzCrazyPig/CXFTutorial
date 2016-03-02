package com.hello;

import java.util.concurrent.Future;

import javax.jws.WebService;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

import org.apache.cxf.annotations.UseAsyncMethod;
import org.apache.cxf.jaxws.ServerAsyncResponse;

@WebService(endpointInterface="com.hello.HelloService")
public class HelloServiceImpl implements HelloService {

	@Override
	@UseAsyncMethod
	public String sayHello(String username) {
		System.out.println("execute sayHello method");
		try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		return "hello " + username;
	}

	@Override
	public Future<String> sayHelloAsync(String username,
			AsyncHandler<String> asyncHandler) {
		System.out.println("execute sayHelloAsync method");
		final ServerAsyncResponse<String> asyncResponse = new ServerAsyncResponse<String>();
        new Thread() {
            public void run() {
                String result = sayHello(username);
                asyncResponse.set(result);
                System.out.println("Responding on background thread\n");
                asyncHandler.handleResponse(asyncResponse);
            }
        }.start();

        return asyncResponse;
	}

	@Override
	public Response<String> sayHelloAsync(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
