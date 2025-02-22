package xfactor.org.app.aop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // ejemplo mas basico de una configuracion Java de Spring Security

    // Como sabe Spring Security que queremos exigir que todos los usuarios estan autenticados ?
    // Como sabe Spring Security que queremos admitir la autenticacion basada en formularios ?
    // Configuracion en Segundo Plano configuracion Predeterminada
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //  VARIABLES
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName("continue");

        return http
        //  EL CSRF ES UNA PROTECCION QUE DEBE ESTAR HABILITADA EN EL PUNTO DE PROTECCION
                .csrf().disable()
        //
                .authorizeHttpRequests(
                        authorize -> {
                            authorize.requestMatchers("/api/").permitAll();
                        //  authorize.requestMatchers("/api/session").permitAll();
                            authorize.requestMatchers("/api/session").hasAnyRole("ADMIN", "STAFF");

                            authorize.anyRequest().authenticated();
                        }
                )
                // GUARDAR SESSION
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .invalidSessionUrl("/login")
                        .maximumSessions(1) // => < 2 para utilizar en multiplataforma
                        .expiredUrl("/login")
                        .sessionRegistry(sessionRegistry()) // <<<<---
                        .and()
                        // CUANDO HAY UNA ATAQE DE SESION -> GENERA OTRO ID DE SESION [newSession]
                        .sessionFixation().migrateSession()
                )
//  GUARDAR CACHE
                .requestCache((cache) -> cache
                        .requestCache(requestCache)
                )
//  PUERTA LOGIN A LA API - DEFECTO
                .formLogin(form -> form
//  --->        .loginPage("/login")  //  MI PROPIO LOGIN
                          .successHandler(successHandler())
                          .permitAll()
                )
//  AUTENTICACION DE CABECERA POR DEFECTO PARA LECTURA
                .httpBasic(withDefaults())
                .build();
    }

    //    GUARDANDO DATOS DE LA SESSION REGISTRADA
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

//  ENCRYPTAR CONTRASEÑA
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//  REDIRIGIENDO A LA PAGINA DESPUES DE PASAR EL LOGIN
    public AuthenticationSuccessHandler successHandler() {
        return (((request, response, authentication) -> {
            response.sendRedirect("/api/Home");
        }));
    }

}
