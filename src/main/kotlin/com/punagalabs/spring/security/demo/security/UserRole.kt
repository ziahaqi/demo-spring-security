package com.punagalabs.spring.security.demo.security

import org.springframework.security.core.authority.SimpleGrantedAuthority

/**
 * @project inaina.id
 * @author ziahq
 */

enum class UserRole(val permissoins: Set<UserPermission>) {
    USER(hashSetOf<UserPermission>()),
    ADMIN(hashSetOf<UserPermission>(
            UserPermission.USER_READ,
            UserPermission.USER_WRITE,
            UserPermission.PRODUCT_READ,
            UserPermission.PRODUCT_WRITE
    ));


    fun authorities(): List<SimpleGrantedAuthority> {
        return permissoins
                .map { SimpleGrantedAuthority(it.permission) }
                .toMutableList()
                .apply {
                    add(SimpleGrantedAuthority("ROLE_${this@UserRole}"))
                }
    }

}