package br.com.appproduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.appproduto.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca,Long> {

}
