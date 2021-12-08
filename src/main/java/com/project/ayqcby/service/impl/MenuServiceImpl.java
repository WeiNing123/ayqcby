package com.project.ayqcby.service.impl;

import com.project.ayqcby.domain.Menu;
import com.project.ayqcby.domain.User;
import com.project.ayqcby.mapper.MenuMapper;
import com.project.ayqcby.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> loadDataToMenu(User user) {
        List<Menu>menu = menuMapper.loadDataToMenu(user);
        return menu;
    }
}
