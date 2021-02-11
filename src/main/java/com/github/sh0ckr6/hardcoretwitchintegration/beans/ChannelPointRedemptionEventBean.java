package com.github.sh0ckr6.hardcoretwitchintegration.beans;

public class ChannelPointRedemptionEventBean {
  public ChannelPointRedemptionBean event;
  
  public static class ChannelPointRedemptionBean {
    public String id;
    public String broadcaster_user_id;
    public String broadcaster_user_login;
    public String broadcaster_user_name;
    public String user_id;
    public String user_login;
    public String user_name;
    public String user_input;
    public String status;
    public ChannelPointRedemptionRewardBean reward;
    public String redeemed_at;
  
    @Override
    public String toString() {
      return "ChannelPointRedemptionBean{" +
             "id='" + id + '\'' +
             ", broadcasterUserId='" + broadcaster_user_id + '\'' +
             ", broadcaster_user_login='" + broadcaster_user_login + '\'' +
             ", broadcaster_user_name='" + broadcaster_user_name + '\'' +
             ", user_id='" + user_id + '\'' +
             ", user_login='" + user_login + '\'' +
             ", user_name='" + user_name + '\'' +
             ", user_input='" + user_input + '\'' +
             ", status='" + status + '\'' +
             ", reward=" + reward +
             ", redeemed_at='" + redeemed_at + '\'' +
             '}';
    }
  }
  
  public static class ChannelPointRedemptionRewardBean {
    public String id;
    public String title;
    public int cost;
    public String prompt;
  
    @Override
    public String toString() {
      return "ChannelPointRedemptionRewardBean{" +
             "id='" + id + '\'' +
             ", title='" + title + '\'' +
             ", cost=" + cost +
             ", prompt='" + prompt + '\'' +
             '}';
    }
  }
  
  @Override
  public String toString() {
    return "ChannelPointRedemptionEventBean{" +
           "channelPointRedemptionBean=" + event +
           '}';
  }
}
