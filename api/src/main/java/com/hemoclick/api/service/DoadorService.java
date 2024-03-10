package com.hemoclick.api.service;

import com.hemoclick.api.dto.doador.RequestDoadorDTO;
import com.hemoclick.api.dto.doador.ResponseDoadorDTO;
import com.hemoclick.api.dto.doador.UpdateDoadorDTO;
import com.hemoclick.api.model.Doador;
import com.hemoclick.api.repository.DoadorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoadorService {

    private final DoadorRepository doadorRepository;

    @Transactional
    public Doador criarDoador(RequestDoadorDTO dto) {
        var doador = new Doador(dto);
        return doadorRepository.save(doador);
    }

    public Doador buscarDoador(Long id) {
        return doadorRepository.getReferenceById(id);
    }

    public Page<ResponseDoadorDTO> buscarTodosDoadores(Pageable pageable) {
        return doadorRepository.findAll(pageable).map(ResponseDoadorDTO::new);
    }

    @Transactional
    public Doador atualizarDoador(Long id, UpdateDoadorDTO dto) {
        var doador = doadorRepository.getReferenceById(id);
        doador.update(dto);
        return doador;
    }

    @Transactional
    public void deletarDoador(Long id) {
        doadorRepository.deleteById(id);
    }

    public boolean checkIfExists(Long id) {
        return doadorRepository.existsById(id);
    }
}
