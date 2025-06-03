package br.com.ucsal.solicitacaoinstalacaoservice.repositories;

import br.com.ucsal.solicitacaoinstalacaoservice.entities.SolicitacaoInstalacaoSoftware;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoInstalacaoRepository extends JpaRepository<SolicitacaoInstalacaoSoftware, Long> {
}