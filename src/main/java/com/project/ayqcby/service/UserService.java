package com.project.ayqcby.service;

import com.project.ayqcby.domain.User;
import com.project.ayqcby.tools.layuipage.LayuiPageRequest;
import com.project.ayqcby.tools.layuipage.LayuiPageResult;

import java.util.List;

public interface UserService {
    List<User> queryUser(User user);

    void modifypassword(User user);

    LayuiPageResult findPage(LayuiPageRequest pageQuery, User user);

    List<User> loadJs();

    int delUser(String yhm);

    int addUser(User user);

    int editUser(User user);
}
