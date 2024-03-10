package com.hemoclick.api.controller;

import com.hemoclick.api.dto.doador.RequestDoadorDTO;
import com.hemoclick.api.dto.doador.ResponseDoadorDTO;
import com.hemoclick.api.dto.doador.UpdateDoadorDTO;
import com.hemoclick.api.service.DoadorService;
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
@RequestMapping("/api/doador")
@RequiredArgsConstructor
public class DoadorController {

    private final DoadorService doadorService;

    @PostMapping
    protected ResponseEntity<ResponseDoadorDTO> create(@RequestBody @Valid RequestDoadorDTO dto, UriComponentsBuilder uriBuilder) {
        var doador = doadorService.criarDoador(dto);
        var uri = uriBuilder.path("/api/doador/{id}").buildAndExpand(doador.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseDoadorDTO(doador));
    }

    @GetMapping("/{id}")
    protected ResponseEntity<ResponseDoadorDTO> get(@PathVariable("id") Long id) {
        var doador = doadorService.buscarDoador(id);
        return ResponseEntity.ok(new ResponseDoadorDTO(doador));
    }

    @GetMapping
    protected ResponseEntity<Page<ResponseDoadorDTO>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = doadorService.buscarTodosDoadores(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    protected ResponseEntity<ResponseDoadorDTO> update(@RequestBody @Valid UpdateDoadorDTO dto, @PathVariable("id") Long id) {
        var doador = doadorService.atualizarDoador(id, dto);
        return ResponseEntity.ok(new ResponseDoadorDTO(doador));
    }

    @DeleteMapping("/{id}")
    @Transactional
    protected ResponseEntity<?> delete(@PathVariable("id") Long id) {
        doadorService.deletarDoador(id);
        return ResponseEntity.noContent().build();
    }
}
