package com.lucas.pingwise.infrastructure.persistence.repository;

import com.lucas.pingwise.application.ports.out.CheckRepository;
import com.lucas.pingwise.domain.model.Check;
import com.lucas.pingwise.infrastructure.persistence.mapper.CheckPersistenceMapper;
import com.lucas.pingwise.infrastructure.persistence.repository.jpa.CheckJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class CheckRepositoryImpl implements CheckRepository {

    private final CheckJpaRepository checkJpaRepository;
    private final CheckPersistenceMapper checkPersistenceMapper;

    @Override
    public List<Check> findByMonitorId(UUID monitorId) {
        return this.checkJpaRepository.findByMonitorId(monitorId)
                .stream()
                .map(checkPersistenceMapper::toDomain)
                .toList();
    }
}
