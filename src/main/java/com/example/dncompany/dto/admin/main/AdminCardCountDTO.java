package com.example.dncompany.dto.admin.main;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AdminCardCountDTO {
   private int cardTotalMembers ;
   private int cardTotalBoards;
   private int cardTotalAnswers ;
   private int cardReports;
   private int cardQnAs;

}
