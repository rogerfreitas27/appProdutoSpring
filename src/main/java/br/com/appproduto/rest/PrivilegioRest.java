package br.com.appproduto.rest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.appproduto.model.Privilegio;
import br.com.appproduto.persistence.PrivilegioPersistence;
import br.com.appproduto.util.Serializar;

@RestController
@RequestMapping("/api/privilegio")
public class PrivilegioRest {
	private final Serializar sr;
	private final PrivilegioPersistence pp;
	
	
	
	public PrivilegioRest(Serializar sr,PrivilegioPersistence pp) {
		this.sr=sr;
		this.pp=pp;
		}
	
	
	

	@GetMapping
	@PreAuthorize("hasAuthority('USUARIO_READ_PRIVILEGE')")
	public ResponseEntity<String> findAll(){
		
		
		List<Privilegio> privilegios = new ArrayList<>();
		try {
			privilegios = pp.findAll();
			if(privilegios.size()==0) {
				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há registros");
			}
			String dados = sr.privilegiosSerializar(privilegios);
			return  ResponseEntity.status(HttpStatus.OK).body(dados);
		} catch (Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao listar privilegios" + e.getMessage());
		}
		
	}

}
