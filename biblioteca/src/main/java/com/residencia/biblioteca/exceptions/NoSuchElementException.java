package com.residencia.biblioteca.exceptions;

public class NoSuchElementException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	
	// A unica diferença entre os construtores são os parametros que são passados para eles:
	
	public NoSuchElementException(String message) {
		super(message);
	}

	public NoSuchElementException(String entidade, Integer id) {
		super("Não foi encontrado(a) " + entidade + " com o id = " + id);
	}
}