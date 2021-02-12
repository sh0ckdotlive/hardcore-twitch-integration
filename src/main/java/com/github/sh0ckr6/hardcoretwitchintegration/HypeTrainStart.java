package com.github.sh0ckr6.hardcoretwitchintegration;

import com.github.sh0ckr6.hardcoretwitchintegration.beans.HypeTrainContributionBean;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.HypeTrainEventBeginBean;

import java.util.ArrayList;
import java.util.List;

public class HypeTrainStart {
  public String broadcasterUserId;
  public String broadcasterUserLogin;
  public String broadcasterUserName;
  public int total;
  public int progress;
  public int goal;
  public List<HypeTrainContribution> topContributions = new ArrayList<>();
  HypeTrainContribution lastContribution;
  
  public HypeTrainStart(HypeTrainEventBeginBean beginEventBean) {
    this.broadcasterUserId = beginEventBean.event.broadcaster_user_id;
    this.broadcasterUserLogin = beginEventBean.event.broadcaster_user_login;
    this.broadcasterUserName = beginEventBean.event.broadcaster_user_name;
    this.total = beginEventBean.event.total;
    this.progress = beginEventBean.event.progress;
    this.goal = beginEventBean.event.goal;
    for (HypeTrainContributionBean contributionBean :
            beginEventBean.event.top_contributions) {
      topContributions.add(new HypeTrainContribution(contributionBean));
    }
    this.lastContribution = new HypeTrainContribution(beginEventBean.event.last_contribution);
  }
}
