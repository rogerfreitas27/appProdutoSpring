package br.com.appproduto.persistence;


import java.util.Optional;
import org.springframework.stereotype.Service;

import br.com.appproduto.model.User;
import br.com.appproduto.model.Usuario;
import br.com.appproduto.repository.UserRepository;


@Service
public class UserPersistence {
	
	private final UserRepository userRepository;
	
	public UserPersistence(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	public Optional<User> findByUsuario(Usuario usuario){
		Optional<User> user;
		
		  user=  userRepository.findByUsuario(usuario);
		 
		
		 
		  
		return user;
	}

}
