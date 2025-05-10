import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Class to represent a menu item
class MenuItem implements Serializable {
    int itemId;
    int quantity;
    double cost;

    MenuItem(int itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
        switch (itemId) {
            case 1: cost = quantity * 45.0; break; // Burger
            case 2: cost = quantity * 55.0; break; // Pizza
            case 3: cost = quantity * 65.0; break; // Salad
            case 4: cost = quantity * 25.0; break; // Juice
        }
    }
}

// Class to represent a single room
class SingleRoom implements Serializable {
    String guestName;
    String phone;
    String gender;
    ArrayList<MenuItem> orders = new ArrayList<>();

    SingleRoom() {
        this.guestName = "";
    }

    SingleRoom(String guestName, String phone, String gender) {
        this.guestName = guestName;
        this.phone = phone;
        this.gender = gender;
    }
}

// Class to represent a double room, extending single room
class DoubleRoom extends SingleRoom implements Serializable {
    String secondGuestName;
    String secondPhone;
    String secondGender;

    DoubleRoom() {
        super();
        this.secondGuestName = "";
    }

    DoubleRoom(String guestName, String phone, String gender, String secondGuestName, String secondPhone, String secondGender) {
        super(guestName, phone, gender);
        this.secondGuestName = secondGuestName;
        this.secondPhone = secondPhone;
        this.secondGender = secondGender;
    }
}

// Custom exception for unavailable rooms
class RoomUnavailableException extends Exception {
    @Override
    public String toString() {
        return "Room is not available!";
    }
}

// Class to hold all rooms
class HotelData implements Serializable {
    DoubleRoom premiumDouble[] = new DoubleRoom[5]; // Premium double rooms
    DoubleRoom standardDouble[] = new DoubleRoom[10]; // Standard double rooms
    SingleRoom premiumSingle[] = new SingleRoom[5]; // Premium single rooms
    SingleRoom standardSingle[] = new SingleRoom[10]; // Standard single rooms
}

// Main hotel management class
class HotelManager {
    static HotelData hotelData = new HotelData();
    static Scanner scanner = new Scanner(System.in);

    // Collect guest details and assign room
    static void registerGuest(int roomType, int roomIndex) {
        String name, phone, gender, name2 = null, phone2 = null, gender2 = "";
        System.out.print("\nEnter guest name: ");
        name = scanner.next();
        System.out.print("Enter phone number: ");
        phone = scanner.next();
        System.out.print("Enter gender: ");
        gender = scanner.next();
        if (roomType < 3) {
            System.out.print("Enter second guest name: ");
            name2 = scanner.next();
            System.out.print("Enter second phone number: ");
            phone2 = scanner.next();
            System.out.print("Enter second gender: ");
            gender2 = scanner.next();
        }

        switch (roomType) {
            case 1: hotelData.premiumDouble[roomIndex] = new DoubleRoom(name, phone, gender, name2, phone2, gender2); break;
            case 2: hotelData.standardDouble[roomIndex] = new DoubleRoom(name, phone, gender, name2, phone2, gender2); break;
            case 3: hotelData.premiumSingle[roomIndex] = new SingleRoom(name, phone, gender); break;
            case 4: hotelData.standardSingle[roomIndex] = new SingleRoom(name, phone, gender); break;
            default: System.out.println("Invalid room type"); break;
        }
    }

    // Book a room
    static void reserveRoom(int roomType) {
        int roomIndex;
        System.out.println("\nAvailable rooms: ");
        switch (roomType) {
            case 1:
                for (int i = 0; i < hotelData.premiumDouble.length; i++) {
                    if (hotelData.premiumDouble[i] == null) System.out.print((i + 1) + ", ");
                }
                System.out.print("\nEnter room number: ");
                try {
                    roomIndex = scanner.nextInt() - 1;
                    if (roomIndex < 0 || roomIndex >= hotelData.premiumDouble.length || hotelData.premiumDouble[roomIndex] != null)
                        throw new RoomUnavailableException();
                    registerGuest(roomType, roomIndex);
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    return;
                }
                break;
            case 2:
                for (int i = 0; i < hotelData.standardDouble.length; i++) {
                    if (hotelData.standardDouble[i] == null) System.out.print((i + 11) + ", ");
                }
                System.out.print("\nEnter room number: ");
                try {
                    roomIndex = scanner.nextInt() - 11;
                    if (roomIndex < 0 || roomIndex >= hotelData.standardDouble.length || hotelData.standardDouble[roomIndex] != null)
                        throw new RoomUnavailableException();
                    registerGuest(roomType, roomIndex);
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    return;
                }
                break;
            case 3:
                for (int i = 0; i < hotelData.premiumSingle.length; i++) {
                    if (hotelData.premiumSingle[i] == null) System.out.print((i + 21) + ", ");
                }
                System.out.print("\nEnter room number: ");
                try {
                    roomIndex = scanner.nextInt() - 21;
                    if (roomIndex < 0 || roomIndex >= hotelData.premiumSingle.length || hotelData.premiumSingle[roomIndex] != null)
                        throw new RoomUnavailableException();
                    registerGuest(roomType, roomIndex);
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    return;
                }
                break;
            case 4:
                for (int i = 0; i < hotelData.standardSingle.length; i++) {
                    if (hotelData.standardSingle[i] == null) System.out.print((i + 31) + ", ");
                }
                System.out.print("\nEnter room number: ");
                try {
                    roomIndex = scanner.nextInt() - 31;
                    if (roomIndex < 0 || roomIndex >= hotelData.standardSingle.length || hotelData.standardSingle[roomIndex] != null)
                        throw new RoomUnavailableException();
                    registerGuest(roomType, roomIndex);
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    return;
                }
                break;
            default:
                System.out.println("Invalid room type");
                return;
        }
        System.out.println("Room reserved successfully!");
    }

    // Display room features
    static void displayRoomFeatures(int roomType) {
        switch (roomType) {
            case 1: System.out.println("Premium Double: 1 double bed, AC, Breakfast, $500/day"); break;
            case 2: System.out.println("Standard Double: 1 double bed, No AC, Breakfast, $350/day"); break;
            case 3: System.out.println("Premium Single: 1 single bed, AC, Breakfast, $250/day"); break;
            case 4: System.out.println("Standard Single: 1 single bed, No AC, Breakfast, $150/day"); break;
            default: System.out.println("Invalid room type"); break;
        }
    }

    // Check room availability
    static void checkAvailability(int roomType) {
        int count = 0;
        switch (roomType) {
            case 1: for (SingleRoom room : hotelData.premiumDouble) if (room == null) count++; break;
            case 2: for (SingleRoom room : hotelData.standardDouble) if (room == null) count++; break;
            case 3: for (SingleRoom room : hotelData.premiumSingle) if (room == null) count++; break;
            case 4: for (SingleRoom room : hotelData.standardSingle) if (room == null) count++; break;
            default: System.out.println("Invalid room type"); return;
        }
        System.out.println("Available rooms: " + count);
    }

    // Generate bill
    static void generateBill(int roomIndex, int roomType) {
        double total = 0;
        String[] menu = {"Burger", "Pizza", "Salad", "Juice"};
        System.out.println("\n===== Bill =====");
        switch (roomType) {
            case 1:
                total += 500;
                System.out.println("Room Charge: $500");
                System.out.println("\nFood Orders:");
                System.out.println("Item      Qty    Cost");
                System.out.println("---------------------");
                for (MenuItem item : hotelData.premiumDouble[roomIndex].orders) {
                    total += item.cost;
                    System.out.printf("%-10s%-7d%-7.2f%n", menu[item.itemId - 1], item.quantity, item.cost);
                }
                break;
            case 2:
                total += 350;
                System.out.println("Room Charge: $350");
                System.out.println("\nFood Orders:");
                System.out.println("Item      Qty    Cost");
                System.out.println("---------------------");
                for (MenuItem item : hotelData.standardDouble[roomIndex].orders) {
                    total += item.cost;
                    System.out.printf("%-10s%-7d%-7.2f%n", menu[item.itemId - 1], item.quantity, item.cost);
                }
                break;
            case 3:
                total += 250;
                System.out.println("Room Charge: $250");
                System.out.println("\nFood Orders:");
                System.out.println("Item      Qty    Cost");
                System.out.println("---------------------");
                for (MenuItem item : hotelData.premiumSingle[roomIndex].orders) {
                    total += item.cost;
                    System.out.printf("%-10s%-7d%-7.2f%n", menu[item.itemId - 1], item.quantity, item.cost);
                }
                break;
            case 4:
                total += 150;
                System.out.println("Room Charge: $150");
                System.out.println("\nFood Orders:");
                System.out.println("Item      Qty    Cost");
                System.out.println("---------------------");
                for (MenuItem item : hotelData.standardSingle[roomIndex].orders) {
                    total += item.cost;
                    System.out.printf("%-10s%-7d%-7.2f%n", menu[item.itemId - 1], item.quantity, item.cost);
                }
                break;
            default:
                System.out.println("Invalid room type");
                return;
        }
        System.out.println("\nTotal: $" + total);
    }

    // Check out and deallocate room
    static void checkOut(int roomIndex, int roomType) {
        switch (roomType) {
            case 1:
                if (hotelData.premiumDouble[roomIndex] == null) {
                    System.out.println("Room is already empty");
                    return;
                }
                System.out.println("Guest: " + hotelData.premiumDouble[roomIndex].guestName);
                break;
            case 2:
                if (hotelData.standardDouble[roomIndex] == null) {
                    System.out.println("Room is already empty");
                    return;
                }
                System.out.println("Guest: " + hotelData.standardDouble[roomIndex].guestName);
                break;
            case 3:
                if (hotelData.premiumSingle[roomIndex] == null) {
                    System.out.println("Room is already empty");
                    return;
                }
                System.out.println("Guest: " + hotelData.premiumSingle[roomIndex].guestName);
                break;
            case 4:
                if (hotelData.standardSingle[roomIndex] == null) {
                    System.out.println("Room is already empty");
                    return;
                }
                System.out.println("Guest: " + hotelData.standardSingle[roomIndex].guestName);
                break;
            default:
                System.out.println("Invalid room type");
                return;
        }
        System.out.print("Proceed with checkout? (y/n): ");
        char confirm = scanner.next().charAt(0);
        if (confirm == 'y' || confirm == 'Y') {
            generateBill(roomIndex, roomType);
            switch (roomType) {
                case 1: hotelData.premiumDouble[roomIndex] = null; break;
                case 2: hotelData.standardDouble[roomIndex] = null; break;
                case 3: hotelData.premiumSingle[roomIndex] = null; break;
                case 4: hotelData.standardSingle[roomIndex] = null; break;
            }
            System.out.println("Checkout completed!");
        }
    }

    // Order food
    static void placeOrder(int roomIndex, int roomType) {
        try {
            System.out.println("\n===== Menu =====\n1. Burger\t$45\n2. Pizza\t$55\n3. Salad\t$65\n4. Juice\t$25\n");
            char more;
            do {
                System.out.print("Enter item number: ");
                int itemId = scanner.nextInt();
                System.out.print("Enter quantity: ");
                int qty = scanner.nextInt();
                switch (roomType) {
                    case 1: hotelData.premiumDouble[roomIndex].orders.add(new MenuItem(itemId, qty)); break;
                    case 2: hotelData.standardDouble[roomIndex].orders.add(new MenuItem(itemId, qty)); break;
                    case 3: hotelData.premiumSingle[roomIndex].orders.add(new MenuItem(itemId, qty)); break;
                    case 4: hotelData.standardSingle[roomIndex].orders.add(new MenuItem(itemId, qty)); break;
                }
                System.out.print("Order more? (y/n): ");
                more = scanner.next().charAt(0);
            } while (more == 'y' || more == 'Y');
        } catch (NullPointerException e) {
            System.out.println("Room not occupied");
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }
}

// Thread to save hotel data
class DataSaver implements Runnable {
    HotelData data;

    DataSaver(HotelData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            FileOutputStream fos = new FileOutputStream("hotel_data.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Error saving data: " + e);
        }
    }
}

// Main class
public class HotelManagementSystem {
    public static void main(String[] args) {
        try {
            File file = new File("hotel_data.dat");
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                HotelManager.hotelData = (HotelData) ois.readObject();
                ois.close();
                fis.close();
            }

            Scanner scanner = new Scanner(System.in);
            char continueLoop;
            do {
                System.out.println("\n===== Hotel Management System =====");
                System.out.println("1. View Room Details");
                System.out.println("2. Check Room Availability");
                System.out.println("3. Reserve Room");
                System.out.println("4. Order Food");
                System.out.println("5. Check Out");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();

                int roomType, roomIndex;
                switch (choice) {
                    case 1:
                        System.out.println("\nRoom Types:\n1. Premium Double\n2. Standard Double\n3. Premium Single\n4. Standard Single");
                        System.out.print("Enter room type: ");
                        roomType = scanner.nextInt();
                        HotelManager.displayRoomFeatures(roomType);
                        break;
                    case 2:
                        System.out.println("\nRoom Types:\n1. Premium Double\n2. Standard Double\n3. Premium Single\n4. Standard Single");
                        System.out.print("Enter room type: ");
                        roomType = scanner.nextInt();
                        HotelManager.checkAvailability(roomType);
                        break;
                    case 3:
                        System.out.println("\nRoom Types:\n1. Premium Double\n2. Standard Double\n3. Premium Single\n4. Standard Single");
                        System.out.print("Enter room type: ");
                        roomType = scanner.nextInt();
                        HotelManager.reserveRoom(roomType);
                        break;
                    case 4:
                        System.out.print("Enter room number: ");
                        roomIndex = scanner.nextInt();
                        if (roomIndex > 40) System.out.println("Invalid room number");
                        else if (roomIndex > 30) HotelManager.placeOrder(roomIndex - 31, 4);
                        else if (roomIndex > 20) HotelManager.placeOrder(roomIndex - 21, 3);
                        else if (roomIndex > 10) HotelManager.placeOrder(roomIndex - 11, 2);
                        else if (roomIndex > 0) HotelManager.placeOrder(roomIndex - 1, 1);
                        else System.out.println("Invalid room number");
                        break;
                    case 5:
                        System.out.print("Enter room number: ");
                        roomIndex = scanner.nextInt();
                        if (roomIndex > 40) System.out.println("Invalid room number");
                        else if (roomIndex > 30) HotelManager.checkOut(roomIndex - 31, 4);
                        else if (roomIndex > 20) HotelManager.checkOut(roomIndex - 21, 3);
                        else if (roomIndex > 10) HotelManager.checkOut(roomIndex - 11, 2);
                        else if (roomIndex > 0) HotelManager.checkOut(roomIndex - 1, 1);
                        else System.out.println("Invalid room number");
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid choice");
                }

                System.out.print("\nContinue? (y/n): ");
                continueLoop = scanner.next().charAt(0);
            } while (continueLoop == 'y' || continueLoop == 'Y');

            Thread saver = new Thread(new DataSaver(HotelManager.hotelData));
            saver.start();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}