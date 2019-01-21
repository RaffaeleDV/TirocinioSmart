package it.unisa.model;

import java.io.Serializable;

public class QuestionarioBean extends AbstractBean implements Serializable {

  private int id;
  private int quests;
  private int nutenti;
  private String nome;
  private String description;
  private String tematica;
  
  public QuestionarioBean() {
    
  }
  
  public QuestionarioBean(int id, int quests, int nutenti, String description, String nome, String tematica) {
    this.id = id;
    this.quests = quests;
    this.nutenti = nutenti;
    this.description = description;
    this.nome = nome;
    this.tematica = tematica;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getQuests() {
    return quests;
  }

  public void setQuests(int quests) {
    this.quests = quests;
  }
  
  public int getNutenti() {
    return nutenti;
  }
  
  public void setNutenti(int nutenti) {
    this.nutenti = nutenti;
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
  
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("id=" + id + ", ");
    str.append("quests=" + quests + ", ");
    str.append("nutenti=" + nutenti + ", ");
    str.append("nome=" + nome + ", ");
    str.append("description=" + description + ", ");
    str.append("tematica=" + tematica + "}");
    return str.toString();
  }
}
