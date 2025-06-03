package br.com.ucsal.solicitacaoinstalacaoservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "solicitacoes_instalacao_software")
public class SolicitacaoInstalacaoSoftware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitacao;

    @Column(nullable = false)
    private Long idProfessor;

    @Column(nullable = false)
    private Long idLaboratorio;

    @ElementCollection
    @CollectionTable(name = "solicitacao_software_ids", joinColumns = @JoinColumn(name = "solicitacao_id"))
    @Column(name = "software_id")
    private List<Long> idsSoftwares;

    @Column(nullable = false)
    private String status; // PENDENTE, APROVADO, REJEITADO

    @Column(nullable = false)
    private LocalDateTime dataInicio;

    @Column(nullable = true)
    private LocalDateTime dataConclusao;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataSolicitacao;

    @PrePersist
    protected void onCreate() {
        this.dataSolicitacao = LocalDateTime.now();
    }
}