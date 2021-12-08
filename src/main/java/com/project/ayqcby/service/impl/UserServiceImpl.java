package com.project.ayqcby.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.ayqcby.domain.User;
import com.project.ayqcby.mapper.UserMapper;
import com.project.ayqcby.service.UserService;
import com.project.ayqcby.tools.layuipage.LayuiPageRequest;
import com.project.ayqcby.tools.layuipage.LayuiPageResult;
import com.project.ayqcby.tools.layuipage.LayuiPageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> queryUser(User user) {
        List<User>u = userMapper.queryUser(user);
        return u;
    }

    @Override
    public void modifypassword(User user) {
        userMapper.modifypassword(user);
    }

    @Override
    public LayuiPageResult findPage(LayuiPageRequest pageRequest, User user) {
        return LayuiPageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,user));
    }

    private PageInfo<User> getPageInfo(LayuiPageRequest pageRequest, User user) {
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getLimit());
        List<User> sysMenus = userMapper.queryUser(user);
        return new PageInfo<User>(sysMenus);
    }

    @Override
    public List<User>loadJs() {
        List<User>data = userMapper.loadJs();
        return data;
    }

    @Override
    public int delUser(String yhm) {
        return userMapper.delUser(yhm);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int editUser(User user) {
        return userMapper.editUser(user);
    }
}
