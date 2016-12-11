package com.rinftech.demo.bus.route

import com.rinftech.demo.bus.route.model.DirectRouteDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Subject

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.http.HttpStatus.OK

@ContextConfiguration(loader = SpringBootContextLoader, classes = Application)
@SpringBootTest(webEnvironment = RANDOM_PORT, value = 'file')
@Subject(BusRouteController)
class BusRouteIT extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    @LocalServerPort
    def localServerPort

    def "no direct route when no data in repository"() {
        when:
        def directRoute = restTemplate.getForEntity new URI("http://localhost:$localServerPort/api/direct?dep_sid=3&arr_sid=6"), DirectRouteDto

        then:
        directRoute.statusCode == OK
        directRoute.body == DirectRouteDto.builder().departureSid(3).arrivalSid(6).hasDirectRoute(Boolean.FALSE).build()
    }

}