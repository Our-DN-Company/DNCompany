package com.example.dncompany.service.dn;

import com.example.dncompany.dto.dn.*;
import com.example.dncompany.dto.dn.file.DnFileDTO;
import com.example.dncompany.dto.page.PageDTO;
import com.example.dncompany.dto.page.PageRequestDTO;
import com.example.dncompany.exception.dn.DnBoardNotFoundException;
import com.example.dncompany.mapper.dn.DnBoardMapper;
import com.example.dncompany.mapper.dn.DnFileMapper;
import com.example.dncompany.mapper.dn.DnProductMapper;
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
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DnBoardService {
    private final DnBoardMapper dnBoardMapper;
    private final DnProductMapper dnProductMapper;
    private final DnFileMapper dnFileMapper;

    @Value("${dn.file.upload-path}")
    private String fileUploadPath;

    // 게시글 추가 기능
    public void addDnBoard(DnBoardWriteDTO dnBoardWriteDTO,
                           ProductDTO productDTO,
                           Long userId) {

        dnProductMapper.insertProduct(productDTO);
        dnBoardWriteDTO.setProductId(productDTO.getProductId());
        dnBoardWriteDTO.setUsersId(userId);
        dnBoardMapper.insertDnBoard(dnBoardWriteDTO);


    }

    // 게시글 조회 기능
    public DnBoardDetailDTO getDnBoardById(Long dnId) {

        return dnBoardMapper.selectDnBoardById(dnId)
                .orElseThrow(() -> new DnBoardNotFoundException("게시글을 찾을 수 없음, ID : " + dnId));
    }

    // 게시글 전체 조회 기능
    public List<DnBoardListDTO> getDnBoardList() {

        return dnBoardMapper.selectAllDnBoardList();
    }


    // 게시글 삭제 처리 기능
    public void removeDnBoard(Long dnId,
                              Long productId) {

        dnBoardMapper.deleteDnBoard(dnId);
        dnProductMapper.deleteProduct(productId);

    }

    // 게시글 추가 기능 + 파일 업로드
    public void addDnBoardWithFile(DnBoardWriteDTO dnBoardWriteDTO,
                                   ProductDTO productDTO,
                                   Long usersId,
                                   MultipartFile multipartFile) throws IOException {
        // 1. 게시글 저장
        dnProductMapper.insertProduct(productDTO);
        dnBoardWriteDTO.setUsersId(usersId);
        dnBoardWriteDTO.setProductId(productDTO.getProductId());
        dnBoardMapper.insertDnBoard(dnBoardWriteDTO);
        // 2. 파일 존재 여부 검사
        if (multipartFile == null || multipartFile.isEmpty()) {
            return;
        }

        // 2-1. 파일이 존재하면 파일 정보 가져오기
        String originalFilename = multipartFile.getOriginalFilename();

        String extension = originalFilename.substring(originalFilename.lastIndexOf(".")); // 확장자 분리, 뒤에서부터 substring을 사용하여 분리
        String uuid = UUID.randomUUID().toString();// uuid (파일 이름 중복을 방지하기 위해 사용)

        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String savePath = this.fileUploadPath + "/" + datePath;// 실제 저장될 전체 디렉터리 경로

        DnFileDTO dnFileDTO = new DnFileDTO();
        dnFileDTO.setProductOriginalFilename(originalFilename);
        dnFileDTO.setProductExtension(extension);
        dnFileDTO.setProductUuid(uuid);
        dnFileDTO.setProductPath(datePath);
        dnFileDTO.setProductId(productDTO.getProductId());

        // 3-1. 서버 컴퓨터에 실제 파일 저장 처리 (파일 입출력 활용)
        File uploadDir = new File(savePath); // 저장하려는 경로 (디렉토리만 포함하는 경로)

        // 만약 저장하려는 경로가 존재하지 않다면????
        if (!uploadDir.exists()) {
            // 경로까지 필요한 모든 디렉터리 생성!!
            uploadDir.mkdirs(); //mkdirs(): make directories 의 약자
        }

        // 실제 저장할 파일의 이름을 uuid로 사용
        String fileSystemName = uuid + extension;
        // fileSystemName : 서버에 저장할 파일 이름, uuid, 확장자
        String fileFullpath = savePath + "/" + fileSystemName;
        // filefullpath : C:/upload/free/yyyy/MM/dd/uuid.확장자
        File file = new File(fileFullpath);

        // 실제 파일 저장하기(실제 저장 처리는 이 한줄이 끝)
        multipartFile.transferTo(file);

        // 썸네일 작업
        String contentType = Files.probeContentType(file.toPath());
        // 이미지 파일인 경우에만 처리하는 조건식
        if (contentType.startsWith("image")) {
            Thumbnails.of(file)
                    .size(300, 200)
                    .toFile(new File(savePath + "/th_" + fileSystemName));
            // 썸네일은 같은 경로상에 파일 이름만 th_를 붙여 사용
        }

        // 3-2. 저장한 실제파일 정보를 DB에 삽입
        dnFileDTO.setProductId(productDTO.getProductId());
        dnFileMapper.insertFile(dnFileDTO);
    }

    // 게시글 수정 처리 기능 + 파일 업로드
    public void modifyDnBoard(DnBoardModifyDTO boardModifyDTO,
                              ProductModifyDTO productModifyDTO,
                              MultipartFile multipartFile) throws IOException {
        dnBoardMapper.updateDnBoard(boardModifyDTO);
        dnProductMapper.updateProduct(productModifyDTO);

        if (multipartFile == null || multipartFile.isEmpty()) {
            return;
        }

        // 파일이 존재하면 삭제 후 다시 저장 처리
        dnFileMapper.deleteByBoardId(productModifyDTO.getProductId());

        // 새 파일로 저장
        String originalFilename = multipartFile.getOriginalFilename();// 원본파일 이름.확장자 가 이름
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".")); // 확장자 분리, 뒤에서부터 substring을 사용하여 분리
        String uuid = UUID.randomUUID().toString();// uuid (파일 이름 중복을 방지하기 위해 사용)
        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String savePath = this.fileUploadPath + "/" + datePath;// 실제 저장될 전체 디렉터리 경로

        DnFileDTO dnFileDTO = new DnFileDTO();
        dnFileDTO.setProductOriginalFilename(originalFilename);
        dnFileDTO.setProductExtension(extension);
        dnFileDTO.setProductUuid(uuid);
        dnFileDTO.setProductPath(datePath);
        dnFileDTO.setProductId(productModifyDTO.getProductId());

        log.debug("freeFileDTO: {}", dnFileDTO);

        File uploadDir = new File(savePath); // 저장하려는 경로 (디렉토리만 포함하는 경로)

        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); //mkdirs(): make directories 의 약자
        }

        String fileSystemName = uuid + extension;
        String fileFullpath = savePath + "/" + fileSystemName;
        File file = new File(fileFullpath);

        multipartFile.transferTo(file);


        String contentType = Files.probeContentType(file.toPath());
        if (contentType.startsWith("image")) {
            Thumbnails.of(file)
                    .size(300, 200)
                    .toFile(new File(savePath + "/th_" + fileSystemName));
        }

        dnFileMapper.insertFile(dnFileDTO);

    }

    public PageDTO<DnBoardListDTO> getDnBoardsBySearchCondWithPage(PageRequestDTO pageRequestDTO, DnSearchDTO dnSearchDTO) {
        pageRequestDTO.setSize(12);
        List<DnBoardListDTO> boardList = dnBoardMapper.selectAllDnBoardListCondWithPage(pageRequestDTO, dnSearchDTO);
        int total = dnBoardMapper.countBySearchCondition(dnSearchDTO);

        return new PageDTO<>(pageRequestDTO.getPage(),
                pageRequestDTO.getSize(),
                total,
                boardList);
    }
}
