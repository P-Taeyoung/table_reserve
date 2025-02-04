package com.zerobase.table_reserve.manager.domain.form;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopForm {


    private Long managerId;
    private String name;
    private String locate;
    private String description;
}
