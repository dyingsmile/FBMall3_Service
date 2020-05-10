package com.excmmy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import pojo.MallConstant;

@Configuration
public class ResourceServerConfig {

    @Autowired
    TokenStore tokenStore;

    // UAA资源服务配置
    @Configuration
    @EnableResourceServer
    public class UAAServerConfig extends ResourceServerConfigurerAdapter {
        @Autowired
        private TokenStore tokenStore;
        @Override
        public void configure(ResourceServerSecurityConfigurer resources){
            resources.tokenStore(tokenStore).resourceId(MallConstant.ORDERS_RESOURCE_ID)
                    .stateless(true);
        }
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    // 访问UAA的请求不需要拦截
                    .antMatchers("/uaa/**").permitAll();
        }
    }

    // Order资源配置
    @Configuration
    @EnableResourceServer
    public class OrderServerConfig extends ResourceServerConfigurerAdapter {
        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources){
            resources.tokenStore(tokenStore).resourceId(MallConstant.ORDERS_RESOURCE_ID)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/order/r/**").access("#oauth2.hasScope('P3')");
        }
    }


}