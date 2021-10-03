package com.punagalabs.spring.security.demo.controller

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
    fun getProductList(): List<Product> {
        return products
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable("id") id: String): Product {
        return products.find { it.id == id }!!
    }

    @PostMapping //if object structure is not valid will eturn 400 bad req
    fun createProduct(@RequestBody product: Product) {
        print(String.format("%s %s", product, " created!"))
    }

    @DeleteMapping("{id}") // similar to using path vaiable
    fun deleteProduct(@PathVariable("id") id: String) {
        print(String.format("%s %s", products.find { it.id == id }, " deleted!!"))
    }

    @PutMapping("{id}")
    fun updateProduct(@PathVariable("id") id: String, @RequestBody product: Product) {
        print(String.format("%s %s %s", products.find { it.id == id }, " with ", " updated !!"))
    }

}