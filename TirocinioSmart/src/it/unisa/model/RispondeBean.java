package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

public class RispondeBean extends AbstractBean implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  private int questionID;
  private int chooseID;
  
  public RispondeBean() {
    super();
  }
  
  public RispondeBean(
      int questionID,
      int chooseID) {
    super();
    this.questionID = questionID;
    this.chooseID = chooseID;
  }

  public int getQuestionID() {
    return questionID;
  }

  public void setQuestionID(int questionID) {
    this.questionID = questionID;
  }

  public int getChooseID() {
    return chooseID;
  }

  public void setChooseID(int chooseID) {
    this.chooseID = chooseID;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("questionID=" + questionID + ", ");
    str.append("chooseID=" + chooseID + "}");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    RispondeBean rispondeBean = (RispondeBean) o;
    if (questionID == rispondeBean.getQuestionID() &&
        chooseID == rispondeBean.getChooseID())
      return true;
    return false;
  }
  
  @Override
  public RispondeBean clone() throws CloneNotSupportedException {
    RispondeBean rispondeBean = null;
    try {
      rispondeBean = (RispondeBean) super.clone();
    } catch(CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return rispondeBean;
  }
}
