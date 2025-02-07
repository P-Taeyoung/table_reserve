package com.zerobase.table_reserve.member.domain.form;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInForm {
    String userId;
    String password;
}
