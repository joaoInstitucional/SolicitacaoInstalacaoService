package br.com.ucsal.solicitacaoinstalacaoservice.controllers;

import br.com.ucsal.solicitacaoinstalacaoservice.dtos.ApiResponse;
import br.com.ucsal.solicitacaoinstalacaoservice.dtos.AtualizaStatusDTO;
import br.com.ucsal.solicitacaoinstalacaoservice.dtos.SolicitacaoInstalacaoDTO;
import br.com.ucsal.solicitacaoinstalacaoservice.entities.SolicitacaoInstalacaoSoftware;
import br.com.ucsal.solicitacaoinstalacaoservice.services.SolicitacaoInstalacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instalacoes")
public class SolicitacaoInstalacaoController {

    @Autowired
    private SolicitacaoInstalacaoService service;

    @PostMapping
    public ResponseEntity<ApiResponse<SolicitacaoInstalacaoSoftware>> criar(@RequestBody SolicitacaoInstalacaoDTO dto) {
        SolicitacaoInstalacaoSoftware solicitacao = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Solicitação criada com sucesso!", solicitacao));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SolicitacaoInstalacaoSoftware>>> buscarTodas() {
        List<SolicitacaoInstalacaoSoftware> solicitacoes = service.buscarTodas();
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Solicitações encontradas", solicitacoes)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SolicitacaoInstalacaoSoftware>> buscarPorId(@PathVariable Long id) {
        Optional<SolicitacaoInstalacaoSoftware> solicitacao = service.buscarPorId(id);
        return solicitacao
                .map(s -> ResponseEntity.ok(
                        new ApiResponse<>(200, "Solicitação encontrada", s)
                ))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(404, "Solicitação não encontrada", null)));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<SolicitacaoInstalacaoSoftware>> atualizarStatus(
            @PathVariable Long id,
            @RequestBody AtualizaStatusDTO statusDTO) {

        SolicitacaoInstalacaoSoftware solicitacao = service.atualizarStatus(id, statusDTO.status());
        return ResponseEntity.ok(
                new ApiResponse<>(200, "Status atualizado com sucesso", solicitacao)
        );
    }
}
