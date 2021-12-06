package br.com.appproduto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.appproduto.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

	
	public Optional<Produto> findByNomeIgnoreCase(String nome);
}
