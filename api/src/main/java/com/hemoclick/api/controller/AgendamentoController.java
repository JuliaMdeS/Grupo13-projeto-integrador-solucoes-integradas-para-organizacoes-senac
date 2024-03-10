package com.hemoclick.api.controller;

import com.hemoclick.api.dto.agendamento.AgendamentoDTO;
import com.hemoclick.api.dto.doador.ResponseDoadorDTO;
import com.hemoclick.api.dto.hemocentro.ResponseHemocentroDTO;
import com.hemoclick.api.service.AgendamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    protected ResponseEntity<AgendamentoDTO> agendar(@RequestBody AgendamentoDTO dto, UriComponentsBuilder uriBuilder) {
        var agendamento = agendamentoService.agendar(dto);
        var uri = uriBuilder.path("api/agendamento/{id}").buildAndExpand(agendamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new AgendamentoDTO(agendamento));
    }

    @GetMapping
    protected ResponseEntity<Page<AgendamentoDTO>> list(@PageableDefault(size = 10, sort = {"data"}) Pageable pageable) {
        var page = agendamentoService.buscarTodosAgendamentos(pageable);
        return ResponseEntity.ok(page);
    }
}
