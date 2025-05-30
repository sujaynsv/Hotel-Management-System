# Hotel Management System

A Java-based console application for managing hotel operations, including room booking, food ordering, billing, and checkout. The system supports premium and standard single/double rooms, persists data to a file, and provides a user-friendly interface.

## Features
- View room details and availability.
- Book rooms with guest information.
- Order food from a menu (e.g., Burger, Pizza).
- Generate bills with room and food charges.
- Check out and deallocate rooms.
- Save hotel state to a file (`hotel_data.dat`) for persistence.

## Prerequisites
- Java Development Kit (JDK) 8 or later.
- A terminal or command-line interface.

## Usage
1. Compile the Java code:
   ```bash
   javac src/HotelManagementSystem.java
   ```
2. Run the program:
   ```bash
   java -cp src HotelManagementSystem
   ```
3. Follow the menu prompts to:
   - View room details (option 1).
   - Check availability (option 2).
   - Book a room (option 3).
   - Order food (option 4).
   - Check out (option 5).
   - Exit (option 6).

## Contributors
- Nimmagadda Sujay
- Shruthika Sunku

## Notes
- The program saves data to `hotel_data.dat` in the project directory.
- Ensure write permissions for the directory to avoid file I/O errors.
- Invalid inputs are handled with user-friendly error messages.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contributing
Contributions are welcome! Please fork the repository, create a new branch, and submit a pull request with your changes.
