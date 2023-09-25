import java.time.temporal.ChronoUnit;

public class Bill
{
    private Room room;
    private Customer customer;
    private Booking booking;
    private double taxes;
    private double serviceFee;
    private double totalAmount;

    private final double taxe = 0.2;
    private final double fee = 0.1;

    public Bill(Room room, Customer customer, Booking booking)
    {
        this.room = room;
        this.customer = customer;
        this.booking = booking;
        this.taxes = ChronoUnit.DAYS.between(booking.getStart(), booking.getEnd()) * taxe * (room.getPrice());
        this.serviceFee = ChronoUnit.DAYS.between(booking.getStart(), booking.getEnd()) * fee * (room.getPrice() + this.taxes);
        this.totalAmount = this.taxes + this.serviceFee + this.room.getPrice();
    }


    public void print()
    {
        StringBuilder str = new StringBuilder("Customer Name: " + this.customer.getName() +
                '\n' + "Customer Email: " + this.customer.getContactEmail() +
                '\n' + "Room ID: " + this.room.getId() +
                '\n' + "Room Type: " + this.room.getType() +
                '\n' + "Booking Period: " + this.booking.getStart() + " to " + this.booking.getEnd() +
                '\n' + "Room Price: $" + this.room.getPrice() +
                '\n' + "Taxes (20%): $" + this.taxes +
                '\n' + "Service Fee (10%): $" + this.serviceFee +
                '\n' + "Total Amount: $" + this.totalAmount);

        System.out.println(str);
    }



}
