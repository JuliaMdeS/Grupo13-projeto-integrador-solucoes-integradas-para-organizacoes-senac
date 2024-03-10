package com.hemoclick.api.controller;

import com.hemoclick.api.dto.doador.RequestDoadorDTO;
import com.hemoclick.api.dto.doador.ResponseDoadorDTO;
import com.hemoclick.api.dto.doador.UpdateDoadorDTO;
import com.hemoclick.api.dto.hemocentro.RequestHemocentroDTO;
import com.hemoclick.api.dto.hemocentro.ResponseHemocentroDTO;
import com.hemoclick.api.dto.hemocentro.UpdateHemocentroDTO;
import com.hemoclick.api.service.HemocentroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/api/hemocentro")
@RequiredArgsConstructor
public class HemocentroController {

    private final HemocentroService hemocentroService;

    @PostMapping
    protected ResponseEntity<ResponseHemocentroDTO> create(@RequestBody @Valid RequestHemocentroDTO dto, UriComponentsBuilder uriBuilder) {
        var hemocentro = hemocentroService.criarHemocentro(dto);
        var uri = uriBuilder.path("/api/hemocentro/{id}").buildAndExpand(hemocentro.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseHemocentroDTO(hemocentro));
    }

    @GetMapping("/{id}")
    protected ResponseEntity<ResponseHemocentroDTO> get(@PathVariable("id") Long id) {
        var hemocentro = hemocentroService.buscarHemocentro(id);
        return ResponseEntity.ok(new ResponseHemocentroDTO(hemocentro));
    }

    @GetMapping
    protected ResponseEntity<Page<ResponseHemocentroDTO>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = hemocentroService.buscarTodosHemocentros(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    protected ResponseEntity<ResponseHemocentroDTO> update(@RequestBody @Valid UpdateHemocentroDTO dto, @PathVariable("id") Long id) {
        var hemocentro = hemocentroService.atualizarHemocentro(id, dto);
        return ResponseEntity.ok(new ResponseHemocentroDTO(hemocentro));
    }

    @DeleteMapping("/{id}")
    @Transactional
    protected ResponseEntity<?> delete(@PathVariable("id") Long id) {
        hemocentroService.deletarHemocentro(id);
        return ResponseEntity.noContent().build();
    }
}
