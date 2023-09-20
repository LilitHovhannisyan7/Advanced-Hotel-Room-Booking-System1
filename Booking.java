import java.io.Serializable;
import java.time.LocalDate;


public class Booking implements Serializable
{
    private int roomId;
    private Customer customer;
    private LocalDate start;
    private LocalDate end;


    public Booking(int roomId, Customer customer, LocalDate start, LocalDate end)
    {
        this.roomId = roomId;
        this.customer = customer;
        this.start = start;
        this.end = end;
    }


    public int getRoomId()
    {
        return this.roomId;
    }


    public Customer getCustomer()
    {
        return this.customer;
    }
    public LocalDate getStart()
    {
        return this.start;
    }


    public LocalDate getEnd()
    {
        return this.end;
    }


}
