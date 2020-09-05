public class citiesGraph {

	//creating a linked list of City object
    private linkedList<City> cities;

    //constructor which creates a new graph(Linked List)
    public citiesGraph() {
        cities = new linkedList<City>();
    }

    //pass in the name of the city to retrieve object
    public City getCity(String name) {
    	//iterate through all the nodes in cities linked list
        for (int i = 0; i < cities.size(); i++) {
        	//get the current city while traversing cities
            City city = cities.get(i);

            //if we find the city object with corresponding name return that city object
            if (city.getName().equalsIgnoreCase(name)) {
                return city;
            }
        }
        //return null if we did not find the city name in the linkedList
        return null;
    }

    // Link 2 cities together to create an edge 
    public void joinCities(String startCityName, String destCityName, double cost, int time) {
    	
    	//create a node for the starting city with the name passed in 
        City startCity = getCity(startCityName);

        //add City object to cities linkedList
        if (startCity == null) {
            startCity = new City(startCityName);
            cities.add(startCity);
        }

        //create a node object City for the destination city
        City destCity = getCity(destCityName);

        //add destCity to cities linked list
        if (destCity == null) {
            destCity = new City(destCityName);
            cities.add(destCity);
        }
        //join the two cities together
        startCity.addExitCity(destCity, time, cost);
    }

    // Helper function used recursively  to find all the paths between two cities
    private void findPaths(stack<City> currentCities, linkedList<Path> solutionPaths, City toCity) {
        //check if stack is empty
    	if (currentCities.isEmpty()) {
            return;
        }

    	//get the top of the stack value
        City currentCity = currentCities.peek();
        
        //check to see we are at the destination city
        if(currentCity == toCity) {
        	//create an object of Path
            Path path = new Path();

            //add all the cities from the stack to the path
            for (int i = 0; i < currentCities.size(); i++) {
                path.addCity(currentCities.get(i));
            }
            //add the path to the solution
            solutionPaths.add(path);
        } 
        else{//DFS APPROACH
        	//if currentCity is not equal to the destination city iterate thorugh all the out deg cities for currentCity 
        for(int i = 0; i < currentCity.getExitCities().size(); i++) {
        	
            City nextCurrentCity = currentCity.getExitCities().get(i).getCity();            
            
            //if stack does not contain out deg edge of cuurentCity, push it to the stack and call function recursively
            if (!currentCities.contains(nextCurrentCity)) {

            	//add the nextCurrentcity to the stack 
                currentCities.push(nextCurrentCity);
                
                //call funciton recursively to check if the last element pushed is in the destination city
                findPaths(currentCities, solutionPaths, toCity);
                
            	}
        	}
        }
        //pop the top element in the stack
        currentCities.pop();
    }
    
    // Find all paths between the two city
    public Path[] findPaths(String fromCityName, String toCityName) {
    	//get the starting and dest city
        City fromCity = getCity(fromCityName);
        City toCity = getCity(toCityName);
        
        if(fromCity == null || toCity == null){
            return null;
        }
        
        //create a stack adding cities into it 
        stack<City> currentCities = new stack<City>();
        
        //make an empty linked list which contains total paths
        linkedList<Path> solutionPaths = new linkedList<Path>();
        
        //add the starting city to the stack
        currentCities.add(fromCity);
        
        //call helper funciton
        findPaths(currentCities, solutionPaths, toCity);
        
        Path[] paths = new Path[solutionPaths.size()];
        
        //add all the various paths into the paths array
        for(int i = 0; i < solutionPaths.size(); i++) {
            paths[i] = solutionPaths.get(i);
        }
        
        return paths;
    }
}
