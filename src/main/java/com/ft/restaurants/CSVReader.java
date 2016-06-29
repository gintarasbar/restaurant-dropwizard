package com.ft.restaurants;

import com.ft.restaurants.domain.Location;
import com.ft.restaurants.domain.Restaurant;
import com.google.common.io.Resources;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Jorge on 6/20/2016.
 */
public class CSVReader {
    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String[] CSV_HEADER = {"Id", "Name", "Description", "Tags", "Address", "City", "Postcode", "Hygiene_rating", "Longitude", "Latitude"};
    private static final String RESTAURANT_ID = "Id";
    private static final String RESTAURANT_NAME = "Name";
    private static final String RESTAURANT_DESCRIPTION = "Description";
    private static final String RESTAURANT_TAG = "Tags";
    private static final String RESTAURANT_ADDRESS = "Address";
    private static final String RESTAURANT_CITY = "City";
    private static final String RESTAURANT_POSTCODE = "Postcode";
    private static final String RESTAURANT_HYGIENE_RATING = "Hygiene_rating";
    private static final String RESTAURANT_LONGITUDE = "Longitude";
    private static final String RESTAURANT_LATITUDE = "Latitude";

    public List<Restaurant> readCSV(String file) throws Exception {

        FileReader fileReader = null;

        CSVParser csvParser = null;

        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(CSV_HEADER).withRecordSeparator(NEW_LINE_SEPARATOR);

        List<Restaurant> restaurants = newArrayList();
        try {

            fileReader = new FileReader(Resources.getResource(file).getPath());
            csvParser = new CSVParser(fileReader, csvFormat);
            List csvRecords = csvParser.getRecords();

            for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord record = (CSVRecord) csvRecords.get(i);

                Restaurant restaurant = new Restaurant(UUID.fromString(record.get(RESTAURANT_ID)),
                        record.get(RESTAURANT_NAME),
                        record.get(RESTAURANT_DESCRIPTION),
                        record.get(RESTAURANT_TAG),
                        record.get(RESTAURANT_ADDRESS),
                        record.get(RESTAURANT_CITY),
                        record.get(RESTAURANT_POSTCODE),
                        NumberUtils.toInt(record.get(RESTAURANT_HYGIENE_RATING), 0),
                        new Location(
                                NumberUtils.toDouble(record.get(RESTAURANT_LONGITUDE), 0),
                                NumberUtils.toDouble(record.get(RESTAURANT_LATITUDE), 0)));
                // restaurants.add(restaurant);
                restaurants.add(restaurant);
            }
        } catch (Exception exception) {
            System.out.println("Error while loading CSV");
            exception.printStackTrace();
            throw new Exception();
        } finally {
            try {
                fileReader.close();
                csvParser.close();
            } catch (IOException ioexception) {
                System.out.println("Error while closing CSV parser");
                ioexception.printStackTrace();
            }
        }
        return restaurants;
    }
}

