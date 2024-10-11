package org.example.controller;

import org.example.dto.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.service.UserBalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance")
public class UserBalanceController {

    @Autowired
    private UserBalanceService userBalanceService;

    // Метод для перевода денег от одного пользователя другому
    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody TransferRequest transferRequest) {
        boolean success = userBalanceService.transferMoney(transferRequest.getSenderId(),
                transferRequest.getReceiverId(), transferRequest.getAmount());

        if (success) {
            return ResponseEntity.ok("Перевод успешно выполнен");
        } else {
            return ResponseEntity.badRequest().body("Недостаточно средств или ошибка в данных");
        }
    }

    // Получение баланса пользователя
    @GetMapping("/{userId}")
    public ResponseEntity<Double> getBalance(@PathVariable Long userId) {
        double balance = userBalanceService.getBalance(userId);
        return ResponseEntity.ok(balance);
    }

    // Пополнение баланса
    @PostMapping("/{userId}/put")
    public ResponseEntity<String> putMoney(@PathVariable Long userId, @RequestParam double amount) {
        userBalanceService.putMoney(userId, amount);
        return ResponseEntity.ok("Balance updated successfully.");
    }

    // Снятие средств
    @PostMapping("/{userId}/take")
    public ResponseEntity<String> takeMoney(@PathVariable Long userId, @RequestParam double amount) {
        boolean success = userBalanceService.takeMoney(userId, amount);
        if (success) {
            return ResponseEntity.ok("Money withdrawn successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient funds or user not found.");
        }
    }
}
