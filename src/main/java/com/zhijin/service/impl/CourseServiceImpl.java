package com.zhijin.service.impl;

import com.zhijin.dao.CourseDao;
import com.zhijin.entity.Course;
import com.zhijin.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;
    @Override
    public Course courseIndex(Integer id) {
        return courseDao.selectByPrimaryKey(id);
    }
}
