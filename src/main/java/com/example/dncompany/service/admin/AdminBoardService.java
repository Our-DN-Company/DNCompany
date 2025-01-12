package com.example.dncompany.service.admin;

import com.example.dncompany.dto.admin.board.AdminAnswerDTO;
import com.example.dncompany.dto.admin.board.AdminEventWriteDTO;
import com.example.dncompany.dto.admin.board.BoardSearchDTO;
import com.example.dncompany.dto.admin.board.file.AdminFIleDTO;
import com.example.dncompany.mapper.admin.AdminBoardMapper;
import com.example.dncompany.mapper.admin.AdminFIleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AdminBoardService {
    private final AdminBoardMapper adminBoardMapper;
    private final AdminFIleMapper adminFIleMapper;


    // 경로 설정
    // 아래 입력에 사용
   @Value("C:/upload/event")
    private String uploadPath;

//    public List<AdminReportBoardDTO> getReportBoardSearch() {
//        return adminBoardMapper.selectReportBoard() ;
//    }
//    public List<AdminQnABoardDTO> getQnABoard() {
//        return adminBoardMapper.selectQnABoard(); }



    // @TODO 카테고리별 게시물 조회 (쿼리 완성시 case 하나씩 추가 예정)
    // 옛날 코드 곧 제거 예정
    public List<?> getBoardByCategory(String category) {
        // 카테고리에 따라 적절한 매퍼 메소드 호출
        switch (category.toLowerCase()) {
            case "qna":
                return adminBoardMapper.selectQnABoard();
            case "신고":
                return adminBoardMapper.selectReportBoard();
            case "도와주세요":
                return adminBoardMapper.selectHelpBoard();
            case "이벤트":
                return adminBoardMapper.selectEventBoard();
            case "all":
                // 전체 목록 조회 로직 (구현 필요)
                return adminBoardMapper.selectAllBoard();
            default:
                return adminBoardMapper.selectReportBoard();
        }
    }

    // 검색 조건에 따른 게시물 조회
    public List<?> getBoardBySearchCondition(BoardSearchDTO searchDTO) {
        // 카테고리가 전체(all)인 경우 전체 게시판 조회
        if ("all".equalsIgnoreCase(searchDTO.getCategory())) {
            return adminBoardMapper.selectBoardByCondition(searchDTO);
        }

        // 특정 카테고리 게시판 조회
        return adminBoardMapper.selectBoardByCondition(searchDTO);
    }


//    ===============================================================================================================================================================



    public void addAnswer(AdminAnswerDTO adminAnswerDTO) {
        // 카테고리에 따라 다른 답변 테이블에 insert
        switch(adminAnswerDTO.getCategory()) {
            case "QNA":
                adminBoardMapper.insertQnaAnswer(adminAnswerDTO);
                adminBoardMapper.updateQnaStatus(adminAnswerDTO.getQnaId());
                break;
//            case "신고":
//                adminBoardMapper.어쩌고 저쩌고 () 식으로 추가
//                break;
            // 다른 카테고리 추가 가능
        }
    }

    public List<AdminAnswerDTO> getAnswersByPostId(Long postId, String category) {
        // 카테고리에 따라 다른 답변 테이블에서 select
        switch(category) {
            case "QNA":
                return adminBoardMapper.selectQnaAnswersByQnaId(postId);
//            case "신고":
//                return adminBoardMapper.selectReportAnswers(postId);
            // 다른 카테고리 추가 가능
            default:
                return Collections.emptyList();
        }
    }

    public Map<String,Object> selectQnaDetail(Long qnaId) {
        try {
            Map<String, Object> qnaDetail = adminBoardMapper.selectQnaDetail(qnaId);
            if (qnaDetail == null) {
                // 데이터가 없을 경우 빈 맵 반환
                return Collections.emptyMap();
            }
            return qnaDetail;
        } catch (Exception e) {
            // 에러 발생 시 로그 기록 및 빈 맵 반환
            log.error("QnA 상세 조회 중 오류 발생: {}", e.getMessage());
            return Collections.emptyMap();
        }
    }

    // 이벤트 입력 with 파일 입력

    public void addAdminEventBoardWithFIle(AdminEventWriteDTO adminEventWriteDTO,
                                           Long userId,
                                           MultipartFile multipartFile) throws IOException {

        log.debug("====== Event Write Service Start ======");

        // 1. 게시글 저장 처리

        adminEventWriteDTO.setUsersId(userId);
        log.debug("Before Insert - AdminEventWriteDTO: {}", adminEventWriteDTO);

        adminBoardMapper.insertEventBoard(adminEventWriteDTO);
        log.debug("After Insert - Event Board ID: {}", adminEventWriteDTO.getEventBoardId());

        // 파일 존재 여부 검사
        if (multipartFile == null || multipartFile.isEmpty()) {
            log.debug("No File Uploaded");

            return;
        }

        // 2-1 파일이 존재한다면, 파일 정보 가져오기
        String eventOriginalFilename = multipartFile.getOriginalFilename();
        // .jpg를 같은 확장자를 분리하여 확장자를 별도 저장 substring으로 . 기준으로 분리하였다.
        String eventExtension = eventOriginalFilename.substring(eventOriginalFilename.lastIndexOf("."));
        String eventUuid = UUID.randomUUID().toString();
        // 파일을 저장할 경로
        // C:/upload/event/yyyy/MM/dd/파일명. 확장자로 순 으로 선언한다
        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String savePath = this.uploadPath + "/" + datePath +  datePath;

        log.debug("File Info - Original Name: {}", eventOriginalFilename);
        log.debug("File Info - Extension: {}", eventExtension);
        log.debug("File Info - UUID: {}", eventUuid);
        log.debug("File Info - Save Path: {}", savePath);


        AdminFIleDTO adminFIleDTO = new AdminFIleDTO();
        adminFIleDTO.setEventOriginalFilename(eventOriginalFilename);
        adminFIleDTO.setEventExtension(eventExtension);
        adminFIleDTO.setEventUuid(eventUuid);
        adminFIleDTO.setEventPath(datePath);
        adminFIleDTO.setEventBoardId(adminEventWriteDTO.getEventBoardId());

        log.debug("AdminFIleDTO: {}", adminFIleDTO);

        // 3-1 서버 컴퓨터에 실제 파일 저장 처리 (파일 입출력 활용)
        File uploadDir = new File(savePath); // 저장할려는 경로 디렉토리만 포함되어있다.
        log.debug("Creating Directory: {}", savePath);
        log.error("Failed to create directory");



        //예외 처리
        if(!uploadDir.exists()){
            // 경로까지 필요한 디렉토리를 만들어라
            uploadDir.mkdirs(); // makedirectories의 약자다

        }

        // 실제 저장할 파일의 이름을 eventUuid + evnetExtension(확장자) 사용
        String fileSystemName = eventUuid + eventExtension;

        // fileFUllPath : 파일 전체 경로(이름포함), C:/upload/free/yyyy/MM/dd/eventUuid.eventExtension
        String fileFUllPath = savePath + "/" + fileSystemName;
        File file = new File(fileFUllPath);

        // 실제  파일 저장하기 위에는 경로와 형식을/이름 지정하는 코드였다
        // 실제 저장은 이코드 한줄로 끝난다
        multipartFile.transferTo(file);

        // 썸네일 저장 이벤트 게시물은 메인페이지에 이미지가 등록될 것이므로 썸네일이 있으면 좋다

        String contentType = Files.probeContentType(file.toPath());


        //썸네일은 같은 경로 상에 파일 이름만 th_를 붙여 사용한다는 코드다
        if (contentType.startsWith("image")) {
            Thumbnails.of(file)
                    .size(300,200)
                    .toFile(new File(savePath+ "/th_" + fileSystemName));


        }

        // -------------------------------------------------------------------------------------------

        // 3-2. 저장한 실제 파일 정보를 DB에 삽입
        adminFIleMapper.insertEventBoard(adminFIleDTO);

          /*
        이 메서드는 총 2번의 Insert를 수행하는 메서드이다.
        만약 첫 번째 insert만 실행되고 두 번째에서 오류가 발생한다면, 둘 다 롤백되어야
        데이터 무결성을 지킬수 있다.
        이를 위해서 트랜잭션 처리를 해줘야한다.
        (SELECT를 제외한 모든 DML은 여러 번 사용했을 때 하나의 트랜잭션이 필요)
        스프링에서 트랜잭션처리는 어노테이션으로 매우 쉽게 처리 가능하다.

        **주의 사항**
        하나의 서비스 클래스에서 내부에 존재하는 다른 메서드를 호출하면 트랜잭션이 적용되지 않는다.
        (내부 호출 적용 안됨!!!!!!)

        예 )
        BoardService에 a메서드와 b메서드가 존재할 때
        a메서드의 쿼리와 b메서드의 쿼리는 다른 트랜잭션을 사용한다.
        a 메서드 내부에서 b메서드를 호출하면 내부 호출 이므로, 별도의 트랜잭션이 적용된다.
        (같은 클래스의 메서드끼리 호출하면 안됨!!!)
         */



    }



}
