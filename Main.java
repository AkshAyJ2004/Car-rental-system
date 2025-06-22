import.java.util.ArrayList;
import.java.util.List;
import.java.util.Scanner;

class CAR {
    private String carid;
    private String model;
    private String brand;
    private double priceperday;
    private boolean isavailable;

    public CAR(String carid, String model, String brand, double priceperday) {
        this.carid = carid;
        this.model = model;
        this.brand = brand;
        this.priceperday= priceperday;
        this.isavailable = true;
    }

    public String getCarid(){
        return carid;
    }

    public String getModel() {
        return model;
    }

    public String getBrand(){
        return brand;
    }

    public double calculateprice(int daysofrental){
        return priceperday*daysofrental;
    }
}

class CUSTOMER {
    private String customerid;
    private String name;

    public CUSTOMER(String customerid, String name) {
        this.customerid = customerid;
        this.name = name;
    }
}

class RENTAL {

}

class CRSYSTEM {

}

public class Main {

}