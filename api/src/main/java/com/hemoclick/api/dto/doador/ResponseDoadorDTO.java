package com.hemoclick.api.dto.doador;

import com.hemoclick.api.model.Doador;
import com.hemoclick.api.model.FichaMedica;

import java.util.Date;

public record ResponseDoadorDTO(Long id, String nome, String cpf, Date dataNascimento, String sexo, Double peso,
                                FichaMedica fichaMedica) {
    public ResponseDoadorDTO(Doador doador) {
        this(doador.getId(), doador.getNome(), doador.getCpf(), doador.getDataNascimento(), doador.getSexo(), doador.getPeso(), doador.getFichaMedica());
    }
}
