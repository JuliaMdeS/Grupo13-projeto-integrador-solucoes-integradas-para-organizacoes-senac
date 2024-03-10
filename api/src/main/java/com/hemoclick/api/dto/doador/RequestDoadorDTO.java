package com.hemoclick.api.dto.doador;

import java.util.Date;

public record RequestDoadorDTO(String nome, String cpf, Date dataNascimento, String sexo, String cep, Double peso,
                               FichaMedicaDTO fichaMedica) {
}
