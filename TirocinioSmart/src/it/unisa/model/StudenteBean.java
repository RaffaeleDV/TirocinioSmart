package it.unisa.model;

import java.io.Serializable;

public class StudenteBean extends AbstractBean implements Serializable, Cloneable {

  private static final long serialVersionUID = -7363690702555462451L;
  
  private String matricola;
  private String password;
  private String nome;
  private String cfu;
  
  public StudenteBean() {
	  
  }
  
  public StudenteBean(
		  String matricola, 
		  String password, 
		  String nome, 
		  String cfu) {
	  this.matricola = matricola;
	  this.password = password;
	  this.nome = nome;
	  this.cfu = cfu;
  }
  
  public String getMatricola() {
	  return matricola;
  }
  
  public String getPassword() {
	  return password;
  }
  
  public String getNome() {
	  return nome;
  }
  
  public String getCfu() {
	  return cfu;
  }
    
  public void setMatricola(String matricola) {
	  this.matricola = matricola;
  }
  
  public void setPassword(String password) {
	  this.password = password;
  }
  
  public void setNome(String nome) {
	  this.nome = nome;
  }
  
  public void setCfu(String cfu) {
	  this.cfu = cfu;
  }
  
  @Override
  public String toString() {
	  StringBuilder str = new StringBuilder();
	  str.append(getClass().getName() + "[");
	  str.append("matricola="+matricola+", ");
	  str.append("password="+password+", ");
	  str.append("nome="+nome+", ");
	  str.append("cfu="+cfu+"]");
	  return str.toString();
  }
  
  @Override
  public boolean equals(Object other) {
	  if(other == null)
		  reutrn false;
	  if(!getClass().getName().equals(other.getClass().getName()))
		  return false;
	  StudenteBean studenteBean = (StudenteBean)other;
	  if(matricola.equals(studentebean.getMatricola()) &&
		 password.equals(studenteBean.getPassword()) &&
		 nome.equals(studenteBean.getNome()) &&
		 cfu.equals(studenteBean.getCfu()))
		  return true;
	  return false;
  }
  
  @Override
  public StudenteBean clone() throws CloneNotSupportedException{
	  StudenteBean other = null;
	  try {
		  other = (StudenteBean)super.clone();		  
	  }catch(CloneNotSupportedException e) {
		  e.printStackTrace();
	  }
	  return other;
  }
}
