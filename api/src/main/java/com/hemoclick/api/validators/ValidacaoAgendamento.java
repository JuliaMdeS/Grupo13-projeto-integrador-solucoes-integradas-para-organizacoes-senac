package com.hemoclick.api.validators;

import com.hemoclick.api.dto.agendamento.AgendamentoDTO;

public interface ValidacaoAgendamento {
    void validate(AgendamentoDTO agendamentoDTO) throws ValidacaoException;
}
