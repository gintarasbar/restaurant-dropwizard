import com.ft.restaurants.CSVReader;
import com.ft.restaurants.domain.Location;
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
                .location(new Location(0.0,0.0))
                .build();

        Restaurant testRestaurant2 = new RestaurantBuilder()
                .id(UUID.fromString("11111111-1111-1111-1111-11111111"))
                .name("testName2")
                .tag("testTag2")
                .address("testAddress2")
                .city("testCity2")
                .postcode("testPostCode2")
                .hygieneRating(1)
                .location(new Location(1.11,1.11))
                .build();

        List<Restaurant> actualRestaurants = new ArrayList<>();
        List<Restaurant> expectedRestaurants = new ArrayList<Restaurant>();

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
