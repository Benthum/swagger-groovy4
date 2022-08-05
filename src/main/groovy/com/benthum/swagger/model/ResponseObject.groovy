package com.benthum.swagger.model

import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant

@Schema
class ResponseObject {
    @Schema(description = 'Example property 1.', example = 'test1')
    String property1
    @Schema(description = 'Example property 2.', example = 'test2')
    String property2

    Instant timestamp
}
