package com.example.dncompany.controller.adminController;

import com.example.dncompany.dto.admin.board.AdminEventWriteDTO;
import com.example.dncompany.mapper.admin.AdminBoardMapper;
import com.example.dncompany.service.admin.AdminBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/event")
public class AdminEventWriteContoller {

    private final AdminBoardService adminBoardService;

    @GetMapping("/write")
    public String adminEventWrite() {




        return "admin/admin_write/admin_write";
    }


    @PostMapping("/write")
    public String adminEventWrite(AdminEventWriteDTO adminEventWriteDTO,
                                  @SessionAttribute("userId") long userId,
                                  @RequestParam(value="image" , required = false) MultipartFile imgFile) {
        log.debug("====== Event Write Controller Start ======");
        log.debug("AdminEventWriteDTO: {}", adminEventWriteDTO);
        log.debug("userId: {}", userId);
        log.debug("Image File Name: {}", imgFile != null ? imgFile.getOriginalFilename() : "No File");
        log.debug("Image File Empty: {}", imgFile != null ? imgFile.isEmpty() : "No File");
        log.debug("Content Length: {}", adminEventWriteDTO.getEventContent() != null ? adminEventWriteDTO.getEventContent().length() : 0);

        try {
            adminBoardService.addAdminEventBoardWithFIle(adminEventWriteDTO, userId, imgFile);
            log.debug("Event Write Success");
        } catch (IOException e) {
            log.error("Event Write Failed: {}", e.getMessage());
            e.printStackTrace();
        }
        log.debug("====== Event Write Controller End ======");

        return "redirect:/admin/main";
    }
}
