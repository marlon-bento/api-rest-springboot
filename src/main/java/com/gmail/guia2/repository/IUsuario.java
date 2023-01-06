package com.gmail.guia2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmail.guia2.model.Usuario;

public interface IUsuario extends JpaRepository<Usuario, Integer>{
	
	Usuario findByEmailIs(String email);

	public Usuario findBynomeOrEmail(String nome, String email);

}
