package it.unisa.model;

import java.io.Serializable;

public class ChooseBean extends AbstractBean implements Serializable {

  private int id;
  private String description;
  private String tipo;
  
  public ChooseBean() {
    
  }
  
  public ChooseBean(int id, String description, String tipo) {
    this.id = id;
    this.description = description;
    this.tipo = tipo;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
  
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("id=" + id + ", ");
    str.append("description=" + description + ", ");
    str.append("tipo=" + tipo + "}");
    return str.toString();
  }
}
