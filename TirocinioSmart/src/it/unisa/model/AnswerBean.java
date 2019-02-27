package it.unisa.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.logging.Logger;
import java.util.logging.Level;

public class AnswerBean extends AbstractBean implements Serializable, Cloneable {
  
  private int questionID;
  private int studenteID;
  private int chooseID;
  private Date answerDate;
  
  public AnswerBean() {
    
  }
  
  public AnswerBean(
      int questionID,
      int studenteID, 
      int chooseID, 
      Date answerDate) {
    this.questionID = questionID;
    this.studenteID = studenteID;
    this.chooseID = chooseID;
    this.answerDate = answerDate;
  }

  public int getQuestionID() {
    return questionID;
  }

  public void setQuestionID(int questionID) {
    this.questionID = questionID;
  }

  public int getStudenteID() {
    return studenteID;
  }

  public void setStudenteID(int studenteID) {
    this.studenteID = studenteID;
  }

  public int getChooseID() {
    return chooseID;
  }

  public void setChooseID(int chooseID) {
    this.chooseID = chooseID;
  }

  public Date getAnswerDate() {
    return answerDate;
  }

  public void setAnswerDate(Date answerDate) {
    this.answerDate = answerDate;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "[");
    str.append("questionID=" + questionID + ", ");
    str.append("studenteID=" + studenteID + ", ");
    str.append("chooseID=" + chooseID + ", ");
    str.append("answerDate=" + answerDate.toString() + "]");
    return str.toString();
  }
  
   @Override
   public boolean equals(Object o) {
     
     if (o == null)
       return false;
     
     if (!getClass().getName().equals(o.getClass().getName()))
       return false;
     
     AnswerBean answerBean = (AnswerBean) o;
     
     if (
         questionID == answerBean.getQuestionID() &&
         studenteID == answerBean.getStudenteID() &&
         chooseID == answerBean.getChooseID() &&
         answerDate.equals(answerBean.getAnswerDate()))
       return true;
     
     return false;
   }
   
   @Override
   public AnswerBean clone() throws CloneNotSupportedException {
     AnswerBean answerBean = null;
     
     try {
       answerBean = (AnswerBean) super.clone();
     } catch (CloneNotSupportedException e) {
       Logger.getGlobal().log(Level.SEVERE, e.getMessage());
     }
     
     return answerBean;
   }
}
