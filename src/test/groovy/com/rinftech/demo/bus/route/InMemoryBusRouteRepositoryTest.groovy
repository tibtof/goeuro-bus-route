package com.rinftech.demo.bus.route

import com.rinftech.demo.bus.route.model.Route
import spock.lang.Specification
import spock.lang.Subject


class InMemoryBusRouteRepositoryTest extends Specification {

    @Subject
    def busRouteRepository = new InMemoryBusRouteRepository()

    def "route repository can store routes"() {
        given:
        def route = Route.builder().id(5).build()

        expect:
        busRouteRepository.save(route) == route
    }

    def "finding a direct route returns empty collection"() {
        expect:
        busRouteRepository.findDirectRoutes(1, 3).isEmpty()
    }

    def "finding a direct route returns single route"() {
        given:
        def route = busRouteRepository.save Route.builder().id(3).stationId(1).stationId(2).stationId(3).stationId(4).build()

        expect:
        busRouteRepository.findDirectRoutes(1, 3) as List == [route]
    }

    def "finding a direct route returns multiple routes"() {
        given:
        def route1 = busRouteRepository.save Route.builder().id(1).stationId(1).stationId(2).stationId(3).stationId(4).build()
        def route2 = busRouteRepository.save Route.builder().id(2).stationId(2).stationId(3).stationId(4).stationId(5).build()
        def route3 = busRouteRepository.save Route.builder().id(3).stationId(4).stationId(5).stationId(6).stationId(7).build()

        expect:
        busRouteRepository.findDirectRoutes(4, 5) as List == [route2, route3]
    }

}