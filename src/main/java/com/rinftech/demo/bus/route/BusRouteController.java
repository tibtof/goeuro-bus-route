package com.rinftech.demo.bus.route;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BusRouteController {

    private final BusRouteRepository busRouteRepository;

    public DirectRouteDto hasDirectRoute(Integer departureSid, Integer arrivalSid) {
        return DirectRouteDto.builder()
                .departureSid(departureSid)
                .arrivalSid(arrivalSid)
                .hasDirectRoute(!busRouteRepository.findDirectRoutes(departureSid, arrivalSid).isEmpty())
                .build();
    }

}
