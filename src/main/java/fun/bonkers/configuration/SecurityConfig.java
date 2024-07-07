package fun.bonkers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
		
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService getUserDetailsService() {
		return new LoginUserDetailsService();
	}
		
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	WebSecurityCustomizer webSecurity() {
		return (web) -> web.ignoring().requestMatchers(
				new AntPathRequestMatcher("/resources/**"), 
				new AntPathRequestMatcher("/static/**"), 
				new AntPathRequestMatcher("/css/**"),
				new AntPathRequestMatcher("/images/**"),
				new AntPathRequestMatcher("/js/**"),
				new AntPathRequestMatcher("/error"),
				new AntPathRequestMatcher("/database/**")	
		);
	}
	
	@Bean
	DaoAuthenticationProvider daoAuthenticationProviderprovider() {
		DaoAuthenticationProvider  provider = new DaoAuthenticationProvider ();
		provider.setUserDetailsService(getUserDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authz -> authz	
				.requestMatchers(
						new AntPathRequestMatcher("/shop/register"),
						new AntPathRequestMatcher("/database/**"),
						new AntPathRequestMatcher("/"), 
						new AntPathRequestMatcher("/shop/**")
						).permitAll()
				.requestMatchers(
						new AntPathRequestMatcher("/admin/**")
						).hasAuthority("ADMIN")
						.anyRequest()
						.authenticated()
						)
				.formLogin(form -> form
						.loginPage("/shop/loginn")
						.loginProcessingUrl("/userLogin")
						.defaultSuccessUrl("/shop/user/profile", true)
						.permitAll()
						)
				.logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/shop/logout"))
						.logoutSuccessUrl("/shop/loginn")
						.deleteCookies("JSESSIONID")
						.invalidateHttpSession(true)
						.permitAll());
		return http.build();
	}
}
