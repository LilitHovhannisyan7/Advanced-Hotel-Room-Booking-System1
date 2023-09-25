import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;


enum Menu
{

    ADDROOM(1),
    ADDCUSTOMER(2),
    BOOKROOM(3),
    SAVESYSTEM(4),
    LOAD(5),
    MAKEREPORT(6),
    EXIT(0);
    private final int number;
    private Menu(int number)
    {
        this.number = number;
    }


}


enum Type
{
    SINGLE(1),
    DOUBLE(2),
    DELUXE(3);
    private final int number;
    private Type(int number)
    {
        this.number = number;
    }
}
public class Main
{
    private static void printMenu()
    {
        String message = """
                Welcome to the Advanced Hotel Room Booking System
                1: Add a Room
                2: Add a Customer
                3: Book a Room for a Customer
                4: Save System State to a File
                5: Load the state Room
                6: Make report
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
        try {
            do {
                printMenu();
                System.out.println("Please enter what you want: ");

                number = scanner.nextInt();

                switch (number) {
                    case 1:
                        System.out.println("You chose: " + Menu.ADDROOM);
                        String msg = """
                                The type for the room: 
                                1: Single
                                2: Double
                                3: Deluxe
                                """;
                        System.out.println(msg);
                        System.out.println("Please enter what type you want: ");
                        scanner.nextLine();
                        Room room = new Room();
                        int type = scanner.nextInt();
                        switch (type) {
                            case 1:
                                System.out.println("You chose: " + Type.SINGLE);
                                room = new Room(roomid, Type.SINGLE);
                                ++roomid;
                                break;
                            case 2:
                                System.out.println("You chose: " + Type.DOUBLE);
                                room = new Room(roomid, Type.DOUBLE);
                                ++roomid;
                                break;
                            case 3:
                                System.out.println("You chose: " + Type.DELUXE);
                                room = new Room(roomid, Type.DELUXE);
                                ++roomid;
                                break;
                            default:
                                System.out.println("Invalid input for type");
                                break;
                        }

                        operator.addRoom(room.getId(), room.getType());
                        break;
                    case 2:
                        System.out.println("Please tell the customers name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        System.out.println("Please tell the customers contact email: ");
                        String contactEmail = scanner.nextLine();
                        Customer customer = new Customer(name, contactEmail);
                        operator.addCustomer(customer);
                        break;
                    case 3:
                        scanner.nextLine();
                        System.out.println("Please enter the customer's contactEmail: ");
                        String customerEmail = scanner.nextLine();
                        Customer customer1 = operator.getCustomerByEmail(customerEmail);
                        if (customer1 == null) {
                            System.out.println("Invalid customer");
                            return;
                        }
                        System.out.println("Please enter the roomId: ");
                        int roomId = scanner.nextInt();
                        Room room1 = operator.getRoomById(roomId);
                        if (room1 == null) {
                            System.out.println("Invalid room");
                            return;
                        }

                        Scanner scanner7 = new Scanner(System.in);
                        System.out.println("Please input the start date you want to book: ");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String date = scanner7.nextLine();
                        LocalDate start = LocalDate.parse(date, formatter);

                        System.out.println("Please input the end date you want to book: ");
                        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String date1 = scanner7.nextLine();
                        LocalDate end = LocalDate.parse(date1, formatter1);


                        operator.bookRoom(customer1, room1, start, end);
                        break;
                    case 4:
                        scanner.nextLine();
                        System.out.println("Please enter the filePath you want to save the system state: ");
                        String filePath = scanner.nextLine();
                        operator.saveSystemStateToFile(filePath);
                        break;
                    case 5:
                        scanner.nextLine();
                        System.out.println("Please enter the filePath you want to load the system state from: ");
                        String filePath1 = scanner.nextLine();
                        operator = operator.loadSystemStateFromFile(filePath1);
                        break;
                    case 6:
                        scanner.nextLine();
                        System.out.println("Please enter the roomId: ");
                        int roomId1 = scanner.nextInt();
                        Scanner scanner6 = new Scanner(System.in);
                        System.out.println("Please enter the filePath you want to save the system state: ");
                        String filePath2 = scanner6.nextLine();
                        operator.generateRoomBookingHistoryReport(roomId1, filePath2);
                        break;
                }
            }
            while (number != 0);
        }
        catch(InputMismatchException e)
        {
            System.out.println("Invalid input");
        }

    }
}
