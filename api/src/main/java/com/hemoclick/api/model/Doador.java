package com.hemoclick.api.model;

import com.hemoclick.api.dto.doador.RequestDoadorDTO;
import com.hemoclick.api.dto.doador.UpdateDoadorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "doadores")
public class Doador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String sexo;
    private String cep;
    private String endereco;
    private String telefone;
    @Embedded
    private FichaMedica fichaMedica;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarios_id")
    private Usuario usuario;

    public void update(UpdateDoadorDTO dto) {
        if (dto.cep() != null) this.cep = dto.cep();
        if (dto.endereco() != null) this.endereco = dto.endereco();
        if (dto.telefone() != null) this.telefone = dto.telefone();
        if (dto.fichaMedica() != null) this.fichaMedica.update(dto.fichaMedica());
    }
}
