package com.residencia.biblioteca.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.biblioteca.DTO.EditoraDTO;
import com.residencia.biblioteca.DTO.ReceitaWsDTO;
import com.residencia.biblioteca.entities.Editora;
import com.residencia.biblioteca.exceptions.NoSuchElementException;
import com.residencia.biblioteca.repositories.EditoraRepository;

import io.jsonwebtoken.io.IOException;

@Service
public class EditoraService {

	@Autowired
	EditoraRepository editoraRepo;

	public List<Editora> listarEditoras() {
		return editoraRepo.findAll();
	}

	public Editora buscarEditoraPorId(Integer id) {
		// return editoraRepo.findById(id).orElse(null);

		return editoraRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Editora", id));
		//return editoraRepo.findById(id).orElseThrow(() -> new AlunoNotFoundException(id));
	}

	public Editora salvarEditora(Editora editora) {
		return editoraRepo.save(editora);

	}

	private EditoraDTO entityToDto(Editora editora) {
		EditoraDTO editoraDto = new EditoraDTO();
		editoraDto.setNome(editora.getNome());
		editoraDto.setImagemFileName(editora.getImagemFileName());
		editoraDto.setImagemNome(editora.getImagemNome());
		editoraDto.setImagemUrl(editora.getImagemUrl());

		return editoraDto;
	}

	private Editora dtoEntity(EditoraDTO editoraDTO) {
		Editora editora = new Editora();
		editora.setNome(editoraDTO.getNome());
		editora.setImagemFileName(editora.getImagemFileName());
		editora.setImagemNome(editora.getImagemNome());
		editora.setImagemUrl(editora.getImagemUrl());

		return editora;

	}

	/*
	 * public EditoraDTO salvarEditoraDTO(EditoraDTO editoraDTO) { Editora editora =
	 * dtoToEntity(editoraDTO); return entityToDto(editoraRepo.save(editora)); }
	 */

	public Editora atualizarEditora(Editora editora) {
		return editoraRepo.save(editora);
	}

	public Boolean deletarEditora(Editora editora) {

		if (editora == null)
			return null;

		Editora editoraExistente = buscarEditoraPorId(editora.getCodigoEditora());

		if (editoraExistente == null)
			return null;

		editoraRepo.delete(editora);

		Editora editoraContinuaExistindo = buscarEditoraPorId(editora.getCodigoEditora());
		if (editoraContinuaExistindo == null)
			return true;

		else
			return false;
	}

	public ReceitaWsDTO consultaCnpj(String cnpj) {

		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://receitaws.com.br/v1//cnpj/{cnpj}"; // {cnpj} parametro que espera receber com o nome cnpj

		Map<String, String> params = new HashMap<String, String>();
		params.put("cnpj", cnpj);

		ReceitaWsDTO receitaDto = restTemplate.getForObject(uri, ReceitaWsDTO.class, params);

		return receitaDto;

	}

	// Para colocar foto:
	
	public Editora salvarEditoraComFoto(String strEditora, MultipartFile arqImg)
			throws JsonMappingException, JsonProcessingException {
		Editora editora = new Editora();

		try {
			ObjectMapper objMp = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			editora = objMp.readValue(strEditora, Editora.class);
		} catch (IOException e) {
			System.out.println("Erro ao converter a string Editora: " + e.toString());
		}

		// Usar array de bytes, vai pegar igual fez nessa estrutura e tem que colocar o array 
		//arqImg.getName()
		//editora.setImagemFileName(arqImg.getBytes());
		
		
		return editoraRepo.save(editora);
	}

}
