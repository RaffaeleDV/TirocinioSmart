package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudenteBean extends AbstractBean implements Serializable, Cloneable {
  private static final long serialVersionUID = -7363690702555462451L;
  private int id;
  private String matricola;
  private String nome;
  private String cfu;
  private String pass;
  private String occupazione;
  private int tutorAccID;
  private int tutorAzID;
  private int progettoFormativoID;
  private int registroID;

  public StudenteBean() {
    super();
  }

  public StudenteBean(
      int id,
      String matricola, 
      String pass, 
      String nome, 
      String cfu,
      String occupazione,
      int tutorAccID,
      int tutorAzID,
      int progettoFormativoID,
      int registroID) {
    super();
    this.id = id;
    this.matricola = matricola;
    this.pass = pass;
    this.nome = nome;
    this.cfu = cfu;
    this.occupazione = occupazione;
    this.tutorAccID = tutorAccID;
    this.tutorAzID = tutorAzID;
    this.progettoFormativoID = progettoFormativoID;
    this.registroID = registroID;
  }

  public void setOccupazione(String occupazione) {
    this.occupazione = occupazione;
  }
  
  public String getOccupazione() {
    return occupazione;
  }
  
  public int getID() {
    return id;
  }
  
  public void setID(int id) {
    this.id = id;
  }
  
  public String getMatricola() {
    return matricola;
  }

  public void setMatricola(String matricola) {
    this.matricola = matricola;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String password) {
    this.pass = password;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCfu() {
    return cfu;
  }

  public void setCfu(String cfu) {
    this.cfu = cfu;
  }

  public int getTutorAccID() {
    return tutorAccID;
  }

  public void setTutorAccID(int tutorAccID) {
    this.tutorAccID = tutorAccID;
  }

  public int getProgettoFormativoID() {
    return progettoFormativoID;
  }
  
  public void setProgettoFormativoID(int progettoFormativoID) {
    this.progettoFormativoID = progettoFormativoID;
  }

  public int getRegistroID() {
    return registroID;
  }
  
  public void setRegistroID(int registroID) {
    this.registroID = registroID;
  }
  
  public int getTutorAzID() {
    return tutorAzID;
  }

  public void setTutorAzID(int tutorAzID) {
    this.tutorAzID = tutorAzID;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("id=" + id + ", ");
    str.append("matricola=" + matricola + ", ");
    str.append("nome=" + nome + ", ");
    str.append("cfu=" + cfu + ", ");
    str.append("pass=" + pass + ", ");
    str.append("occupazione=" + occupazione + ", ");
    str.append("tutorAccID=" + tutorAccID + ", ");
    str.append("tutorAzID=" + tutorAzID + ", ");
    str.append("progettoFormativoID=" + progettoFormativoID + ", ");
    str.append("registroID=" + registroID + "}");
    return str.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (!getClass().getName().equals(other.getClass().getName()))
      return false;
    StudenteBean studenteBean = (StudenteBean) other;
    if (id == studenteBean.getID() &&
        matricola.equals(studenteBean.getMatricola()) && 
        pass.equals(studenteBean.getPass()) && 
        nome.equals(studenteBean.getNome()) && 
        cfu.equals(studenteBean.getCfu()) &&
        occupazione.equals(studenteBean.getOccupazione()) &&
        tutorAccID == studenteBean.getTutorAccID() &&
        tutorAzID == studenteBean.getTutorAzID() &&
        registroID == studenteBean.getRegistroID() &&
        progettoFormativoID == studenteBean.getProgettoFormativoID())
      return true;
    return false;
  }

  @Override
  public StudenteBean clone() throws CloneNotSupportedException {
    StudenteBean other = null;
    try {
      other = (StudenteBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return other;
  }
}
