package it.unisa.model;

import java.io.Serializable;

public class QuestionBean extends AbstractBean implements Serializable {

  private int id;
  private int max_chooses;
  private int max_answers;
  private String question;
  private String description;
  
  public QuestionBean() {
    
  }
  
  public QuestionBean(int id, String question, String description, int max_chooses, int max_answers) {
    this.id = id;
    this.question = question;
    this.description = description;
    this.max_chooses = max_chooses;
    this.max_answers = max_answers;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public int getMax_chooses() {
    return max_chooses;
  }

  public void setMax_chooses(int max_chooses) {
    this.max_chooses = max_chooses;
  }

  public int getMax_answers() {
    return max_answers;
  }

  public void setMax_answers(int max_answers) {
    this.max_answers = max_answers;
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("id=" + id + ", ");
    str.append("question=" + question + ", ");
    str.append("description=" + description + ", ");
    str.append("max_chooses=" + max_chooses + ", ");
    str.append("max_answers=" + max_answers + "}");
    return str.toString();
  }
}
