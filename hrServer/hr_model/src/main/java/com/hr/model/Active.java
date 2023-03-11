package com.hr.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Active {

  private String id;
  private String title;
  private String type;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
  private Date activeTime;
  private Integer activeDuration;
  private String content;
  private Integer points;
  private String remark;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public Date getActiveTime() {
    return activeTime;
  }

  public void setActiveTime(java.sql.Timestamp activeTime) {
    this.activeTime = activeTime;
  }


  public long getActiveDuration() {
    return activeDuration;
  }

  public void setActiveDuration(Integer activeDuration) {
    this.activeDuration = activeDuration;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public long getPoints() {
    return points;
  }

  public void setPoints(Integer points) {
    this.points = points;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Override
  public String toString() {
    return "Active{" +
            "id='" + id + '\'' +
            ", title='" + title + '\'' +
            ", type='" + type + '\'' +
            ", activeTime=" + activeTime +
            ", activeDuration=" + activeDuration +
            ", content='" + content + '\'' +
            ", points=" + points +
            ", remark='" + remark + '\'' +
            '}';
  }
}
