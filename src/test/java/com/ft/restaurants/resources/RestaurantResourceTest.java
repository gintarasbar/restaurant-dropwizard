package com.ft.restaurants.resources;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Jorge on 6/16/2016.
 */
public class RestaurantResourceTest {
    @ClassRule
    public static final ResourceTestRule RULE
            =  ResourceTestRule.builder()
            .addResource(new RestaurantResource())
            .build();

    @Test
    public void testGetGreeting() {
        String expected = "Hello world";
        String actual = RULE
                .getJerseyTest()
                .target("/hello")
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);
        assertEquals(expected, actual);
    }
}
