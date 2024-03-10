package com.hemoclick.api.model;

import com.hemoclick.api.dto.doador.FichaMedicaDTO;
import com.hemoclick.api.dto.doador.UpdateFichaMedicaDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FichaMedica {
    private String tipoSanguineo;
    private Boolean jaDoou;
    private Date ultimaDoacao;

    public FichaMedica(FichaMedicaDTO dto) {
        this.tipoSanguineo = dto.tipoSanguineo();
        this.jaDoou = dto.jaDoou();
        this.ultimaDoacao = dto.ultimaDoacao();
    }

    public void update(UpdateFichaMedicaDTO dto) {
        if (dto.ultimaDoacao() != null) this.ultimaDoacao = dto.ultimaDoacao();
        if (dto.jaDoou() != null) this.jaDoou = dto.jaDoou();
    }
}
