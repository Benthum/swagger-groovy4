package com.benthum.swagger.controller

import com.benthum.swagger.StaticValues
import com.benthum.swagger.model.ResponseObject
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import java.time.Instant
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = StaticValues.SwaggerTagSwaggerController)
class TestController {
    @GetMapping('/test')
    @CrossOrigin
    @ResponseBody
    @Operation(summary = 'Test Controller.')
    @ApiResponses([
            @ApiResponse(responseCode = '200', description = 'Success', content = @Content(schema = @Schema(implementation = ResponseObject)))
    ])
    ResponseEntity test(@RequestParam(required = true) @Parameter(description = 'Property 1') String property1,
                        @RequestParam(required = false) @Parameter(description = 'Property 2') String property2,
                        @RequestParam(required = false) @Parameter(description = 'Timestamp in format yyyy-MM-dd\'T\'HH:mm:ss.SSSXXX') @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant time) {
        def response = new ResponseObject(
                property1: property1,
                property2: property2,
                timestamp: time ?: Instant.now()
        )

        return new ResponseEntity(response, HttpStatus.OK)
    }
}
