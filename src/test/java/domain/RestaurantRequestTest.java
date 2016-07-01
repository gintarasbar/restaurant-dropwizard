package domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ft.restaurants.domain.Location;
import com.ft.restaurants.domain.RestaurantRequest;
import io.dropwizard.jackson.Jackson;
import org.json.JSONException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class RestaurantRequestTest {
    RestaurantRequest sut;
    Location location;
    static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @BeforeClass
    public static void oneTimeSetup() {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Before
    public void onSetup() {
        location = new Location(0.0, 0.0);
        sut = new RestaurantRequest(
                "testName",
                "testDescription",
                "testType",
                "testTag",
                "testAddress",
                "testCity",
                "testPostCode",
                0,
                location
        );
    }

    @Test
    public void serializesJSON() throws JsonProcessingException, JSONException {
        String expected = fixture("fixture/RestaurantRequest.json");
        String actual = MAPPER.writeValueAsString(sut);
        assertEquals(expected, actual, STRICT);
    }

    @Test
    public void deserializesJSON() throws IOException {
        RestaurantRequest expected = sut;
        RestaurantRequest actual = MAPPER.readValue(fixture("fixture/RestaurantRequest.json"), RestaurantRequest.class);
        assertThat(actual, is(expected));
    }

    @Test
    public void serializesJSONWithOptionalEmptyValues() throws JsonProcessingException, JSONException {
        Location location = new Location(0.0, 0.0);
        RestaurantRequest request = new RestaurantRequest(
                "testName",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                location
        );
        String expected = MAPPER.writeValueAsString(request);
        String actual = fixture("fixture/RestaurantRequestDefault.json");
        assertEquals(expected, actual, STRICT);
    }
}
