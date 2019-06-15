package com.pippo.fatec.projetofinal;

public class Resposta {
	
	private String filename;
	private String problem;
	private String datetime;
	private String sourceCode;
	
	
	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String status;
	
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
	public Resposta(String filename, String problem, String datetime, String sourceCode) {
		super();
		this.filename = filename;
		this.problem = problem;
		this.datetime = datetime;
		this.sourceCode = sourceCode;
	}
	public Resposta(String filename, String problem, String datetime, String sourceCode, String status) {
		super();
		this.filename = filename;
		this.problem = problem;
		this.datetime = datetime;
		this.sourceCode = sourceCode;
		this.status = status;
	}
	
	
	
	

	
	
	
	
}
