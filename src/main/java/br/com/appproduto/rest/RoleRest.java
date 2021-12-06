package br.com.appproduto.rest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.appproduto.model.Role;
import br.com.appproduto.persistence.RolePersistence;
import br.com.appproduto.util.Serializar;

@RestController
@RequestMapping("/api/role")
public class RoleRest {
	
	
	private final RolePersistence rolp ;
	private final Serializar sr;
	public RoleRest(RolePersistence rolp, Serializar sr) {
		
		this.rolp = rolp;
		this.sr = sr;
	}
	
	
	
	
	@GetMapping("/buscarTodosPorCliente")
	public ResponseEntity<String> listarTodos(){
		
		try {
			String dados = sr.permissoesSerializar(rolp.findAll());
			return  ResponseEntity.status(HttpStatus.OK).body(dados);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao processas solicitação");
		}
		
	}
	
	
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('USUARIO_READ_PRIVILEGE')")
	public ResponseEntity<String> findAll(){
		
		
		List<Role> roles = new ArrayList<>();
		try {
			roles = rolp.findAll();
			if(roles.size()==0) {
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há registros");
			}
			String dados = sr.rolesSerializar(roles);
			return  ResponseEntity.status(HttpStatus.OK).body(dados);
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao listar roles" + e.getMessage());
		}
		
	}
	
	
	
	

}
