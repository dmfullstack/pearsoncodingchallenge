package com.pearson.codingchallenge.utility;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.pearson.codingchallenge.bean.StoreData;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

@RunWith(MockitoJUnitRunner.class)
public class CsvReaderUtilityUnitTest {
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	@Mock
	private CacheManager cacheManager;	
	
	@Mock
	private Cache storeCache;
	
	@InjectMocks
	CsvReaderUtility csvReaderUtility = new CsvReaderUtility(){
		@Override
		protected void cacheStoreDataList(List<StoreData> storeDataList) {
			//do nothing
		}
		
	};

	@Test(expected=IOException.class)
	public void testReadCsvFileWithoutFileOnsystem() throws ParseException, IOException {
		ReflectionTestUtils.setField(csvReaderUtility, "storesCsvFileName", "sample.csv");
		List<StoreData> storeDataList = csvReaderUtility.readCsvFile();
		assertEquals(0, storeDataList.size());
	}

	@Test
	public void testReadCsvFile() throws ParseException, IOException {
		ReflectionTestUtils.setField(csvReaderUtility, "storesCsvFileName", "teststores.csv");
		Mockito.when(cacheManager.getCache("Stores")).thenReturn(storeCache);
		
		List<StoreData> storeDataList = csvReaderUtility.readCsvFile();
		assertEquals(3, storeDataList.size());
		StoreData storeData = storeDataList.get(0);
		assertEquals("store1", storeData.getStoreId());
		assertEquals("AB11 5PS", storeData.getPostCode());
		assertEquals("london", storeData.getCity());
		assertEquals("LSU 1A Union Square, Guild Street", storeData.getAddress());
		Date expectedDate = dateFormat.parse("21/02/1965");
		assertEquals(expectedDate, storeData.getOpenedDate());
	}
}
