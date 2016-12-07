package com.rinftech.demo.bus.route;

import lombok.*;

import java.util.Collection;

@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "stationIds")
@Getter
@Builder
@ToString
public class Route {

    private final Integer id;

    @Singular
    private final Collection<Integer> stationIds;

}
