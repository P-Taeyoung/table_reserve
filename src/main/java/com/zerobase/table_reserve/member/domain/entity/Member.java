package com.zerobase.table_reserve.member.domain.entity;

import com.zerobase.table_reserve.member.domain.form.SignUpForm;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.envers.AuditOverride;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Member extends BaseEntity {

    @Id
    private String id;
    private String password;
    private String name;
    private String phone;

    private boolean emailAuthYn;
    private String emailAuthKey;

    private boolean isManager;

    public static Member from(SignUpForm signUpForm) {
        return Member.builder()
                .id(signUpForm.getId())
                .password(signUpForm.getPassword())
                .name(signUpForm.getName())
                .phone(signUpForm.getPhone())
                .emailAuthKey(signUpForm.getEmailAuthKey())
                .isManager(signUpForm.isManager())
                .build();
    }

}
