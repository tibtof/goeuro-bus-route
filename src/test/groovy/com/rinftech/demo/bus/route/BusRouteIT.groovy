package com.rinftech.demo.bus.route

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@ContextConfiguration(loader = SpringBootContextLoader, classes = Application)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class BusRouteIT extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    @LocalServerPort
    def localServerPort

}