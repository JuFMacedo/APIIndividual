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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.biblioteca.DTO.AlunoResumidoDTO;
import com.residencia.biblioteca.entities.Aluno;
import com.residencia.biblioteca.services.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	AlunoService alunoService;

	// vai mapear todos os alunos

	@GetMapping
	public ResponseEntity<List<Aluno>> ListarAlunos() {
		return new ResponseEntity<>(alunoService.listarAlunos(), HttpStatus.OK);
	}

	// vai maperar um determinado aluno pelo número ID
	// Até 3 parâmetros passa no path, se for mais que isso passa pelo corpo

	@GetMapping("/{id}")
	public ResponseEntity<Aluno> buscarPorId(@PathVariable Integer id) {
		Aluno aluno = alunoService.buscarAlunoPorId(id);

		if (aluno == null)
			return new ResponseEntity<>(aluno, HttpStatus.NOT_FOUND);

		else
			return new ResponseEntity<>(aluno, HttpStatus.OK);
	}

	//DTO
	
	@GetMapping("/resumido/{id}")
	public ResponseEntity<AlunoResumidoDTO> getResumidoPorId(@PathVariable Integer id) {
		AlunoResumidoDTO alunoResDTO = alunoService.getAlunoResumoPorId(id);
		if (alunoResDTO == null)
			return new ResponseEntity<>(alunoResDTO, HttpStatus.NOT_FOUND);

		else
			return new ResponseEntity<>(alunoResDTO, HttpStatus.OK);
	}

	// Pesquisa por QUERY: /aluno/porid?id=5

	@GetMapping("/porid")
	public ResponseEntity<Aluno> buscarAlunoPorId(@RequestParam Integer id) {
		return new ResponseEntity<>(alunoService.buscarAlunoPorId(id), HttpStatus.OK);

	}

	// para salvar/postar um novo aluno

	@PostMapping
	public ResponseEntity<Aluno> salvar(@RequestBody Aluno aluno) {
		return new ResponseEntity<>(alunoService.salvarAluno(aluno), HttpStatus.CREATED);

	}
	// Para atualizar dados

	@PutMapping
	public ResponseEntity<Aluno> atualizar(@RequestBody Aluno aluno) {
		return new ResponseEntity<>(alunoService.atualizarAluno(aluno), HttpStatus.OK);

	}

	// Para deletar um aluno do cadastro

	@DeleteMapping
	public ResponseEntity<String> deletarAluno(@RequestBody Aluno aluno) {
		if (alunoService.deletarAluno(aluno))
			return new ResponseEntity<>("Deletado com Sucesso", HttpStatus.OK);

		else
			return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
	}
}
