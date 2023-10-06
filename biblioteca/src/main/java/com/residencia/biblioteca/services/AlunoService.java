package com.residencia.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.biblioteca.entities.Aluno;
import com.residencia.biblioteca.repositories.AlunoRepository;

@Service
public class AlunoService {

	// CRUD (são métodos)
	/*
	 * Com esse método é o suficiente para pegar todos os dados necessários dentro
	 * de alunos para verificar as informações.
	 */
	@Autowired
	AlunoRepository alunoRepo;

	// recuperar todos os alunos
	public List<Aluno> listarAlunos() {
		return alunoRepo.findAll();
	}

	// recuperar um aluno pela chave primária
	public Aluno buscarAlunoPorId(Integer id) {
		return alunoRepo.findById(id).get();
	}

	// salvar um novo aluno
	public Aluno salvarAluno(Aluno aluno) {
		return alunoRepo.save(aluno);

	}

	// atualizar um determinado aluno
	public Aluno atualizarAluno(Aluno aluno) {
		return alunoRepo.save(aluno);
	}

	// deletar um determinado aluno
	public void deletarAluno(Aluno aluno) { // pode usar puxando pelo ID, o que torna a busca e
		alunoRepo.delete(aluno); // o deletar mais fácil ao invés do nome

		/*
		 * Aluno confereAlunoDeletado =
		 * buscarAlunoPorId(aluno.getNumeroMatriculaAluno());
		 * 
		 * if(null == confereAlunoDeletado)
		 * o aluno foi deletado
		 * 
		 * else
		 * O aluno continua existindo
		 */
	}

}
