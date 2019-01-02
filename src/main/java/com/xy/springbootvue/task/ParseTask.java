package com.xy.springbootvue.task;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.xy.springbootvue.entity.Javadependency;
import com.xy.springbootvue.entity.Projecttask;
import com.xy.springbootvue.entity.Snykflaw;
import com.xy.springbootvue.parser.maven.MavenParser;
import com.xy.springbootvue.parser.maven.item.JavaDependency;
import com.xy.springbootvue.service.IJavadependencyService;
import com.xy.springbootvue.service.IProjecttaskService;
import com.xy.springbootvue.service.ISnykflawService;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class ParseTask implements Runnable{
    private String fileName;
    private String path;
    private IProjecttaskService projecttaskService;
    private IJavadependencyService javadependencyService;
    private ISnykflawService snykflawService;

    public ParseTask(String fileName, String path, IProjecttaskService projecttaskService, IJavadependencyService javadependencyService,ISnykflawService snykflawService) {
        this.fileName = fileName;
        this.path = path;
        this.projecttaskService = projecttaskService;
        this.javadependencyService = javadependencyService;
        this.snykflawService = snykflawService;
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
        List<Snykflaw> snykflawList = snykflawService.selectList(null);
        for(JavaDependency j:jds){
            String gaid = j.getGroupId()+":"+j.getArtifactId();
            for(Snykflaw snykflaw:snykflawList){
                if(gaid.equals(snykflaw.getGaid())){
                    if(snykflaw.getVersion()!=null||j.getVersion()!=null) {
                        if (versionContains(snykflaw.getVersion(), j.getVersion())) {
                            System.out.println(j.getGroupId() + "/" + j.getArtifactId() + "/" + j.getVersion());
                            System.out.println("affected:");
                            System.out.println(snykflaw.getId() + ":" + snykflaw.getGaid() + ":" + snykflaw.getVersion());
                        }
                    }
                }
            }
//            System.out.println(j.getGroupId()+"/"+j.getArtifactId()+"/"+j.getVersion());
//            Javadependency javadependency = new Javadependency();
//            javadependency.setArtifactid(j.getArtifactId());
//            javadependency.setGroupid(j.getGroupId());
//            javadependency.setVersion(j.getVersion());
//            javadependency.setProjectId(projecttask.getId());
//            javadependencyService.insert(javadependency);
        }
        projecttask.setCheck_end_time(new Date());
        projecttaskService.insertOrUpdate(projecttask);
    }

    private boolean versionContains(String flawVersion, String version) {
        String[] v = flawVersion.split(",");
        int left = 0;
        String leftVersion = null;
        int right = 0;
        String rightVersion = null;
        left = v[0].charAt(0)=='['?1:v[0].charAt(0)=='('?2:3;
        right = v[1].charAt(v[1].length()-1)==']'?1:v[1].charAt(v[1].length()-1)==')'?2:3;
        if(v[0].length()==1){
            if(v[0].equals("[")){
                leftVersion = "0";
            }else if(v[0].equals("(")){
                leftVersion = "0";
            }
        }else {
            leftVersion = v[0].substring(1);
        }
        if(v[1].length()==1){
            if(v[1].equals("[")){
                rightVersion = "0";
            }else if(v[1].equals("(")){
                rightVersion = "0";
            }
        }else {
            rightVersion = v[1].substring(0,v[1].length()-1);
        }
        String[]vl = leftVersion.split("[.]");
        String[] ve = version.split("[.]");
        String[] vr = rightVersion.split("[.]");
        try {
            for (int i = 0; i < ve.length; i++) {
                if (i < vl.length) {
                    if (Integer.parseInt(ve[i].trim()) > Integer.parseInt(vl[i].trim())) {
                        break;
                    } else if (Integer.parseInt(ve[i].trim()) == Integer.parseInt(vl[i].trim())) {
                        if (left == 1 && i == vl.length && i == ve.length) {
                            break;
                        }
                    } else {
                        return false;
                    }
                } else {
                    break;
                }
            }
            for (int i = 0; i < ve.length; i++) {
                if (i < vr.length) {
                    if (Integer.parseInt(ve[i].trim()) < Integer.parseInt(vr[i].trim())) {
                        return true;
                    } else if (Integer.parseInt(ve[i].trim()) == Integer.parseInt(vr[i].trim())) {
                        if (right == 1 && i == vr.length-1 && i == ve.length-1) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }catch (NumberFormatException e){
            return false;
        }
        return false;
    }
}