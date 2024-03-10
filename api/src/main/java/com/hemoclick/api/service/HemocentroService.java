package com.hemoclick.api.service;

import com.hemoclick.api.dto.hemocentro.RequestHemocentroDTO;
import com.hemoclick.api.dto.hemocentro.ResponseHemocentroDTO;
import com.hemoclick.api.dto.hemocentro.UpdateHemocentroDTO;
import com.hemoclick.api.model.Hemocentro;
import com.hemoclick.api.repository.HemocentroRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HemocentroService {

    private final HemocentroRepository hemocentroRepository;

    @Transactional
    public Hemocentro criarHemocentro(RequestHemocentroDTO dto) {
        var hemocentro = new Hemocentro(dto);
        return hemocentroRepository.save(hemocentro);
    }

    public Hemocentro buscarHemocentro(Long id) {
        return hemocentroRepository.getReferenceById(id);
    }

    public Page<ResponseHemocentroDTO> buscarTodosHemocentros(Pageable pageable) {
        return hemocentroRepository.findAll(pageable).map(ResponseHemocentroDTO::new);
    }
    @Transactional
    public Hemocentro atualizarHemocentro(Long id, UpdateHemocentroDTO dto) {
        var hemocentro = hemocentroRepository.getReferenceById(id);
        hemocentro.update(dto);
        return hemocentro;
    }
    @Transactional
    public void deletarHemocentro(Long id) {
        hemocentroRepository.deleteById(id);
    }

    public boolean checkIfExists(Long id) {
        return hemocentroRepository.existsById(id);
    }
}

