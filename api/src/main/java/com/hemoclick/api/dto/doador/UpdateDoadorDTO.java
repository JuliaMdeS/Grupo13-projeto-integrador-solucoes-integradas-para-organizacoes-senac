package com.hemoclick.api.dto.doador;

public record UpdateDoadorDTO(String endereco, String cep, String telefone, UpdateFichaMedicaDTO fichaMedica) {
}
