package com.hemoclick.api.model;

import com.hemoclick.api.dto.doador.RequestDoadorDTO;
import com.hemoclick.api.dto.doador.UpdateDoadorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Double peso;
    @Embedded
    private FichaMedica fichaMedica;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarios_id")
    private Usuario usuario;

    public Doador(RequestDoadorDTO dto) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.dataNascimento = dto.dataNascimento();
        this.sexo = dto.sexo();
        this.endereco = dto.endereco();
        this.cep = dto.cep();
        this.telefone = dto.telefone();
        this.peso = dto.peso();
        this.fichaMedica = new FichaMedica(dto.fichaMedica());
    }

    public void update(UpdateDoadorDTO dto) {
        if (dto.cep() != null) this.cep = dto.cep();
        if (dto.peso() != null) this.peso = dto.peso();
        if (dto.fichaMedica() != null) this.fichaMedica.update(dto.fichaMedica());
    }
}
