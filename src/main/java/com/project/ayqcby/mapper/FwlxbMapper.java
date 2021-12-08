package com.project.ayqcby.mapper;

import com.project.ayqcby.domain.Fwlxb;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FwlxbMapper {

    List<Fwlxb> loadData(Fwlxb fwlxb);
}
