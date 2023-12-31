package com.residencia.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.biblioteca.entities.Autor;
import com.residencia.biblioteca.repositories.AutorRepository;

@Service
public class AutorService {

	@Autowired
	AutorRepository autorRepo;

	public List<Autor> listarAutores() {
		return autorRepo.findAll();
	}

	public Autor buscarAutorId(Integer id) {
		return autorRepo.findById(id).orElse(null);
	}

	public Autor salvarAutor(Autor autor) {
		return autorRepo.save(autor);
	}

	public Autor atualizarAutor(Autor autor) {
		return autorRepo.save(autor);
	}

	public Boolean deletarAutor(Autor autor) {
		if (autor == null)
			return null;

		Autor autorExistente = buscarAutorId(autor.getCodigoAutor());
		if (autorExistente == null)
			return false;

		autorRepo.delete(autor);

		Autor autorContinuaExistente = buscarAutorId(autor.getCodigoAutor());

		if (autorContinuaExistente == null)
			return true;

		return false;

	}
}