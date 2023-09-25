import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Operator
{
    private List<Customer> customers;
    private List<Room> rooms;

    private static Operator instance;


    public static Operator getInstance()
    {
        if (instance == null)
        {
            instance = new Operator();
        }
        return instance;
    }

    private Operator()
    {
        this.rooms = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer)
    {
        for (Customer value : this.customers) {
            if (value.getContactEmail().equals(customer.getContactEmail())) {
                System.out.println("Sorry but customer " + customer.getContactEmail() + " is already registered");
                return;
            }
        }

        customers.add(customer);
        System.out.println("Customer added successfully.");
    }

    public void addRoom(int roomId, Type type)
    {

        for (Room value : this.rooms) {
            if (value.getId() == roomId) {
                System.out.println("Sorry but room " + roomId + " is added");
                return;
            }
        }
        Room room = new Room(roomId, type);
        rooms.add(room);
        System.out.println("Room added successfully.");
    }


    public void bookRoom(Customer customer, Room room, LocalDate start, LocalDate end)
    {
        for (Room value : this.rooms) {
            if (value.getId() == room.getId()) {

                if (room.isAvailable(start, end)) {
                    Booking booking = new Booking(room.getId(), customer, start, end);
                    room.addBooking(booking);
                    customer.addBookingToCustomer(booking);
                    Bill bill = new Bill(room, customer, booking);
                    bill.print();
                    saveSystemStateToFile("bill.txt");

                } else {
                    System.out.println("This room is not available");
                    return;
                }

            }
        }
        System.out.println("Room booked successfully.");


    }


        public void saveSystemStateToFile(String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(this.rooms);
            out.writeObject(this.customers);

            System.out.println("System state saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error while savingSystem");
        }


    }


    public Operator loadSystemStateFromFile(String filePath)
    {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream in = new ObjectInputStream(fileIn))
        {

            this.rooms = (ArrayList<Room>) in.readObject();
            this.customers = (ArrayList<Customer>) in.readObject();

            System.out.println("System state loaded from " + filePath);
        }
        catch (Exception e)
        {
            System.out.println("Error while loading bill");
        }

        return this;
    }


    public void generateRoomBookingHistoryReport(int roomId, String filePath)
    {
        for (Room room : this.rooms) {
            if (room.getId() == roomId) {
                room.generateBookingHistoryReport(filePath);
                return;
            }
        }
        System.out.println("Room with ID " + roomId + " not found.");

    }

    public Room getRoomById(int roomId)
    {
        for (Room room : this.rooms) {
            if (room.getId() == roomId) {
                return room;
            }
        }
        return null;
    }


    public Customer getCustomerByEmail(String contactEmail)
    {
        for (Customer customer : this.customers)
        {
            if (customer.getContactEmail().equals(contactEmail))
            {
                return customer;
            }
        }
        return null;

    }
}
