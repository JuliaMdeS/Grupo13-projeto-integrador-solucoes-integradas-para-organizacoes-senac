package com.hemoclick.api.model;

import com.hemoclick.api.dto.doador.UpdateFichaMedicaDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class FichaMedica {
    private Double peso;
    private String tipoSanguineo;
    private Boolean possuiTatuagens;
    private Date ultimaDoacao;
    private String doencasPreexistentes;

    public void update(UpdateFichaMedicaDTO dto) {
        if (dto.peso() != null) this.peso = dto.peso();
        if (dto.possuiTatuagens() != null) this.possuiTatuagens = dto.possuiTatuagens();
        if (dto.ultimaDoacao() != null) this.ultimaDoacao = dto.ultimaDoacao();
        if (dto.doencasPreexistentes() != null) this.doencasPreexistentes = dto.doencasPreexistentes();
    }
}
