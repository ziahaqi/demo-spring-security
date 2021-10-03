package com.punagalabs.spring.security.demo.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

/**
 * @project inaina.id
 * @author ziahq
 */

@RestController
@RequestMapping("/api/products")
class ProductController {

    val products = listOf(
            Product("1", "Product A"),
            Product("2", "Product B"),
            Product("3", "Product C")
    )


    @GetMapping("/list", "/") // "" includeing "/", but "/" not including ""
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OTHER')")
    fun getProductList(): List<Product> {
        return products
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable("id") id: String): Product {
        return products.find { it.id == id }!!
    }

    @PostMapping //if object structure is not valid will eturn 400 bad req
    @PreAuthorize("hasAnyAuthority('product:write')")
    fun createProduct(@RequestBody product: Product) {
        print(String.format("%s %s", product, " created!"))
    }

    @DeleteMapping("{id}") // similar to using path vaiable
    @PreAuthorize("hasAnyAuthority('product:write')")
    fun deleteProduct(@PathVariable("id") id: String) {
        print(String.format("%s %s", products.find { it.id == id }, " deleted!!"))
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('product:write')")
    fun updateProduct(@PathVariable("id") id: String, @RequestBody product: Product) {
        print(String.format("%s %s %s", products.find { it.id == id }, " with ", " updated !!"))
    }

}