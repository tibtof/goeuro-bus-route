package com.rinftech.demo.bus.route.rest;

import com.rinftech.demo.bus.route.model.DirectRouteDto;
import com.rinftech.demo.bus.route.repository.BusRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class BusRouteController {

	private final BusRouteRepository busRouteRepository;

	@RequestMapping(method = GET, path = "/direct", produces = APPLICATION_JSON_VALUE)
	public DirectRouteDto hasDirectRoute(@RequestParam("dep_sid") Integer departureSid, @RequestParam("arr_sid") Integer arrivalSid) {
		return DirectRouteDto.builder()
				.departureSid(departureSid)
				.arrivalSid(arrivalSid)
				.hasDirectRoute(!busRouteRepository.findDirectRoutes(departureSid, arrivalSid).isEmpty())
				.build();
	}

}
