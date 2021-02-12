package com.github.sh0ckr6.hardcoretwitchintegration;

import com.github.sh0ckr6.hardcoretwitchintegration.beans.HypeTrainContributionBean;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.HypeTrainEventProgressBean;

import java.util.ArrayList;
import java.util.List;

public class HypeTrainProgress {
  public String broadcasterUserId;
  public String broadcasterUserLogin;
  public String broadcasterUserName;
  public int level;
  public int total;
  public int progress;
  public int goal;
  public List<HypeTrainContribution> topContributions = new ArrayList<>();
  HypeTrainContribution lastContribution;
  
  public HypeTrainProgress(HypeTrainEventProgressBean progressEventBean) {
    this.broadcasterUserId = progressEventBean.event.broadcaster_user_id;
    this.broadcasterUserLogin = progressEventBean.event.broadcaster_user_login;
    this.broadcasterUserName = progressEventBean.event.broadcaster_user_name;
    this.level = progressEventBean.event.level;
    this.total = progressEventBean.event.total;
    this.progress = progressEventBean.event.progress;
    this.goal = progressEventBean.event.goal;
    for (HypeTrainContributionBean contributionBean :
            progressEventBean.event.top_contributions) {
      topContributions.add(new HypeTrainContribution(contributionBean));
    }
    this.lastContribution = new HypeTrainContribution(progressEventBean.event.last_contribution);
  }
}
