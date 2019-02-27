package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TutorBean extends AbstractBean implements Serializable, Cloneable {

  /**
   * 
   */
  private static final long serialVersionUID = 7743978606183893973L;
  private int id;
  private int convenzioneID;
  private String email;
  private String nome;
  private String pass;
  private String tipo;

  public TutorBean() {
    
  }
  
  public TutorBean(
      int id,
      int convenzioneID,
      String email,
      String nome,
      String pass,
      String tipo) {
    this.id = id;
    this.email = email;
    this.nome = nome;
    this.pass = pass;
    this.tipo = tipo;
    this.convenzioneID = convenzioneID;
  }
  
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public int getConvenzioneID() {
    return convenzioneID;
  }

  public void setConvenzioneID(int convenzioneID) {
    this.convenzioneID = convenzioneID;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "TutorBean [id=" + id + ", email=" + email + ", nome=" + nome + ", pass=" + pass + ", tipo=" + tipo
        + ", convenzioneID=" + convenzioneID + "]";
  }

  @Override
  public boolean equals(Object o) {
    
    if (o == null)
      return false;
    
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    
    TutorBean tutorBean = (TutorBean) o;
    
    if (
        id == tutorBean.getID() &&
        convenzioneID == tutorBean.getConvenzioneID() &&
        email.equals(tutorBean.getEmail()) &&
        nome.equals(tutorBean.getNome()) &&
        pass.equals(tutorBean.getPass()) &&
        tipo.equals(tutorBean.getTipo()))
      return true;
      
    return false;
  }

  @Override
  public TutorBean clone() throws CloneNotSupportedException {
    TutorBean tutorBean = null;
    
    try {
      tutorBean = (TutorBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    
    return tutorBean;
  }
}
