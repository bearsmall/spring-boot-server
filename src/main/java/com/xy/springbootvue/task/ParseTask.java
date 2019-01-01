package com.xy.springbootvue.task;

import com.xy.springbootvue.entity.Javadependency;
import com.xy.springbootvue.entity.Projecttask;
import com.xy.springbootvue.parser.maven.MavenParser;
import com.xy.springbootvue.parser.maven.item.JavaDependency;
import com.xy.springbootvue.service.IJavadependencyService;
import com.xy.springbootvue.service.IProjecttaskService;

import java.util.Date;
import java.util.Set;

public class ParseTask implements Runnable{
    private String fileName;
    private String path;
    private IProjecttaskService projecttaskService;
    private IJavadependencyService javadependencyService;

    public ParseTask(String fileName, String path, IProjecttaskService projecttaskService, IJavadependencyService javadependencyService) {
        this.fileName = fileName;
        this.path = path;
        this.projecttaskService = projecttaskService;
        this.javadependencyService = javadependencyService;
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

    public IJavadependencyService getJavadependencyService() {
        return javadependencyService;
    }

    public void setJavadependencyService(IJavadependencyService javadependencyService) {
        this.javadependencyService = javadependencyService;
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
        MavenParser mavenParser = new MavenParser();
        mavenParser.parse(path);
        Set<JavaDependency> jds = mavenParser.getJavaDependencySet();
        for(JavaDependency j:jds){
            System.out.println(j.getGroupId()+"/"+j.getArtifactId()+"/"+j.getVersion());
            Javadependency javadependency = new Javadependency();
            javadependency.setArtifactid(j.getArtifactId());
            javadependency.setGroupid(j.getGroupId());
            javadependency.setVersion(j.getVersion());
            javadependency.setProjectId(projecttask.getId());
            javadependencyService.insert(javadependency);
        }
        projecttask.setCheck_end_time(new Date());
        projecttaskService.insertOrUpdate(projecttask);
    }
}