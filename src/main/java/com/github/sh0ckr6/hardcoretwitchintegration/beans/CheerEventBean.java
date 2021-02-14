package com.github.sh0ckr6.hardcoretwitchintegration.beans;

public class CheerEventBean {
  public CheerBean event;
  
  public static class CheerBean {
    public String is_anonymous;
    public String user_id;
    public String user_login;
    public String user_name;
    public String broadcaster_user_id;
    public String broadcaster_user_login;
    public String broadcaster_user_name;
    public String message;
    public int bits;
  }
}
