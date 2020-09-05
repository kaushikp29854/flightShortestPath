import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class FlightPlan {

    // Retreives the  the cities from file and initialize their connectivity to each other
    private static citiesGraph initializeCities(String filename) throws Exception {
        citiesGraph cities = new citiesGraph();

        //make a scanner object
        Scanner file = new Scanner(new File(filename));
        //read in the first line
        file.nextLine();

        //while loop to read in and parse the data one line at a time
        while (file.hasNextLine()) {
        	//store info into a string Array
            String[] tokens = file.nextLine().split("\\|");
            String fromCityName = tokens[0];
            String toCityName = tokens[1];
           
            //convert cost to a bouble
            double cost = Double.parseDouble(tokens[2]);
            
            //convert time into an int
            int time = Integer.parseInt(tokens[3]);

            //create edges between the cities
            cities.joinCities(fromCityName, toCityName, cost, time);
            //making the edges undirected
            cities.joinCities(toCityName, fromCityName, cost, time);

        }

        file.close();

        return cities;
    }
    
    // Sort the paths by least amount of time by iterating throguh all the paths
    private static void sortByTime(Path[] paths) {
        for (int i = 0; i < paths.length - 1; i++) {
            for (int j = i + 1; j < paths.length; j++) {
                if (paths[j].getTotalTime()< paths[i].getTotalTime()) {
                	//swap the indices of the paths if condition is true
                    Path temp = paths[i];
                    paths[i] = paths[j];
                    paths[j] = temp;
                }
            }
        }
    }

    // Sort the paths by least amount of cost by iterating through all the paths
    private static void sortByCost(Path[] paths) {
        for (int i = 0; i < paths.length - 1; i++) {
            for (int j = i + 1; j < paths.length; j++) {
                if (paths[j].getTotalCost() < paths[i].getTotalCost()) {
                	//if condition is true swap the indices
                    Path temp = paths[i];
                    paths[i] = paths[j];
                    paths[j] = temp;
                }
            }
        }
    }

    // MAIN FUNCTION
    public static void main(String[] args) throws Exception {
        // Make sure arguments are provided
        if (args.length != 3) {
            System.out.println("Arguments should be: (flights file) (requests file) (output file)");
            return;
        }

        citiesGraph cities = initializeCities(args[0]);

        // Now we handle the requests from file too and print out the result to another file
        Scanner file = new Scanner(new File(args[1]));
        file.nextLine();

        PrintWriter result = new PrintWriter(new FileWriter(new File(args[2])));

        int requestNumber = 1;

        while (file.hasNextLine()) {
            String[] tokens = file.nextLine().split("\\|");

            
            String fromCityName = tokens[0];
            String toCityName = tokens[1];
            String sortType = tokens[2];

            // Process it
            Path[] paths = cities.findPaths(fromCityName, toCityName);

            result.print("Flight " + requestNumber + ": " + fromCityName + ", " + toCityName + " ");

            if (sortType.equalsIgnoreCase("C")) {
                result.println("(Cost)");
                sortByCost(paths);
            } else {
                result.println("(Time)");
                sortByTime(paths);
            }

            // Print out the final result (top 3 only)
            for (int i = 0; i < 3 && i < paths.length; i++) {
                result.println("Path " + (i + 1) + ": " + paths[i].toString() + ". Time: "
                        + paths[i].getTotalTime() + " Cost: "
                        + String.format("%.2f", paths[i].getTotalCost()));
            }

            result.println();
            
            requestNumber++;
        }

        file.close();
        result.close();
    }
}