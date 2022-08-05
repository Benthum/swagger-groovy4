package com.benthum.swagger.controller

import com.benthum.swagger.ComplaintServiceApplication
import com.benthum.swagger.model.ResponseObject
import com.fasterxml.jackson.databind.json.JsonMapper
import java.time.Instant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

@WebMvcTest(controllers = [TestController])
class TestControllerSpec extends Specification {
    @Autowired
    protected MockMvc mockMvc
    JsonMapper jsonMapper = ComplaintServiceApplication.jsonMapper()

    //region Test Controller
    def "Test Controller - OK - all"() {
        given:
        def property1 = 'test1'
        def property2 = 'test2'
        def time = Instant.now()

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get('/test')
                .param('property1', property1)
                .param('property2', property2)
                .param('time', time.toString()))
                .andReturn().response
        def result = jsonMapper.readValue(response.contentAsString, ResponseObject)

        then:
        response
        response.status == HttpStatus.OK.value()

        result
        result.property1 == property1
        result.property2 == property2
        result.timestamp == time
    }

    def "Test Controller - OK - min"() {
        given:
        def property1 = 'test1'

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get('/test')
                .param('property1', property1))
                .andReturn().response
        def result = jsonMapper.readValue(response.contentAsString, ResponseObject)

        then:
        response
        response.status == HttpStatus.OK.value()

        result
        result.property1 == property1
        result.property2 == null
        result.timestamp >= Instant.now().minusSeconds(10)
    }
    //endregion
}
