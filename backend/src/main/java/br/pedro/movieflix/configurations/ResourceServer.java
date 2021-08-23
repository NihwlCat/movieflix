package br.pedro.movieflix.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    private static final String[] PUBLIC = {"/oauth/token"};
    private static final String[] VISITOR = {"/movies/**", "/genres/**"};
    private static final String[] MEMBER = {"/reviews/**"};

    @Override
    public void configure(ResourceServerSecurityConfigurer resources){
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET,VISITOR).hasAnyRole("VISITOR","MEMBER")
                .antMatchers(HttpMethod.POST,MEMBER).hasRole("MEMBER");
    }
}
