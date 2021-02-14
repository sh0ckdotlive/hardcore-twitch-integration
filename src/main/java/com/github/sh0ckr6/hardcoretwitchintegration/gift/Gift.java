package com.github.sh0ckr6.hardcoretwitchintegration.gift;

import java.util.ArrayList;
import java.util.List;

public class Gift<T> {
  public List<T> items = new ArrayList<>();
  public int tier;
  
  public Gift(int tier) {
    this.tier = tier;
  }
  public Gift(int tier, List<T> items) {
    this.tier = tier;
    this.items = items;
  }
  
  public void add(T objectToAdd) {
    items.add(objectToAdd);
  }
}
