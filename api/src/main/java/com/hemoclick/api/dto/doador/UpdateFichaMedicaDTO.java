package com.hemoclick.api.dto.doador;

import java.util.Date;

public record UpdateFichaMedicaDTO(Boolean possuiTatuagens, Date ultimaDoacao, Double peso, String doencasPreexistentes) {
}
