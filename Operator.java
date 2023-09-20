import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Operator
{
    private ArrayList<Customer> customers;
    private ArrayList<Room> rooms;

    private static Operator instance;




    public static Operator getInstance() {
        if (instance == null) {
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
        for(int i = 0; i < this.customers.size(); ++i)
        {
            if(this.customers.get(i).getContactEmail().equals(customer.getContactEmail()))
            {
                System.out.println("Sorry but customer " + customer.getContactEmail() + " is already registered");
                return;
            }
        }

        customers.add(customer);
        System.out.println("Customer added successfully.");
    }

    public void addRoom(Room room)
    {

        for(int i = 0; i < this.rooms.size(); ++i)
        {
            if(this.rooms.get(i).getId() == room.getId())
            {
                System.out.println("Sorry but room " + room.getId() + " is added");
                return;
            }
        }
        rooms.add(room);
        System.out.println("Room added successfully.");
    }


    public void bookRoom(Customer customer, Room room, LocalDate start, LocalDate end)
    {
        for(int i = 0; i < this.rooms.size(); ++i)
        {
            if(this.rooms.get(i).getId() == room.getId())
            {


                if(room.isAvailable(start, end))
                {
                    Booking booking = new Booking(room.getId(),customer, start, end);
                    room.addBooking(booking);
                    customer.addBookingToCustomer(booking);
                    Bill bill = new Bill(room, customer, booking);
                    bill.print();
                    saveSystemStateToFile("bill.txt");

                }
                else
                {
                    System.out.println("This room is not available");
                    return;
                }

            }
        }
        System.out.println("Room booked successfully.");


    }



    public void saveSystemStateToFile(String filePath)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.rooms);
            out.writeObject(this.customers);
            out.close();
            fileOut.close();
            System.out.println("System state saved to " + filePath);
        }
        catch(IOException e)
        {
            System.out.println("Error while savingSystem");
        }

    }


    public Operator loadSystemStateFromFile(String filePath)
    {
        try
        {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            this.rooms = (ArrayList<Room>) in.readObject();
            this.customers  = (ArrayList<Customer>) in.readObject();
            in.close();
            fileIn.close();

            System.out.println("System state loaded from " + filePath);
        }
        catch(Exception e)
        {
            System.out.println("Error while loading bill");
        }
        return this;
    }


    public void generateRoomBookingHistoryReport(int roomId, String filePath)
    {
        for (int i = 0; i < this.rooms.size(); ++i)
        {
            if (this.rooms.get(i).getId() == roomId)
            {
                this.rooms.get(i).generateBookingHistoryReport(filePath);
                return;
            }
        }
        System.out.println("Room with ID " + roomId + " not found.");

    }


    public Room getRoomById(int roomId)
    {
        for (int i = 0; i < this.rooms.size(); ++i)
        {
            if (this.rooms.get(i).getId() == roomId)
            {
                return this.rooms.get(i);
            }
        }
        return null;
    }


    public Customer getCustomerByEmail(String contactEmail)
    {
        for (int i = 0; i < this.customers.size(); ++i)
        {
            if (this.customers.get(i).getContactEmail().equals(contactEmail))
            {
                return this.customers.get(i);
            }
        }
        return null;
    }
}