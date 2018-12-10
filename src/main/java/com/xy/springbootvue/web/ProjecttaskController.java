package com.xy.springbootvue.web;


import com.xy.springbootvue.entity.Projecttask;
import com.xy.springbootvue.service.IProjecttaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bearsmall
 * @since 2018-12-10
 */
@RestController
@RequestMapping("/projecttask")
public class ProjecttaskController {

    @Autowired
    private IProjecttaskService projecttaskService;

    @RequestMapping("/all")
    public List<Projecttask> getAll(){
        return projecttaskService.selectList(null);
    }

}
