package com.vogella.jersey.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.vogella.jersey.dao.TodoDao;
import com.vogella.jersey.jaxb.model.Todo;

//Maps the resource to the URL /todos
@Path("/todos")
public class TodosResource {
	
	//Allows the insertion of contextual objects into the class
	//e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	/**
	 * Returns the list of todos to the user browser
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Todo> getTodosBrowser(){
		List<Todo> todos = new ArrayList<Todo>();
		todos.addAll(TodoDao.INSTANCE.getModel().values());
		return todos;
	}
	
	/**
	 * Returns the list of todos to applications
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Todo> getTodos(){
		List<Todo> todos = new ArrayList<Todo>();
		todos.addAll(TodoDao.INSTANCE.getModel().values());
		return todos;
	}
	/**
	 * Returns the number of todos
	 * Use http://server:port(or DNS)/projectRest/rest/todos/count to get the total numer of records
	 * @return
	 */
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount(){
		int count = TodoDao.INSTANCE.getModel().size();
		return String.valueOf(count);
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newTodo(@FormParam("id") String id,
			@FormParam("summary") String summary,
			@FormParam("description") String description,
			@Context HttpServletResponse servletResponse ) throws IOException{
		Todo todo = new Todo();
		todo.setId(id);
		todo.setSummary(summary);
		if(description != null)
			todo.setDescription(description);
		
		TodoDao.INSTANCE.getModel().put(todo.getId(), todo);
		
		servletResponse.sendRedirect("../create-todo.html");		
	}
	/**
	 * Defines that the next path parameter after /todos is treated as a parameter
	 * and passed to the TodoResource.
	 * Allows to type http://server:port/projectRest/rest/todos/1
	 * 1 being treated as a parameter todo and passed to TodoResource
	 * @param id
	 * @return
	 */
	@Path("{todo}")
	public TodoResource getTodo(@PathParam("todo") String id){
		return new TodoResource(uriInfo, request, id);
	}
	
}
