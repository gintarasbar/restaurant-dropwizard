package domain;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ft.restaurants.domain.Location;
import com.ft.restaurants.domain.Restaurant;
import com.ft.restaurants.domain.RestaurantBuilder;
import io.dropwizard.jackson.Jackson;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

/**
 * Created by Jorge on 6/22/2016.
 */
public class RestaurantTest {
    Restaurant sut;
    static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @BeforeClass
    public static void oneTimeSetup() {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Before
    public void onSetup() {
        sut = new RestaurantBuilder()
                        .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                        .name("testName")
                        .description("testDescription")
                        .type("testType")
                        .tag("testTag")
                        .address("testAddress")
                        .city("testCity")
                        .postcode("testPostCode")
                        .hygieneRating(0)
                        .location(new Location(0.0,0.0))
                        .build();
    }

    @Test
    public void serializeJSON() throws Exception {
        String expected = fixture("fixture/Restaurant.json");
        String actual = MAPPER.writeValueAsString(sut);
        assertEquals(expected, actual, STRICT);
    }

    // TODO: fixture error need to fix
    @Test
    public void deserializeJSON() throws Exception {
        Restaurant expected = sut;

        Restaurant actual = MAPPER.readValue(
                fixture("fixture/Restaurant.json"),
                Restaurant.class
        );
        assertThat(actual.getName(), is(expected.getName()));
        assertThat(actual.getId(), is(expected.getId()));
    }


}
