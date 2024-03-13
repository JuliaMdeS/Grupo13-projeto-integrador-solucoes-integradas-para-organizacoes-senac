package com.hemoclick.api.validators.agendamento;

import com.hemoclick.api.dto.agendamento.AgendamentoDTO;
import com.hemoclick.api.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidacaoConflitoDoador implements ValidacaoAgendamento {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Override
    public void validate(AgendamentoDTO dto) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime twoWeeksAgo = currentDate.minusWeeks(2);
        var doouUltimasDuasSemanas = agendamentoRepository.existsByDoadorIdAndDataBetween(dto.idDoador(),twoWeeksAgo, currentDate);

        if (doouUltimasDuasSemanas) {
            throw new ValidacaoException("Scheduling conflict with the provided doador!");
        }
    }
}
