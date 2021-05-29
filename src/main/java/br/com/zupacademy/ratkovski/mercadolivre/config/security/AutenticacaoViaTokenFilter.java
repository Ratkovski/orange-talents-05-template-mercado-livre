package br.com.zupacademy.ratkovski.mercadolivre.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.ratkovski.mercadolivre.modelo.Usuario;
import br.com.zupacademy.ratkovski.mercadolivre.repository.UsuarioRepository;


//OncePerRequestFilter filtro do spring ham,ado uma unica vez a cada requisicao
//pra funcionar precisa add securityConfigurationn

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter{

	private TokenConfig tokenConfig;

	private UsuarioRepository repository;
	//como não consegue injetar precisa passar para o construtor
	
	public AutenticacaoViaTokenFilter(TokenConfig tokenConfig, UsuarioRepository repository) {
		super();
		this.tokenConfig = tokenConfig;
		this.repository = repository;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {


		String token = recuperarToken(request);
		//System.out.println(token);
		
		boolean valido = tokenConfig.isTokenValido(token);
		if(valido) {
			autenticarCliente(token);
			
		}
		//System.out.println(valido);
		
		 filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		// Considere que esteja autenticado
		//dentro do token tem o id de usuario
		Long idUsuario = tokenConfig.getIdUsuario(token);
		Usuario usuario = repository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);		;
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty()|| !token.startsWith("Bearer ")) {
		return null;
	}
return token.substring(7, token.length());//pq do 7 numeros +o espaco
	}
}
