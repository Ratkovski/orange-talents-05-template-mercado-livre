package br.com.zupacademy.ratkovski.mercadolivre.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.zupacademy.ratkovski.mercadolivre.repository.UsuarioRepository;

@EnableWebSecurity//habilita o modo segurança
@Configuration
//@Profile("prod")//so vai carregar se o profile for de producao
//@Profile(value ={"prod", "test"}) // pode passarr um ou varios profiles @Profile("prod")
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private Autenticacao autenticacao;
	
	@Autowired
	private TokenConfig token;
	@Autowired
	private UsuarioRepository  usuarioRepository ;
	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	//configuracoes de autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.userDetailsService(autenticacao).passwordEncoder(new BCryptPasswordEncoder());
		
	}
	
	
	//configuracoes de autorizacao quem pode acessar oq url
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
		.antMatchers("/auth").permitAll()
		.antMatchers("/usuarios/**").permitAll()
		.antMatchers("/categorias").permitAll()
		.antMatchers("/produtos").permitAll()
		.anyRequest().authenticated()//qualquer outra requisicao precisa estar autenticada
		.and().csrf().disable()//ataque mas quando usa token automaticamente desconsidera ai desabilita
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//metodo para dizer q n q usar sessao
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(token,usuarioRepository), UsernamePasswordAuthenticationFilter.class);
		/**and().formLogin();  //spring já tem um formulario padrao*/ //tira este para colocar token
		
		//como tira o form também perde o controller para criar a parte de autenticacao ai cria no controler
		//autenticacaocontroller
	}
	
	
	//configuracoes de recuros estaticos(css,js,imagems,etc)
	@Override
	public void configure(WebSecurity web) throws Exception {

		    web.ignoring()
		        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
		
	
	}

	//so pra gerar a senha
	//public static void main(String[] args ) 
	//System.out.println(new BCryptPasswordEncoder().encode("123456"));
		
	//}
}
