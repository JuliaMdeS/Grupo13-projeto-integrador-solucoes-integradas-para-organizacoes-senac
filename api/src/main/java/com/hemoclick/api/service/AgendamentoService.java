package com.hemoclick.api.service;

import com.hemoclick.api.dto.agendamento.AgendamentoDTO;
import com.hemoclick.api.model.Agendamento;
import com.hemoclick.api.repository.AgendamentoRepository;
import com.hemoclick.api.validators.ValidacaoAgendamento;
import com.hemoclick.api.validators.ValidacaoException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final HemocentroService hemocentroService;
    private final DoadorService doadorService;
    private final List<ValidacaoAgendamento> validacaoAgendamento;

    public Agendamento agendar(AgendamentoDTO dto) {
        if (dto.idHemocentro() != null && !hemocentroService.checkIfExists(dto.idHemocentro())) {
            throw new ValidacaoException("Id of the hemocentro provided does not exist!");
        }

        if (!doadorService.checkIfExists(dto.idDoador())) {
            throw new ValidacaoException("Id of the doador provided does not exist!");
        }

        validacaoAgendamento.forEach(v -> v.validate(dto));

        var hemocentro = hemocentroService.buscarHemocentro(dto.idHemocentro());
        var doador = doadorService.buscarDoador(dto.idDoador());
        var agendamento = new Agendamento(null, doador, hemocentro, dto.data());

        return agendamentoRepository.save(agendamento);
    }

    public Page<AgendamentoDTO> buscarTodosAgendamentos(Pageable pageable) {
        return agendamentoRepository.findAll(pageable).map(AgendamentoDTO::new);
    }
}
