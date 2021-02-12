package com.github.sh0ckr6.hardcoretwitchintegration.beans;

import java.util.Arrays;

public class HypeTrainEventEndBean {
  public HypeTrainEndBean event;
  
  @Override
  public String toString() {
    return "HypeTrainEventProgressBean{" +
           "event=" + event +
           '}';
  }
  
  public class HypeTrainEndBean {
    public String broadcaster_user_id;
    public String broadcaster_user_login;
    public String broadcaster_user_name;
    public int level;
    public int total;
    public HypeTrainContributionBean[] top_contributions;
  
    @Override
    public String toString() {
      return "HypeTrainProgressBean{" +
             "broadcaster_user_id='" + broadcaster_user_id + '\'' +
             ", broadcaster_user_login='" + broadcaster_user_login + '\'' +
             ", broadcaster_user_name='" + broadcaster_user_name + '\'' +
             ", level=" + level +
             ", total=" + total +
             ", top_contributions=" + Arrays.toString(top_contributions) +
             '}';
    }
  }
}
