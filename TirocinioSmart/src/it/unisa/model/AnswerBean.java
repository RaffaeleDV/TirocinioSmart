package it.unisa.model;

import java.io.Serializable;
import java.sql.Date;

public class AnswerBean extends AbstractBean implements Serializable {
  private int quest;
  private int utente;
  private int choose;
  private Date a_date;
  
  public AnswerBean() {
    
  }
  
  public AnswerBean(int quest, int utente, int choose, Date a_date) {
    this.quest = quest;
    this.utente= utente;
    this.choose = choose;
    this.a_date = a_date;
  }

  public int getQuest() {
    return quest;
  }

  public void setQuest(int quest) {
    this.quest = quest;
  }

  public int getUtente() {
    return utente;
  }

  public void setUtente(int utente) {
    this.utente = utente;
  }

  public int getChoose() {
    return choose;
  }

  public void setChoose(int choose) {
    this.choose = choose;
  }

  public Date getA_date() {
    return a_date;
  }

  public void setA_date(Date a_date) {
    this.a_date = a_date;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("quest=" + quest + ", ");
    str.append("utente=" + utente + ", ");
    str.append("choose=" + choose + ", ");
    str.append("a_date=" + a_date.toString() + "}");
    return str.toString();
  }
}
