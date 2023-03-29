package com.hr.model;


import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

  private String rid;
  private String title;


  public String getRid() {
    return rid;
  }

  public void setRid(String rid) {
    this.rid = rid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String getAuthority() {
    return this.rid;
  }
}
