package com.github.sh0ckr6.hardcoretwitchintegration;

import com.github.sh0ckr6.hardcoretwitchintegration.beans.HypeTrainContributionBean;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.HypeTrainEventEndBean;

import java.util.ArrayList;
import java.util.List;

public class HypeTrainEnd {
  public String broadcasterUserId;
  public String broadcasterUserLogin;
  public String broadcasterUserName;
  public int level;
  public int total;
  public List<HypeTrainContribution> topContributions = new ArrayList<>();
  
  public HypeTrainEnd(HypeTrainEventEndBean progressEventBean) {
    this.broadcasterUserId = progressEventBean.event.broadcaster_user_id;
    this.broadcasterUserLogin = progressEventBean.event.broadcaster_user_login;
    this.broadcasterUserName = progressEventBean.event.broadcaster_user_name;
    this.level = progressEventBean.event.level;
    this.total = progressEventBean.event.total;
    for (HypeTrainContributionBean contributionBean :
            progressEventBean.event.top_contributions) {
      topContributions.add(new HypeTrainContribution(contributionBean));
    }
  }
}
