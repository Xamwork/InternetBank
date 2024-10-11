package org.example.repository;

import org.example.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    // Поиск всех переводов, где указан пользователь является отправителем
    List<Transfer> findBySenderId(Long senderId);

    // Поиск всех переводов, где указан пользователь является получателем
    List<Transfer> findByReceiverId(Long receiverId);

    // Поиск всех переводов, связанных с пользователем как отправителем или получателем
    List<Transfer> findBySenderIdOrReceiverId(Long senderId, Long receiverId);

}