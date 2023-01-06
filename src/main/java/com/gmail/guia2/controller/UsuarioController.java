package com.gmail.guia2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.gmail.guia2.arq.ArquivoMestre;
import com.gmail.guia2.service.EmailSenderService;
import com.gmail.guia2.security.Token;
import com.gmail.guia2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.guia2.dto.UsuarioDto;
import com.gmail.guia2.model.Usuario;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

	private UsuarioService usuarioService;
	@Autowired
	private EmailSenderService senderService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> listaUsuarios() throws Exception {
		ArquivoMestre arq = new ArquivoMestre();

		arq.escreveNoArq("Foi usada a função de listar usuarios");
		return ResponseEntity.status(200).body(usuarioService.listarUsuario());
	}


	@PostMapping("/cadastro")
	public String criarUsuario(@Valid @RequestBody Usuario usuario) throws Exception {
		boolean verifica = usuarioService.verifica(usuario);
		ArquivoMestre arq = new ArquivoMestre();

		arq.escreveNoArq("Foi usada a função de cadastrar novo usuario");
		if(verifica == true){
			senderService.sendEmail(usuario.getEmail(), "Cadastro realizado com sucesso",
					"Parabens, você realizou seu cadastro com sucesso com o email: " + usuario.getEmail() + "     \ne senha:  " + usuario.getSenha());
			ResponseEntity.status(201).body(usuarioService.criarUsuario(usuario));
			return "Parabens " + usuario.getNome() + " você foi cadastrado com sucesso verifique seu email "+ usuario.getEmail();
		}else{
			return "O usuario já existe na base de dados";
		}
	}

	@PutMapping
	public ResponseEntity<Usuario> editarUsuario(@Valid @RequestBody Usuario usuario) throws Exception {
		ArquivoMestre arq = new ArquivoMestre();

		arq.escreveNoArq("Foi usada a função de editar usuario");
		return ResponseEntity.status(200).body(usuarioService.editarUsuario(usuario));
	}

	@DeleteMapping("/{id}")
	public String excluirUsuario(@PathVariable Integer id) throws Exception {
		boolean verifica = usuarioService.excluirUsuario(id);
		ArquivoMestre arq = new ArquivoMestre();

		arq.escreveNoArq("Foi usada a função de deletar usuario");
		if(verifica == true){
			return "usuario deletado";
		}else {
			return "usuario não encontrado para deletar";
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Token> logar(@Valid @RequestBody UsuarioDto usuario) throws Exception {
		ArquivoMestre arq = new ArquivoMestre();

		arq.escreveNoArq("Foi usada a função de fazer login na api");
		Token token = usuarioService.gerarToken(usuario);
		if (token != null) {
			return ResponseEntity.ok(token);
		}
		return ResponseEntity.status(403).build();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) throws Exception {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
			
		});
		return errors;
	}
}
