package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

public class IncludeBean extends AbstractBean implements Serializable {
  
  private int questionarioID;
  private int questionID;
  
  public IncludeBean() {
    
  }
  
  public IncludeBean(int questionarioID, int questionID) {
    this.questionarioID = questionarioID;
    this.questionID = questionID;
  }

  public int getQuestionarioID() {
    return questionarioID;
  }

  public void setQuestionarioID(int questionarioID) {
    this.questionarioID = questionarioID;
  }

  public int getQuestionID() {
    return questionID;
  }

  public void setQuestionID(int questionID) {
    this.questionID = questionID;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "[");
    str.append("questionarioID=" + questionarioID + ", ");
    str.append("questionID=" + questionID + "]");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    
    if (o == null)
      return false;
    
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    
    IncludeBean includeBean = (IncludeBean) o;
    
    if (
        questionarioID == includeBean.getQuestionarioID() &&
        questionID == includeBean.getQuestionID())
      return true;
    
    return false;
  }
  
  @Override
  public IncludeBean clone() throws CloneNotSupportedException {
    IncludeBean includeBean = null;
    
    try {
      includeBean = (IncludeBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    
    return includeBean;
  }
}
