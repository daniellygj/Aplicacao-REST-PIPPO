package com.pippo.fatec.projetofinal;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.json.JSONException;
import org.json.JSONObject;

import ro.pippo.core.Application;
import ro.pippo.core.Request;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EventApplication extends Application {
	
	Resposta resposta;
	
	protected void onInit() {
		
		Controller py = new Controller();

		/*Aqui iremos carregar o json*/
		/*acessar a rota, pelo postman enviando o Json*/
		POST("/loadjson", routeContext -> {
			
			final Request req = routeContext.json().getRequest();
			
			addResposta(req);
			py.createCodePython(resposta);
			BufferedReader stdInput = py.runPython(resposta.getFilename());
			py.compareResult(stdInput, resposta);
			
			/*Retorna Json da resultado da Resposta*/
			routeContext.json().send(resposta);
			
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
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
