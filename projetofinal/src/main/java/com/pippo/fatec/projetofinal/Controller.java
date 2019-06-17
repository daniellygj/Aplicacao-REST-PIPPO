package com.pippo.fatec.projetofinal;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;


public class Controller{
	
	private List<Resposta> respostas = new ArrayList<Resposta>();
	
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

	private void inputPy(OutputStream out, int num, String fileName) throws IOException {
		String inputName = "\\input" + num + ".txt";
		fileName = fileName.split("\\.")[0];
		File input = new File("files\\input-python\\"  + fileName +  inputName);

		BufferedReader buffer = new BufferedReader(new FileReader(input));
		String inputPython = "";
		String line;

		while (true) {
			line = buffer.readLine();

			if (line == null)
				break;

			if (!inputPython.isEmpty())
				inputPython += "\n" + line;
			else
				inputPython += line;
		}
		out.write(inputPython.getBytes());
		out.close();
	}

	/*Método de execuçao do arquivo python gerado*/
	public LinkedList<BufferedReader> runPython(String fileName){
		/*execução do arquivo na pasta files raiz do projeto*/
		String execution = "cmd.exe /c python files/script-python/" + fileName;

		Process p;
		OutputStream out;
		LinkedList<BufferedReader> listStdImput = new LinkedList<>();

		int limit = getQuantityOfInput(fileName);

		try {
			for (int i = 1; i < limit; i++) {
				p = Runtime.getRuntime().exec(execution);
				out = p.getOutputStream();
				inputPy(out, i, fileName);
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream(), "ISO-8859-1"));

				listStdImput.add(stdInput);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listStdImput;
	}

	public int getQuantityOfInput(String fileName) { // TODO aqui
		File directory = new File("files\\input-python\\" + fileName.split("\\.")[0]);

		return directory.list().length;
	}

	public String compareResult(LinkedList<BufferedReader> inputList, Resposta resp) {
		BufferedReader reader;
		String result = "Success";
		int cont = 1;

		try {
			for (BufferedReader stdInput : inputList) {
				String outputName = "\\output" + (cont++) + ".txt";
				reader = new BufferedReader(new FileReader("files\\expected-result\\" + resp.getFilename().split("\\.")[0] + outputName));

				String s = null;
				String line = null;

				while (true) {
					s = stdInput.readLine();
					line = reader.readLine();

					System.out.println(s);
					System.out.println(line);

					if (s == null && line == null)
						break;

					if (s == null || line == null) {
						result = "Failed";
						break;
					}

					if (!line.equals(s))
						result = "Failed";
				}
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
	public List<Resposta> getRespostas() {
		return respostas;
	}
	
	
	public List<Resposta> getByIdProblem(String problem){
		List<Resposta> listByProblem = new ArrayList<Resposta>();
		
		for(Resposta resp: respostas) {
			if(resp.getProblem().equals(problem)) {
				listByProblem.add(resp);
			}
		}
		return listByProblem;
	}
	
	public List<Resposta> getByDate(String date){
		List<Resposta> listByDate = new ArrayList<Resposta>();
		
		for(Resposta resp: respostas) {
			if(resp.getDatetime().equals(date)) {
				listByDate.add(resp);
			}
		}
		return listByDate;
	}
	
	public List<Resposta> getByStatus(String status){
		List<Resposta> listByStatus = new ArrayList<Resposta>();
		
		for(Resposta resp: respostas) {
			if(resp.getStatus().equals(status)) {
				listByStatus.add(resp);
			}
		}
		return listByStatus;
	}
}
