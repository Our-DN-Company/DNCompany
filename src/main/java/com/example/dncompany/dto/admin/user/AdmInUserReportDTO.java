package com.example.dncompany.dto.admin.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class AdmInUserReportDTO {
    // 기존 신고 관련 필드
    private Long reportId;         // REPORT_ID
    private String reportTitle;    // REPORT_TITLE
    private String reportContent;  // REPORT_CONTENT
    private Long helpOfferId;      // HELP_OFFER_ID
    private LocalDateTime reportDate;  // REPORT_DATE
    private String reportCheckStatus;  // REPORT_CHECK_STATUS (처리상태)

    // 신고자 정보
    private String reporterName;      // 신고한 사람 이름
    private String reporterLoginId;   // 신고한 사람 아이디

    // 신고당한 사람 정보
    private String reportedName;      // 신고당한 사람 이름
    private String reportedLoginId;   // 신고당한 사람 아이디

    // 추가되는 정지 관련 필드
    private String status;         // USER_STATUS 테이블의 정지상태
    private LocalDateTime banStartDate;    // 정지 시작일
    private LocalDateTime banEndDate;      // 정지 종료일
}