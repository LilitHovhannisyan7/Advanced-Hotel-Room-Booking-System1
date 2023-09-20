import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main
{
    private static void printMenu()
    {
        String message = """
                Welcome to the Advanced Hotel Room Booking System
                1: Add a room
                2: Add a customer
                3: Book a room
                4: Save system state to a file
                5: Load the state room
                6: Generate report
                0: Exit
                """;
        System.out.println(message);

    }
    public static void main(String[] args)
    {
        Operator operator = Operator.getInstance();
        Scanner scanner = new Scanner(System.in);
        int number;
        int roomid = 1;
        do
        {
            printMenu();
            System.out.println("Please enter what you want: ");

            number = scanner.nextInt();
            if(number == 1)
            {

                String msg = """
                    The type for the room: 
                    1: Single
                    2: Double
                    3: Deluxe
                    """;
                System.out.println(msg);
                System.out.println("Please enter what type you want: ");
                Scanner scanner1 = new Scanner(System.in);
                Room room = new Room();
                int type = scanner1.nextInt();
                if(type == 1)
                {
                    room = new Room(roomid, "Single");
                    ++roomid;
                }
                else if(type == 2)
                {
                    room = new Room(roomid, "Double");
                    ++roomid;
                }
                else if(type == 3)
                {
                    room = new Room(roomid, "Deluxe");
                    ++roomid;
                }
                else
                {
                    System.out.println("Invalid input for type");
                }


                operator.addRoom(room);
            }
            else if(number == 2)
            {
                System.out.println("Please tell the customers name: ");
                Scanner scanner2 = new Scanner(System.in);
                String name = scanner2.nextLine();
                System.out.println("Please tell the customers contact email: ");
                String contactEmail = scanner2.nextLine();
                Customer customer = new Customer(name, contactEmail);
                operator.addCustomer(customer);
            }
            else if(number == 3)
            {
                Scanner scanner3 = new Scanner(System.in);
                System.out.println("Please enter the customer's contactEmail: ");
                String customerEmail = scanner3.nextLine();
                Customer customer = operator.getCustomerByEmail(customerEmail);
                if(customer == null)
                {
                    System.out.println("Invalid customer");
                    return;
                }
                System.out.println("Please enter the roomId: ");
                int roomId = scanner3.nextInt();
                Room room = operator.getRoomById(roomId);
                if(room == null)
                {
                    System.out.println("Invalid room");
                    return;
                }

                Scanner scanner7 = new Scanner(System.in);
                System.out.println("Please input the start date you want to book: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String date = scanner7.nextLine();
                LocalDate start= LocalDate.parse(date, formatter);

                System.out.println("Please input the end date you want to book: ");
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String date1 = scanner7.nextLine();
                LocalDate end = LocalDate.parse(date1, formatter1);


                operator.bookRoom(customer, room, start, end);

            }
            else if(number == 4)
            {
                Scanner scanner4 = new Scanner(System.in);
                System.out.println("Please enter the filePath you want to save the system state: ");
                String filePath = scanner4.nextLine();
                operator.saveSystemStateToFile(filePath);
            }
            else if(number == 5)
            {
                Scanner scanner5 = new Scanner(System.in);
                System.out.println("Please enter the filePath you want to load the system state from: ");
                String filePath = scanner5.nextLine();
                operator = operator.loadSystemStateFromFile(filePath);
            }
            else if(number == 6)
            {
                Scanner scanner5 = new Scanner(System.in);
                System.out.println("Please enter the roomId: ");
                int roomId1 = scanner5.nextInt();
                Scanner scanner6 = new Scanner(System.in);
                System.out.println("Please enter the filePath you want to save the system state: ");
                String filePath1 = scanner6.nextLine();
                operator.generateRoomBookingHistoryReport(roomId1,filePath1);
            }
            else if(number != 0)
            {
                System.out.println("Invalid input ");
                return;
            }

        }
        while(number != 0);





    }
}
