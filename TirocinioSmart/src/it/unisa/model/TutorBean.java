package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TutorBean extends AbstractBean implements Serializable, Cloneable {
  private static final long serialVersionUID = 7743978606183893973L;
  private int id;
  private String email;
  private String nome;
  private String pass;
  private String tipo;

  public TutorBean() {
    super();
  }
  
  public TutorBean(
      int id,
      String email,
      String nome,
      String pass,
      String tipo) {
    super();
    this.id = id;
    this.email = email;
    this.nome = nome;
    this.pass = pass;
    this.tipo = tipo;
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

  public int getID() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("id=" + id + ", ");
    str.append("email=" + email + ", ");
    str.append("nome=" + nome + ", ");
    str.append("pass=" + pass + ", ");
    str.append("tipo=" + tipo + "}");
    return str.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    TutorBean tutorBean = (TutorBean) o;
    if (id == tutorBean.getID() &&
        email.equals(tutorBean.getEmail()) &&
        nome.equals(tutorBean.getNome()) &&
        pass.equals(tutorBean.getPass()) &&
        tipo.equals(tutorBean.getTipo()))
      return true;
    return false;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    TutorBean tutorBean = null;
    try {
      tutorBean = (TutorBean) super.clone();
    } catch (CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return tutorBean;
  }
}
