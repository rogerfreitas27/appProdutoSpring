package br.com.appproduto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.appproduto.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

	public Optional<Categoria> findByNome(Categoria c) ;
		
	
}
