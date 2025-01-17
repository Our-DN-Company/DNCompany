package com.example.dncompany.dto.dn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProductModifyDTO {
    private Long productId;
    private String productName;
    private int productPrice;
    private String productCategory;
}
