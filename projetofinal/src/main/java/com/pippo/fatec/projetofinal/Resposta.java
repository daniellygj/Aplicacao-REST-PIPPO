package com.pippo.fatec.projetofinal;

public class Resposta {
	
	private String filename;
	private String problem;
	private String sourceCode;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public Resposta(String filename, String problem, String sourceCode) {
		super();
		this.filename = filename;
		this.problem = problem;
		this.sourceCode = sourceCode;
	}
	
	
	
	
}
