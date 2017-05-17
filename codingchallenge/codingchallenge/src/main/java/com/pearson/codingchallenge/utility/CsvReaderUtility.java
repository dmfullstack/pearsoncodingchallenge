package com.pearson.codingchallenge.utility;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.pearson.codingchallenge.bean.StoreData;

/**
 * This is helper class to read ,parse stores csv file
 * 
 * @author swaonkar
 *
 */
@Component
@PropertySource("classpath:config.properties")
public class CsvReaderUtility {
	private static final String[] FILE_HEADER_MAPPING = { "Store Id", "Post Code", "City", "Address", "Opened Date" };
	private static final String STORE_ID = "Store Id";
	private static final String POSTCODE = "Post Code";
	private static final String CITY = "City";
	private static final String ADDRESS = "Address";
	private static final String OPENED_DATE = "Opened Date";
	private static final String DATE_FORMAT = "dd/MM/yyyy";

	private static final Logger LOG = Logger.getLogger(CsvReaderUtility.class);

	@Value("${stores.csv.filename}")
	private String storesCsvFileName;

	/**
	 * read store data from csv file name specified in config properties file
	 * 
	 * @return list of storedata objects
	 * @throws IOException
	 */
	public List<StoreData> readCsvFile() throws IOException {
		CSVParser csvFileParser = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		Date today = new Date();
		List<StoreData> storeDataList = new ArrayList<StoreData>();
		FileReader fileReader = null;

		ClassPathResource resource = new ClassPathResource(storesCsvFileName);
		fileReader = new FileReader(resource.getFile());
		csvFileParser = new CSVParser(fileReader, csvFileFormat);
		List<CSVRecord> csvRecords = csvFileParser.getRecords();
		for (int i = 1; i < csvRecords.size(); i++) {
			CSVRecord record = csvRecords.get(i);
			try {
				Date openedDate = dateFormat.parse(record.get(OPENED_DATE));
				int openedForDays = Days.daysBetween(new LocalDate(openedDate), new LocalDate(today)).getDays();
				StoreData storeData = new StoreData(record.get(STORE_ID), record.get(POSTCODE), record.get(CITY),
						record.get(ADDRESS), openedDate, openedForDays);
				storeDataList.add(storeData);
			} catch (ParseException e) {
				LOG.error("Error parsing record with store id" + record.get(STORE_ID));
			}
		}
		fileReader.close();
		return storeDataList;
	}
}
