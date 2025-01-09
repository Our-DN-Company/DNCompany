package com.example.dncompany.dto.admin.main;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AdminUserCountDTO {

    private String chartDate;
    private int newMembers;
    private int totalMembers;
}
