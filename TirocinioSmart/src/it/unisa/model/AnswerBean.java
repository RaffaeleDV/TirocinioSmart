package it.unisa.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.logging.Logger;
import java.util.logging.Level;

public class AnswerBean extends AbstractBean implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  private int questionID;
  private int utenteID;
  private int chooseID;
  private String tipoUtente;
  private Date answerDate;
  
  public AnswerBean() {
    super();
  }
  
  public AnswerBean(
      int questionID,
      int utenteID, 
      int chooseID,
      String tipoUtente,
      Date answerDate) {
    super();
    this.questionID = questionID;
    this.utenteID = utenteID;
    this.chooseID = chooseID;
    this.tipoUtente = tipoUtente;
    this.answerDate = answerDate;
  }

  public Date getAnswerDate() {
    return answerDate;
  }
  
  public void setAnswerDate(Date answerDate) {
    this.answerDate = answerDate;
  }
  
  public String getTipoUtente() {
    return tipoUtente;
  }
  
  public void setTipoUtente(String tipoUtente) {
    this.tipoUtente = tipoUtente;
  }
  
  public int getChooseID() {
    return chooseID;
  }
  
  public void setChooseID(int chooseID) {
    this.chooseID = chooseID;
  }
  
  public int getUtenteID() {
    return utenteID;
  }
  
  public void setUtenteID(int utenteID) {
    this.utenteID = utenteID;
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
    str.append(getClass().getName() + "{");
    str.append("questionID=" + questionID + ", ");
    str.append("utenteID=" + utenteID + ", ");
    str.append("chooseID=" + chooseID + ", ");
    str.append("tipoUtente=" + tipoUtente + ", ");
    str.append("answerDate=" + answerDate.toString() + "}");
    return str.toString();
  }
  
   @Override
   public boolean equals(Object o) {
     if (o == null)
       return false;
     if (!getClass().getName().equals(o.getClass().getName()))
       return false;
     AnswerBean answerBean = (AnswerBean) o;
     if (questionID == answerBean.getQuestionID() &&
         utenteID == answerBean.getUtenteID() &&
         chooseID == answerBean.getChooseID() &&
         tipoUtente.equals(answerBean.getTipoUtente()) &&
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
