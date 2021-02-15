package com.test.edge;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.edge.entity.CustomNumberEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NumberFinder implements NumberFinderInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberFinder.class);

    private FastestComparator fastestComparator;

    public NumberFinder(FastestComparator fastestComparator) {
        this.fastestComparator = fastestComparator;
    }

    @Override
    public boolean contains(int valueToFind, List<CustomNumberEntity> list) {

        return list.parallelStream()
                .map(entity -> {
                    try {
                        LOGGER.info("Mapping value: {}.", entity.getNumber());
                        return fastestComparator.compare(valueToFind, entity);

                    } catch (NumberFormatException exception) {

                        LOGGER.error("Value is not parsable: {}.", entity.getNumber());
                        return null;
                    }
                })
                .anyMatch(value -> value != null && value == 0);
    }

    @Override
    public List<CustomNumberEntity> readFromFile(String filePath) throws IOException {

        final ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<CustomNumberEntity>>(){});
    }
}
