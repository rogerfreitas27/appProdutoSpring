package br.com.appproduto.persistence;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appproduto.model.Role;
import br.com.appproduto.repository.RoleRepository;

@Service
public class RolePersistence {
	private final RoleRepository rol;
	
	public  RolePersistence (RoleRepository rol)  {
		this.rol=rol;
	}
	
	
	
	public Role save(Role p) throws Exception{
		return rol.save(p);
	}
	
	public List<Role> findAll () throws Exception{
		return rol.findAll();
	}
	
	
	public void delete(Role p) throws Exception {
		rol.delete(p);
	}
	
	public Optional<Role> findById(Long id) throws Exception {
		 return rol.findById(id);
	}
	
	
	/**
	 * 
	 * 	  @param id Este id aqui é o do usuario, aqui eu busco a roles dele
	 * @return devolvo a role com id e nome
	 * @throws Exception
	 */
	public Role findBy(Long id) throws Exception{
		return rol.findBy(id);
	}
	
	

}
