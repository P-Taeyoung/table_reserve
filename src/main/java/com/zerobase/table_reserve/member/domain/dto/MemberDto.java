package com.zerobase.table_reserve.member.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private String id;
    private boolean isManager;

}
