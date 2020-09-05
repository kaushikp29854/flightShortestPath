
//gets tht total time, total cost, 
public class Path {

	//create a linked list of object City which holds cities
    private linkedList<City> cities;
    

    //Path constructor which creates a linked list
    public Path() {
        cities = new linkedList<City>();
    }

    // Add a new city to the path
    public void addCity(City city) {
        cities.add(city);
    }

    // Return the total time traversing through all the cities for a path
    public int getTotalTime() {
        int totalTime = 0;

        for (int i = 0; i < cities.size() - 1; i++) {
        	//get the objects for both
            City city = cities.get(i);
            City toCity = cities.get(i + 1);

            //traverse through the cities in the path
            for (int j = 0; j < city.getExitCities().size(); j++) {
            	//
                ExitCity exitCity = city.getExitCities().get(j);
                //if its equal
                if (exitCity.getCity() == toCity) {
                    totalTime += exitCity.getTime();
                }
            }
        }

        //return the total Time 
        return totalTime;
    }

    // GET The total cost traversing through the path
    public double getTotalCost() {
        double totalCost = 0;

        for (int i = 0; i < cities.size() - 1; i++) {
            City city = cities.get(i);
            City toCity = cities.get(i + 1);

            for (int j = 0; j < city.getExitCities().size(); j++) {
                ExitCity exitCity = city.getExitCities().get(j);

                if (exitCity.getCity() == toCity) {
                    totalCost += exitCity.getCost();
                }
            }
        }
        //return the total cost
        return totalCost;
    }
    
    // Return a string representation of the Path
    @Override
    public String toString() {
        String string = "";
        
        for(int i = 0; i < cities.size(); i++) {
            string += cities.get(i).getName();
            
            if(i + 1 < cities.size()) {
                string += " -> ";
            }
        }
        
        return string;
    }
}