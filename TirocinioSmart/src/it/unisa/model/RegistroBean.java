package it.unisa.model;

import java.io.Serializable;

public class RegistroBean extends AbstractBean implements Serializable, Cloneable {

  /**
  * 
  */
  private static final long serialVersionUID = -2505094168799389057L;
  private int id;
  private String nome;
  private String descrizione;

  public RegistroBean() {

  }

  public RegistroBean(int id, String nome, String descrizione) {
    this.id = id;
    this.nome = nome;
    this.descrizione = descrizione;
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

  public String getDescrizione() {
    return descrizione;
  }


  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "[");
    str.append("id=" + id + ", ");
    str.append("nome=" + nome + ", ");
    str.append("descrizione=" + descrizione);
    return str.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (!getClass().getName().equals(other.getClass().getName()))
      return false;
    RegistroBean registroBean = (RegistroBean) other;
    if (id == registroBean.getId() && nome.equals(registroBean.getNome())
        && descrizione.equals(registroBean.getDescrizione()))
      return true;
    return false;
  }

  @Override
  public RegistroBean clone() throws CloneNotSupportedException {
    RegistroBean other = null;
    try {
      other = (RegistroBean) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return other;
  }
}
