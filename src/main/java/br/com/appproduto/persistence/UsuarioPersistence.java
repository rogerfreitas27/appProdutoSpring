package br.com.appproduto.persistence;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import br.com.appproduto.model.Usuario;
import br.com.appproduto.repository.UsuarioRepository;

@Service
public class UsuarioPersistence {
	
private final UsuarioRepository ur;


@Autowired
private BCryptPasswordEncoder encoder;
	
	public UsuarioPersistence(UsuarioRepository ur) {
		this.ur = ur;
	}
	
	/**Método salva ou altera ou altera os dados do usuario
	 * Se o usuario passado não tiver id o usuario será salvo
	 * Caso o usuario tenha id o spring altera os dados
	 * @author Roger
	 * @param objeto Usuario  
	 * @return  retorna se foi salvo ou não
	 * @exception Eu deixo para a classe que chama(UsuarioRest) tratar
	 */
	public Usuario save(Usuario u) throws Exception{
		u.setSenha(encoder.encode(u.getSenha()));
		return ur.save(u);
	}
	
	public List<Usuario> findAll () throws Exception{
		return ur.findAll();
	}
	
	
	public void delete(Usuario u) throws Exception {
		ur.delete(u);
	}
	
	public Optional<Usuario> findById(Long id) throws Exception {
		 return ur.findById(id);
	}
	
	public Optional<Usuario> findByEmail(String email) {
		
	return  ur.findByEmail(email);
		
	}
	
	
	private int contadorUsuario()  throws Exception{
		int  cont = ur.findAll().size();
	return cont;
	}
	
	public int RespcontadorUsuario() throws Exception{
		return   contadorUsuario(); 
	}

}
