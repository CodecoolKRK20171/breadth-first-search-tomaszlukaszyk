package com.codecool.bfsexample.dao;

import com.codecool.bfsexample.model.UserNode;

import java.util.List;

public interface UserDao {

    List<UserNode> getAllUsers();
    UserNode getUserByFullName(String fullName);
}
