package com.github.sh0ckr6.hardcoretwitchintegration;

import com.github.sh0ckr6.hardcoretwitchintegration.beans.ChannelPointRedemptionEventBean;

public class ChannelPointRedemption {
  public String id;
  public String broadcasterUserId;
  public String broadcasterUserLogin;
  public String broadcasterUserName;
  public String userId;
  public String userLogin;
  public String userName;
  public String userInput;
  public String status;
  public String rewardId;
  public String rewardTitle;
  public int rewardCost;
  public String rewardPrompt;
  public String redeemedAt;
  
  public ChannelPointRedemption(ChannelPointRedemptionEventBean channelPointRedemptionEventBean) {
    this.id = channelPointRedemptionEventBean.event.id;
    this.broadcasterUserId = channelPointRedemptionEventBean.event.broadcaster_user_id;
    this.broadcasterUserLogin = channelPointRedemptionEventBean.event.broadcaster_user_login;
    this.broadcasterUserName = channelPointRedemptionEventBean.event.broadcaster_user_name;
    this.userId = channelPointRedemptionEventBean.event.user_id;
    this.userLogin = channelPointRedemptionEventBean.event.user_login;
    this.userName = channelPointRedemptionEventBean.event.user_name;
    this.userInput = channelPointRedemptionEventBean.event.user_input;
    this.status = channelPointRedemptionEventBean.event.status;
    this.rewardId = channelPointRedemptionEventBean.event.reward.id;
    this.rewardTitle = channelPointRedemptionEventBean.event.reward.title;
    this.rewardCost = channelPointRedemptionEventBean.event.reward.cost;
    this.rewardPrompt = channelPointRedemptionEventBean.event.reward.prompt;
    this.redeemedAt = channelPointRedemptionEventBean.event.redeemed_at;
  }
}
