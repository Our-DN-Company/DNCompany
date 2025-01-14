package com.example.dncompany.controller.adminController;

import com.example.dncompany.dto.admin.board.AdminAnswerDTO;
import com.example.dncompany.dto.admin.board.AdminBoardDeleteDTO;
import com.example.dncompany.dto.admin.board.BoardSearchDTO;
import com.example.dncompany.service.admin.AdminBoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Slf4j
@RequestMapping("/admin/board")
@Controller
@RequiredArgsConstructor
public class AdminBoardController {
    private final AdminBoardService adminBoardService;


    @GetMapping("/list")
    public String list() {
        return "admin/admin_board/admin_board";
    }

    // 카테고리별 게시판 데이터 조회
//    @PostMapping("/list/reportBoard")
//    public String adminBoardList(@RequestParam String category, Model model) {
//        // 카테고리에 따른 게시물 데이터 조회
//        List<?> boards = adminBoardService.getBoardByCategory(category);
//        model.addAttribute("boardType", boards);
//        return "admin/admin_board/admin_board :: #postListBody";
//    }

    // 게시판 데이터 검색 및 조회
    // 위에 코드 기반으로 개조 테스트 후 문제 없으면 위에 코드 삭제 예정
    @PostMapping("/list/reportBoard")
    public String adminBoardList(@ModelAttribute BoardSearchDTO searchDTO,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 Model model, HttpSession session) {
        if(session.getAttribute("loginId") == null || !"ROLE_ADMIN".equals(session.getAttribute("role"))) {
            return "redirect:/admin/login";
        }

        // 페이지 정보 설정
        searchDTO.setPage(page);
        searchDTO.setSize(size);
        searchDTO.setOffset((page - 1) * size);

        // 전체 데이터 수와 페이징된 데이터 가져오기
        int totalCount = adminBoardService.getTotalBoardCount(searchDTO);
        List<?> boards = adminBoardService.getBoardBySearchCondition(searchDTO);

        // 페이징 정보 계산
        int totalPages = (int) Math.ceil((double) totalCount / size);

        model.addAttribute("boardType", boards);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalCount", totalCount);

        return "admin/admin_board/admin_board :: #postListBody";
    }


    // 답변 추가
    // 처음에는 여러 게시글을 답변할려고 했지만
    // QnA 답변만 필요하다고 이야기나와서 현재 QnA만 받는중
    @ResponseBody
    @PostMapping("/reply")
    public ResponseEntity<?> addAnswer(@RequestBody Map<String, Object> boardList) {
        log.info("받은 데이터: {}", boardList); // 로그 추가
        try {
            // null 체크 추가
            if (boardList.get("qnaId") == null ||
                    boardList.get("qnaAnswerContent") == null ||
                    boardList.get("category") == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "필수 데이터가 누락되었습니다."));
            }

            AdminAnswerDTO adminAnswerDTO = new AdminAnswerDTO();
            String category = boardList.get("category").toString();

            if ("QNA".equals(category)) {
                // 형변환
                adminAnswerDTO.setQnaId(Long.parseLong(boardList.get("qnaId").toString()));
                adminAnswerDTO.setQnaAnswerContent(boardList.get("qnaAnswerContent").toString());
                adminAnswerDTO.setCategory(category);

                adminBoardService.addAnswer(adminAnswerDTO);
                return ResponseEntity.ok().body(Map.of("message", "답변이 성공적으로 등록되었습니다."));
            }

            return ResponseEntity.badRequest()
                    .body(Map.of("error", "QnA 게시글만 답변 등록이 가능합니다."));
        } catch (Exception e) {
            e.printStackTrace(); // 디버깅을 위한 로그
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "답변 등록 실패: " + e.getMessage()));
        }
    }



    @GetMapping("/replies")
    public ResponseEntity<List<AdminAnswerDTO>> getAnswers(
            @RequestParam Long postId,
            @RequestParam String category
    ) {
        List<AdminAnswerDTO> replies = adminBoardService.getAnswersByPostId(postId, category);
        return ResponseEntity.ok(replies);
    }

    @GetMapping("/qna/detail")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getQnaDetail(@RequestParam Long qnaId) {
        try {
            Map<String, Object> detail = adminBoardService.selectQnaDetail(qnaId);
            if (detail == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(detail);
        } catch (Exception e) {
            log.error("QnA 상세 조회 실패: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Integer>> deleteBoards(@RequestBody AdminBoardDeleteDTO adminBoardDeleteDTO) {
        Map<String, Integer> results = adminBoardService.deleteBoards(adminBoardDeleteDTO);
        return ResponseEntity.ok(results);
    }


}