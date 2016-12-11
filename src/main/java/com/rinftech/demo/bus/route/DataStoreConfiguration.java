package com.rinftech.demo.bus.route;

import com.rinftech.demo.bus.route.reader.BusRouteFileReader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor(onConstructor = @_(@Autowired))
@Configuration
public class DataStoreConfiguration {

	private final BusRouteFileReader busRouteFileReader;

	@Value("${data}")
	private String dataFilePath;

	@PostConstruct
	public void init() {
		busRouteFileReader.load(dataFilePath);
	}

}
