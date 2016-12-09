package com.rinftech.demo.bus.route.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
public class DirectRouteDto {

    private final Integer departureSid;
    private final Integer arrivalSid;
    private final Boolean hasDirectRoute;

}
