package com.projetopix.ProjetoPix.repository;

import com.projetopix.ProjetoPix.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
    List<Transacao> findByIdContaDestino(Long idContaDestino);
}
