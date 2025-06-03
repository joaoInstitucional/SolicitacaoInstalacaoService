package br.com.ucsal.solicitacaoinstalacaoservice.dtos;

public record ApiResponse<T>(
        int status,
        String message,
        T data
) {
}