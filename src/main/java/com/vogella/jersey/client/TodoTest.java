package com.vogella.jersey.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.vogella.jersey.jaxb.model.Todo;

public class TodoTest {

	public static void main(String[] args) {
		
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());
		
		//Creates a todo
		Todo todo = new Todo();
		todo.setId("7");
		todo.setDescription("Ask for a friend to help you.");
		todo.setSummary("Challenge");
		
		Response response = target.path("rest").
				path("todos").
				path(todo.getId()).
				request(MediaType.APPLICATION_XML).
				put(Entity.entity(todo, MediaType.APPLICATION_XML),Response.class);
		
		//Return code should be 201 = created resource
		System.out.println("Return todo creation: " + response.getStatus());
		
		//Gets the todos
		System.out.println("Return todos: " + target.path("rest").path("todos").request().accept(MediaType.TEXT_XML).get(String.class));
		
		//Gets the todos Json for application
		System.out.println("Return todos Json for application: " + target.path("rest").path("todos").request().accept(MediaType.APPLICATION_JSON).get(String.class));
		
		//Gets the todos XML for application
		System.out.println("Return todos XML for application: " + target.path("rest").path("todos").request().accept(MediaType.APPLICATION_XML).get(String.class));
		
		//Gets Todo with id 1
		System.out.println("Gets todo with id 1:" +  target.path("rest").path("todos/1").request().accept(MediaType.APPLICATION_XML).get());
		
		//Deletes Todo with id 1
		target.path("rest").path("todos/1").request().delete();
		
		//Gets the todos (id 1 deleted)
		System.out.println("Return todos (without id 1): " + target.path("rest").path("todos").request().accept(MediaType.TEXT_XML).get(String.class));
		
		//Creates a todo via Form
		Form form = new Form();
		form.param("id", "8");
		form.param("summary","Demonstration of the client lib for forms");
		response = target.path("rest").
				path("todos").
				request().
				post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED),Response.class);
		
		//Return code should be 201 = created resource
		System.out.println("Return todo creation with form: " + response.getStatus());
		
		//Get all the todos, id 4 should have been created
	    System.out.println(target.path("rest").path("todos").request().accept(MediaType.APPLICATION_XML).get(String.class));
		
    
		 //Get all the todos as a list
		 List<Todo> listTodo = target.path("rest").path("todos").request().accept(MediaType.APPLICATION_XML).get(new GenericType<List<Todo>>(){});
		 System.out.println("Print the list of todo:" + listTodo.toString());
	
		/*// Get XML
		String xmlResponse = target.path("rest").path("todo").request().accept(MediaType.TEXT_XML).get(String.class);
		// Get XML for application
		String xmlAppResponse = target.path("rest").path("todo").request().accept(MediaType.APPLICATION_XML).get(String.class);
		//Get Json for application
		String jsonAppResponse = target.path("rest").path("todo").request().accept(MediaType.APPLICATION_JSON).get(String.class);
		// For JSON response also add the Jackson libraries to your webapplication

		System.out.println("Response text/xml: " + xmlResponse);
		System.out.println("Response application/xml: " + xmlAppResponse);
		System.out.println("Response application/json: " + jsonAppResponse);*/
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/projectRest").build();
	}

}
