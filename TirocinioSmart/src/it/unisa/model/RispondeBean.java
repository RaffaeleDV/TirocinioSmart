package it.unisa.model;

import java.io.Serializable;

public class RispondeBean extends AbstractBean implements Serializable {
  private int quest;
  private int choose;
  
  public RispondeBean() {
    
  }
  
  public RispondeBean(int quest, int choose) {
    this.quest = quest;
    this.choose = choose;
  }

  public int getQuest() {
    return quest;
  }

  public void setQuest(int quest) {
    this.quest = quest;
  }

  public int getChoose() {
    return choose;
  }

  public void setChoose(int choose) {
    this.choose = choose;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("quest=" + quest + ", ");
    str.append("choose=" + choose + "}");
    return str.toString();
  }
}
