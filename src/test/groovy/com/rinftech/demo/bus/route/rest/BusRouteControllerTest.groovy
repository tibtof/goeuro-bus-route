package com.rinftech.demo.bus.route.rest

import com.rinftech.demo.bus.route.model.DirectRouteDto
import com.rinftech.demo.bus.route.model.Route
import com.rinftech.demo.bus.route.repository.BusRouteRepository
import com.rinftech.demo.bus.route.rest.BusRouteController
import spock.lang.Specification
import spock.lang.Subject

import static java.util.Collections.emptyList
import static java.util.Collections.singleton

class BusRouteControllerTest extends Specification {

    def static
    final NO_DIRECT_ROUTE = DirectRouteDto.builder().departureSid(1).arrivalSid(2).hasDirectRoute(false).build()
    def static final DIRECT_ROUTE = DirectRouteDto.builder().departureSid(1).arrivalSid(3).hasDirectRoute(true).build()

    def busRouteRepository = Mock(BusRouteRepository)

    @Subject
    def busController = new BusRouteController(busRouteRepository)

    def "direct route check returns false when there is no direct route"() {
        when:
        def route = busController.hasDirectRoute(NO_DIRECT_ROUTE.getDepartureSid(), NO_DIRECT_ROUTE.getArrivalSid())

        then:
        1 * busRouteRepository.findDirectRoutes(NO_DIRECT_ROUTE.getDepartureSid(), NO_DIRECT_ROUTE.getArrivalSid()) >> emptyList()

        then:
        route == NO_DIRECT_ROUTE
    }

    def "direct route check returns true when there is a direct route"() {
        when:
        def route = busController.hasDirectRoute(DIRECT_ROUTE.getDepartureSid(), DIRECT_ROUTE.getArrivalSid())

        then:
        1 * busRouteRepository.findDirectRoutes(DIRECT_ROUTE.getDepartureSid(), DIRECT_ROUTE.getArrivalSid()) >>
                singleton(Route.builder().id(42).build())

        then:
        route == DIRECT_ROUTE
    }

}
