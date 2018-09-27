package it.unisa.model;

import java.io.Serializable;

public class TutorBean extends AbstractBean implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7743978606183893973L;
  private int id;
  private String nome;
  private String pass;
  private String tipo;
  private int convenzioneID;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "TutorBean [id=" + id + ", nome=" + nome + ", pass=" + pass + ", tipo=" + tipo
        + ", convenzioneID=" + convenzioneID + "]";
  }



}
