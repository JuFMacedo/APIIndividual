package com.residencia.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.biblioteca.entities.Livro;
import com.residencia.biblioteca.services.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	LivroService livroService;

	// vai mapear todos os Livros

	@GetMapping
	public ResponseEntity<List<Livro>> ListarLivros() {
		return new ResponseEntity<>(livroService.listarLivros(), HttpStatus.OK);
	}

	// vai maperar um determinado Livro pelo número ID
	// Até 3 parâmetros passa no path, se for mais que isso passa pelo corpo

	@GetMapping("/{id}")
	public ResponseEntity<Livro> buscarPorId(@PathVariable Integer id) {
		return new ResponseEntity<>(livroService.buscarLivroPorId(id), HttpStatus.OK);

	}
	// para salvar/postar um novo Livro

	@PostMapping
	public ResponseEntity<Livro> salvar(@RequestBody Livro livro) {
		return new ResponseEntity<>(livroService.salvarLivro(livro), HttpStatus.OK);

	}
	// Para atualizar dados

	@PutMapping
	public ResponseEntity<Livro> atualizar(@RequestBody Livro livro) {
		return new ResponseEntity<>(livroService.atualizarLivro(livro), HttpStatus.OK);

	}

	// Para deletar um Livro do cadastro

	@DeleteMapping
	public ResponseEntity<String> deletarLivro(@RequestBody Livro livro) {
		livroService.deletarLivro(livro);
		return new ResponseEntity<>("Deletado com Sucesso", HttpStatus.OK);
	}
}
