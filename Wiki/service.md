public class Service {
    private String serviceID;
    private String name;
    private String description;
    private double price;

    public Service(String serviceID, String name, String description, double price) {
        this.serviceID = serviceID;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getServiceDetails() {
        return "Service ID: " + serviceID + 
               "\nName: " + name + 
               "\nDescription: " + description + 
               "\nPrice: $" + price;
    }

    // Getters and setters
    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
