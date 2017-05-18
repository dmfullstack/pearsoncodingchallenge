package com.pearson.codingchallenge.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.pearson.codingchallenge.bean.OrderByEnum;
import com.pearson.codingchallenge.bean.StoreData;
import com.pearson.codingchallenge.exception.ServiceException;
import com.pearson.codingchallenge.service.impl.StoreServiceImpl;
import com.pearson.codingchallenge.utility.CsvReaderUtility;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceUnitTest {

	private List<StoreData> storeDataList = new ArrayList<>();

	@Mock
	private CsvReaderUtility csvReaderUtility;
	
	@InjectMocks
	private StoreServiceImpl storeService = new StoreServiceImpl(){

		@Override
		public List<StoreData> getStoreDataCached() {
			return null;
		}		
	};
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	@Before
	public void setStoreDataList() throws ParseException {
		StoreData storeData1 = new StoreData("store1", "ha4 6lh", "london", "test store1", dateFormat.parse("12/03/2010"), 590);
		StoreData storeData2 = new StoreData("store2", "ha4 9kk", "Aberdeen", "test store2", dateFormat.parse("07/03/2015"),190);
		StoreData storeData3 = new StoreData("store3", "ha4 8kk", "Hatfield", "test store3", dateFormat.parse("09/03/1985"), 1000);

		storeDataList.add(storeData1);
		storeDataList.add(storeData2);
		storeDataList.add(storeData3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetStoreByIdNullStoreId() throws ServiceException {
		storeService.getStoreById(null);
	}

	@Test
	public void testGetStoreByIdValidStoreId() throws IOException, ServiceException {
		Mockito.when(csvReaderUtility.readCsvFile()).thenReturn(storeDataList);
		StoreData storeFound = storeService.getStoreById("store1");
		assertNotNull(storeFound);
		assertEquals("test store1", storeFound.getAddress());
	}

	@Test(expected=ServiceException.class)
	public void testGetStoreByIdInvalidStoreId() throws IOException, ServiceException {
		Mockito.when(csvReaderUtility.readCsvFile()).thenReturn(storeDataList);
		StoreData storeFound = storeService.getStoreById("store123");
	}
	
	@Test(expected=ServiceException.class)
	public void testGetStoreByIdWithUnableToReadFile() throws IOException, ServiceException {
		Mockito.when(csvReaderUtility.readCsvFile()).thenThrow(IOException.class);
		StoreData storeFound = storeService.getStoreById("store123");
		assertNull(storeFound);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetStoresWithNullOrderBy() throws ServiceException {
		storeService.getStores(null);
	}
	
	@Test
	public void testGetStoresOrderByCity() throws ServiceException, IOException {
		Mockito.when(csvReaderUtility.readCsvFile()).thenReturn(storeDataList);
		List<StoreData> storeDataList = storeService.getStores(OrderByEnum.CITY);
		assertEquals("Aberdeen",storeDataList.get(0).getCity());
	}
	
	@Test
	public void testGetStoresOrderByOpenedDate() throws ServiceException, ParseException, IOException {
		Mockito.when(csvReaderUtility.readCsvFile()).thenReturn(storeDataList);
		List<StoreData> storeDataList = storeService.getStores(OrderByEnum.OPENED_DATE);
		Date expectedDate = dateFormat.parse("09/03/1985");
		assertEquals(expectedDate,storeDataList.get(0).getOpenedDate());
	}
}
