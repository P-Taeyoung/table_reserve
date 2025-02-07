package com.zerobase.table_reserve.member.domain.form;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {
    private boolean manager;
    private String id;
    private String password;
    private String name;
    private String phone;

    private String emailAuthKey;
}
