import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public boolean isavailable(){
        return isavailable;
    }

    public void rent(){
        isavailable = false;
    }

    public void returncar(){
        isavailable = true;
    }
}

class CUSTOMER {
    private String customerid;
    private String name;

    public CUSTOMER(String customerid, String name) {
        this.customerid = customerid;
        this.name = name;
    }

    public String getCustomerid(){
        return customerid;
    }

    public String getName() {
        return name;
    }
}

class RENTAL {
    private CAR car;
    private CUSTOMER customer;
    private int days;

    public RENTAL(CAR car, CUSTOMER customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public CAR getCar(){
        return car;
    }

    public CUSTOMER getCustomer(){
        return customer;
    }

    public int getDays(){
        return days;
    }
}

class CRSYSTEM {
    private List<CAR> cars;
    private List<CUSTOMER> customers;
    private List<RENTAL> rentals;

    public CRSYSTEM(){
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(CAR car){
        cars.add(car);
    }

    public void addCustomer(CUSTOMER customer) {
        customers.add(customer);
    }

    public void rentcar(CAR car, CUSTOMER customer, int days) {
        if(car.isavailable()){
            car.rent();
            rentals.add(new RENTAL(car, customer, days));
        }
        else{
            System.out.println("car is not available for rent");
        }
    }

    public void returncar(CAR car){
        car.returncar();
        RENTAL rentedtoremove = null;
        for(RENTAL rental : rentals){
            if(rental.getCar() == car){
                rentedtoremove = rental;
                break;
            }
        }
        if(rentedtoremove != null){
            rentals.remove(rentedtoremove);
        }
        else {
            System.out.println("car was not rented");
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("========== Car Rental System ==========");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1) {
                System.out.println("\n==== Rent a Car ====\n");
                System.out.print("Enter your name: ");
                String customername = sc.nextLine();

                System.out.println("rent a car");
                for (CAR car : cars) {
                    if (car.isavailable()) {
                        System.out.println(car.getCarid() + "-" + car.getBrand() + "-" + car.getModel());
                    }
                }

                System.out.print("Enter the car ID you want to rent: ");
                String carid = sc.nextLine();

                System.out.print("Enter the number of days for rental: ");
                int daysofrental = sc.nextInt();
                sc.nextLine(); // Consume newline

                CUSTOMER newcustomer = new CUSTOMER("CUS" + (customers.size() + 1), customername);
                 addCustomer(newcustomer);


                CAR selectedcar = null;
                for (CAR car : cars) {
                    if (car.getCarid().equals(carid) && car.isavailable()) {
                        selectedcar = car;
                        break;
                    }
                }

                if (selectedcar != null) {
                    double totalprice = selectedcar.calculateprice(daysofrental);
                    System.out.println("==== Rental Information ====");
                    System.out.println("Customer ID: " + newcustomer.getCustomerid());
                    System.out.println("Customer Name: " + newcustomer.getName());
                    System.out.println("Car: " + selectedcar.getBrand() + " " + selectedcar.getModel());
                    System.out.println("Rental Days: " + daysofrental);
                    System.out.printf("Total Price: $%.2f%n", totalprice);

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = sc.nextLine();
                    sc.nextLine();


                    if(confirm.equalsIgnoreCase("Y")) {
                        rentcar(selectedcar, newcustomer, daysofrental);
                        System.out.println("\nCar rented successfully.");
                    } else {
                        System.out.println("\nRental canceled.");
                    }
                } else {
                    System.out.println("\nInvalid car selection or car not available for rent.");
                }
            } else if (choice==2) {
                System.out.println("\n== Return a Car ==\n");
                System.out.print("Enter the car ID you want to return: ");
                String carid = sc.nextLine();

                CAR cartoreturn = null;
                for(CAR car: cars){
                    if(car.getCarid().equals(carid) && !car.isavailable()){
                        cartoreturn = car;
                        break;
                    }
                }

                if(cartoreturn !=null){
                    CUSTOMER customer = null;
                    for(RENTAL rental: rentals){
                        if(rental.getCar() == cartoreturn){
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    if(customer!=null){
                        returncar(cartoreturn);
                        System.out.println("Car returned successfully by " + customer.getName());
                    } else {
                        System.out.println("Car was not rented or rental information is missing.");
                    }
                }
                else {
                    System.out.println("Invalid car ID or car is not rented.");
                }
            }
            else if(choice ==3){
                break;
            }
            else{
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        System.out.println("\nThank you for using the Car Rental System!");
    }
}

public class Main {
    public static void main(String[] args){
        CRSYSTEM rentalsystem = new CRSYSTEM();

        CAR car1 = new CAR("C001", "Toyota", "Camry", 60.0); // Different base price per day for each car
        CAR car2 = new CAR("C002", "Honda", "Accord", 70.0);
        CAR car3 = new CAR("C003", "Mahindra", "Thar", 150.0);
        rentalsystem.addCar(car1);
        rentalsystem.addCar(car2);
        rentalsystem.addCar(car3);

        rentalsystem.menu();
    }
}
