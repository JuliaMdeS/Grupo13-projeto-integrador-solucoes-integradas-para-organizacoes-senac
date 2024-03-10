package com.hemoclick.api.dto.hemocentro;

import com.hemoclick.api.model.Hemocentro;

public record ResponseHemocentroDTO(Long id, String nome, String endereco, String cep, String telefone) {
    public ResponseHemocentroDTO(Hemocentro hemocentro) {
        this(hemocentro.getId(), hemocentro.getNome(), hemocentro.getEndereco(), hemocentro.getCep(), hemocentro.getTelefone());
    }
}
