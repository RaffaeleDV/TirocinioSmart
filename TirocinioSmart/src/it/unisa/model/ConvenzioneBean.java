package it.unisa.model;

import java.io.Serializable;

public class ConvenzioneBean extends AbstractBean implements Serializable, Cloneable {

  /**
  * 
  */
  private static final long serialVersionUID = 2768300536554372790L;
  private int id;
  private String info;

  public ConvenzioneBean() {

  }

  public ConvenzioneBean(int id, String info) {
    this.id = id;
    this.info = info;
  }

  public void setId(int id) {
    this.id = id;
  }
  
  public int getId() {
    return id;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(getClass().getName() + "[");
    str.append("id=" + id + ", ");
    str.append("info=" + info + "]");
    return str.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (!getClass().getName().equals(other.getClass().getName()))
      return false;
    ConvenzioneBean convenzioneBean = (ConvenzioneBean) other;
    if (id == convenzioneBean.getId() && info.equals(convenzioneBean.getInfo()))
      return true;
    return false;
  }

  @Override
  public ConvenzioneBean clone() throws CloneNotSupportedException {
    ConvenzioneBean other = null;
    try {
      other = (ConvenzioneBean) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return other;
  }
}
