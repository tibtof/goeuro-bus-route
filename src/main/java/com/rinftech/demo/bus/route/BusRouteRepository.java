package com.rinftech.demo.bus.route;

import com.rinftech.demo.bus.route.model.Route;

import java.util.Collection;

public interface BusRouteRepository {

	Route save(Route route);

	Collection<Route> findDirectRoutes(Integer departureSid, Integer arrivalSid);

}
