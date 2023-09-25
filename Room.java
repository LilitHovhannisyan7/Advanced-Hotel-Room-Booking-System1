import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable
{
    private int roomId;
    private Type type;
    private String roomHas;
    protected double price;

    private List<Booking> bookings;


    public Room()
    {
        this.roomId = 1;
        this.type = Type.SINGLE;
        this.price = 20.0;
    }

    public Room(int roomId, Type type)
    {
        this.roomId = roomId;
        this.bookings = new ArrayList<>();
        switch(type)
        {
            case SINGLE:
                this.type = type;
                this.roomHas = "This room has a single bed, bathroom, TV, and closet";
                this.price = 20.0;
                break;
            case DOUBLE:
                this.type = type;
                this.roomHas = "Double Room: This room has a double bed, bathroom, TV, and closet";
                this.price = 35.0;
                break;
            case DELUXE:
                this.type = type;
                this.roomHas = "This room has a minibar, a bathtub, a king-size bed, and a sitting area.";
                this.price = 55.0;
                break;
            default:
                System.out.println("Sorry, but " + type + " is invalid type of room");
                break;
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

    public Type getType()
    {
        return this.type;
    }


    
    public void generateBookingHistoryReport(String filePath) 
    {
        try (FileWriter writer = new FileWriter(filePath)) 
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            StringBuilder str = new StringBuilder("Room ID: " + roomId + "\n" +
                    "Room Type: " + type + "\n" +
                    this.roomHas + "\n" +
                    "Booking History:\n");
            writer.write(String.valueOf(str));

            for (Booking booking : bookings) {
                writer.write("Customer: " + booking.getCustomer().getName() + "\n");
                writer.write("Booking Period: " + booking.getStart() + " to " + booking.getEnd() + "\n");
                writer.write("\n");
            }

            System.out.println("Booking history report saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error while saving the booking history report: " + e.getMessage());
        }
    
    }


}
