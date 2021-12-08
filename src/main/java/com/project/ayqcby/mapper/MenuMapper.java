package com.project.ayqcby.mapper;

import com.project.ayqcby.domain.Menu;
import com.project.ayqcby.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> loadDataToMenu(User user);
}
