import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Room implements Serializable
{
    private int roomId;
    private String type;
    private String roomHave;
    protected double price;

    private ArrayList<Booking> bookings;


    public Room()
    {
        this.roomId = 1;
        this.type = "Single";
        this.price = 20.0;
    }

    public Room(int roomId, String type)
    {
        this.roomId = roomId;
        this.bookings = new ArrayList<>();
        if(type.equals("Single"))
        {
            this.type = type;
            this.roomHave = "This room has a single bed, bathroom, TV, and closet";
            this.price = 20.0;
        }
        else if(type.equals("Double"))
        {
            this.type = type;
            this.roomHave = "Double Room: This room has a double bed, bathroom, TV, and closet";
            this.price = 35.0;
        }
        else if(type.equals("Deluxe"))
        {
            this.type = type;
            this.roomHave = "This room has a minibar, a bathtub, a king-size bed, and a sitting area.";
            this.price = 55.0;
        }
        else
        {
            System.out.println("Sorry, but " + type + " is invalid type of room");

        }
    }


    public boolean isAvailable(LocalDate start, LocalDate end)
    {
        for(int i = 0; i < this.bookings.size(); ++i)
        {
            if(start.isBefore(bookings.get(i).getEnd()) && end.isAfter(bookings.get(i).getStart()))
            {
                return false;
            }
        }

        return true;
    }


    public void addBooking(Booking booking)
    {
        this.bookings.add(booking);
    }

    public int getId()
    {
        return this.roomId;
    }


    public double getPrice()
    {
        return this.price;
    }

    public String getType()
    {
        return this.type;
    }


    public void generateBookingHistoryReport(String filePath)
    {
        try
        {
            FileWriter writer = new FileWriter(filePath);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            writer.write("Room ID: " + roomId + "\n");
            writer.write("Room Type: " + type + "\n");
            writer.write(this.roomHave + "\n");
            writer.write("Booking History:\n");

            for (Booking booking : bookings)
            {
                writer.write("Customer: " + booking.getCustomer().getName() + "\n");
                writer.write("Booking Period: " + booking.getStart() + " to " + booking.getEnd() + "\n");
                writer.write("\n");
            }

            writer.close();
            System.out.println("Booking history report saved to " + filePath);

        } catch (IOException e)
        {
            System.out.println("Error while saving the booking history report: " + e.getMessage());
        }

    }






}
