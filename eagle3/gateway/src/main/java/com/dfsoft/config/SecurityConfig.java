package com.dfsoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.dfsoft.handler.AuthenticationFaillHandler;
import com.dfsoft.handler.AuthenticationSuccessHandler;
import com.dfsoft.handler.EntryPointUnauthorizedHandler;
import com.dfsoft.handler.RestAccessDeniedHandler;
import com.dfsoft.manange.JWTReactiveAuthorizationManager;
import com.dfsoft.service.impl.RoleInfoServiceImpl;


/**
 * 启用webflux登陆权限校验 
 */
@EnableWebFluxSecurity
public class SecurityConfig {
	
	@Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;//认证成功处理
    @Autowired
    private AuthenticationFaillHandler authenticationFaillHandler;//认证失败处理
	
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
    @Autowired
    private RestAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private RoleInfoServiceImpl roleInfoService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    
	//security的鉴权排除列表
    private static final String[] excludedAuthPages = {
            "/auth/login",
            "/auth/**",
            "/auth/logout",
            "/health",
            "/api/socket/**",
            "/favicon.ico", 
            "/*.html", 
            "/**/*.html", 
            "/**/*.css", 
            "/**/*.js", 
            "/actuator/**", 
            "/consumer/**", 
            "/con-zuul/**", 
            "/data-service1/**", 
            "/coc/**",
            "/oauth/**",
            "/login/**", 
            "/logout"
    };
    
    
    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
    	//RedirectServerAuthenticationEntryPoint loginPoint = new RedirectServerAuthenticationEntryPoint("/auth/login");
    	
    	http.httpBasic().and()
            .formLogin()
            .authenticationSuccessHandler(authenticationSuccessHandler) //认证成功
            .loginPage("/auth/login")//.authenticationEntryPoint(loginPoint)
        	.authenticationFailureHandler(authenticationFaillHandler) //登陆验证失败
            .and().authorizeExchange().pathMatchers(excludedAuthPages).permitAll()
            .and().authorizeExchange().anyExchange().access(new JWTReactiveAuthorizationManager(roleInfoService,redisTemplate))
            //.and().authorizeExchange().anyExchange().authenticated()
            .and().csrf().disable()// 由于使用的是JWT，我们这里不需要csrf 
            .logout().disable()
            .exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler).accessDeniedHandler(accessDeniedHandler);
        return http.build();
    }
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
