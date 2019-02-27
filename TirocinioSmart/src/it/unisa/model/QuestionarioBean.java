package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

public class QuestionarioBean extends AbstractBean implements Serializable, Cloneable {
  
  private int id;
  private int questions;
  private int nstudenti;
  private String nome;
  private String description;
  private String tematica;
  
  public QuestionarioBean() {
    
  }
  
  public QuestionarioBean(
      int id,
      int questions,
      int nstudenti,
      String nome, 
      String description,
      String tematica) {
    this.id = id;
    this.questions = questions;
    this.nstudenti = nstudenti;
    this.description = description;
    this.nome = nome;
    this.tematica = tematica;
  }

  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public int getQuestions() {
    return questions;
  }

  public void setQuestions(int questions) {
    this.questions = questions;
  }
  
  public int getNstudenti() {
    return nstudenti;
  }
  
  public void setNstudenti(int nstudenti) {
    this.nstudenti = nstudenti;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTematica() {
    return tematica;
  }

  public void setTematica(String tematica) {
    this.tematica = tematica;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "[");
    str.append("id=" + id + ", ");
    str.append("questions=" + questions + ", ");
    str.append("nstudenti=" + nstudenti + ", ");
    str.append("nome=" + nome + ", ");
    str.append("description=" + description + ", ");
    str.append("tematica=" + tematica + "]");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    
    if (o == null)
      return false;
    
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    
    QuestionarioBean questionarioBean = (QuestionarioBean) o;
    
    if (
        id == questionarioBean.getID() &&
        questions == questionarioBean.getQuestions() &&
        nstudenti == questionarioBean.getNstudenti() &&
        nome.equals(questionarioBean.getNome()) &&
        description.equals(questionarioBean.getDescription()) &&
        tematica.equals(questionarioBean.getTematica()))
      return true;
    
    return false;
  }
  
  @Override
  public QuestionarioBean clone() throws CloneNotSupportedException {
    QuestionarioBean questionarioBean = null;
    
    try {
      questionarioBean = (QuestionarioBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    
    return questionarioBean;
  }
}
