package br.com.unikedin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.unikedin.model.DadosPessoa;

@Repository
@Transactional
public interface DadosPessoaRepository extends JpaRepository<DadosPessoa, Long> {

    List<DadosPessoa> findByCampus(String campus);

}
