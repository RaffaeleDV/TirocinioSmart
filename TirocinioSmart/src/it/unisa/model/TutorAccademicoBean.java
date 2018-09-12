
import java.io.Serializable;

public class TutorAccademicoBean extends AbstractBean implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String password;
	
	public TutorAccademicoBean() {
		
	}
	
	public TutorAccademicoBean(int id, String nome, String password) {
		this.id  = id;
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
		str.append("password="+password+"]");
		return str.toString();
	}
}
