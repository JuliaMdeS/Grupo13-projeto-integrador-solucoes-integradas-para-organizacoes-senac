package com.hemoclick.api.dto.agendamento;

import com.hemoclick.api.model.Agendamento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoDTO(
        @NotNull
        Long idHemocentro,

        @NotNull
        Long idDoador,

        @Future
        @NotNull
                LocalDateTime data) {

        public AgendamentoDTO(Agendamento agendamento) {
                this(agendamento.getHemocentro().getId(), agendamento.getDoador().getId(), agendamento.getData());
        }
}
