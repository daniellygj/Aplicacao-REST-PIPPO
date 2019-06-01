package com.pippo.fatec.projetofinal;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;

import ro.pippo.core.Application;
import ro.pippo.core.Request;
import ro.pippo.core.Application;

public class EventApplication extends Application {
	
	protected void onInit() {
			/*Aqui ficaraão nossas rotas*/
		
		 GET("/", routeContext -> routeContext.send("Hello World"));
		 
		 /*Aqui iremos carregar o json*/
		 POST("/loadjson", routeContext -> {
			 final Request req = routeContext.json().getRequest();
			
			// routeContext.json().send(getCodeJson(req));
			 try {
				createCodePython(req);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      });

	}
	
	private void createCodePython(Request request) throws IOException {

		try {
			JSONObject json;
			json = new JSONObject(request.getBody());
			String filename = json.getString("filename");
			String problem = json.getString("problem");
			String sourceCode = json.getString("sourcecode");
			
			FileWriter filePython = new FileWriter("d:\\" + filename);
			PrintWriter filePythonWriter = new PrintWriter(filePython);
			
			filePythonWriter.printf(sourceCode);
			filePythonWriter.close();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
