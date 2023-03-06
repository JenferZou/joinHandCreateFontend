package com.hr.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Contest {

  private String id;
  private String title;
  private String remark;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
  private Date startTime;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
  private Date endTime;
  private String content;


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


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }


  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "Contest{" +
            "id='" + id + '\'' +
            ", title='" + title + '\'' +
            ", remark='" + remark + '\'' +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", content='" + content + '\'' +
            '}';
  }
}
