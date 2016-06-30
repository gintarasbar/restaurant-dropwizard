package com.ft.restaurants;

import com.ft.restaurants.domain.Restaurant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    private static final String NEW_LINE_SEPARATOR = "\n";
    // List<Restaurant> restaurants = newArrayList();

    //CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

    public static void writeCSV(String file, List<Restaurant> restaurants) throws IOException {
        FileWriter fileWriter = new FileWriter(new File("src/main/resources/" + file), true);
        try {
            //fileWriter = new FileWriter(Resources.getResource(file).getPath(), true);
            for (Restaurant restaurant : restaurants) {
                fileWriter.append(restaurant.getId().toString());
                fileWriter.append(",");
                fileWriter.append(restaurant.getName());
                fileWriter.append(",");
                fileWriter.append(restaurant.getDescription());
                fileWriter.append(",");
                fileWriter.append(restaurant.getTag());
                fileWriter.append(",");
                fileWriter.append(restaurant.getAddress());
                fileWriter.append(",");
                fileWriter.append(restaurant.getCity());
                fileWriter.append(",");
                fileWriter.append(restaurant.getPostcode());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(restaurant.getHygieneRating()));
                fileWriter.append(",");
                fileWriter.append(String.format("%.6f", restaurant.getLocation().getLongitude()));
                fileWriter.append(",");
                fileWriter.append(String.format("%.6f", restaurant.getLocation().getLatitude()));
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
