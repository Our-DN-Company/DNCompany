package com.example.dncompany.dto.user.mypage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PetImageDTO {
    private Long petImgId;
    private Long petId;
    private String petOriginalImageName;
    private String petUuid;
    private String petImagePath;
    private String petExtension;
    private LocalDateTime petRegDate;
}
