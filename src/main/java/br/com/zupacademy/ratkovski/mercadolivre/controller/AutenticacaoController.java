package br.com.zupacademy.ratkovski.mercadolivre.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.ratkovski.mercadolivre.config.security.TokenConfig;
import br.com.zupacademy.ratkovski.mercadolivre.dto.LoginForm;
import br.com.zupacademy.ratkovski.mercadolivre.dto.TokenDto;

@RestController
@RequestMapping("/auth")
//@Profile(value ={"prod", "test"}) // pode passarr um ou varios profiles @Profile("prod")
//@Profile(value ="usuarios")
public class AutenticacaoController {
	
	//precisa desta classe  ela é do spring mas não consegue fazer injecao de dependencia automaticamente
	//habilita na securityConfigurations
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenConfig tokenConfig;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			//não pode passar dados senha e emai aberto ai usa cod acima
			Authentication authentication = authManager.authenticate(dadosLogin);
			
			//gerar o token para n ficar jogado criar outra
			String token = tokenConfig.gerarToken(authentication);
			
			//System.out.println(token);
		
			//para testar se está chegando e-mail e senha
			//System.out.println(form.getEmail());
			//System.out.println(form.getSenha());
			//return ResponseEntity.ok().build();
			//Bearer é o tipo de autenticacao
			
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	
		
	}

}
