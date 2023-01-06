package com.gmail.guia2.service;

import java.util.List;

import javax.validation.Valid;

import com.gmail.guia2.security.Token;
import com.gmail.guia2.security.TokenUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gmail.guia2.dto.UsuarioDto;
import com.gmail.guia2.model.Usuario;
import com.gmail.guia2.repository.IUsuario;

@Service
public class UsuarioService {

	private IUsuario repository;
	private PasswordEncoder passwordEncoder;

	public UsuarioService(IUsuario repository) {
		this.repository = repository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	public List<Usuario> listarUsuario() {
		List<Usuario> lista = repository.findAll();
		return lista;
	}

	public Usuario criarUsuario(Usuario usuario) {
		String encoder = this.passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		Usuario usuarioNovo = repository.save(usuario);
		return usuarioNovo;
	}

	public Usuario editarUsuario(Usuario usuario) {
		String encoder = this.passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		Usuario usuarioNovo = repository.save(usuario);
		return usuarioNovo;
	}
	public boolean verifica(Usuario usuario) {
		List<Usuario> lista= this.listarUsuario();
		boolean verifica = false;
		for(int i = 0; i< lista.size(); i++){
			if (lista.get(i).getEmail().equals(usuario.getEmail())) {
				verifica = true;
				//email encontrado fim do loop
				break;
			}
		}
		if (verifica == false){
			return true;
		}
		else{
			return false;
		}

	}
	public Boolean excluirUsuario(Integer id) {
		repository.deleteById(id);
		return true;
	}

	public Boolean validarSenha(Usuario usuario) {
		String senha = repository.getById(usuario.getId()).getSenha();
		Boolean valid = passwordEncoder.matches(usuario.getSenha(), senha);
		return valid;
	}

	public Token gerarToken(@Valid UsuarioDto usuario) {
		Usuario user = repository.findBynomeOrEmail(usuario.getNome(), usuario.getEmail());
		if (user != null) {
			Boolean valid = passwordEncoder.matches(usuario.getSenha(), user.getSenha());
			if (valid) {
				return new Token(TokenUtil.createToken(user));
			}
		}
		return null;
	}
}