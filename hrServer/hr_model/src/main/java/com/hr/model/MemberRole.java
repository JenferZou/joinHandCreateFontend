package com.hr.model;


public class MemberRole {

  private String mid;
  private String rid;


  public String getMid() {
    return mid;
  }

  public void setMid(String mid) {
    this.mid = mid;
  }


  public String getRid() {
    return rid;
  }

  public void setRid(String rid) {
    this.rid = rid;
  }

  @Override
  public String toString() {
    return "MemberRole{" +
            "mid='" + mid + '\'' +
            ", rid='" + rid + '\'' +
            '}';
  }
}
