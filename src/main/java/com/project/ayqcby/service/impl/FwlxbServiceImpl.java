package com.project.ayqcby.service.impl;

import com.project.ayqcby.domain.Fwlxb;
import com.project.ayqcby.mapper.FwlxbMapper;
import com.project.ayqcby.service.FwlxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FwlxbServiceImpl implements FwlxbService {

    @Autowired
    FwlxbMapper fwlxbMapper;

    @Override
    public List<Fwlxb> loadData(Fwlxb fwlxb) {
        return fwlxbMapper.loadData(fwlxb);
    }
}
