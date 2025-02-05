package com.zerobase.table_reserve.reserve.domain.shop;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopUpdateForm {
    private String name;
    private String locate;
    private String description;
    private Integer reserveLimit;

}
