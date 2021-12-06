package br.com.appproduto.security;


import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.appproduto.model.User;
import br.com.appproduto.model.Usuario;
import br.com.appproduto.persistence.UserPersistence;
import br.com.appproduto.persistence.UsuarioPersistence;


/**Classe implementa uma busca customizada.
* @author Roger Freitas
* @version 1.01
* @since 
*/
@Component
public class DetalheClienteServiceImpl implements UserDetailsService {
	
	private final UsuarioPersistence usuarioPersistence;
	private final UserPersistence  userPersistence;
	
	public DetalheClienteServiceImpl(UsuarioPersistence usuarioPersistence,UserPersistence  userPersistence) {
		this.userPersistence = userPersistence;
		this.usuarioPersistence = usuarioPersistence;
	}

	@Override
	/**Método carregar informações do usuario (Permissões)
	 * 
	 * @author Roger
	 * @param um identificador unico do usuario 
	 * @return  User do Spring Security
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario ;
		Optional<User> user ;
	
		usuario = usuarioPersistence.findByEmail(username);
		
		if(usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuário não encontrado [" +
					  username + "] não encontrado");
		}
		Usuario u = new Usuario();
		u = usuario.get();
		user = userPersistence.findByUsuario(u);
		
		if(user.isEmpty() ) {
			throw new UsernameNotFoundException("Usuário não encontrado" );
					 
		}
		
		User us = new User();
		us = user.get();
		
		  return new org.springframework.security.core.userdetails.User(u.getEmail(),u.getSenha(), us.getAutorities());
	}

}
