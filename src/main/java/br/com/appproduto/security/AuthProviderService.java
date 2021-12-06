package br.com.appproduto.security;

import org.springframework.security.authentication.AuthenticationProvider;

import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import br.com.appproduto.dto.UsuarioDto;
import br.com.appproduto.model.User;
import br.com.appproduto.model.Usuario;
import br.com.appproduto.persistence.UserPersistence;
import br.com.appproduto.persistence.UsuarioPersistence;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.slf4j.Logger;






/**Classe para realizar a autenticação.
* @author Roger Freitas
* @version 1.01
* @since 
*/
@Component
public class AuthProviderService  implements AuthenticationProvider {

	private final UsuarioPersistence up;
   private final UserPersistence userPersistence;
	 
	 
	   private final BCryptPasswordEncoder encoder;
	   private final Logger log = LoggerFactory.getLogger(AuthProviderService.class);
	
	   
	 

	public AuthProviderService(UsuarioPersistence up, BCryptPasswordEncoder encoder,UserPersistence userPersistence) {
		
		this.up = up;
		this.encoder = encoder;
		this.userPersistence=userPersistence;
	}

	
	
	
	
	/**Método para autenticar
	 * fto para viagem.
	 * @author Roger
	 * @param Usuario autenticada (Que é uma classse usuario dto)
	 * @return  UsernamePasswordAuthenticationToken - que é um usuario autenticado com login, senha e a lista de permissões
	 */
	public Authentication authenticate(UsuarioDto authentication) throws Exception {
		String login = authentication.getEmail();
	      String senha = authentication.getSenha();
	    
	      Optional<Usuario> usuario = up.findByEmail(login);
	      Usuario u = new Usuario();
	    
	      
	      if(usuario.isEmpty()) {
	    	 
	    	  throw new UsernameNotFoundException("Login e/ou Senha inválidos.1");
	      }
	      u = usuario.get();
	      
	      Optional<User> user = userPersistence.findByUsuario(u);
	      
	      if(user.isEmpty()) {
		    	
	    	  throw new UsernameNotFoundException("Login e/ou Senha inválidos.2");
	      }
	      
	      User us = new User();
	      us = user.get();
	      
	      					// senha digitada pelo usuario,senha do banco
	      if (encoder.matches(senha,u.getSenha())) {
	    	  System.out.println("Senhas conferem");
	    	  return new UsernamePasswordAuthenticationToken(u.getEmail(), u.getSenha(), us.getAutorities());
	    	   
	    	} else {
	    		log.warn("Error senhas não conferem");
	    		
	    		 throw new UsernameNotFoundException("Login e/ou Senha inválidos.3");
	    	}
	    
	}




	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
}
