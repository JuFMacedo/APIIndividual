package com.residencia.biblioteca.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.biblioteca.DTO.AlunoResumidoDTO;
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

		// return alunoRepo.findById(id).get();
		/*
		 * Optional<Aluno> alunoBanco = alunoRepo.findById(id); if
		 * (alunoBanco.isPresent()) return alunoRepo.findById(id).get(); else return
		 * null;
		 */
		return alunoRepo.findById(id).orElse(null);
	}

	//DTO
	
	public AlunoResumidoDTO getAlunoResumoPorId(Integer id) {

		Aluno aluno = alunoRepo.findById(id).orElse(null);
		AlunoResumidoDTO alunoResDTO = new AlunoResumidoDTO();

		alunoResDTO.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
		alunoResDTO.setNome(aluno.getNome());
		alunoResDTO.setCpf(aluno.getCpf());

		return alunoResDTO;
	}

	public List<AlunoResumidoDTO> listarAlunosResumidos() {
        List<Aluno> alunos = alunoRepo.findAll();
        List<AlunoResumidoDTO> alunosDTO = new ArrayList<>();

        //O aluno dentro do for, vai receber a cada interação, o conteúdo de alunos(lista que vem do banco). alunos é um array da enntidade aluno.
        //a cada posição ele recebe um item do array alunos.
        for (Aluno aluno : alunos) {
            AlunoResumidoDTO alunoResDTO = new AlunoResumidoDTO();
            alunoResDTO.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
            alunoResDTO.setNome(aluno.getNome());
            alunoResDTO.setCpf(aluno.getCpf());

            //Add alunoResDTO dentro do array alunosDTO para que não resete e perca nenhum dado a cada interação.
            alunosDTO.add(alunoResDTO);
        }

        return alunosDTO;
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

	/*
	 * public void deletarAlunoPorId(Integer id) { if (id == null) return null;
	 * 
	 * Aluno aluno = buscarAlunoPorId(id); if (aluno == null) return null;
	 * 
	 * alunoRepo.deleteById(id); }
	 */

	public Boolean deletarAluno(Aluno aluno) { // pode usar puxando pelo ID, o que torna a busca e o deletar mais fácil
												// ao invés do nome

		if (aluno == null)
			return null;

		Aluno alunoExistente = buscarAlunoPorId(aluno.getNumeroMatriculaAluno());

		if (alunoExistente == null)
			return null;

		alunoRepo.delete(aluno);

		Aluno alunoContinuaExistindo = buscarAlunoPorId(aluno.getNumeroMatriculaAluno());
		if (alunoContinuaExistindo == null)
			return true;

		else
			return false;

		// É o esboço para poder colocar esse controle de verificação
		// e retorno se foi deletado ou não
		/*
		 * Aluno confereAlunoDeletado =
		 * buscarAlunoPorId(aluno.getNumeroMatriculaAluno());
		 * 
		 * if(null == confereAlunoDeletado) significa que o aluno foi deletado
		 * 
		 * else significa que O aluno continua existindo
		 */
	}

}
