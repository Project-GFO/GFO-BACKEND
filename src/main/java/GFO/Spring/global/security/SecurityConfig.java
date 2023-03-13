package GFO.Spring.global.security;

import GFO.Spring.global.filter.JwtRequestFilter;
import GFO.Spring.global.security.handler.CustomAccessDeniedHandler;
import GFO.Spring.global.security.handler.CustomAuthenticationEntryPointHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig  {
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable();

        httpSecurity
                // auth
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/signup", "/auth").permitAll()
                .antMatchers(HttpMethod.DELETE, "/auth").permitAll()
                .antMatchers(HttpMethod.PATCH, "/auth").permitAll()

                // post
                .antMatchers(HttpMethod.POST, "/post").authenticated()
                .antMatchers(HttpMethod.PATCH, "post/{id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "post/{id}").authenticated()

                // email
                .antMatchers(HttpMethod.POST, "/email").permitAll()
                .antMatchers(HttpMethod.HEAD, "/email").permitAll()

                // image
                .antMatchers(HttpMethod.POST, "/image").authenticated()

                // comment
                .antMatchers(HttpMethod.POST, "/comment/{id}").authenticated()
                .antMatchers(HttpMethod.PATCH, "/comment/{id}").authenticated()
                .anyRequest().authenticated();

        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(new CustomAuthenticationEntryPointHandler());

        httpSecurity
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}