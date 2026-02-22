import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class HotelFileSystem {
    public static class User {
        HotelFileSystem hotelFileSystem = new HotelFileSystem();
        
        String role, name, email, mobile, gender;
    
        public User(String role, String name, String email, String mobile, String gender) {
            this.role = role;
            this.name = name;
            this.email = email;
            this.mobile = mobile;
            this.gender = gender;
        }
    
        // Convert to CSV format for saving to file
        public String toCsv() {
            return String.join(",", role, name, email, mobile, gender);
        }
        
    
        // Create User from CSV string
        public static User fromCsv(String csv) {
            String[] parts = csv.split(",");
            if (parts.length != 5) {
                throw new IllegalArgumentException("Invalid CSV format for User: " + csv);
            }
            return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
        }
    }
    
    private static final String USERS_FILE_NAME = "bookings.txt";
    private static List<User> users = new ArrayList<>();
    public HotelFileSystem hotelFileSystem;
    
        public void addUser(User user) {
            users.add(user);
            saveUsersToFile();
        }
        
        public void saveUsersToFile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE_NAME))) {
                for (User user : users) {
                    writer.write(user.toCsv());
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Error saving users to file: " + e.getMessage());
            }
            
        }
        
    
        public void loadUsersFromFile() {
            users.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE_NAME))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        users.add(User.fromCsv(line));
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("No users file found. Starting with an empty list.");
            } catch (IOException e) {
                System.err.println("Error loading users from file: " + e.getMessage());
            }
            hotelFileSystem.loadUsersFromFile();
        
    }

    public static class Booking {
        String transactionId, firstName, lastName, roomNo, roomType, checkInDate, checkOutDate, amenities, status;
        double roomRate, totalCost;
        int noOfDays, noOfAdults, noOfChildren;
        public Object Meals;
        public Booking(String transactionId, String firstName, String lastName, String roomNo, String roomType,
            double roomRate, String checkInDate, String checkOutDate, int noOfDays, int noOfAdults,
            int noOfChildren,int Meals, String amenities, double totalCost, String Status) {
            this.transactionId = transactionId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.roomNo = roomNo;
            this.roomType = roomType;
            this.roomRate = roomRate;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
            this.noOfDays = noOfDays;
            this.noOfAdults = noOfAdults;
            this.noOfChildren = noOfChildren;
            this.Meals = Meals;                           
        
                
            this.amenities = amenities;
            this.totalCost = totalCost;
            this.status = Status;
        }

        // Convert to CSV string for saving to file
        public String toCsv() {
            return String.join(",",
                transactionId, firstName, lastName, roomNo, roomType, 
                String.valueOf(roomRate), checkInDate, checkOutDate,
                String.valueOf(noOfDays), String.valueOf(noOfAdults),
                String.valueOf(noOfChildren),String.valueOf(Meals), amenities, 
                String.valueOf(totalCost), String.valueOf(status));
        }

        // Create a Booking object from CSV string
        public static Booking fromCsv(String csv) {
            String[] parts = csv.split(",");
            if (parts.length < 15) {
                throw new IllegalArgumentException("Invalid CSV format: " + csv);
            }
            try {
                return new Booking(
                    parts[0], parts[1], parts[2], parts[3], parts[4],
                    Double.parseDouble(parts[5]), parts[6], parts[7],
                    Integer.parseInt(parts[8]), Integer.parseInt(parts[9]),
                    Integer.parseInt(parts[10]), Integer.parseInt(parts[11]),
                    parts[12], Double.parseDouble(parts[13]), parts[14]
                );
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format in CSV: " + csv, e);
            }
        }
        
    }

    private static List<Booking> bookings = new ArrayList<>();
        private static final String FILE_NAME = "bookings.txt";
    
        public void addBooking(Booking booking) {
            bookings.add(booking);
            saveToFile();
        }
    
        public static List<Booking> getAllBookings() {
            return bookings;
    }

    public void deleteBooking(String transactionId) {
        bookings.removeIf(b -> b.transactionId.equals(transactionId));
        saveToFile();
    }

    public Booking findBooking(String transactionId) {
        return bookings.stream().filter(b -> b.transactionId.equals(transactionId)).findFirst().orElse(null);
    }

    // Save bookings to file
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Booking booking : bookings) {
                writer.write(booking.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving bookings to file: " + e.getMessage());
        }
    }

    // Load bookings from file
    public void loadFromFile() {
        bookings.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    try {
                        bookings.add(Booking.fromCsv(line));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Skipping invalid line: " + e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No bookings file found. Starting with an empty list.");
        } catch (IOException e) {
            System.err.println("Error loading bookings from file: " + e.getMessage());
        }
    }

    public void generateBookingReport() {
        File pdfFile = new File("Booking_Report.pdf");
        try (FileOutputStream fos = new FileOutputStream(pdfFile);
             PrintStream printer = new PrintStream(fos)) {
    
            // Debugging: Log all bookings
            System.out.println("Generating Booking Report...");
            System.out.println("Total bookings: " + getAllBookings().size());
    
            // PDF Header
            printer.println("%PDF-1.4");
            printer.println("1 0 obj << /Type /Catalog /Pages 2 0 R >> endobj");
            printer.println("2 0 obj << /Type /Pages /Kids [3 0 R] /Count 1 >> endobj");
            printer.println("3 0 obj << /Type /Page /Parent 2 0 R /MediaBox [0 0 612 792] /Contents 4 0 R >> endobj");
    
            // Prepare Content
            StringBuilder content = new StringBuilder();
            content.append("BOOKING REPORT\n")
                   .append("=================\n")
                   .append(String.format("%-15s %-15s %-15s %-15s %-15s\n",
                           "Transaction ID", "First Name", "Last Name", "Room No", "Status"))
                   .append("--------------------------------------------------------------\n");
    
            for (Booking booking : getAllBookings()) {
                content.append(String.format("%-15s %-15s %-15s %-15s %-15s\n",
                        booking.transactionId, booking.firstName, booking.lastName,
                        booking.roomNo, booking.status));
            }
    
            // Debugging: Log content before writing
            System.out.println("Generated Content:");
            System.out.println(content.toString());
    
            // PDF Content
            String contentStr = content.toString();
            printer.println("4 0 obj << /Length " + contentStr.length() + " >> stream");
            printer.println(contentStr);
            printer.println("endstream endobj");
            printer.println("xref");
            printer.println("trailer << /Root 1 0 R >>");
            printer.println("startxref");
            printer.println("%%EOF");
    
            System.out.println("Booking Report generated successfully: " + pdfFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error generating Booking Report: " + e.getMessage());
        }
    }

    public void generateRevenueReport() {
        File pdfFile = new File("Revenue_Report.pdf");
        try (FileOutputStream fos = new FileOutputStream(pdfFile);
             PrintStream printer = new PrintStream(fos)) {
    
            // Debugging: Log all bookings
            System.out.println("Generating Revenue Report...");
            System.out.println("Total bookings: " + getAllBookings().size());
    
            // PDF Header
            printer.println("%PDF-1.4");
            printer.println("1 0 obj << /Type /Catalog /Pages 2 0 R >> endobj");
            printer.println("2 0 obj << /Type /Pages /Kids [3 0 R] /Count 1 >> endobj");
            printer.println("3 0 obj << /Type /Page /Parent 2 0 R /MediaBox [0 0 612 792] /Contents 4 0 R >> endobj");
    
            // Prepare Content
            StringBuilder content = new StringBuilder();
            content.append("REVENUE REPORT\n")
                   .append("=================\n")
                   .append(String.format("%-15s %-15s %-15s %-15s\n",
                           "Transaction ID", "Room Rate", "No. of Days", "Total Revenue"))
                   .append("----------------------------------------------------\n");
    
            double totalRevenue = 0.0;
            for (Booking booking : getAllBookings()) {
                double revenue = booking.roomRate * booking.noOfDays;
                totalRevenue += revenue;
                content.append(String.format("%-15s %-15.2f %-15d %-15.2f\n",
                        booking.transactionId, booking.roomRate, booking.noOfDays, revenue));
            }
            content.append("\nTotal Revenue: ").append(totalRevenue);
    
            // Debugging: Log content before writing
            System.out.println("Generated Content:");
            System.out.println(content.toString());
    
            // PDF Content
            String contentStr = content.toString();
            printer.println("4 0 obj << /Length " + contentStr.length() + " >> stream");
            printer.println(contentStr);
            printer.println("endstream endobj");
            printer.println("xref");
            printer.println("trailer << /Root 1 0 R >>");
            printer.println("startxref");
            printer.println("%%EOF");
    
            System.out.println("Revenue Report generated successfully: " + pdfFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error generating Revenue Report: " + e.getMessage());
        }
    }
    
    

    public void printReport(File file) {
        if (file == null || !file.exists() || !file.canRead()) {
            System.err.println("Invalid file: " + (file == null ? "null" : file.getAbsolutePath()));
            return;
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            Doc doc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
            if (printService != null) {
                DocPrintJob printJob = printService.createPrintJob();
                printJob.print(doc, null);
                System.out.println("Report sent to printer: " + file.getName());
            } else {
                System.out.println("No printer found.");
            }
            fis.close();
        } catch (Exception e) {
            System.err.println("Error printing report: " + e.getMessage());
        }
    }

    public HotelFileSystem.User[] getAllUsers() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }
    


    
}
