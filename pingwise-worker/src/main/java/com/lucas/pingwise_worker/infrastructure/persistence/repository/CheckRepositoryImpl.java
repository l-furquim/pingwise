package com.lucas.pingwise_worker.infrastructure.persistence.repository;

import com.lucas.pingwise_worker.application.ports.out.CheckRepository;
import com.lucas.pingwise_worker.domain.model.Check;
import com.lucas.pingwise_worker.infrastructure.persistence.mapper.CheckPersistenceMapper;
import com.lucas.pingwise_worker.infrastructure.persistence.repository.jpa.CheckJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CheckRepositoryImpl implements CheckRepository {

    private final CheckJpaRepository checkJpaRepository;
    private final CheckPersistenceMapper checkPersistenceMapper;

    @Override
    public void save(Check check) {
        final var entity = this.checkPersistenceMapper.toEntity(check);

        this.checkJpaRepository.save(entity);
    }
}
