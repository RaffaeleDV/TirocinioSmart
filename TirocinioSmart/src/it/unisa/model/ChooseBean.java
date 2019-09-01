package it.unisa.model;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ChooseBean extends AbstractBean implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  private int id;
  private String description;
  private String tipo;
  
  public ChooseBean() {
    super();
  }
  
  public ChooseBean(
      int id,
      String description,
      String tipo) {
    super();
    this.id = id;
    this.description = description;
    this.tipo = tipo;
  }

  public int getID() {
    return id;
  }

  public void setID(int id) {
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
  
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "{");
    str.append("id=" + id + ", ");
    str.append("description=" + description + ", ");
    str.append("tipo=" + tipo + "}");
    return str.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (!getClass().getName().equals(o.getClass().getName()))
      return false;
    ChooseBean chooseBean = (ChooseBean) o;
    if (id == chooseBean.getID() &&
        description.equals(chooseBean.getDescription()) &&
        tipo.equals(chooseBean.getTipo()))
      return true;
    return false;
  }
  
  @Override
  public ChooseBean clone() throws CloneNotSupportedException {
    ChooseBean chooseBean = null;
    try {
      chooseBean = (ChooseBean) super.clone();
    } catch(CloneNotSupportedException e) {
      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    }
    return chooseBean;
  }
}
