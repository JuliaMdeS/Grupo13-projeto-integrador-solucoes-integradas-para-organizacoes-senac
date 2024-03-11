package com.hemoclick.api.dto.doador;

import java.util.Date;

public record RequestDoadorDTO(Long idUsuario, String nome, String cpf, Date dataNascimento, String sexo, String endereco, String cep, String telefone,
                               FichaMedicaDTO fichaMedica) {
}
