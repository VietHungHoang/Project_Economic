package com.example.project_economic.service;

import com.example.project_economic.entity.UserEntity;

public interface UserService {
    UserEntity createUser(UserEntity userEntity) throws Exception;
    UserEntity findUserById(Long userId);
}
