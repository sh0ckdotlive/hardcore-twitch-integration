package com.github.sh0ckr6.hardcoretwitchintegration.beans;

public class SubscriptionEventBean {
  public SubscriptionBean event;
  
  public class SubscriptionBean {
    public String user_id;
    public String user_login;
    public String user_name;
    public String broadcaster_user_id;
    public String broadcaster_user_name;
    public String tier;
    public boolean is_gift;
  }
}
