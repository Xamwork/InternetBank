package org.example.service;

import org.example.model.UserBalance;
import org.example.repository.UserBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBalanceService {

    @Autowired
    private UserBalanceRepository userBalanceRepository;

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
    }

    // Снятие средств
    public boolean takeMoney(Long userId, double amount) {
        Optional<UserBalance> userOptional = userBalanceRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserBalance user = userOptional.get();
            if (user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                userBalanceRepository.save(user);
                return true;
            }
        }
        return false;
    }
}