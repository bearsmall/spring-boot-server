package com.xy.springbootvue.task;

import com.xy.springbootvue.entity.Projecttask;
import com.xy.springbootvue.parser.maven.MavenParser;
import com.xy.springbootvue.parser.maven.item.JavaDependency;
import com.xy.springbootvue.parser.util.DirUtils;
import com.xy.springbootvue.service.IProjecttaskService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParseTask implements Runnable{
    private String fileName;
    private String path;
    private IProjecttaskService projecttaskService;

    public ParseTask(String fileName, String path, IProjecttaskService projecttaskService) {
        this.fileName = fileName;
        this.path = path;
        this.projecttaskService = projecttaskService;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public IProjecttaskService getProjecttaskService() {
        return projecttaskService;
    }

    public void setProjecttaskService(IProjecttaskService projecttaskService) {
        this.projecttaskService = projecttaskService;
    }

    @Override
    public void run() {
        Projecttask projecttask = new Projecttask();
        projecttask.setName(fileName);
        projecttask.setDir(path);
        projecttask.setCheck_start_time(new Date());
        projecttask.setState(1);
        projecttask.setType(1);
        projecttask.setUpload_time(new Date());
        projecttask.setZip_size(1);
        projecttaskService.insert(projecttask);
        List<String> paths = new DirUtils().filter(path,"pom.xml");
        Set<JavaDependency> jds = new HashSet<>();
        for(String p:paths) {
            MavenParser mavenParser = new MavenParser();
            mavenParser.parse(p);
            Set<JavaDependency> jd = mavenParser.getJavaDependencySet();
            jds.addAll(jd);
        }
        for(JavaDependency j:jds){
            System.out.println(j.getGroupId()+"/"+j.getArtifactId()+"/"+j.getVersion());
        }
        projecttask.setCheck_end_time(new Date());
        projecttaskService.insertOrUpdate(projecttask);
    }
}