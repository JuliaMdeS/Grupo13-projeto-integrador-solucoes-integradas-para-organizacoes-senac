package com.hemoclick.api.model;

import com.hemoclick.api.dto.hemocentro.RequestHemocentroDTO;
import com.hemoclick.api.dto.hemocentro.UpdateHemocentroDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hemocentros")
public class Hemocentro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    private String cep;
    private String telefone;

    public Hemocentro(RequestHemocentroDTO dto) {
        this.nome = dto.nome();
        this.endereco = dto.endereco();
        this.cep = dto.cep();
        this.telefone = dto.telefone();
    }

    public void update(UpdateHemocentroDTO dto) {
        if (dto.telefone() != null) this.telefone = dto.telefone();
        if (dto.endereco() != null) this.endereco = dto.endereco();
        if (dto.cep() != null) this.cep = dto.cep();
    }
}
