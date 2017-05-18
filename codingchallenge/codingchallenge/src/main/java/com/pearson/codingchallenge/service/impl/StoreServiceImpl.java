package com.pearson.codingchallenge.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearson.codingchallenge.bean.OrderByEnum;
import com.pearson.codingchallenge.bean.StoreData;
import com.pearson.codingchallenge.exception.ServiceException;
import com.pearson.codingchallenge.service.StoreService;
import com.pearson.codingchallenge.utility.CsvReaderUtility;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private CsvReaderUtility csvReaderUtility;
	private static final Logger LOG = Logger.getLogger(StoreServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	public StoreData getStoreById(String id) throws ServiceException {
		if (id == null) {
			LOG.debug("Store id parameter can not be null");
			throw new IllegalArgumentException("Store id parameter can not be null");
		}
		List<StoreData> storeDataList = null;
		try {
			storeDataList = csvReaderUtility.readCsvFile();
			StoreData machingStoreData = storeDataList.stream().filter(storeData -> storeData.getStoreId().equals(id))
					.findAny().orElse(null);
			if (machingStoreData == null) {
				throw new ServiceException("Store with id" + id + " does not exist");
			}
			return machingStoreData;
		} catch (IOException e) {
			LOG.debug("Error while reading stores data from file");
			throw new ServiceException("Error while reading stores data from file");
		}
	}

	/**
	 * {{@inheritDoc}
	 */
	public List<StoreData> getStores(OrderByEnum orderBy) throws ServiceException {
		List<StoreData> storeDataList;
		if (orderBy == null) {
			throw new IllegalArgumentException("orderby parameter can not be null");
		}
		try {
			storeDataList = csvReaderUtility.readCsvFile();

			if (orderBy.equals(OrderByEnum.CITY)) {
				Collections.sort(storeDataList, new Comparator<StoreData>() {
					public int compare(StoreData s1, StoreData s2) {
						return s1.getCity().compareTo(s2.getCity());
					}
				});
			} else if (orderBy.equals(OrderByEnum.OPENED_DATE)) {
				Collections.sort(storeDataList, new Comparator<StoreData>() {
					public int compare(StoreData s1, StoreData s2) {
						return s1.getOpenedDate().compareTo(s2.getOpenedDate());
					}
				});
			}
		} catch (IOException e) {
			LOG.debug("Error while reading stores data from file");
			throw new ServiceException("Error while reading stores data from file");
		}

		return storeDataList;
	}

	/**
	 * {@inheritDoc}
	 */
	public void createStore(StoreData store) {
		// TODO:Add implementation
	}

}
