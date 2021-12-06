package br.com.appproduto.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import br.com.appproduto.model.Privilegio;
import br.com.appproduto.model.Role;



/**Classe usada para tarnsporte dos dados e uso ela no angular no localStorage
 * para armazenar dados e privilegios do usuario.
* @author Roger Freitas
* @version 1.01
* @since 
*/
public class UsuarioDto implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String senha;	
	private List<Privilegio> privilegios = new ArrayList<>();
	private String token;
	private Role role;
    private int quantidade_categoria;
    private int quantidade_marca;
    private int quantidade_produto;
    private int quantidade_usuario;
	
	public UsuarioDto() {
		
	}

	public UsuarioDto(Long id, String nome, String email, String senha, List<Privilegio> privilegios, String token,
			Role role, int quantidade_categoria, int quantidade_marca, int quantidade_produto, int quantidade_usuario) {
		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.privilegios = privilegios;
		this.token = token;
		this.role = role;
		this.quantidade_categoria = quantidade_categoria;
		this.quantidade_marca = quantidade_marca;
		this.quantidade_produto = quantidade_produto;
		this.quantidade_usuario = quantidade_usuario;
	}

	@Override
	public String toString() {
		return "UsuarioDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", privilegios="
				+ privilegios + ", token=" + token + ", role=" + role + ", quantidade_categoria=" + quantidade_categoria
				+ ", quantidade_marca=" + quantidade_marca + ", quantidade_produto=" + quantidade_produto
				+ ", quantidade_usuario=" + quantidade_usuario + "]";
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
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Privilegio> getPrivilegios() {
		return privilegios;
	}

	public void setPrivilegios(List<Privilegio> privilegios) {
		this.privilegios = privilegios;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getQuantidade_categoria() {
		return quantidade_categoria;
	}

	public void setQuantidade_categoria(int quantidade_categoria) {
		this.quantidade_categoria = quantidade_categoria;
	}

	public int getQuantidade_marca() {
		return quantidade_marca;
	}

	public void setQuantidade_marca(int quantidade_marca) {
		this.quantidade_marca = quantidade_marca;
	}

	public int getQuantidade_produto() {
		return quantidade_produto;
	}

	public void setQuantidade_produto(int quantidade_produto) {
		this.quantidade_produto = quantidade_produto;
	}

	public int getQuantidade_usuario() {
		return quantidade_usuario;
	}

	public void setQuantidade_usuario(int quantidade_usuario) {
		this.quantidade_usuario = quantidade_usuario;
	}
	
	
	
	
	

	
	
	

	
	

}
