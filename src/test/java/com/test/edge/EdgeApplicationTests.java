package com.test.edge;

import com.test.edge.entity.CustomNumberEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootTest
class EdgeApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(EdgeApplicationTests.class);

	private static NumberFinder numberFinder;

	@BeforeAll
	static void setup() {
		FastestComparator fastestComparator = new FastestComparator();
		numberFinder = new NumberFinder(fastestComparator);
	}

	@Test
	public void contains_WhenValueIsPresent() throws IOException {
		LocalTime begin = LocalTime.now();
		LOGGER.info("Test started at: {}", begin);

		final List<CustomNumberEntity> list = numberFinder.readFromFile("json-file/numbers.json");
		LOGGER.info("List size: {}", list.size());

		final boolean contains = numberFinder.contains(3, list);
		Assertions.assertTrue(contains);

		LocalTime end = LocalTime.now();
		LOGGER.info("Test ended at: {}", end);

		LOGGER.info("The test execution took {} seconds to be done.", begin.until(end, ChronoUnit.SECONDS));
	}

	@Test
	public void contains_WhenValueIsNotPresent() throws IOException {
		LocalTime begin = LocalTime.now();
		LOGGER.info("Test started at: {}", begin);

		final List<CustomNumberEntity> list = numberFinder.readFromFile("json-file/numbers.json");
		LOGGER.info("List size: {}", list.size());

		final boolean contains = numberFinder.contains(43, list);
		Assertions.assertFalse(contains);

		LocalTime end = LocalTime.now();
		LOGGER.info("Test ended at: {}", end);

		LOGGER.info("The test execution took {} seconds to be done.", begin.until(end, ChronoUnit.SECONDS));
	}

}
