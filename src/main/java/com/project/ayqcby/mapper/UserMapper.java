package com.project.ayqcby.mapper;

import com.project.ayqcby.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> queryUser(User user);

    void modifypassword(User user);

    List<User> loadJs();

    int delUser(String yhm);

    int addUser(User user);

    int editUser(User user);
}
