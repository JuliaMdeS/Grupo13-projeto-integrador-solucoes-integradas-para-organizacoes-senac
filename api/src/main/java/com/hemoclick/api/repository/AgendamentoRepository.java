package com.hemoclick.api.repository;

import com.hemoclick.api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    Boolean existsByHemocentroIdAndData(Long id, LocalDateTime date);

    Boolean existsByDoadorIdAndDataBetween(Long id, LocalDateTime firstTime, LocalDateTime lastTime);
}
