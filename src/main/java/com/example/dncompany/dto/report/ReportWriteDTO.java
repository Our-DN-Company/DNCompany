package com.example.dncompany.dto.report;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
public class ReportWriteDTO {
    private Long reportId;
    private String reportContent;
    private String reportCheckStatus;
    private Long helpOfferId;
    private String reportTitle;
    private Date reportDate;
}
