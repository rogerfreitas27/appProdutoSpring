package br.com.appproduto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import br.com.appproduto.model.User;
import br.com.appproduto.model.Usuario;

public interface UserRepository extends JpaRepository<User,Long> {

	
	//@Query(value = "SELECT new br.com.appproduto.security.User(user.id,usuario)"
	//		+ " from User user INNER JOIN Usuario usuario on usuario.id=user.usuario "
	//		+ "   WHERE  user.usuario=:idUsuario")
	//public Optional<User> findByUsuario(@Param("idUsuario")Long usuario);
	public Optional<User> findByUsuario(Usuario usuario);
		
	
	
}
