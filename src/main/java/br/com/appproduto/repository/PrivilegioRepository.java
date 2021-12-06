package br.com.appproduto.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import br.com.appproduto.model.Privilegio;


public interface PrivilegioRepository   extends JpaRepository<Privilegio,Long>{
	
	
	/**Método carregaros os privilegios do user  de acordo com o id passado
	 * @author Roger
	 * @param id  do user  
	 * @return  retorna uma lista com apenas o id e o nome do privilegio
	 */
	    @Query(value = "SELECT  privilegio.id as id, privilegio.nome as nome FROM  user_privilegios  inner join privilegio  on user_privilegios.privilegio_id=privilegio.id where user_privilegios.user_id=:id", nativeQuery = true)
			public List<Privilegio> findAll(Long id);
	    
	    
	    
	    
	    /**Método lista todos os privilegios existentes na tabela privilegios
		 * @author Roger
		 * @return  retorna uma lista com  o nome e id de  todos os privilegios
		 */
	    @Query(value = "SELECT  privilegio.id as id, privilegio.nome as nome FROM   privilegio", nativeQuery = true)
		public List<Privilegio> findAll();
}
