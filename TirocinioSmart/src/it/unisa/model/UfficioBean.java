package it.unisa.model;

import java.io.Serializable;

public class UfficioBean extends UtenteBean implements Serializable, Cloneable {

  /**
  * 
  */
  private static final long serialVersionUID = -3117662707685361390L;
  private int id;
  private String email;
  private String nome;
  private String pass;

  public UfficioBean() {

  }

  public UfficioBean(int id, String nome, String pass) {
    this.id = id;
    this.nome = nome;
    this.pass = pass;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getPass() {
    return pass;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "[");
    str.append("id=" + id + ", ");
    str.append("email=" + email + ", ");
    str.append("nome=" + nome + ", ");
    str.append("pass=" + pass + "]");
    return str.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (!getClass().getName().equals(other.getClass().getName()))
      return false;
    UfficioBean ufficioBean = (UfficioBean) other;
    if (id == ufficioBean.getId() && nome.equals(ufficioBean.getNome())
        && pass.equals(ufficioBean.getPass()) &&  email.equals(ufficioBean.getEmail()))
      return true;
    return false;
  }

  @Override
  public UfficioBean clone() throws CloneNotSupportedException {
    UfficioBean other = null;
    try {
      other = (UfficioBean) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return other;
  }
}
