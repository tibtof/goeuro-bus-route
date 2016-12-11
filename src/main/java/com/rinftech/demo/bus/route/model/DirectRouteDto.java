package com.rinftech.demo.bus.route.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
public class DirectRouteDto {

	@JsonProperty("dep_sid")
	private final Integer departureSid;

	@JsonProperty("arr_sid")
	private final Integer arrivalSid;

	@JsonProperty("direct_bus_route")
	private final Boolean hasDirectRoute;

}
