package com.pippo.fatec.projetofinal;


import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;

import ro.pippo.core.Application;
import ro.pippo.core.Request;

public class EventApplication extends Application {
	
	protected void onInit() {
			/*Aqui ficaraão nossas rotas*/
		
		 GET("/", routeContext -> routeContext.send("Hello World"));
		 
		 /*Aqui iremos carregar o json*/
		 /*acessar a rota, pelo postman enviando o Json*/
		 POST("/loadjson", routeContext -> {
			 final Request req = routeContext.json().getRequest();
			
			// routeContext.json().send(getCodeJson(req));
			 try {
				createCodePython(req);
			 }catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      });

	}
	
	
	private void createCodePython(Request request) throws IOException {

		try {
			JSONObject json;
			json = new JSONObject(request.getBody());
			String fileName = json.getString("filename");
			String problem = json.getString("problem");
			String sourceCode = json.getString("sourcecode");
			
			System.out.println(fileName);
			
			FileWriter filePython = new FileWriter("files\\" + fileName);
			PrintWriter filePythonWriter = new PrintWriter(filePython);
			
			filePythonWriter.printf(sourceCode);
			filePythonWriter.close();
			
			runPython(fileName);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/*Método de execuçao do arquivo python gerado*/
	public void runPython(String fileName) throws IOException {
		
		
		//String python_path = System.getenv("PYTHON_VENV");
		/*execução do arquivo na pasta files raiz do projeto*/
		String execution = "cmd.exe /c python files/" + fileName; 
		
        System.out.println(execution);

        Process p = Runtime.getRuntime().exec(execution);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream(), "ISO-8859-1"));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream(), "ISO-8859-1"));

        String s = null;
        
        while ((s = new String(stdInput.readLine().getBytes())) != null) {
            System.out.println(s);
        }

	}
	
	
	
}
