package VingadoresDoYahoo.HoraMarcada.security;

import static VingadoresDoYahoo.HoraMarcada.models.RoleType.ADMIN;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import VingadoresDoYahoo.HoraMarcada.services.UsuarioService;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioService usuarioService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/cadastroConsumidor", "/cadastroPrestador", 
                        "/perfilConsumidor", "/perfilPrestador",
                        "/exibicaoPrestador", "/avaliacaoPrestador", "/agendamentosPrestador", "/formServico",
                        "/novoAgendamento", "/salvarAgendamento",
                        "/fotos/**", "/css/**", "/js/**", "/fontawesome/**").permitAll()
            .antMatchers("/lista").hasRole(ADMIN.name())
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll().defaultSuccessUrl("/", true)
                .passwordParameter("senha")
                .usernameParameter("email")
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // https://docs.spring.io/spring-security/site/docs/4.2.12.RELEASE/apidocs/org/springframework/security/config/annotation/web/configurers/LogoutConfigurer.html
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login");
    }

    
     @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .withUser("admin@teste").password(passwordEncoder.encode("password")).roles("ADMIN")
        .and()
        .withUser("consumidor@teste").password(passwordEncoder.encode("password")).roles("CONSUMIDOR");

        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(usuarioService);
        return provider;
    }

}