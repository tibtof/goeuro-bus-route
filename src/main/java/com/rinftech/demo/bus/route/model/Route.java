package com.rinftech.demo.bus.route.model;

import lombok.*;

import java.util.Collection;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@Builder
@ToString
public class Route {

	private final Integer id;

	@Singular
	private final Collection<Integer> stationIds;

}
