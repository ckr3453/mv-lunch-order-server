package kr.movements.lunchorder.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * packageName : kr.movements.lunchorder.controller
 * fileName    : TestController
 * author      : ckr
 * date        : 24. 8. 6.
 * description :
 */

@Tag(name = "TestController", description = "Test 용 컨트롤러")
@RestController
class TestController {

    @Operation(summary = "hello", description = "hello world")
    @GetMapping("/test")
    fun helloWorld(): ResponseEntity<String> {
        return ResponseEntity.ok("hello World!")
    }
}