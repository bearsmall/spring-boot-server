package com.xy.springbootvue.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author bearsmall
 * @since 2018-12-10
 */
public class Projecttask extends Model<Projecttask> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String dir;

    private Integer zip_size;

    private Date upload_time;

    private Date check_start_time;

    private Date check_end_time;

    private String language;

    private Integer type;

    private Integer state;

    private Integer vul_high;

    private Integer vul_mid;

    private Integer vul_low;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
    public Integer getZip_size() {
        return zip_size;
    }

    public void setZip_size(Integer zip_size) {
        this.zip_size = zip_size;
    }
    public Date getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Date upload_time) {
        this.upload_time = upload_time;
    }
    public Date getCheck_start_time() {
        return check_start_time;
    }

    public void setCheck_start_time(Date check_start_time) {
        this.check_start_time = check_start_time;
    }
    public Date getCheck_end_time() {
        return check_end_time;
    }

    public void setCheck_end_time(Date check_end_time) {
        this.check_end_time = check_end_time;
    }
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public Integer getVul_high() {
        return vul_high;
    }

    public void setVul_high(Integer vul_high) {
        this.vul_high = vul_high;
    }
    public Integer getVul_mid() {
        return vul_mid;
    }

    public void setVul_mid(Integer vul_mid) {
        this.vul_mid = vul_mid;
    }
    public Integer getVul_low() {
        return vul_low;
    }

    public void setVul_low(Integer vul_low) {
        this.vul_low = vul_low;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Projecttask{" +
        "id=" + id +
        ", name=" + name +
        ", dir=" + dir +
        ", zip_size=" + zip_size +
        ", upload_time=" + upload_time +
        ", check_start_time=" + check_start_time +
        ", check_end_time=" + check_end_time +
        ", language=" + language +
        ", type=" + type +
        ", state=" + state +
        ", vul_high=" + vul_high +
        ", vul_mid=" + vul_mid +
        ", vul_low=" + vul_low +
        "}";
    }
}
