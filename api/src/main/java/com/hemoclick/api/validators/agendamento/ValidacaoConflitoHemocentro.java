package com.hemoclick.api.validators.agendamento;

import com.hemoclick.api.dto.agendamento.AgendamentoDTO;
import com.hemoclick.api.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoConflitoHemocentro implements ValidacaoAgendamento {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Override
    public void validate(AgendamentoDTO dto) {
        var hemocentro = agendamentoRepository.existsByHemocentroIdAndData(dto.idHemocentro(), dto.data());
        if (hemocentro) throw new ValidacaoException("Scheduling conflict with the provided hemocentro!");
    }
}
