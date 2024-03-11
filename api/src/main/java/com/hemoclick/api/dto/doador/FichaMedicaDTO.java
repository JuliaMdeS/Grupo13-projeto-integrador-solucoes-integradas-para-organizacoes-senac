package com.hemoclick.api.dto.doador;

import java.util.Date;

public record FichaMedicaDTO(Double peso, String tipoSanguineo, Boolean possuiTatuagens, Date ultimaDoacao, String doencasPreexistentes) {
}
