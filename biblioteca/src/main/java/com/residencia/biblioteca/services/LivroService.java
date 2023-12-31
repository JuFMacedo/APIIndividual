package com.residencia.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.biblioteca.entities.Livro;
import com.residencia.biblioteca.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	LivroRepository livroRepo;

	@Autowired
	EmailService emailService;

	public List<Livro> listarLivros() {
		return livroRepo.findAll();
	}

	public Livro buscarLivroPorId(Integer id) {
		return livroRepo.findById(id).orElse(null);
	}

	public Livro salvarLivro(Livro livro) {
		Livro newLivro = livroRepo.save(livro);
		
		//email que vai receber a notificação
		emailService.enviarEmail("juliafmacedo712@gmail.com", "Novo livro cadastrado", newLivro.toString()); 
		return newLivro;

	}

	public Livro atualizarLivro(Livro livro) {
		return livroRepo.save(livro);
	}

	public Boolean deletarLivro(Livro livro) {
		if (livro == null)
			return null;

		Livro livroExistente = buscarLivroPorId(livro.getCodigoLivro());

		if (livroExistente == null)
			return null;

		livroRepo.delete(livro);

		Livro livroContinuaExistindo = buscarLivroPorId(livro.getCodigoLivro());
		if (livroContinuaExistindo == null)
			return true;

		else
			return false;
	}
}
