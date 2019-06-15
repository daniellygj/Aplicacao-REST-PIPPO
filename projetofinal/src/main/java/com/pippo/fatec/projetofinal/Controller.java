package com.pippo.fatec.projetofinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class Controller{
	
	List<Resposta> respostas = new ArrayList<Resposta>();
	
	public void createCodePython(Resposta rep){
		
		/*Conversão de base 64*/
		String sourceCode = new String(Base64.getDecoder().decode(rep.getSourceCode()));
		
		FileWriter filePython;
		try {
			filePython = new FileWriter("files\\script-python\\" + rep.getFilename());
			
			PrintWriter filePythonWriter = new PrintWriter(filePython);
			
			filePythonWriter.printf(sourceCode);
			filePythonWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public BufferedReader executePython(String fileName) throws IOException { // função temporaria
		
		String python_path = "C:\\Users\\danny\\AppData\\Local\\Programs\\Python\\Python37-32\\python.exe";
		Process p = Runtime.getRuntime().exec(python_path + " files\\script-python\\" + fileName);
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream(), "ISO-8859-1"));
		return stdInput;
		
	}
	
	/*Método de execuçao do arquivo python gerado*/
	public BufferedReader runPython(String fileName){
		/*execução do arquivo na pasta files raiz do projeto*/
		String execution = "cmd.exe /c python files/script-python/" + fileName;
		System.out.println(execution);
		Process p;
		try {
			p = Runtime.getRuntime().exec(execution);
			
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream(), "ISO-8859-1"));
			
			return stdInput;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String compareResult(BufferedReader stdInput, Resposta resp) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("files\\expected-result\\" + resp.getFilename().split("\\.")[0] + ".txt"));

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
			
			resp.setStatus(result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "An error has ocurred";
	}
	
	
	public void addRespostas(Resposta resposta) {
		respostas.add(resposta);
	}
	
	

}
