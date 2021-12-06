package br.com.appproduto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class User implements Serializable {
	
	
	
private static final long serialVersionUID = 2422373020592189539L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	 @JoinColumn(name = "usuario", referencedColumnName = "id")
	 @OneToOne
	 @JsonIgnore 
     private Usuario usuario;
	
	 
	 @ManyToMany(fetch = FetchType.LAZY)	
     @JoinTable( 
         name = "USER_ROLES", 
         joinColumns = @JoinColumn(
           name = "user_id", referencedColumnName = "id"), 
         inverseJoinColumns = @JoinColumn(
           name = "role_id", referencedColumnName = "id")) 
	 	
		 private List<Role> roles = new ArrayList<>();
     
	 
     @ManyToMany(fetch = FetchType.EAGER)
	     @JoinTable(
             name = "USER_PRIVILEGIOS", 
             joinColumns = @JoinColumn(
                     name = "user_id", referencedColumnName = "id"), 
             inverseJoinColumns = @JoinColumn(
                     name = "privilegio_id", referencedColumnName = "id")) 
    
      private List<Privilegio> privilegios = new ArrayList<>();
     
     
     @Transient
     private Collection<GrantedAuthority> authorities = new ArrayList<>();
     
     
     
     
     
     
   
     
     @Override
	public String toString() {
		return "User [id=" + id + ", usuario=" + usuario + ", roles=" + roles + ", privilegios=" + privilegios
				+ ", authorities=" + authorities + "]";
	}



	public User() {
    	 
     }
     
     
     
     public User(Long id, Usuario usuario) {
		
		this.id = id;
		this.usuario = usuario;
	}
     
     
     
     
     

	public User(Long id, Usuario usuario, List<Role> roles, List<Privilegio> privilegios) {
		
		this.id = id;
		this.usuario = usuario;
		this.roles = roles;
		this.privilegios = privilegios;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<GrantedAuthority> getAutorities() {
                
      
         for (Privilegio privilege : this.privilegios) {
             authorities.add(new SimpleGrantedAuthority(privilege.getNome()));
         }
         
         for (Role role : this.roles) {
             authorities.add(new SimpleGrantedAuthority(role.getNome()));
             for (Privilegio privilege : role.getPrivilegios()) {
                 authorities.add(new SimpleGrantedAuthority(privilege.getNome()));
             }
         }
         
         return authorities;
     }
	
	
	
	
	public void setAutorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
     
     public void addPrivilege(Privilegio privilage) {
         this.privilegios.add(privilage);
     }
     
     public void addRole(Role role) {
         this.roles.add(role);
     }
	 
	

}
