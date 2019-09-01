package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

public class QuestionBean extends AbstractBean implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  private int id;
  private int maxChooses;
  private int maxAnswers;
  private String question;
  private String description;
  
  public QuestionBean() {
    super();
  }
  
  public QuestionBean(
      int id, 
      int maxChooses, 
      int maxAnswers, 
      String question, 
      String description) {
    super();
    this.id = id;
    this.question = question;
    this.description = description;
    this.maxChooses = maxChooses;
    this.maxAnswers = maxAnswers;
  }

  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getMaxChooses() {
    return maxChooses;
  }

  public void setMaxChooses(int maxChooses) {
    this.maxChooses = maxChooses;
  }

  public int getMaxAnswers() {
    return maxAnswers;
  }

  public void setMaxAnswers(int maxAnswers) {
    this.maxAnswers = maxAnswers;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("id=" + id + ", ");
    str.append("maxChooses=" + maxChooses + ", ");
    str.append("maxAnswers=" + maxAnswers + ", ");
    str.append("question=" + question + ", ");
    str.append("description=" + description + "}");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    QuestionBean questionBean = (QuestionBean) o;
    if (id == questionBean.getID() &&
        maxChooses == questionBean.getMaxChooses() &&
        maxAnswers == questionBean.getMaxAnswers() &&
        question.equals(questionBean.getQuestion()) &&
        description.equals(questionBean.getDescription()))
      return true;
    return false;
  }
  
  @Override
  public QuestionBean clone() throws CloneNotSupportedException {
    QuestionBean questionBean = null;
    try {
      questionBean = (QuestionBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return questionBean;
  }
}
