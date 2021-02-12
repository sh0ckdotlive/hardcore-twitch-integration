package com.github.sh0ckr6.hardcoretwitchintegration;

import com.github.sh0ckr6.hardcoretwitchintegration.beans.SubscriptionEventBean;

public class Subscription {
  public String userId;
  public String userLogin;
  public String username;
  public String broadcasterUserId;
  public String broadcasterUsername;
  public int tier;
  public boolean isGift;
  
  public Subscription(SubscriptionEventBean bean) {
    this.userId = bean.event.user_id;
    this.userLogin = bean.event.user_login;
    this.username = bean.event.user_name;
    this.broadcasterUserId = bean.event.broadcaster_user_id;
    this.broadcasterUsername = bean.event.broadcaster_user_name;
    this.tier = Integer.parseInt(bean.event.tier) / 1000;
    this.isGift = bean.event.is_gift;
  }
}
