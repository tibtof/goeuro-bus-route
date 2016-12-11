package com.rinftech.demo.bus.route

import com.rinftech.demo.bus.route.model.DirectRouteDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification
import spock.lang.Subject

import static java.lang.Boolean.FALSE
import static java.lang.Boolean.TRUE
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.http.HttpStatus.OK

@ContextConfiguration(loader = SpringBootContextLoader, classes = Application)
@SpringBootTest(webEnvironment = RANDOM_PORT, value = 'file')
@TestPropertySource(properties = "data=tests/docker/example")
@Subject(BusRouteController)
class BusRouteIT extends Specification {

    static final def NO_DIRECT_ROUTE =
            DirectRouteDto.builder().departureSid(3).arrivalSid(6).hasDirectRoute(FALSE).build()
    static final def DIRECT_ROUTE =
            DirectRouteDto.builder().departureSid(114).arrivalSid(169).hasDirectRoute(TRUE).build()

    @Autowired
    TestRestTemplate restTemplate

    @LocalServerPort
    def localServerPort

    def "no direct route"() {
        when: "querying a set of stations with no direct route"
        def directRoute = restTemplate.getForEntity(
                new URI("http://localhost:$localServerPort/api/direct?dep_sid=$NO_DIRECT_ROUTE.departureSid&arr_sid=$NO_DIRECT_ROUTE.arrivalSid"),
                DirectRouteDto)

        then: "return a response having the queried stations and false for direct route"
        directRoute.statusCode == OK
        directRoute.body == NO_DIRECT_ROUTE
    }

    def "direct bus route"() {
        when: "querying a set of stations with direct route"
        def directRoute = restTemplate.getForEntity(
                new URI("http://localhost:$localServerPort/api/direct?dep_sid=$DIRECT_ROUTE.departureSid&arr_sid=$DIRECT_ROUTE.arrivalSid"),
                DirectRouteDto)

        then: "return a response having the queried stations and true for direct route"
        directRoute.statusCode == OK
        directRoute.body == DIRECT_ROUTE
    }

}
