package br.com.appproduto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.appproduto.dto.UsuarioDto;
import br.com.appproduto.model.Privilegio;
import br.com.appproduto.model.Role;
import br.com.appproduto.model.User;
import br.com.appproduto.model.Usuario;
import br.com.appproduto.persistence.CategoriaPersistence;
import br.com.appproduto.persistence.MarcaPersistence;
import br.com.appproduto.persistence.PrivilegioPersistence;
import br.com.appproduto.persistence.ProdutoPersistence;
import br.com.appproduto.persistence.RolePersistence;
import br.com.appproduto.persistence.UsuarioPersistence;
import br.com.appproduto.security.JwtUtil;
import br.com.appproduto.util.Serializar;



/**
 * 
 * @author roger
 * Esta classe é uma auxiliar para a classe UsuarioRest
 *
 */
@Service
public class UsuarioService {
	private final UsuarioPersistence up ;
	private final Serializar sr;	
	private final JwtUtil jwtUtil;	
	private final PrivilegioPersistence pp;
	private final ProdutoPersistence prodp;
	private final CategoriaPersistence cp;
	private final MarcaPersistence mp;
	private final RolePersistence rl;
	
	
	
	public  UsuarioService(UsuarioPersistence up,Serializar sr,JwtUtil jwtUtil,
			PrivilegioPersistence pp,ProdutoPersistence prodp,CategoriaPersistence cp,
			MarcaPersistence mp,RolePersistence rl) {
		this.up=up;
		this.sr=sr;
		this.jwtUtil=jwtUtil;
		this.pp=pp;
		this.prodp=prodp;
		this.cp=cp;
		this.mp=mp;
		this.rl = rl;
	}
	
	
	
	//Este metodo carrega os dados para o dashboard e guardo no localStorage no front
	public String carregarUsuarioDto( User us,Usuario u) throws Exception {
		
    	 List<Privilegio> privilegios =  new ArrayList<>();
    	 privilegios= pp.findAll(us.getId());
    	  String token = jwtUtil.generateToken(u.getEmail());
    	  Role r = rl.findBy(u.getId());
        int quantidade_categoria = cp.RespcontadorCategoria();
        int quantidade_marca = mp.RespcontadorMarca();
        int quantidade_produto =  prodp.RespcontadorProduto();
        int quantidade_usuario = up.RespcontadorUsuario();
        
    	  UsuarioDto dto = new UsuarioDto(u.getId(),u.getNome(),u.getEmail(),"",
    			  privilegios, token,r,quantidade_categoria,quantidade_marca,
    			  quantidade_produto,quantidade_usuario);
    	  
    	  
    	  String dados=sr.UsuarioDtoSerializar(dto);
    	  return dados;
    	  
		
	}
	
	
	// Este metodo busca os dados do back para o formUsuario
	public String carregarFrontUsuarioDto( User us,Usuario u) throws Exception {
		
   	 List<Privilegio> privilegios =  new ArrayList<>();
   	 privilegios= pp.findAll(us.getId());
   	 Role r = rl.findBy(u.getId());
   	 UsuarioDto dto = new UsuarioDto(); 
       dto.setId(u.getId());
   	  dto.setNome(u.getNome());
   	 dto.setEmail(u.getEmail());
   	 dto.setPrivilegios(privilegios);
   	 dto.setRole(r);
   	
   	  
   	  
   	  String dados=sr.UsuarioDtoSerializar(dto);
   	  return dados;
   	  
		
	}

}
