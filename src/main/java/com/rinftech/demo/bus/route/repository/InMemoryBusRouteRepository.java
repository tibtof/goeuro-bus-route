package com.rinftech.demo.bus.route.repository;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.rinftech.demo.bus.route.model.Route;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static com.google.common.collect.Multimaps.synchronizedSetMultimap;

@Repository
public class InMemoryBusRouteRepository implements BusRouteRepository {

	private final SetMultimap<Integer, Route> indexByStation = synchronizedSetMultimap(HashMultimap.create());

	@Override
	public Route save(Route route) {
		return index(route);
	}

	private Route index(Route route) {
		route.getStationIds().forEach(sid -> indexByStation.put(sid, route));
		return route;
	}

	@Override
	public Collection<Route> findDirectRoutes(Integer departureSid, Integer arrivalSid) {
		return Sets.intersection(indexByStation.get(departureSid), indexByStation.get(arrivalSid));
	}

}
