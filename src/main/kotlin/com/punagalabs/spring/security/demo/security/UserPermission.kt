package com.punagalabs.spring.security.demo.security

/**
 * @project inaina.id
 * @author ziahq
 */

enum class UserPermission(val permission: String) {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write")
}