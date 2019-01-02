package com.xy.springbootvue.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author bearsmall
 * @since 2019-01-02
 */
public class Snykflaw extends Model<Snykflaw> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String gaid;

    private String version;

    private String level;

    private String description;

    private String type;

    private String publish;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getGaid() {
        return gaid;
    }

    public void setGaid(String gaid) {
        this.gaid = gaid;
    }
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Snykflaw{" +
        "id=" + id +
        ", gaid=" + gaid +
        ", version=" + version +
        ", level=" + level +
        ", description=" + description +
        ", type=" + type +
        ", publish=" + publish +
        ", url=" + url +
        "}";
    }
}
