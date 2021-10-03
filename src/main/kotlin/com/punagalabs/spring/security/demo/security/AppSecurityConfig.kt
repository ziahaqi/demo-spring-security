package com.punagalabs.spring.security.demo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

/**
 * @project inaina.id
 * @author ziahq
 */

@Configuration
@EnableWebSecurity
class AppSecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.csrf()?.disable()
                ?.authorizeRequests()?.antMatchers("/")?.permitAll()
//                ?.antMatchers("/api/users/**")?.hasRole(UserRole.USER.name)
                ?.antMatchers(HttpMethod.DELETE, "/api/products/**")?.hasAuthority(UserPermission.PRODUCT_WRITE.permission)
                ?.antMatchers(HttpMethod.POST, "/api/products/**")?.hasAuthority(UserPermission.PRODUCT_WRITE.permission)
                ?.antMatchers(HttpMethod.PUT, "/api/products/**")?.hasAuthority(UserPermission.PRODUCT_WRITE.permission)
                ?.antMatchers(HttpMethod.GET, "/api/products/**")?.hasAuthority(UserPermission.PRODUCT_WRITE.permission)
                ?.antMatchers(HttpMethod.GET, "/api/users/**")?.hasAnyRole(UserRole.ADMIN.name, UserRole.USER.name)
                ?.anyRequest()?.authenticated()
                ?.and()?.httpBasic()
    }


    @Bean
    override fun userDetailsService(): UserDetailsService {

        val userdetails = User.builder()
                .username("user")
                .password("{noop}password")
                .authorities(UserRole.USER.authorities())
//                .roles(UserRole.USER.name)
                .build()

        val admin = User.builder()
                .username("admin")
                .password("{noop}password")
                .authorities(UserRole.ADMIN.authorities())
//                .roles(UserRole.ADMIN.name)
                .build()

        return InMemoryUserDetailsManager(listOf(userdetails, admin))
    }
}