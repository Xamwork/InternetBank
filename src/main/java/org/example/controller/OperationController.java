package org.example.controller;

import org.example.model.Operation;
import org.example.service.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/operations")
public class OperationController {

    @Autowired
    private UserBalanceService userBalanceService;

    // Получение списка операций за выбранный диапазон времени
    @GetMapping("/{userId}")
    public ResponseEntity<List<Operation>> getOperationList(
            @PathVariable Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Operation> operations = userBalanceService.getOperationList(userId, startDate, endDate);
        return ResponseEntity.ok(operations);
    }
}
