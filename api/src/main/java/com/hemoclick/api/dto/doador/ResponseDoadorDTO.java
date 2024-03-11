package com.hemoclick.api.dto.doador;

import com.hemoclick.api.model.Doador;
import com.hemoclick.api.model.FichaMedica;
import com.hemoclick.api.model.Usuario;

import java.util.Date;

public record ResponseDoadorDTO(Long idUsuario, Long id, String nome, String cpf, String telefone, Date dataNascimento, String sexo, Double peso,
                                FichaMedica fichaMedica) {
    public ResponseDoadorDTO(Doador doador) {
        this(doador.getUsuario().getId(), doador.getId(), doador.getNome(), doador.getCpf(), doador.getTelefone(), doador.getDataNascimento(), doador.getSexo(), doador.getPeso(), doador.getFichaMedica());
    }
}
