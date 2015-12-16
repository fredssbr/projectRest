package com.vogella.jersey.dao;

import java.util.HashMap;
import java.util.Map;

import com.vogella.jersey.jaxb.model.Todo;

public enum TodoDao {
	INSTANCE;
	
	private Map<String, Todo> contentProvider = new HashMap<String, Todo>();
	
	private TodoDao(){
		Todo todo = new Todo();
		todo.setId("1");
		todo.setDescription("Go to the gym.");
		todo.setSummary("Exercise");
		
		Todo todo2 = new Todo();
		todo2.setId("2");
		todo2.setDescription("Read a book.");
		todo2.setSummary("Read");
		
		Todo todo3 = new Todo();
		todo3.setId("3");
		todo3.setDescription("Create a web app.");
		todo3.setSummary("Code");
		
		Todo todo4 = new Todo();
		todo4.setId("4");
		todo4.setDescription("Go to the club.");
		todo4.setSummary("Dance");
		
		Todo todo5 = new Todo();
		todo5.setId("5");
		todo5.setDescription("Play the piano.");
		todo5.setSummary("Music");
		
		contentProvider.put("1", todo);
		contentProvider.put("2", todo2);
		contentProvider.put("3", todo3);
		contentProvider.put("4", todo4);
		contentProvider.put("5", todo5);
	}
	
	public Map<String, Todo> getModel(){
		return contentProvider;
	}

}
