package com.example.dncompany.service.admin;

import com.example.dncompany.dto.admin.user.board.AdminUserAllBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminUserServiceTest {

    @Autowired
    private AdminUserService adminUserService;

    @Test
    void getAllUserData() {
        AdminUserAllBoard searchCriteria = new AdminUserAllBoard();
        List<AdminUserAllBoard> adminUserAllBoards = adminUserService.getAllUserData(searchCriteria);
    }
}