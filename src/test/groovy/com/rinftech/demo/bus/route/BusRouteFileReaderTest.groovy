package com.rinftech.demo.bus.route

import com.rinftech.demo.bus.route.model.Route
import com.rinftech.demo.bus.route.reader.BusRouteFileReader
import com.rinftech.demo.bus.route.reader.CorruptedBusRouteFileException
import spock.lang.Specification
import spock.lang.Subject

import java.nio.file.Paths


class BusRouteFileReaderTest extends Specification {

    static final Route ROUTE_1 = Route.builder().id(0).stationIds([0, 1, 2, 3, 4]).build()
    static final Route ROUTE_2 = Route.builder().id(1).stationIds([3, 1, 6, 5]).build()
    static final Route ROUTE_3 = Route.builder().id(2).stationIds([0, 6, 4]).build()

    def busRouteRepository = Mock(BusRouteRepository)

    @Subject
    def fileBusRouteLoader = new BusRouteFileReader(busRouteRepository)

    def "all routes from file are saved into the repository"() {
        when: 'the file is loaded'
        load 'test-bus-routes.txt'

        then: 'all routes are saved into the repository'
        1 * busRouteRepository.save(ROUTE_1)
        1 * busRouteRepository.save(ROUTE_2)
        1 * busRouteRepository.save(ROUTE_3)
    }

    def "only the specified number of routes are saved into the repository"() {
        when: 'the file is loaded'
        load 'test-bus-routes-more-routes-than-specified.txt'

        then: 'all routes are saved into the repository'
        1 * busRouteRepository.save(ROUTE_1)
        1 * busRouteRepository.save(ROUTE_2)
        0 * _
    }

    def "multiple spaces are ignored"() {
        when: 'the file is loaded'
        load 'test-bus-routes-multiple-spaces.txt'

        then: 'all routes are saved into the repository'
        1 * busRouteRepository.save(ROUTE_1)
        1 * busRouteRepository.save(ROUTE_2)
        1 * busRouteRepository.save(ROUTE_3)
    }

    def "corrupted files are dealt with"() {
        when: 'a corrupted file is loaded'
        load 'test-bus-routes-corrupted.txt'

        then: 'a corrupted file exception is thrown'
        thrown(CorruptedBusRouteFileException)
    }

    private def load(String fileName) {
        fileBusRouteLoader.load Paths.get(this.class.getResource("/data/$fileName").toURI()).toString()
    }

}