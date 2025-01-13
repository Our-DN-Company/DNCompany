package com.example.dncompany.dto.dn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProductModifyDTO {
    Long productId;
    int productPrice;
    String productCategory;
}
