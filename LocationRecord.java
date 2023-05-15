//Data storage class; each instance of this class will store one row of the original table in easy to access string values
import java.sql.* ;


public class LocationRecord {
    //Internal storage for the data inside of each row
    String id;
    String name;
    String city;
    String state;
    String zip;
    String country;

    //Helper method to pull strings out of the row
    public String columnToString(ResultSet table, String key){
        try {
            return table.getString(key);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    //Class constructor; takes a ResultSet and parses the current entry to initialize internal storage
    public LocationRecord(ResultSet inputData) {
        this.id = columnToString(inputData,"nct_id");
        this.name = columnToString(inputData,"name");
        this.city = columnToString(inputData,"city");
        this.state = columnToString(inputData,"state");
        this.zip = columnToString(inputData,"zip");
        if(this.zip == null){
            this.zip = "No zipcode provided";
        }
        this.country = columnToString(inputData,"country");
    }


}
