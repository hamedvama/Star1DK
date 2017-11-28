package fr.istic.star1DK.tables;

import fr.istic.star1DK.StarContract;

/**
 * Created by hamed on 21/11/2017.
 */

public class StoptimesC implements StarContract {

    private String tripId = StopTimes.StopTimeColumns.TRIP_ID;
    private String arrivalTime = StopTimes.StopTimeColumns.ARRIVAL_TIME;
    private String departureTime = StopTimes.StopTimeColumns.DEPARTURE_TIME;
    private String stopId = StopTimes.StopTimeColumns.STOP_ID;
    private String stopsequence = StopTimes.StopTimeColumns.STOP_SEQUENCE;

    public StoptimesC() {
    }

    public StoptimesC(String tripId, String arrivalTime, String departureTime, String stopId, String stopsequence) {
        this.tripId = tripId;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.stopId = stopId;
        this.stopsequence = stopsequence;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getStopsequence() {
        return stopsequence;
    }

    public void setStopsequence(String stopsequence) {
        this.stopsequence = stopsequence;
    }
}
