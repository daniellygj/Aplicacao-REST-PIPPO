package com.pippo.fatec.projetofinal;

import java.io.BufferedReader;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import ro.pippo.core.Application;
import ro.pippo.core.Request;



public class EventApplication extends Application {
	
	private Resposta resposta;
	private Controller py = new Controller();
	protected void onInit() {
	
		/*Aqui iremos carregar o json*/
		/*acessar a rota, pelo postman enviando o Json*/
		POST("/loadjson", routeContext -> {
			
			final Request req = routeContext.json().getRequest();
			
			addResposta(req);
			py.createCodePython(resposta);
			BufferedReader stdInput = py.runPython(resposta.getFilename());
			py.compareResult(stdInput, resposta);
			
			/*Retorna Json da resultado da Resposta*/
			routeContext.json().send(new Resposta(resposta.getFilename(), resposta.getProblem(), resposta.getStatus()));
			
		});
		
		/*Rota GET, alterar no Postman para GET retorna o Hitorico
		 *
		 * Criar Rota com inserção de variaveis para consultar com filtros
		 * loadjson/status
		 * loadjson/id
		 * loadjson/perido*/
		
		GET("/loadjson", routeContext -> {
			routeContext.json().send(py.getRespostas());
		});
		
		GET("/loadjson/id/{value}", routeContext -> {
			
			if(routeContext.getParameter("value") != null) {
				routeContext.json().send(py.getByIdProblem(routeContext.getParameter("value").toString()));
			}
			
		});
		GET("/loadjson/status/{value}", routeContext -> {
					
			if(routeContext.getParameter("value") != null){
				routeContext.json().send(py.getByStatus(routeContext.getParameter("value").toString()));
			}
					
				});
		GET("/loadjson/datetime/{value}", routeContext -> {
			if(routeContext.getParameter("value") != null){
				System.out.println(routeContext.getParameter("value"));
				routeContext.json().send(py.getByIdDate(routeContext.getParameter("value").toString()));
			}
			
		});
		
		
		
		/*
		 	if(routeContext.getParameter("status") != null){
				System.out.println(routeContext.getParameter("status"));
				routeContext.json().send(py.getByStatus(routeContext.getParameter("status").toString()));
			}
			if(routeContext.getParameter("datetime") != null){
				System.out.println(routeContext.getParameter("datetime"));
				routeContext.json().send(py.getByIdDate(routeContext.getParameter("datetime").toString()));
			}
		  
		 * */
		
		
		
		
	}
	
	private void addResposta(Request request) {
		try {
			JSONObject json;
			json = new JSONObject(request.getBody());
			String fileName = json.getString("filename");
			String problem = json.getString("problem");
			String sourceCode = json.getString("sourcecode");
			
			
			LocalDateTime agora = LocalDateTime.now();
			
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");
	        
	        String data = agora.format(formatter);
	        
	        
	        
			/*Date dataHoraAtual = new Date();
			String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataHoraAtual);*/
			
			
			resposta = new Resposta(fileName, problem, data,sourceCode);
			py.addRespostas(resposta);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
