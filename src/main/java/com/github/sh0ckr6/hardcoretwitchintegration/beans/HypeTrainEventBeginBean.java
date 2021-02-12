package com.github.sh0ckr6.hardcoretwitchintegration.beans;

import java.util.Arrays;

public class HypeTrainEventBeginBean {
  public HypeTrainBeginBean event;
  
  @Override
  public String toString() {
    return "HypeTrainEventProgressBean{" +
           "event=" + event +
           '}';
  }
  
  public class HypeTrainBeginBean {
    public String broadcaster_user_id;
    public String broadcaster_user_login;
    public String broadcaster_user_name;
    public int total;
    public int progress;
    public int goal;
    public HypeTrainContributionBean[] top_contributions;
    public HypeTrainContributionBean last_contribution;
  
    @Override
    public String toString() {
      return "HypeTrainProgressBean{" +
             "broadcaster_user_id='" + broadcaster_user_id + '\'' +
             ", broadcaster_user_login='" + broadcaster_user_login + '\'' +
             ", broadcaster_user_name='" + broadcaster_user_name + '\'' +
             ", total=" + total +
             ", progress=" + progress +
             ", goal=" + goal +
             ", top_contributions=" + Arrays.toString(top_contributions) +
             ", last_contribution=" + last_contribution +
             '}';
    }
  }
}
