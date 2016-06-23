import com.ft.restaurants.CSVReader;
import com.ft.restaurants.domain.Restaurant;
import com.ft.restaurants.domain.RestaurantBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Jorge on 6/23/2016.
 */
public class CSVReaderTest {
    CSVReader testReader = new CSVReader();

    // TODO: Fix Test failing for IDs java.lang.AssertionError: Expected: is "11111111-1111-1111-1111-000011111111" but: was "01111111-1111-1111-1111-000011111111"
    @Test
    public void shouldReadDataAndContainExpectedRestaurantIdAndName() throws Exception {
        Restaurant testRestaurant1 = new RestaurantBuilder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .name("testName1")
                .tag("testTag1")
                .address("testAddress1")
                .city("testCity1")
                .postcode("testPostCode1")
                .hygieneRating(0)
                .longitude(0.00)
                .latitude(0.00)
                .build();

        Restaurant testRestaurant2 = new RestaurantBuilder()
                .id(UUID.fromString("11111111-1111-1111-1111-11111111"))
                .name("testName2")
                .tag("testTag2")
                .address("testAddress2")
                .city("testCity2")
                .postcode("testPostCode2")
                .hygieneRating(1)
                .longitude(1.11)
                .latitude(1.11)
                .build();

        List<Restaurant> expectedRestaurants = new ArrayList<Restaurant>();
        List<Restaurant> actualRestaurants = new ArrayList<Restaurant>();
        expectedRestaurants.add(testRestaurant1);
        expectedRestaurants.add(testRestaurant2);

        actualRestaurants = testReader.readCSV("test.csv");

        System.out.println(actualRestaurants.get(0).getId().toString());
        System.out.println(expectedRestaurants.get(0).getId().toString());
        System.out.println(actualRestaurants.get(1).getId().toString());
        System.out.println(expectedRestaurants.get(1).getId().toString());

        assertThat(actualRestaurants.get(0).getId().toString(), is(expectedRestaurants.get(0).getId().toString()));
        assertThat(actualRestaurants.get(1).getId().toString(), is(expectedRestaurants.get(1).getId().toString()));
        assertThat(actualRestaurants.get(0).getName(), is(expectedRestaurants.get(0).getName()));
        assertThat(actualRestaurants.get(1).getName(), is(expectedRestaurants.get(1).getName()));
    }
}
