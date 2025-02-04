package com.zerobase.table_reserve.manager.domain.form;

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
}
