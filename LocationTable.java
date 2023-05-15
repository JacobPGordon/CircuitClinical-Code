//Table to store sorted rows; it consists of an array of LocationRecord arrays that are sorted in some way (defaults to zip on
// addition)

public class LocationTable {
    //Table that stores the arrays
    LocationRecord[][] locationTable;
    int records = 0;


    //Updates zip grouping with a new LocationRecord
    public LocationRecord[] addToZip(LocationRecord input, LocationRecord[] oldTable){
        int tableLength = oldTable.length;

        for (int i = 0; i < tableLength; i++) {
            if(oldTable[i]==null){
                oldTable[i]= input;
                return oldTable;
            }
        }
        LocationRecord[] newTable = new LocationRecord[tableLength*2];
        for (int i = 0; i < tableLength; i++) {
            newTable[i] = oldTable[i];
        }
        newTable[tableLength] = input;

        return newTable;
    }

    //Adds new zipcode to LocationTable
    public LocationRecord[][] addNewZip(LocationRecord[][] oldTable, LocationRecord newZip){
        int tableLength = oldTable.length;
        for (int i = 0; i < tableLength; i++){
            if(oldTable[i][0] == null){
                oldTable[i][0] = newZip;
                return oldTable;
            }
        }

        LocationRecord[][] newTable = new LocationRecord[tableLength*2][10];
        for (int i = 0; i < tableLength; i++) {
            newTable[i] = oldTable[i];

        }
        LocationRecord[] newZipArray = new LocationRecord[10];
        newZipArray[0] = newZip;
        newTable[tableLength] = newZipArray;
        return newTable;

    }

    //This method is used to add a new record to the table; it automatically organizes via zip code and dynamically resizes itself
    public void addLocation(LocationRecord input){
        //Initializing the table if no records have been added yet
        if(locationTable == null){
            locationTable = new LocationRecord[10][10];
            locationTable[0][0] = input;
            return;

        }

        //Searching the array for a matching zip; if we find a match, we add it to the end of that array
        int tableLength = locationTable.length;
        boolean jobComplete = false;
        for (int i = 0; i < tableLength; i++) {
            if(locationTable[i][0] == null){
                break;
            }
            if(locationTable[i][0].zip == input.zip){
                locationTable[i] = addToZip(input, locationTable[i]);
                jobComplete = true;
                break;
            }
        }

        //If a matching zip isn't found, we add a new array to the end of locationTable
        if(jobComplete == false){
            locationTable = addNewZip(locationTable,input);
        }
        records++;
        if(records%1000==0){
            System.out.println(records + " records processes. \n");
        }

    }

    

}
