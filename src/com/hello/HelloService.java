package com.hello;

import java.util.concurrent.Future;

import javax.jws.WebService;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "helloService")
public interface HelloService {
	
	@ResponseWrapper(localName = "sayHelloResponse", className = "java.lang.String")
	public String sayHello(String username);

	@ResponseWrapper(localName = "sayHelloResponse", className = "java.lang.String")
	public Future<String> sayHelloAsync(String username, AsyncHandler<String> asyncHandler);
	
	public Response<String> sayHelloAsync(String username);
	
}
