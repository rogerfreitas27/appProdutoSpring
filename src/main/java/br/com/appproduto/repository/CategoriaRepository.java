package br.com.appproduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.appproduto.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

}
