package com.github.sh0ckr6.hardcoretwitchintegration.beans;

public class EventSubNotificationBean {
  public EventSubSubscriptionBean subscription;
  
  @Override
  public String toString() {
    return "EventSubNotificationBean{" +
           "subscription=" + subscription +
           '}';
  }
  
  public static class EventSubSubscriptionBean {
    public String id;
    public String type;
  
    @Override
    public String toString() {
      return "EventSubSubscriptionBean{" +
             "id='" + id + '\'' +
             ", type='" + type + '\'' +
             '}';
    }
  }
}
