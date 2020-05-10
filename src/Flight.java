import org.json.JSONArray;

public class Flight {

    private String icao;
    private String callsign;
    private String originCountry;
    private int timePosition;
    private int lastContact;
    private float longitude;
    private float latitude;
    private JSONArray jsonArray;
    private double distance;

    /**
     * Constructor of flight object
     * @param jsonArray - jsonArray of flight data
     * @param userLong - user longitude for distance calculation
     * @param userLat - user latitude for distance calculation
     */
    public Flight(JSONArray jsonArray, double userLong, double userLat){
        this.jsonArray = jsonArray;

        //set flight properties
        try {
            this.icao = jsonArray.getString(0);
            this.callsign = jsonArray.getString(1);
            this.originCountry = jsonArray.getString(2);
            this.timePosition = jsonArray.getInt(3);
            this.lastContact = jsonArray.getInt(4);
            this.longitude = jsonArray.getFloat(5);
            this.latitude = jsonArray.getFloat(6);
        } catch(Exception e){
            //some null errors might occur here
        }

        //calculate distance of flight from user
        this.distance = distance(latitude,longitude,userLat,userLong);

    }

    /**
     * Method to get distance of flight from the user
     * @return double - flight from user
     */
    public double getDistanceFromUser(){
        return this.distance;
    }

    /**
     * Method to return toString of flight object
     * @return String - flight data
     */
    public String toString(){
        String toReturn = "ICAO: " + this.icao + "\nCallSign: " + this.callsign + "\nOrigin Country: "+
                this.originCountry +"\nLongitude: " + this.longitude + "\nLatitude: " + this.latitude;
        return toReturn;
    }

    /**
     * Method to calculate the distance between flight and user
     * @param lat1 - flight latitude
     * @param lon1 - flight longitude
     * @param lat2 - user latitude
     * @param lon2 - user longitude
     * @return double - distance
     */
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        // Haversine great circle distance approximation, returns meters
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60; // 60 nautical miles per degree of separation
        dist = dist * 1852; // 1852 meters per nautical mile
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
