package geoTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeoServiceImplTest {
    GeoServiceImpl sut;

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        sut = new GeoServiceImpl();
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
        sut = null;
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testLocationByIp(String Ip, Location expected){
        Location result = sut.byIp(Ip);
        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getCountry(), result.getCountry());
        assertEquals(expected.getStreet(), result.getStreet());
        assertEquals(expected.getBuiling(), result.getBuiling());
    }

    private static Stream<Arguments> source(){
        return Stream.of(Arguments.of("172.0.32.11",new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)));
    }

    @ParameterizedTest
    @MethodSource("source2")
    public void byCoordinates(double latitude, double longitude){
        var expected = RuntimeException.class;

        assertThrows(expected,
                () -> sut.byCoordinates(latitude,longitude));
    }

    private static Stream<Arguments> source2(){
        return Stream.of(Arguments.of(3333.333,111.1111));
    }
}




