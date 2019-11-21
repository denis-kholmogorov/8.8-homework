package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Line implements Comparable<Line>
{
    private static final Logger logger = LogManager.getLogger(Line.class);
    private int number;
    private String name;
    private List<Station> stations;


    public Line(int number, String name)
    {

        this.number = number;
        this.name = name;
        stations = new ArrayList<>();
        logger.info("Конструктор линии (" + this.number + " - " + this.name + ") отработал");
    }

    public int getNumber()
    {
        return number;
    }

    public String getName()
    {
        return name;
    }

    public void addStation(Station station)
    {
        stations.add(station);
    }

    public List<Station> getStations()
    {
        return stations;
    }

    @Override
    public int compareTo(Line line)
    {
        return Integer.compare(number, line.getNumber());
    }

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Line) obj) == 0;
    }
}