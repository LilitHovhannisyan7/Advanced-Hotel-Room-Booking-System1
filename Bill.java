import java.time.temporal.ChronoUnit;

public class Bill
{
    private Room room;
    private Customer customer;
    private Booking booking;
    private double taxes;
    private double serviceFee;
    private double totalAmount;


    public Bill(Room room, Customer customer, Booking booking)
    {
        this.room = room;
        this.customer = customer;
        this.booking = booking;
        this.taxes = ChronoUnit.DAYS.between(booking.getStart(), booking.getEnd()) * 0.2 * (room.getPrice());
        this.serviceFee = ChronoUnit.DAYS.between(booking.getStart(), booking.getEnd()) * 0.1 * (room.getPrice() + this.taxes);
        this.totalAmount = this.taxes + this.serviceFee + this.room.getPrice();
    }


    public void print()
    {
        System.out.println("Customer Name: " + this.customer.getName());
        System.out.println("Customer Email: " + this.customer.getContactEmail());
        System.out.println("Room ID: " + this.room.getId());
        System.out.println("Room Type: " + this.room.getType());
        System.out.println("Booking Period: " + this.booking.getStart() + " to " + this.booking.getEnd());
        System.out.println("Room Price: $" + this.room.getPrice());
        System.out.println("Taxes (20%): $" + this.taxes);
        System.out.println("Service Fee (10%): $" + this.serviceFee);
        System.out.println("Total Amount: $" + this.totalAmount);
    }



}
