package com.xy.springbootvue.web;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.xy.springbootvue.entity.Javadependency;
import com.xy.springbootvue.entity.Projecttask;
import com.xy.springbootvue.service.IJavadependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bearsmall
 * @since 2019-01-01
 */
@RestController
@RequestMapping("/javadependency")
public class JavadependencyController {
    @Autowired
    private IJavadependencyService javadependencyService;

    @RequestMapping("/project/{projectId}")
    public List<Javadependency> getByProjectId(@PathVariable("projectId") Integer projectId){
        Wrapper wrapper= Condition.create().eq("project_id",projectId);
        List<Javadependency> list = javadependencyService.selectList(wrapper);
        return list;
    }
}
