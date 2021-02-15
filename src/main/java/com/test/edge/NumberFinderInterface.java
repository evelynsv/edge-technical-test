package com.test.edge;

import com.test.edge.entity.CustomNumberEntity;

import java.io.IOException;
import java.util.List;

public interface NumberFinderInterface {
	
	/**
	 * Checks if valueToFind is contained in the list
	 * @param valueToFind
	 * @param list
	 * @return true if valueToFind exist in the list
	 *          false if not
	 */
	boolean contains(int valueToFind, List<CustomNumberEntity> list);
	
	/**
	 * Read a list of CustommNumberEntity from a file 
	 * In the file the list is as a JSON  object
	 * @param filePath
	 * @return a list of CustomNumberEntity objects read from the file.
	 */
	List<CustomNumberEntity> readFromFile(String filePath) throws IOException;

}

