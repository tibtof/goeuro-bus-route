package com.rinftech.demo.bus.route.reader;

import com.rinftech.demo.bus.route.BusRouteRepository;
import com.rinftech.demo.bus.route.model.Route;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collector;

import static java.util.Arrays.*;

@RequiredArgsConstructor
public class BusRouteFileReader {

	private final BusRouteRepository busRouteRepository;

	@SneakyThrows
	public void load(String path) {
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
			int noRoutes = Integer.parseInt(reader.readLine().trim());
			reader.lines()
					.limit(noRoutes)
					.map(String::trim)
					.map(s -> stream(s.split(" +")).map(Integer::parseInt).toArray(Integer[]::new))
					.map(line -> Route.builder().id(head(line)).stationIds(tail(line)).build())
					.collect(Collector.of(() -> busRouteRepository, BusRouteRepository::save, (repository, otherRepository) -> repository));
		} catch (NumberFormatException e) {
			throw new CorruptedBusRouteFileException(e);
		}
	}

	private Integer head(Integer[] array) {
		return array[0];
	}

	private List<Integer> tail(Integer[] line) {
		return asList(copyOfRange(line, 1, line.length));
	}

}
