import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Customer implements Serializable
{
    private String name;
    private String contactEmail;

    private List<Booking> customerBookings;

    public Customer(String name, String contactEmail)
    {
        this.name = name;
        this.contactEmail = contactEmail;
        this.customerBookings = new ArrayList<>();
    }

    public void addBookingToCustomer(Booking booking)
    {
        this.customerBookings.add(booking);
    }


    public String getName()
    {
        return this.name;
    }


    public String getContactEmail()
    {
        return this.contactEmail;
    }


}
