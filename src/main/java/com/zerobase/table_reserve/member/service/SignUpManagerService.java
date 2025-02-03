package com.zerobase.table_reserve.member.service;

import com.zerobase.table_reserve.member.domain.entity.Manager;
import com.zerobase.table_reserve.member.domain.form.SignUpForm;
import com.zerobase.table_reserve.member.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpManagerService {
    private final ManagerRepository managerRepository;

    public Manager signUp(SignUpForm signUpForm) {
        return managerRepository.save(Manager.from(signUpForm));
    }

    public boolean isEmailExist(String id) {
        return managerRepository.findById(id).isPresent();
    }
}
