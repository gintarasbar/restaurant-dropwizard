package domain;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ft.restaurants.domain.Location;
import io.dropwizard.jackson.Jackson;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class LocationTest {
    static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private Location sut;

    @BeforeClass
    public static void oneTimeSetup() {
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Before
    public void onSetup() {
        sut = new Location(1.0, 1.0);
    }

    @Test
    public void serializesJSON() throws Exception {
        String expected = fixture("fixture/Location.json");
        String actual = MAPPER.writeValueAsString(sut);
        assertEquals(expected, actual, JSONCompareMode.STRICT);
    }

    @Test
    public void deserializesJSON() throws Exception {
        Location expected = sut;
        Location actual = MAPPER.readValue(
                fixture("fixture/Location.json"),
                Location.class
        );
        assertThat(actual.getLatitude(), is(expected.getLatitude()));
        assertThat(actual.getLongitude(), is(expected.getLongitude()));
    }

}
