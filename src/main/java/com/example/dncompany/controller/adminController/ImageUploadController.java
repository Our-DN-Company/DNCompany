package com.example.dncompany.controller.adminController;

import com.example.dncompany.dto.admin.board.file.AdminFIleDTO;
import com.example.dncompany.mapper.admin.AdminFIleMapper;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/api")
public class ImageUploadController {

    @Value("${event.file.upload-path}")
    private String uploadPath;

    private final AdminFIleMapper adminFIleMapper;  // 추가

    public ImageUploadController(AdminFIleMapper adminFIleMapper) {
        this.adminFIleMapper = adminFIleMapper;
    }

    @PostMapping("/upload/summernote")
    public ResponseEntity<Map<String, String>> uploadSummernoteImage(
            @RequestParam("file") MultipartFile file) {
        log.debug("====== Summernote Image Upload Start ======");
        try {
            String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            String uuid = UUID.randomUUID().toString();
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            // 파일 저장
            String savePath = uploadPath + "/" + datePath;
            File uploadDir = new File(savePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String saveFileName = uuid + extension;
            File saveFile = new File(savePath + "/" + saveFileName);
            file.transferTo(saveFile);

            String contentType = Files.probeContentType(saveFile.toPath());
            if (contentType != null && contentType.startsWith("image")) {
                Thumbnails.of(saveFile)
                        .size(300, 200)
                        .toFile(new File(savePath + "/th_" + saveFileName));
                log.debug("Thumbnail created: {}", "th_" + saveFileName);
            }

            // EVENT_IMG 테이블에 저장
            AdminFIleDTO adminFIleDTO = new AdminFIleDTO();
            adminFIleDTO.setEventOriginalFilename(originalFilename);
            adminFIleDTO.setEventUuid(uuid);
            adminFIleDTO.setEventPath(datePath);
            adminFIleDTO.setEventExtension(extension);

            log.debug("Saving to EVENT_IMG - AdminFIleDTO: {}", adminFIleDTO);
//            adminFIleMapper.insertEventBoard(adminFIleDTO);
              // 현재 게시글 등록 전에 썸머노트에 이미지가 먼저 들어가는 문제가 생김
              // 그로 인해 null값을 받는 상황이 발생
              // 심지어 eventId는 자식키로 먼저 저장 후 올리는 것 도 불가능함
              // 그래서 이미지를 나중에 저장 처리로 변경
            String imageUrl = "/upload/event/" + datePath + "/" + saveFileName;
            log.debug("Image URL: {}", imageUrl);

            Map<String, String> result = new HashMap<>();
            result.put("url", imageUrl);

            // 썸네일 URL도 반환 (필요한 경우)
            result.put("thumbnailUrl", "/upload/event/" + datePath + "/th_" + saveFileName);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Image Upload Failed: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}