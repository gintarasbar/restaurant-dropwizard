package domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ft.restaurants.domain.Restaurant;
import com.ft.restaurants.domain.RestaurantBuilder;
import io.dropwizard.jackson.Jackson;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

/**
 * Created by Jorge on 6/22/2016.
 */
public class RestaurantTest {
    Restaurant restaurant;
    static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Before
    public void onSetup() {
        restaurant = new RestaurantBuilder()
                        .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                        .name("testName")
                        .tag("testTag")
                        .address("testAddress")
                        .city("testCity")
                        .postcode("testPostCode")
                        .hygieneRating(0)
                        .longitude(0.00)
                        .latitude(0.00)
                        .build();
    }

    @Test
    public void serializeJSON() throws Exception {
        String expected = fixture("fixtures/Restaurant.json");
        String actual = MAPPER.writeValueAsString(restaurant);
        assertEquals(expected, actual, STRICT);
    }

    @Test
    public void deserializeJSON() throws Exception {
        Restaurant expected = restaurant;
        // TODO: fixture error need to fix
        /*Restaurant actual = MAPPER.readValue(fixture("fixture/Restaurant.json")), Restaurant.class);
        assertThat(actual.getName(), is(expected.getName()));
        assertThat(actual, is(expected));*/
    }


}
