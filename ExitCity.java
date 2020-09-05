
public class ExitCity {
    private double cost;
    private int time;
    private City city;
    
    // Initialize the exit city
    public ExitCity(double cost, int time, City city) {
        this.cost = cost;
        this.time = time;
        this.city = city;
    }
    
    //Get the city
    public City getCity() {
        return city;
    }
    
    // Get the time
    public int getTime() {
        return time;
    }
    
    // Get the cost
    public double getCost() {
        return cost;
    }
}

