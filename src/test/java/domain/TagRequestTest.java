package domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ft.restaurants.domain.TagRequest;
import io.dropwizard.jackson.Jackson;
import org.json.JSONException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class TagRequestTest {
    TagRequest sut;
    static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @BeforeClass
    public static void oneTimeSetup() { MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); }

    @Before
    public void onSetup() {
        sut = new TagRequest("testTag");
    }

    @Test
    public void serializesJSON() throws JsonProcessingException, JSONException {
        String expected = fixture("fixture/TagRequest.json");
        String actual = MAPPER.writeValueAsString(sut);
        assertEquals(expected, actual, STRICT);
    }

    @Test
    public void deserializesJSON() throws IOException {
        TagRequest expected = sut;
        TagRequest actual = MAPPER.readValue(fixture("fixture/TagRequest.json"), TagRequest.class);
        assertThat(actual.getTag(), is(expected.getTag()));
    }
}
