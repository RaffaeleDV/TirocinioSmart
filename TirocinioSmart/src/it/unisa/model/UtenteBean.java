package it.unisa.model;

import java.io.Serializable;

public class UtenteBean extends AbstractBean implements Serializable, Cloneable {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String nome;
  private String password;
  
  public UtenteBean() {
    
  }
  
  public UtenteBean(String nome, String password) {
    this.nome = nome;
    this.password = password;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("nome=" + nome + ", ");
    str.append("password=" + password + "}");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    
    if (!getClass().getName().equals(other.getClass().getName()))
      return false;
    
    UtenteBean utente = (UtenteBean) other;
    if (utente.getNome().equals(nome) && password.equals(utente.getPassword()))
      return true;
    
    return false;
  }
  
  @Override
  public UtenteBean clone() throws CloneNotSupportedException {
    UtenteBean utente = null;
    
    try {
      utente = (UtenteBean) super.clone();
    } catch(CloneNotSupportedException e) {
      e.printStackTrace();
    }
    
    return utente;
  }
}
