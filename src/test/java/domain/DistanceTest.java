package domain;

import com.ft.restaurants.domain.Distance;
import com.ft.restaurants.domain.Location;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class DistanceTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReturnCorrectDistanceWithGivenCoordinates() {
        Location testLocation1 = new Location(74.0059, 40.7128);
        Location testLocation2 = new Location(118.2437, 34.0522);

        Distance distance = new Distance();

        Double expectedDistance = 3949.0;
        Double actualDistance = distance.distance(testLocation1, testLocation2);

        assertThat(actualDistance, closeTo(expectedDistance,14));
    }

    @Test
    public void shouldReturnCorrectDistanceWithGivenCoordinates2() {
        Location testLocation1 = new Location(-0.0946407, 51.5072498);
        Location testLocation2 = new Location(-0.097552, 51.5056301);

        Distance distance = new Distance();

        Double expectedDistance = 0.269;
        Double actualDistance = distance.distance(testLocation1, testLocation2);

        assertThat(actualDistance, closeTo(expectedDistance,0.0015));
    }
}
