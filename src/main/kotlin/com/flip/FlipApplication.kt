package com.flip

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FlipApplication

fun main(args: Array<String>) {
	runApplication<FlipApplication>(*args)
}
