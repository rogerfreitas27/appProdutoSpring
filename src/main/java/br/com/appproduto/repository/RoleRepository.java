package br.com.appproduto.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import br.com.appproduto.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
	
	
	
	
	/**Método carregaros os roles do user  de acordo com o id passado
	 * @author Roger
	 * @param id  do user  
	 * @return  retorna uma lista com apenas o id e o nome do role
	 */
	 @Query(value = "SELECT  role.id as id, role.nome as nome FROM  user_roles  inner join role  on user_roles.role_id=role.id where user_roles.user_id=:id", nativeQuery = true)
		public Role findBy(Long id);
	
}
