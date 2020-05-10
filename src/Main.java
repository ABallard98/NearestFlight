import org.json.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Grabbing flight data...");

        final Double myLong; //set longitude here
        Double myLat; //set latitude here

        try {
            JSONArray jsonArray = FlightDataGrabber.grabData();
            if(jsonArray != null){
                Flight nearestFlight = FlightDataGrabber.findNearestFlight(jsonArray, myLong, myLat);
                System.out.println("\nNearest Flight To You: \n" + nearestFlight.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
