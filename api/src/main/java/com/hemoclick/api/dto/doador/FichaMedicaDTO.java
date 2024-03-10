package com.hemoclick.api.dto.doador;

import java.util.Date;

public record FichaMedicaDTO(String tipoSanguineo, Boolean jaDoou, Date ultimaDoacao) {
}
