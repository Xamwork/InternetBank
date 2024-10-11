package org.example.repository;

import org.example.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findByUserId(Long userId);

    List<Operation> findByUserIdAndDateTimeBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    List<Operation> findByUserIdAndDateTimeAfter(Long userId, LocalDateTime startDate);

    List<Operation> findByUserIdAndDateTimeBefore(Long userId, LocalDateTime endDate);
}