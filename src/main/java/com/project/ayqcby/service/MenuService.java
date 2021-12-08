package com.project.ayqcby.service;

import com.project.ayqcby.domain.Menu;
import com.project.ayqcby.domain.User;

import java.util.List;

public interface MenuService {
    List<Menu> loadDataToMenu(User user);
}
