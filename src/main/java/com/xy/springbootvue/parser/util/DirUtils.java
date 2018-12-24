package com.xy.springbootvue.parser.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirUtils {
    private List<String> paths = new ArrayList<>();

    public List<String> filter(String root,String suffix){
        iteratePath(root,suffix);
        return paths;
    }

    public void iteratePath(String root, String suffix) {
        File file = new File(root);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f:files){
                iteratePath(f.getAbsolutePath(),suffix);
            }
        }else if(file.isFile()){
            if(file.getAbsolutePath().endsWith(suffix)){
                paths.add(file.getAbsolutePath());
            }
        }
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }
}
