package com.rinftech.demo.bus.route;

import java.util.Collection;

public interface BusRouteRepository {

    Collection<Route> findDirectRoutes(Integer departureSid, Integer arrivalSid);

}
