package com.vogella.jersey.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class Test {

	public static void main(String[] args) {
		
		ClientConfig config = new ClientConfig();
		
		Client client = ClientBuilder.newClient(config);
		
		WebTarget target = client.target(getBaseURI());
		
		String response = target.path("rest").
				path("hello").
				request().
				accept(MediaType.TEXT_PLAIN).
				get(Response.class)
				.toString();
		
		String plainAnswer = target.path("rest").path("hello").request().accept(MediaType.TEXT_PLAIN).get(String.class);
		String xmlAnswer = target.path("rest").path("hello").request().accept(MediaType.TEXT_XML).get(String.class);
		String htmlAnswer = target.path("rest").path("hello").request().accept(MediaType.TEXT_HTML).get(String.class);
		
		System.out.println("Response: " + response);
		System.out.println("Response text/plain: " + plainAnswer);
		System.out.println("Response text/xml: " + xmlAnswer);
		System.out.println("Response text/html: " + htmlAnswer);
	}
	
	public static URI getBaseURI(){
		return UriBuilder.fromUri("http://localhost:8080/projectRest").build();
	}
}