package br.com.ucsal.solicitacaoinstalacaoservice.services;

import br.com.ucsal.solicitacaoinstalacaoservice.dtos.SolicitacaoInstalacaoDTO;
import br.com.ucsal.solicitacaoinstalacaoservice.entities.SolicitacaoInstalacaoSoftware;
import br.com.ucsal.solicitacaoinstalacaoservice.repositories.SolicitacaoInstalacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoInstalacaoService {

    @Autowired
    private SolicitacaoInstalacaoRepository repository;

    public SolicitacaoInstalacaoSoftware criar(SolicitacaoInstalacaoDTO dto) {
        SolicitacaoInstalacaoSoftware solicitacao = new SolicitacaoInstalacaoSoftware();
        solicitacao.setIdProfessor(dto.idProfessor());
        solicitacao.setIdLaboratorio(dto.idLaboratorio());
        solicitacao.setIdsSoftwares(dto.idsSoftwares());
        solicitacao.setDataInicio(dto.dataInicio());
        solicitacao.setStatus("PENDENTE");

        return repository.save(solicitacao);
    }

    public Optional<SolicitacaoInstalacaoSoftware> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<SolicitacaoInstalacaoSoftware> buscarTodas() {
        return repository.findAll();
    }

    public SolicitacaoInstalacaoSoftware atualizarStatus(Long id, String status) {
        SolicitacaoInstalacaoSoftware solicitacao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        solicitacao.setStatus(status);
        return repository.save(solicitacao);
    }
}