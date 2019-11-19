import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RouteCalculatorTests extends TestCase {

    Line line1;
    Line line2;
    Line line3;
    Station station11;
    Station station12;
    Station station13;
    Station station21;
    Station station22;
    Station station31;
    Station station32;
    ArrayList<Station> routeNoConnector;
    ArrayList<Station> routeOneConnector;
    ArrayList<Station> routeTwoConnector;
    ArrayList<Station> connection1;
    ArrayList<Station> connection2;
    StationIndex stationIndex;




    @BeforeClass
    protected void setUp() throws Exception {

        stationIndex = new StationIndex();
        routeNoConnector = new ArrayList<>();
        connection1 = new ArrayList<>();
        connection2 = new ArrayList<>();

        line1 = new Line(1, "Первая");
        line2 = new Line(2, "Вторая");
        line3 = new Line(3, "Третья");

        station11 = new Station("Одинадцатая", line1);
        station12 = new Station("Двенадцатая", line1);
        station13 = new Station("Тринадцатая", line1);
        station21 = new Station("ДвадцатьПервая", line2);
        station22 = new Station("ДвадцатьВторая", line2);
        station31 = new Station("ТридцатьПервая", line3);
        station32 = new Station("ТридцатьВторая", line3);

        line1.addStation(station11);
        line1.addStation(station12);
        line1.addStation(station13);
        line2.addStation(station21);
        line2.addStation(station22);
        line3.addStation(station31);
        line3.addStation(station32);


        routeNoConnector.add(station11);
        routeNoConnector.add(station12);
        routeNoConnector.add(station13);
        routeOneConnector = (ArrayList<Station>) routeNoConnector.clone();
        routeOneConnector.add(station21);
        routeOneConnector.add(station22);
        routeTwoConnector = (ArrayList<Station>) routeOneConnector.clone();
        routeTwoConnector.add(station31);
        routeTwoConnector.add(station32);

        stationIndex.addStation(station11);
        stationIndex.addStation(station12);
        stationIndex.addStation(station13);
        stationIndex.addStation(station21);
        stationIndex.addStation(station22);
        stationIndex.addStation(station31);
        stationIndex.addStation(station32);
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        connection1.add(station13);
        connection1.add(station21);
        connection2.add(station22);
        connection2.add(station31);

        stationIndex.addConnection(connection1);
        stationIndex.addConnection(connection2);
    }
    @Test
    public void testGetRouteOnTheLineNoConnector(){
        double actual = RouteCalculator.calculateDuration(routeNoConnector);
        double expected = 5;
        assertEquals("Wrong! Time no connector test",expected, actual);
    }

    @Test
    public void testGetRouteOnTheLineOneConnector(){
        double actual = RouteCalculator.calculateDuration(routeOneConnector);
        double expected = 11;
        assertEquals("Wrong! Time one connector test", expected, actual);
    }

    @Test
    public void testGetRouteOnTheLineTwoConnector(){
        double actual = RouteCalculator.calculateDuration(routeTwoConnector);
        double expected = 17;
        assertEquals("Wrong! Time two connector test", expected, actual);
    }

    @Test
    public void testGetShortestRouteNoConnector(){
        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List<Station> actual = calculator.getShortestRoute(station11, station13);
        List<Station> expected = Arrays.asList(station11, station12, station13);
        assertEquals("Wrong! Short route connector", expected, actual);
    }
    @Test
    public void testGetShortestRouteOneConnector(){
        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List<Station> actual = calculator.getShortestRoute(station21, station32);
        List<Station> expected = Arrays.asList(station21, station22, station31, station32);
        assertEquals("Wrong! Short route one connector", expected, actual);
    }

    @Test
    public void testGetShortestRouteTwoConnector(){
        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List<Station> actual = calculator.getShortestRoute(station32, station11);
        List<Station> expected = Arrays.asList(station32, station31, station22, station21, station13, station12, station11 );
        assertEquals("Wrong! Short route two connector", expected, actual);

    }

    @Test
    public void testGetShortestNotSameExpected(){
        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List<Station> actual = calculator.getShortestRoute(station11, station13);
        List<Station> expected = Arrays.asList(station11, station12, station13);
        assertNotSame("Wrong! Routers are same", expected, actual);
    }

    @Test
    public void testGetShortestNotNull(){
        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List<Station> actual = calculator.getShortestRoute(station11, station13);
        assertNotNull("Wrong! List is null", actual);
    }
}


