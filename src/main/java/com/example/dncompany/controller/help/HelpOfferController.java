package com.example.dncompany.controller.help;

import com.example.dncompany.mapper.help.HelpOfferMapper;
import com.example.dncompany.service.help.HelpOfferService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/help/offer")
@RequiredArgsConstructor
public class HelpOfferController {
    private final HelpOfferService helpOfferService;

    @PostMapping("/{helpId}")
    @ResponseBody
    public ResponseEntity<String> helpOffer(
            @PathVariable Long helpId,
            @SessionAttribute(value = "usersId", required = false) Long usersId,
            HttpSession session) {


        log.info("========== 도움 신청 컨트롤러 시작 ==========");
        log.info("helpId: {}, usersId: {}", helpId, usersId);

//        usersId = 6L;

//        try {
//            helpOfferService.registerHelpOffer(helpId, usersId);
//            log.info("========== 도움 신청 컨트롤러 종료 - 성공 ==========");
//            return ResponseEntity.ok("신청이 완료되었습니다.");
//        } catch (Exception e) {
//            log.error("도움 신청 실패", e);
//            log.info("========== 도움 신청 컨트롤러 종료 - 실패 ==========");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("신청 처리 중 오류가 발생했습니다.");
//        }

        try {
            if (helpOfferService.helpOfferCheckTest(helpId, usersId)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("본인의 게시글에는 신청할 수 없습니다.");
            }

            helpOfferService.registerHelpOffer(helpId, usersId);
            log.info("========== 도움 신청 컨트롤러 종료 - 성공 ==========");
            return ResponseEntity.ok("신청이 완료되었습니다.");
        } catch (IllegalStateException e) {
            log.error("도움 신청 실패 - 본인 게시글", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            log.error("도움 신청 실패", e);
            log.info("========== 도움 신청 컨트롤러 종료 - 실패 ==========");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("신청 처리 중 오류가 발생했습니다.");
        }
   }
}




