package it.unisa.model;

import java.io.Serializable;

public class IncludeBean extends AbstractBean implements Serializable {
  private int questionario;
  private int question;
  
  public IncludeBean() {
    
  }
  
  public IncludeBean(int questionario, int question) {
    this.questionario = questionario;
    this.question = question;
  }

  public int getQuestionario() {
    return questionario;
  }

  public void setQuestionario(int questionario) {
    this.questionario = questionario;
  }

  public int getQuestion() {
    return question;
  }

  public void setQuestion(int question) {
    this.question = question;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("questionario=" + questionario + ", ");
    str.append("question=" + question + "}");
    return str.toString();
  }
}
