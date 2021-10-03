package com.punagalabs.spring.security.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

/**
 * @project inaina.id
 * @author ziahq
 */

@RestController
@RequestMapping("/api/users")
class UserController {

    val users = listOf(
            User("1", "A"),
            User("2", "B"),
            User("3", "C")
    )


    @GetMapping("/list")
    fun getListUser(): List<User> {
        return users
    }

    @GetMapping("/{id}")
    fun getuser(@PathVariable("id") id: String): User {
      return users.find { it.id== id }!!
    }
}