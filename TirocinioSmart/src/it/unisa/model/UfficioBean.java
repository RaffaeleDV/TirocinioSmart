
import java.io.Serializable;

public class UfficioBean extends AbstractBeans implements Serializable, Cloneable{

	private int id;
	private String nome;
	private String password;
	
	public UfficioBean() {
		
	}
	
	public UfficioBean(int id, String nome, String password) {
		this.id = id;
		this.nome = nome;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(getClass().getName() + "[");
		str.append("id="+id+", ");
		str.append("nome="+nome+", ");
		str.append("password="+password+"]");
		return str.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null)
			return false;
		if(!getClass().getName().equals(other.getClass().getName()))
			return false;
		UfficioBean ufficioBean = (UfficioBean)other;
		if(id == ufficioBean.getId() && nome.equals(ufficioBean.getNome()) && password.equals(ufficioBean.getPassword()))
			return true;
		return false;
	}
	
	@Override
	public UfficioBean clone() throws CloneNotSupportedException{
		UfficioBean other = null;
		try {
			other = (UfficioBean)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return other;
	}
}
