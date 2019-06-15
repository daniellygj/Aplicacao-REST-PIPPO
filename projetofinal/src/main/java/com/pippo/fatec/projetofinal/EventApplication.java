package com.pippo.fatec.projetofinal;

import java.io.BufferedReader;

import java.text.SimpleDateFormat;
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
		POST("/historico", routeContext -> {
			routeContext.json().send(py.getRespostas());
		});
		
	}
	
	private void addResposta(Request request) {
		try {
			JSONObject json;
			json = new JSONObject(request.getBody());
			String fileName = json.getString("filename");
			String problem = json.getString("problem");
			String sourceCode = json.getString("sourcecode");
			
			Date dataHoraAtual = new Date();
			String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataHoraAtual);
			
			
			resposta = new Resposta(fileName, problem, data,sourceCode);
			py.addRespostas(resposta);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
