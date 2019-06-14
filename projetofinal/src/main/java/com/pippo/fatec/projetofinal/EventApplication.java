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

import java.util.Base64;

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
			String fileName = json.getString("filename");
			String problem = json.getString("problem");
			String sourceCode = json.getString("sourcecode");
			sourceCode = new String(Base64.getDecoder().decode(sourceCode));

			
			FileWriter filePython = new FileWriter("files\\script-python\\" + fileName);
			PrintWriter filePythonWriter = new PrintWriter(filePython);
			
			filePythonWriter.printf(sourceCode);
			filePythonWriter.close();

			executePython(fileName);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/*Método de execuçao do arquivo python gerado*/
	public void runPython(String fileName) throws IOException {

		//String python_path = System.getenv("PYTHON_VENV");
		/*execução do arquivo na pasta files raiz do projeto*/
		String execution = "cmd.exe /c python files/script-python/" + fileName;

        System.out.println(execution);

        Process p = Runtime.getRuntime().exec(execution);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream(), "ISO-8859-1"));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream(), "ISO-8859-1"));

        String s = null;

		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}
	}

	public static void executePython(String fileName) throws IOException {
		String python_path = "C:\\Users\\danny\\AppData\\Local\\Programs\\Python\\Python37-32\\python.exe";
		System.out.println(python_path);

		Process p = Runtime.getRuntime().exec(python_path + " files\\script-python\\" + fileName);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

		String s = null;
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}

		while ((s = stdError.readLine()) != null) {
			System.out.println(s);
		}
	}


	public String decoder(String texto) {

		String textoDeco = new String(Base64.getDecoder().decode(texto));

		System.out.println("Texto desconvetido da base64: "+ textoDeco);
		return textoDeco;

	}


}
