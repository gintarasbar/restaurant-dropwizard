package com.ft.restaurants;

import com.ft.restaurants.domain.Restaurant;
import com.ft.restaurants.repository.RestaurantRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Jorge on 6/22/2016.
 */
public class CSVWriter {
    private static final String NEW_LINE_SEPARATOR = "\n";
    static Set<Restaurant> restaurants = RestaurantRepository.restaurants;
    FileWriter fileWriter = null;
    CSVPrinter csvPrinter = null;
    CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

    public static void writeCSV(String file) throws IOException {
        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;
        CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try {
            fileWriter = new FileWriter(file, true);
            for (Restaurant restaurant : restaurants) {
                fileWriter.append(restaurant.getId().toString());
                fileWriter.append(",");
                fileWriter.append(restaurant.getName());
                fileWriter.append(",");
                fileWriter.append(restaurant.getTag());
                fileWriter.append(",");
                fileWriter.append(restaurant.getAddress());
                fileWriter.append(",");
                fileWriter.append(restaurant.getCity());
                fileWriter.append(",");
                fileWriter.append(restaurant.getPostcode());
                fileWriter.append(",");
                fileWriter.append((char) restaurant.getHygieneRating());
                fileWriter.append(",");
                fileWriter.append((char) restaurant.getLongitude());
                fileWriter.append(",");
                fileWriter.append((char) restaurant.getLatitude());
                fileWriter.append("\n");
            }
            // csvPrinter = new CSVPrinter(fileWriter, csvFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
