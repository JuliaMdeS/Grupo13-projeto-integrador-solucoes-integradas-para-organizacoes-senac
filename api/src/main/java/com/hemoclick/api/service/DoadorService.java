package com.hemoclick.api.service;

import com.hemoclick.api.dto.doador.RequestDoadorDTO;
import com.hemoclick.api.dto.doador.ResponseDoadorDTO;
import com.hemoclick.api.dto.doador.UpdateDoadorDTO;
import com.hemoclick.api.exceptions.*;
import com.hemoclick.api.model.Doador;
import com.hemoclick.api.model.FichaMedica;
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
    private final UserAuthService userAuthService;

    @Transactional
    public Doador criarDoador(RequestDoadorDTO dto) {
        var optionalUsuario = userAuthService.buscarUsuario(dto.idUsuario());

        if (optionalUsuario.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado.");
        }

        if (doadorRepository.existsByUsuario(optionalUsuario.get())) {
            throw new UsuarioJaAssociadoException("Já existe um doador associado a este usuário.");
        }

        var fichaMedica = FichaMedica.builder()
                .peso(dto.fichaMedica().peso())
                .tipoSanguineo(dto.fichaMedica().tipoSanguineo())
                .ultimaDoacao(dto.fichaMedica().ultimaDoacao())
                .possuiTatuagens(dto.fichaMedica().possuiTatuagens())
                .doencasPreexistentes(dto.fichaMedica().doencasPreexistentes())
                .build();

        var doador = Doador.builder()
                .nome(dto.nome())
                .cpf(dto.cpf())
                .dataNascimento(dto.dataNascimento())
                .sexo(dto.sexo())
                .cep(dto.cep())
                .endereco(dto.endereco())
                .telefone(dto.telefone())
                .fichaMedica(fichaMedica)
                .usuario(optionalUsuario.get())
                .build();

        return doadorRepository.save(doador);
    }

    public Doador buscarDoador(Long id) {
        var doadorOptional = doadorRepository.findById(id);

        if (doadorOptional.isEmpty()) {
            throw new DoadorNaoEncontradoException("Doador com o ID fornecido não encontrado.");
        }

        return doadorOptional.get();
    }

    public Page<ResponseDoadorDTO> buscarTodosDoadores(Pageable pageable) {

        if (pageable.getPageNumber() < 0) {
            throw new NumeroPaginasException("O número da página deve ser maior ou igual a zero.");
        }
        if (pageable.getPageSize() <= 0) {
            throw new TamanhoPaginasException("O tamanho da página deve ser maior que zero.");
        }

        return doadorRepository.findAll(pageable).map(ResponseDoadorDTO::new);
    }

    @Transactional
    public Doador atualizarDoador(Long id, UpdateDoadorDTO dto) {
        var doadorOptional = doadorRepository.findById(id);

        if (doadorOptional.isEmpty()) {
            throw new DoadorNaoEncontradoException("Doador com o ID fornecido não encontrado.");
        }

        doadorOptional.get().update(dto);
        return doadorOptional.get();
    }

    @Transactional
    public void deletarDoador(Long id) {

        if (!doadorRepository.existsById(id)) {
            throw new DoadorNaoEncontradoException("Doador com o ID fornecido não encontrado.");
        }

        doadorRepository.deleteById(id);
    }

    public boolean checkIfExists(Long id) {
        return doadorRepository.existsById(id);
    }
}
