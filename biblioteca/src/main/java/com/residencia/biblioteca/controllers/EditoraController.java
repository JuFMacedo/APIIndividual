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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.residencia.biblioteca.DTO.ReceitaWsDTO;
import com.residencia.biblioteca.entities.Editora;
import com.residencia.biblioteca.services.EditoraService;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

	@Autowired
	EditoraService editoraService;

	// vai mapear todos as editoras

	@GetMapping
	public ResponseEntity<List<Editora>> ListarEditora() {
		return new ResponseEntity<>(editoraService.listarEditoras(), HttpStatus.OK);
	}

	// vai maperar um determinado aluno pelo número ID
	// Até 3 parâmetros passa no path, se for mais que isso passa pelo corpo

	
	@GetMapping("/{id}")
	public ResponseEntity<Editora> buscarPorId(@PathVariable Integer id) {

		Editora editora = editoraService.buscarEditoraPorId(id);
		return new ResponseEntity<>(editora, HttpStatus.OK);
		
		/*Editora editora = editoraService.buscarEditoraPorId(id);

		if (editora == null)
			return new ResponseEntity<>(editora, HttpStatus.NOT_FOUND);

		else
			return new ResponseEntity<>(editora, HttpStatus.OK);*/
	}
	

	// para salvar/postar uma nova editora

	@PostMapping
	public ResponseEntity<Editora> salvar(@RequestBody Editora editora) {
		return new ResponseEntity<>(editoraService.salvarEditora(editora), HttpStatus.OK);

	}

	// para colocar uma imagem no insomnia

	@PostMapping("/comfoto")
	public ResponseEntity<Editora> salvarComFoto(@RequestPart("edt") String strEditora, // edt foi o nome que foi criado
																						// dentro do insomnia
			@RequestPart("img") MultipartFile arqImg) throws JsonMappingException, JsonProcessingException {
		return new ResponseEntity<>(editoraService.salvarEditoraComFoto(strEditora, arqImg), HttpStatus.OK);

	}

	// Para atualizar dados

	@PutMapping
	public ResponseEntity<Editora> atualizar(@RequestBody Editora editora) {
		return new ResponseEntity<>(editoraService.atualizarEditora(editora), HttpStatus.OK);

	}

	// Para deletar um aluno do cadastro

	@DeleteMapping
	public ResponseEntity<String> deletarEditora(@RequestBody Editora editora) {

		if (editoraService.deletarEditora(editora))
			return new ResponseEntity<>("Deletado com Sucesso", HttpStatus.OK);

		else
			return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/consulta-cnpj/{cnpj}")
	public ResponseEntity<ReceitaWsDTO> consultaCnpj(@PathVariable String cnpj) {

		return new ResponseEntity<>(editoraService.consultaCnpj(cnpj), HttpStatus.OK);

	}
}
