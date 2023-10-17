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

import com.residencia.biblioteca.entities.Emprestimo;
import com.residencia.biblioteca.services.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
	
	@Autowired
	EmprestimoService emprestimoService;

	// vai mapear todos os Emprestimos

	@GetMapping
	public ResponseEntity<List<Emprestimo>> ListarEmprestimos() {
		return new ResponseEntity<>(emprestimoService.listarEmprestimos(), HttpStatus.OK);
	}

	// vai maperar um determinado Emprestimo pelo número ID
	// Até 3 parâmetros passa no path, se for mais que isso passa pelo corpo

	@GetMapping("/{id}")
	public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Integer id) {
		Emprestimo emprestimo = emprestimoService.buscarEmprestimoPorId(id);
		
		if (emprestimo == null)
			return new ResponseEntity<>(emprestimo, HttpStatus.NOT_FOUND);

		else
			return new ResponseEntity<>(emprestimo, HttpStatus.OK);

	}
	// para salvar/postar um novo Emprestimo

	@PostMapping
	public ResponseEntity<Emprestimo> salvar(@RequestBody Emprestimo emprestimo) {
		return new ResponseEntity<>(emprestimoService.salvarEmprestimo(emprestimo), HttpStatus.OK);

	}
	// Para atualizar dados

	@PutMapping
	public ResponseEntity<Emprestimo> atualizar(@RequestBody Emprestimo emprestimo) {
		return new ResponseEntity<>(emprestimoService.atualizarEmprestimo(emprestimo), HttpStatus.OK);

	}

	// Para deletar um Emprestimo do cadastro

	@DeleteMapping
	public ResponseEntity<String> deletarEmprestimo(@RequestBody Emprestimo emprestimo) {

		if (emprestimoService.deletarEmprestimo(emprestimo))
			return new ResponseEntity<>("Deletado com Sucesso", HttpStatus.OK);

		else
			return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
	}

}
