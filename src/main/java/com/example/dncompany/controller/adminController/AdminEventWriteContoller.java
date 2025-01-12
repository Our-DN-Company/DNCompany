package com.example.dncompany.controller.adminController;

import com.example.dncompany.dto.admin.board.AdminEventWriteDTO;
import com.example.dncompany.mapper.admin.AdminBoardMapper;
import com.example.dncompany.service.admin.AdminBoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * 이벤트 게시글 등록을 처리하는 컨트롤러
 * 게시글 저장 버튼을 클릭할 때 호출됨
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/event")
public class AdminEventWriteContoller {

    private final AdminBoardService adminBoardService;

    @GetMapping("/write")
    public String adminEventWrite(HttpSession session) {
        if(session.getAttribute("loginId") == null || !"ROLE_ADMIN".equals(session.getAttribute("role"))) {
            return "redirect:/admin/login";
        }




        return "admin/admin_write/admin_write";
    }

    /**
     * 이벤트 게시글 등록 처리
     * 1. 게시글 기본 정보 저장 (EVENT_BOARD 테이블)
     * 2. 본문에 포함된 이미지 URL들을 찾아서 DB에 저장 (EVENT_IMG 테이블)
     * 3. 추가 첨부된 이미지가 있다면 처리
     *    - 파일 저장
     *    - 썸네일 생성
     *    - DB 저장
     *
     * 주의:
     * - 본문 이미지와 첨부 이미지 모두 같은 테이블에 저장됨
     * - 썸네일 우선순위가 명확하지 않을 수 있음
     * - 썸머노트로 충분히 썸네일과 이미지 업로드가 가능해서
     * - 본문에 이미지 업 로드 기능을 아직 구현하지 않음
     * - 구현시 이미지파일명과 데이터는 uuid로 인해 중복이 발생하지 않아
     * - 충돌 위험은 없을 거라고 판단하지만
     * - 썸네일 등록 과정에서 어떤게 우선순위로 처리될지 아직 미지수임
     * - 본문 첨부파일로 이미지 업로드 기능을 구현한다면
     * - 조건을 줘서 우선처리를 해야할 것 같음
     *
     *
     * @param adminEventWriteDTO 게시글 정보
     * @param multipartFile 추가 첨부 이미지
     * @return 처리 결과 페이지
     */
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
