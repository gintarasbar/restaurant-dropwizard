package domain;

import com.ft.restaurants.domain.Distance;
import com.ft.restaurants.domain.Location;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DistanceTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldReturnCorrectDistanceWithGivenCoordinates() {
        Location testLocation1 = new Location(74.0059, 40.7128);
        Location testLocation2 = new Location(34.0522, 118.2437);

        Distance distance = new Distance();

        Double expectedDistance = 9062.0;
        Double actualDistance = Math.floor(distance.distance(testLocation1, testLocation2));

        assertThat(actualDistance, is(expectedDistance));
    }
}
