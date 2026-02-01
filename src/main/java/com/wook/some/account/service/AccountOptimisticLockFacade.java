package com.wook.some.account.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountOptimisticLockFacade {
    private final AccountService accountService;

    public void deposit(Long id, Long amount) throws InterruptedException {
        while (true) {
            try {
                accountService.deposit(id, amount);
                break; // 성공하면 반복문 탈출
            } catch (Exception e) {
                // 버전이 맞지 않아 실패하면 0.05초 쉬고 다시 시도
                Thread.sleep(50);
            }
        }
    }
}
