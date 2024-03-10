package com.hemoclick.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "agendamentos")
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doadores_id")
    private Doador doador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hemocentros_id")
    private Hemocentro hemocentro;

    private LocalDateTime data;
}
