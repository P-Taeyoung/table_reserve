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
public class Manager extends BaseEntity {

    @Id
    private String id;
    private String password;
    private String name;
    private String phone;
    private boolean isManagerYn;

    public static Manager from(SignUpForm signUpForm) {
        return Manager.builder()
                .id(signUpForm.getId())
                .password(signUpForm.getPassword())
                .name(signUpForm.getName())
                .phone(signUpForm.getPhone())
                .isManagerYn(true)
                .build();
    }

}
