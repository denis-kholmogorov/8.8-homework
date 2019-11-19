import core.Line;
import core.Station;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RouteCalculatorTests extends TestCase {

    ArrayList<Station> routeNoConnector;
    ArrayList<Station> routeOneConnector;
    ArrayList<Station> routeTwoConnector;
    ArrayList<Station> stations1 = new ArrayList<>();
    ArrayList<Station> stations2 = new ArrayList<>();
    StationIndex stationIndex = new StationIndex();

    Line line1 = new Line(1, "Первая");
    Line line2 = new Line(2, "Вторая");
    Line line3 = new Line(3, "Третья");
    Station station11 = new Station("Одинадцатая", line1);
    Station station12 = new Station("Двенадцатая", line1);
    Station station13 = new Station("Тринадцатая", line1);
    Station station21 = new Station("ДвадцатьПервая", line2);
    Station station22 = new Station("ДвадцатьВторая", line2);
    Station station31 = new Station("ТридцатьПервая", line3);
    Station station32 = new Station("ТридцатьВторая", line3);

    @Override
    protected void setUp() throws Exception {

        line1.addStation(station11);
        line1.addStation(station12);
        line1.addStation(station13);
        line2.addStation(station21);
        line2.addStation(station22);
        line3.addStation(station31);
        line3.addStation(station32);

        routeNoConnector = new ArrayList<>();
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
        stations1.add(station13);
        stations1.add(station21);
        stations2.add(station22);
        stations2.add(station31);
        stationIndex.addConnection(stations1);
        stationIndex.addConnection(stations2);
    }

    public void testGetRouteOnTheLineNoConnector(){
        double actual = RouteCalculator.calculateDuration(routeNoConnector);
        double expected = 5;
        assertEquals(expected, actual);
    }

    public void testGetRouteOnTheLineOneConnector(){
        double actual = RouteCalculator.calculateDuration(routeOneConnector);
        double expected = 11;
        assertEquals(expected, actual);
    }

    public void testGetRouteOnTheLineTwoConnector(){
        double actual = RouteCalculator.calculateDuration(routeTwoConnector);
        double expected = 17;
        assertEquals(expected, actual);
    }

    public void testGetShortestRoute(){
        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List<Station> actual = calculator.getShortestRoute(station11, station32);
        List<Station> expected = Arrays.asList(station11, station12, station13, station21, station22, station31, station32);
        assertEquals(expected, actual);
    }
}


