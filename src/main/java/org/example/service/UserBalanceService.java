package org.example.service;

import org.example.model.Transfer;
import org.example.model.UserBalance;
import org.example.model.Operation;
import org.example.repository.TransferRepository;
import org.example.repository.UserBalanceRepository;
import org.example.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserBalanceService {

    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private TransferRepository transferRepository;

    // Получение баланса
    public double getBalance(Long userId) {
        Optional<UserBalance> user = userBalanceRepository.findById(userId);
        return user.map(UserBalance::getBalance).orElse(0.0);
    }

    // Пополнение баланса
    public void putMoney(Long userId, double amount) {
        UserBalance user = userBalanceRepository.findById(userId).orElse(new UserBalance());
        user.setUserId(userId);
        user.setBalance(user.getBalance() + amount);
        userBalanceRepository.save(user);

        // Добавление записи операции
        Operation operation = new Operation();
        operation.setUserId(userId);
        operation.setType(1); // Предположим, 1 - пополнение
        operation.setAmount(amount);
        operation.setDateTime(LocalDateTime.now());
        operationRepository.save(operation);
    }

    // Снятие средств
    public boolean takeMoney(Long userId, double amount) {
        Optional<UserBalance> userOptional = userBalanceRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserBalance user = userOptional.get();
            if (user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                userBalanceRepository.save(user);

                // Добавление записи операции
                Operation operation = new Operation();
                operation.setUserId(userId);
                operation.setType(2); // Предположим, 2 - снятие
                operation.setAmount(-amount);
                operation.setDateTime(LocalDateTime.now());
                operationRepository.save(operation);

                return true;
            }
        }
        return false;
    }

    // Получение списка операций за выбранный диапазон времени
    public List<Operation> getOperationList(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate != null && endDate != null) {
            return operationRepository.findByUserIdAndDateTimeBetween(userId, startDate, endDate);
        } else if (startDate != null) {
            return operationRepository.findByUserIdAndDateTimeAfter(userId, startDate);
        } else if (endDate != null) {
            return operationRepository.findByUserIdAndDateTimeBefore(userId, endDate);
        } else {
            return operationRepository.findByUserId(userId);
        }
    }

    // Перевод денег от одного пользователя другому
    public boolean transferMoney(Long senderId, Long receiverId, double amount) {
        Optional<UserBalance> senderOptional = userBalanceRepository.findById(senderId);
        Optional<UserBalance> receiverOptional = userBalanceRepository.findById(receiverId);

        if (senderOptional.isPresent() && receiverOptional.isPresent()) {
            UserBalance sender = senderOptional.get();
            UserBalance receiver = receiverOptional.get();

            // Проверка, что на счету отправителя достаточно денег
            if (sender.getBalance() >= amount) {
                // Снимаем деньги со счета отправителя
                sender.setBalance(sender.getBalance() - amount);
                userBalanceRepository.save(sender);

                // Добавляем деньги на счет получателя
                receiver.setBalance(receiver.getBalance() + amount);
                userBalanceRepository.save(receiver);

                // Создаем запись о переводе в таблице операций
                Operation senderOperation = new Operation();
                senderOperation.setUserId(senderId);
                senderOperation.setType(3); // Предположим, 3 - перевод отправителя
                senderOperation.setAmount(-amount);
                senderOperation.setDateTime(LocalDateTime.now());
                operationRepository.save(senderOperation);

                Operation receiverOperation = new Operation();
                receiverOperation.setUserId(receiverId);
                receiverOperation.setType(4); // Предположим, 4 - перевод получателя
                receiverOperation.setAmount(amount);
                receiverOperation.setDateTime(LocalDateTime.now());
                operationRepository.save(receiverOperation);

                // Запись в таблицу переводов
                Transfer transfer = new Transfer();
                transfer.setSenderId(senderId);
                transfer.setReceiverId(receiverId);
                transfer.setAmount(amount);
                transfer.setTransferDate(LocalDateTime.now());
                transferRepository.save(transfer);

                return true;
            }
        }
        return false; // Недостаточно средств или один из пользователей не найден
    }
}