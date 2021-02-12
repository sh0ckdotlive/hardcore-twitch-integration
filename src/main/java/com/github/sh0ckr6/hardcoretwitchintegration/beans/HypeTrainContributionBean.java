package com.github.sh0ckr6.hardcoretwitchintegration.beans;

public class HypeTrainContributionBean {
  public String user_id;
  public String user_login;
  public String user_name;
  public String type;
  public int total;
  
  @Override
  public String toString() {
    return "HypeTrainContributionBean{" +
           "user_id='" + user_id + '\'' +
           ", user_login='" + user_login + '\'' +
           ", user_name='" + user_name + '\'' +
           ", type='" + type + '\'' +
           ", total=" + total +
           '}';
  }
}
