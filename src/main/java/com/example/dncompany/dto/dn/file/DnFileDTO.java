package com.example.dncompany.dto.dn.file;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DnFileDTO {
    private Long productImgId;
    private Long productId;
    private String productOriginalFilename;
    private String productUuid;
    private String productPath;
    private String productExtension;
}
