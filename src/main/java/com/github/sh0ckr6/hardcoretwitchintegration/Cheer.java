package com.github.sh0ckr6.hardcoretwitchintegration;

import com.github.sh0ckr6.hardcoretwitchintegration.beans.CheerEventBean;

public class Cheer {
  public boolean anonymous;
  public String userId;
  public String userLogin;
  public String userName;
  public String broadcasterId;
  public String broadcasterLogin;
  public String broadcasterName;
  public String message;
  public int amount;
  
  public Cheer(CheerEventBean cheerEventBean) {
    this.anonymous = Boolean.parseBoolean(cheerEventBean.event.is_anonymous);
    this.userId = cheerEventBean.event.user_id;
    this.userLogin = cheerEventBean.event.user_login;
    this.userName = cheerEventBean.event.user_name;
    this.broadcasterId = cheerEventBean.event.broadcaster_user_id;
    this.broadcasterLogin = cheerEventBean.event.broadcaster_user_login;
    this.broadcasterName = cheerEventBean.event.broadcaster_user_name;
    this.message = cheerEventBean.event.message;
    this.amount = cheerEventBean.event.bits;
  }
}
