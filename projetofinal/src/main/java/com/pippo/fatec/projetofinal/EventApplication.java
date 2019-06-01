package com.pippo.fatec.projetofinal;


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
			 
			
				try {
					final Request req = routeContext.json().getRequest();
					
					
					JSONObject json;
					json = new JSONObject(req.getBody());
					String file = json.getString("filename");
					System.out.println(file);
					 
					//routeContext.send(a);
			            //Resposta pesposta = newResposta();
			         routeContext.json().send(file);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
				 
				
			 
	      });
		 
	

	}
	
	private Resposta newResposta() {
		return new Resposta("teste.py", "A", "print(\"Hello world\")\r\n" + 
				"print(2 * 2)");
	}
}
