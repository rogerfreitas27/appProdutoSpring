package br.com.appproduto.persistence;



import java.util.List;
import org.springframework.stereotype.Service;


import br.com.appproduto.model.Privilegio;
import br.com.appproduto.repository.PrivilegioRepository;

@Service
public class PrivilegioPersistence {

	
	private final PrivilegioRepository pr;
	
	public PrivilegioPersistence (PrivilegioRepository pr ) {
		this.pr=pr;
	}
	
	public  List<Privilegio> findAll( Long id) {		
		return pr.findAll(id);
	}
	
	public  List<Privilegio> findAll() {		
		return pr.findAll();
	}
}
