package br.com.unikedin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.unikedin.model.DadosPessoa;

@Repository
@Transactional
public interface DadosPessoaRepository extends JpaRepository<DadosPessoa, Long> {

    List<DadosPessoa> findByCampus(String campus);

    @Query("SELECT p FROM DadosPessoa p WHERE LOWER(p.nome) LIKE %:nome%")
    List<DadosPessoa> buscarPorNomeIgnoreCase(String nome);

    @Query("SELECT p FROM DadosPessoa p WHERE LOWER(p.nome) LIKE %:nome% AND LOWER(p.curso) LIKE %:curso%")
    List<DadosPessoa> buscarPorNomeECursoIgnoreCase(String nome, String curso);

    @Query("SELECT p FROM DadosPessoa p WHERE LOWER(p.curso) LIKE %:curso%")
    List<DadosPessoa> buscarPorCursoIgnoreCase(String curso);

}
