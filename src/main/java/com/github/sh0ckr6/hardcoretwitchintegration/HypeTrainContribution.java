package com.github.sh0ckr6.hardcoretwitchintegration;

import com.github.sh0ckr6.hardcoretwitchintegration.beans.HypeTrainContributionBean;

public class HypeTrainContribution {
  public String userId;
  public String userLogin;
  public String userName;
  public String type;
  public int total;
  
  public HypeTrainContribution(HypeTrainContributionBean contributionBean) {
    this.userId = contributionBean.user_id;
    this.userLogin = contributionBean.user_login;
    this.userName = contributionBean.user_name;
    this.type = contributionBean.type;
    this.total = contributionBean.total;
  }
}
