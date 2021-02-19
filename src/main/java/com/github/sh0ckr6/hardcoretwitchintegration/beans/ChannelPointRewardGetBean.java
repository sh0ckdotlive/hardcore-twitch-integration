package com.github.sh0ckr6.hardcoretwitchintegration.beans;

import java.util.ArrayList;
import java.util.List;

public class ChannelPointRewardGetBean {
  public List<ChannelPointRewardBean> data = new ArrayList<>();
  
  @Override
  public String toString() {
    return "ChannelPointRewardGetBean{" +
           "data=" + data +
           '}';
  }
  public static class ChannelPointRewardBean {
    public String id;
    public String title;
    public String prompt;
    public int cost;
    public String background_color;
    public boolean is_enabled;
    public boolean is_user_input_required;
    public boolean is_paused;
  
    @Override
    public String toString() {
      return "ChannelPointRewardBean{" +
             "id='" + id + '\'' +
             ", title='" + title + '\'' +
             ", prompt='" + prompt + '\'' +
             ", cost=" + cost +
             ", background_color='" + background_color + '\'' +
             ", is_enabled=" + is_enabled +
             ", is_user_input_required=" + is_user_input_required +
             ", is_paused=" + is_paused +
             '}';
    }
  }
}
