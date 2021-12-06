package br.com.appproduto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

@Entity
public class Role  implements Serializable{

	
	
	private static final long serialVersionUID = 1702938777543739067L;	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Size(min = 3, max = 20, message 
		    = "O campo nome só pode ter entre 3 e 20 caracteres")
	@Column(unique = true)
    private String nome;
     
     @ManyToMany(mappedBy = "roles")
         private Collection<User> users = new ArrayList<>();
     
     
     
     @ManyToMany(fetch = FetchType.EAGER)
      @JoinTable(
             name = "ROLES_PRIVILEGIOS", 
             joinColumns =  @JoinColumn(name = "role_id", referencedColumnName = "id")  , 
             inverseJoinColumns = @JoinColumn(
                    name = "privilegio_id", referencedColumnName = "id")) 
    
     private List<Privilegio> privilegios = new ArrayList<>();
     
     public void addPrivilege(Privilegio
    		 privilege) {
         this.privilegios.add(privilege);
     }
     
     public void addUser(User user) {
         this.users.add(user);
     }

	public Role(Long id, String nome, Collection<User> usuarios, List<Privilegio> privilegios) {
	
		this.id = id;
		this.nome = nome.toUpperCase();
		this.users = usuarios;
		this.privilegios = privilegios;
	}

	public Role() {
	
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", nome=" + nome + ", user=" + users + ", privilegios=" + privilegios + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public Collection<User> getUser() {
		return users;
	}

	public void setUsuarios(Collection<User> users) {
		this.users = users;
	}

	public List<Privilegio> getPrivilegios() {
		return privilegios;
	}

	public void setPrivilegios(List<Privilegio> privilegios) {
		this.privilegios = privilegios;
	}
	
	
	
   
	
	
     
	
     
     
	

}
