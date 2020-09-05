
//This class is for a linked list for the out-degrees for a City
public class City {

    private String name;
    
    //make a linked list object of all the out-degree nodes for a City object
    private linkedList<ExitCity> outDegCities;

    //Create a city constructor passing in the variable name
    public City(String name) {
        this.name = name;
        outDegCities = new linkedList<ExitCity>();
    }
    
    // Add a new out-degree to the repsective city 
    public void addExitCity(City city, int time, double cost) {
    	outDegCities.add(new ExitCity(cost, time, city));
    }

    // Get the linked list of all the out-degree cities from this city
    public linkedList<ExitCity> getExitCities() {
        return outDegCities;
    }

    // Get the name of the city
    public String getName() {
        return name;
    }
}