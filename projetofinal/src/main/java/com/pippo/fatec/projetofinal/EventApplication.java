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
import java.util.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

	public static void executePython(String fileName) throws IOException { // função temporaria
		String python_path = "C:\\Users\\danny\\AppData\\Local\\Programs\\Python\\Python37-32\\python.exe";

		Process p = Runtime.getRuntime().exec(python_path + " files\\script-python\\" + fileName);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream(), "ISO-8859-1"));

		System.out.println(compareResult(stdInput, fileName));
	}


	public static String compareResult(BufferedReader stdInput, String fileName) throws IOException {
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("files\\expected-result\\" + fileName.split("\\.")[0] + ".txt"));

			String s = null;
			String line = null;
			String result = "Success";

			while (true) {
				s = stdInput.readLine();
				line = reader.readLine();

				if (s == null && line == null)
					break;

				if (s == null || line == null) {
					result = "Failed";
					break;
				}

				if (!line.equals(s))
					result = "Failed";
			}
			return result;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "An error has ocurred";
	}

	/*Método de execuçao do arquivo python gerado*/
	public void runPython(String fileName) throws IOException {
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
}
