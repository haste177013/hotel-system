import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.table.*;

public class HFS_GUI {
    private HotelFileSystem hotelFileSystem = new HotelFileSystem();

    public JFrame frame;
    public static JPanel mainPanel;
    public static CardLayout cardLayout;

    public boolean isValidDate(String date) {
        // Regex for MM/DD/YYYY format
        String datePattern = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$";  // MM/DD/YYYY regex
        Pattern pattern = Pattern.compile(datePattern);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches(); // Check if the date matches the regex pattern
    }
        
    public HFS_GUI(String role) {
        hotelFileSystem.loadFromFile(); // Load data from the file at application startup

        // Initialize frame and main panel
        frame = new JFrame("Hotel Filing System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
            
         // Add panels for each screen
         mainPanel.add(MainMenu(), "MainMenu");
        mainPanel.add(LoginScreen(role), "LoginScreen");
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }

        public HFS_GUI() {
        }
        
            // Main Menu Screen
            public JPanel MainMenu() {
                    JPanel panel = new JPanel(new BorderLayout());
            
                    // Title Section
                    JLabel titleLabel = new JLabel("Welcome to HOTEL101", JLabel.CENTER);
                    titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
                    titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
            
                    JPanel topPanel = new JPanel(new BorderLayout());
                    topPanel.add(titleLabel, BorderLayout.CENTER);
                    topPanel.setBackground(Color.WHITE);
            
                    // Blue bar at the top
                    JPanel blueBar = new JPanel();
                    blueBar.setBackground(new Color(32, 66, 211));
                    blueBar.setPreferredSize(new Dimension(200, 40));
                    topPanel.add(blueBar, BorderLayout.NORTH);
            
                    // Center Panel with Buttons
                    JPanel centerPanel = new JPanel(new GridBagLayout());
                    centerPanel.setBackground(Color.WHITE);
            
                    JButton adminButton = new JButton("Admin");
                    JButton managerButton = new JButton("Hotel Manager");
                    JButton deskButton = new JButton("Front Desk Employee");
            
                    JButton[] buttons = {adminButton, managerButton, deskButton};
                    for (JButton button : buttons) {
                        button.setPreferredSize(new Dimension(300, 60));
                        button.setFont(new Font("Arial", Font.PLAIN, 16));
                        button.setBackground(new Color(231, 231, 231));
                        button.setFocusPainted(false);
                        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                    }
            
                    // Add Buttons to Panel
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.insets = new Insets(10, 0, 10, 0); // Spacing between buttons
                    centerPanel.add(adminButton, gbc);
                    gbc.gridy++;
                    centerPanel.add(managerButton, gbc);
                    gbc.gridy++;
                    centerPanel.add(deskButton, gbc);
            
                    // Footer Section
                    JLabel footerLabel = new JLabel(
                            "For any inquiries or support, contact hotelms101@gmail.com", JLabel.CENTER
                    );
                    footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                    footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
                        // Button Actions
                        adminButton.addActionListener(e -> switchToLogin("Admin"));
                        managerButton.addActionListener(e -> switchToLogin("Hotel Manager"));
                        deskButton.addActionListener(e -> switchToLogin("Front Desk Employee"));
            
                    // Add Components to Panel
                    panel.add(topPanel, BorderLayout.NORTH);
                    panel.add(centerPanel, BorderLayout.CENTER);
                    panel.add(footerLabel, BorderLayout.SOUTH);
            
                    return panel;
                }
            
                // Login Screen
                public JPanel LoginScreen(String role) {
                    
                    JPanel panel = new JPanel(new GridBagLayout());
                    panel.setBackground(Color.WHITE);
                    GridBagConstraints gbc = new GridBagConstraints();
            
                    // Title
                    JLabel titleLabel = new JLabel("User Login - " + role);
                    titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.gridwidth = 2;
                    gbc.insets = new Insets(20, 0, 30, 0); // Top and bottom spacing for title
                    panel.add(titleLabel, gbc);
            
                    // Username Label and TextField
                    JLabel usernameLabel = new JLabel("Username:");
                    usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                    JTextField usernameField = new JTextField(15);
                    usernameField.setPreferredSize(new Dimension(200, 30));
                    usernameField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                    gbc.gridwidth = 1;
                    gbc.gridy = 1;
                    gbc.insets = new Insets(10, 0, 10, 10); // Add spacing between label and text box
                    panel.add(usernameLabel, gbc);
                    gbc.gridx = 1;
                    gbc.insets = new Insets(10, 10, 10, 0);
                    panel.add(usernameField, gbc);
            
                    // Password Label and TextField
                    JLabel passwordLabel = new JLabel("Password:");
                    passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                    JPasswordField passwordField = new JPasswordField(15);
                    passwordField.setPreferredSize(new Dimension(200, 30));
                    passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    gbc.insets = new Insets(10, 0, 10, 10);
                    panel.add(passwordLabel, gbc);
                    gbc.gridx = 1;
                    gbc.insets = new Insets(10, 10, 10, 0);
                    panel.add(passwordField, gbc);
            
                    // Login Button
                    JButton loginButton = new JButton("Login");
                    loginButton.setBackground(new Color(32, 66, 211));
                    loginButton.setForeground(Color.WHITE);
                    loginButton.setFont(new Font("Arial", Font.BOLD, 16));
                    loginButton.setFocusPainted(false);
                    gbc.gridx = 0;
                    gbc.gridy = 3;
                    gbc.gridwidth = 2;
                    gbc.insets = new Insets(20, 0, 1, 0); // Spacing above button
                    panel.add(loginButton, gbc);
            
                    // Back Button
                    JButton backButton = new JButton("Back");
                    backButton.setBackground(new Color(219, 215, 215));
                    backButton.setForeground(Color.BLACK);
                    backButton.setFont(new Font("Arial", Font.BOLD, 10));
                    backButton.setFocusPainted(false);
                    gbc.gridx = 0;
                    gbc.gridy = 4;
                    panel.add(backButton, gbc);
            
                    // login Button Actions
                    loginButton.addActionListener(e -> {
                        String username = usernameField.getText();
                        String password = new String(passwordField.getPassword());
                        if (username.equals("admin") && password.equals("password") && role.equals("Admin")) {
                            JOptionPane.showMessageDialog(frame, "Login successful!");
                            AdminGUI();
                            cardLayout.show(mainPanel, "MainMenu");
                        }else if (username.equals("frontdesk") && password.equals("123") && role.equals("Front Desk Employee")) {
                            JOptionPane.showMessageDialog(frame, "Login successful!");
                            JPanel frontDeskPanel = frontDesk();
                            mainPanel.add(frontDeskPanel, "FrontDesk");
                            cardLayout.show(mainPanel, "FrontDesk");
                        } else if (username.equals("manager") && password.equals("managing") && role.equals("Hotel Manager")) {
                            JOptionPane.showMessageDialog(frame, "Login successful!");
                            panel.setVisible(false);
                            Manager();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });
            
                    backButton.addActionListener(e -> cardLayout.show(mainPanel, "MainMenu"));
            
                    return panel;
                }
            
                private void switchToLogin(String role) {
                    JPanel loginPanel = LoginScreen(role); // Pass role to LoginScreen
                    mainPanel.add(loginPanel, "LoginScreen");
                    cardLayout.show(mainPanel, "LoginScreen");
                }
                
                //private static int transactionCounter = 1; // Static counter for unique IDs

                /*public static  String generateTransactionID() {
                    return String.format("T%03d", transactionCounter++);
                } */
                public static String generateTransactionID() {
                    return "T" + UUID.randomUUID().toString();
                }

        public JPanel frontDesk() {

            //frame
            JFrame frame = new JFrame("Front Desk");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLayout(new BorderLayout());
            frame.getContentPane().setBackground(Color.WHITE);
            JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
            contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            contentPanel.setBackground(Color.WHITE);
            frame.add(contentPanel, BorderLayout.CENTER);

            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setBackground(Color.WHITE);

            JPanel blueBar = new JPanel();
            blueBar.setBackground(new Color(33, 76, 180));
            blueBar.setPreferredSize(new Dimension(frame.getWidth(), 3));
            // topPanel.add(blueBar, BorderLayout.SOUTH);

            JLabel lblTitle = new JLabel("  Front Desk");
            lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
            lblTitle.setForeground(new Color(33, 76, 180));
            // topPanel.add(lblTitle, BorderLayout.WEST);

            JLabel lblLogout = new JLabel("Log out  ");
            lblLogout.setFont(new Font("Arial", Font.PLAIN, 14));
            lblLogout.setForeground(new Color(33, 76, 180));
            lblLogout.setHorizontalAlignment(SwingConstants.RIGHT);
            lblLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
            topPanel.add(lblLogout, BorderLayout.EAST);
            contentPanel.add(topPanel, BorderLayout.NORTH);

            lblLogout.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int response = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to logout?",
                        "Confirm Logout",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                    );
            
                    if (response == JOptionPane.YES_OPTION) {
                        System.out.println("Logging out...");
                        frame.dispose();
                        cardLayout.show(mainPanel, "MainMenu");
                    }
                }
            });
            JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
            centerPanel.setBackground(Color.WHITE);
            JPanel inputPanel = new JPanel(new GridLayout(0, 2, 3, 5));
            inputPanel.setPreferredSize(new Dimension(260, 0));
            inputPanel.setBackground(Color.WHITE);
            @SuppressWarnings("unused")
            JLabel lblTransactionId = new JLabel("Transaction ID:");
            JLabel txtTransactionId = new JLabel(generateTransactionID());
            JLabel lblFirstname = new JLabel("Firstname:");
            JTextField txtFirstname = new JTextField(15);
            JLabel lblLastname = new JLabel("Lastname:");
            JTextField txtLastname = new JTextField(15);
            JLabel lblRoomNo = new JLabel("Room No:");
            JTextField txtRoomNo = new JTextField(15);
            JLabel lblRoomType = new JLabel("Room Type:");
            JComboBox<String> cbRoomType = new JComboBox<>(new String[]{"Deluxe Room", "First Class Room", "Ordinary Room"});
            JTextField txtRoomRate = new JTextField(10);
            txtRoomRate.setEditable(false);
            JLabel lblCheckInDate = new JLabel("Check In Date:");
            JTextField txtCheckInDate = new JTextField("MM/DD/YYYY", 15);
            JLabel lblCheckOutDate = new JLabel("Check Out Date:");
            JTextField txtCheckOutDate = new JTextField("MM/DD/YYYY", 15);
            JLabel lblNoOfDays = new JLabel("No. of Days:");
            JComboBox<Integer> cbNoOfDays = new JComboBox<>(generateNumbers(1, 30));
            JLabel lblNoOfAdults = new JLabel("No. of Adults:");
            JComboBox<Integer> cbNoOfAdults = new JComboBox<>(generateNumbers(1, 10));
            JLabel lblNoOfChildren = new JLabel("No. of Children:");
            JComboBox<Integer> cbNoOfChildren = new JComboBox<>(generateNumbers(0, 10));
            JLabel lblMeals = new JLabel("Meals:");
            JComboBox<Integer> cbMeals = new JComboBox<>(generateNumbers(0, 10));
            JLabel lblStatus = new JLabel(" Payment Status: ");
            JComboBox<String> cbStatus = new JComboBox<>(new String[]{"Paid", "Unpaid", "Partially Paid"});
            JLabel lblAmenities = new JLabel("Amenities:");
            JCheckBox cbWiFi = new JCheckBox("Wi-Fi (₱10)");
            JCheckBox cbParking = new JCheckBox("Parking (₱5)");

            JCheckBox cbPool = new JCheckBox("Pool (₱15)");
            JCheckBox cbSpa = new JCheckBox("Spa (₱20)");
            JCheckBox cbCoffeeMaker = new JCheckBox("Coffee Maker (₱350)");
            JCheckBox cbToothbrush = new JCheckBox("Toothbrush (₱100)");
            JCheckBox cbToothpaste = new JCheckBox("Toothpaste (₱100)");
            JCheckBox cbBathroomTowels = new JCheckBox("2 Bathroom Towels (₱300)");
            JCheckBox cbBathrobe = new JCheckBox("3 Bathrobes (₱600)");
            JCheckBox cbFaceTowels = new JCheckBox("4 Face Towels (₱200)");
            JCheckBox cbRazor = new JCheckBox("Razor (₱100)");
            JCheckBox cbShavingCream = new JCheckBox("Shaving Cream (₱200)");
            JCheckBox cbSoap = new JCheckBox("Soap (₱100)");
            JCheckBox cbShampoo = new JCheckBox("Shampoo (₱100)");
            JCheckBox cbDishwashingLiquid = new JCheckBox("Dishwashing Liquid (₱100)");
            JCheckBox cbFabricConditioner = new JCheckBox("Fabric Conditioner (₱150)");
            JCheckBox cbInsideSlippers = new JCheckBox("Inside Slippers (₱250)");
            JCheckBox cbPantry = new JCheckBox("Pantry (₱1000)");
            JCheckBox cbRefrigirator = new JCheckBox("Refrigerator (₱500)");

            JPanel amenitiesPanel = new JPanel(new GridLayout(0, 1, 4, 4));
            amenitiesPanel.setPreferredSize(new Dimension(200, 300));
            amenitiesPanel.add(cbWiFi);
            amenitiesPanel.add(cbParking);
            amenitiesPanel.add(cbPool);
            amenitiesPanel.add(cbSpa);
            amenitiesPanel.add(cbCoffeeMaker);
            amenitiesPanel.add(cbToothbrush);
            amenitiesPanel.add(cbToothpaste);
            amenitiesPanel.add(cbBathroomTowels);
            amenitiesPanel.add(cbBathrobe);
            amenitiesPanel.add(cbFaceTowels);
            amenitiesPanel.add(cbRazor);
            amenitiesPanel.add(cbShavingCream);
            amenitiesPanel.add(cbSoap);
            amenitiesPanel.add(cbShampoo);
            amenitiesPanel.add(cbDishwashingLiquid);
            amenitiesPanel.add(cbFabricConditioner);
            amenitiesPanel.add(cbInsideSlippers);
            amenitiesPanel.add(cbPantry);
            amenitiesPanel.add(cbRefrigirator);


            JScrollPane scrollPaneAmenities = new JScrollPane(amenitiesPanel);
            scrollPaneAmenities.setPreferredSize(new Dimension(200, 350));
            inputPanel.add(lblFirstname);
            inputPanel.add(txtFirstname);
            inputPanel.add(lblLastname);
            inputPanel.add(txtLastname);
            inputPanel.add(lblRoomNo);
            inputPanel.add(txtRoomNo);
            inputPanel.add(lblRoomType);
            inputPanel.add(cbRoomType);
            inputPanel.add(lblCheckInDate);
            inputPanel.add(txtCheckInDate);
            inputPanel.add(lblCheckOutDate);
            inputPanel.add(txtCheckOutDate);
            inputPanel.add(lblNoOfDays);
            inputPanel.add(cbNoOfDays);
            inputPanel.add(lblNoOfAdults);
            inputPanel.add(cbNoOfAdults);
            inputPanel.add(lblNoOfChildren);
            inputPanel.add(cbNoOfChildren);
            inputPanel.add(lblMeals);
            inputPanel.add(cbMeals);
            inputPanel.add(lblStatus);
            inputPanel.add(cbStatus);
            inputPanel.add(lblAmenities);
            inputPanel.add(scrollPaneAmenities);
            centerPanel.add(inputPanel, BorderLayout.WEST);
            DefaultTableModel model = new DefaultTableModel();
            JTable table = new JTable(model);
            model.setColumnIdentifiers(new Object[]{
                    "Transaction ID", "Firstname", "Lastname", "Room No", "Room Type", "Room Rate",
                    "Check In Date", "Check Out Date", "No. of Days", "No. of Adults", "No. of Children", "Meals",
                    "Amenities", "Total", "Payment Status"
            });
            JScrollPane scrollPane = new JScrollPane(table);
            centerPanel.add(scrollPane, BorderLayout.CENTER);
            contentPanel.add(centerPanel, BorderLayout.CENTER);
            JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
            bottomPanel.setPreferredSize(new Dimension(frame.getWidth(), 25));
            bottomPanel.setBackground(Color.WHITE);
            JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            totalPanel.setBackground(Color.WHITE);
            JLabel lblTotal = new JLabel("Total: ");
            JTextField txtTotal = new JTextField(10);
            txtTotal.setEditable(false);
            totalPanel.add(lblTotal);
            totalPanel.add(txtTotal);
            bottomPanel.add(totalPanel, BorderLayout.WEST);

            // Bottom Panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.setPreferredSize(new Dimension(500, 40));
            buttonPanel.setBackground(Color.WHITE);
        
            JButton btnAdd = new JButton("Add");
            JButton btnUpdate = new JButton("Update");
            JButton btnDelete = new JButton("Delete");
            JButton btnReset = new JButton("Reset");
            JButton btnExit = new JButton("Exit");
            JButton btnTotal = new JButton("Total"); // Updated title
        
            btnAdd.setPreferredSize(new Dimension(100, 30));
            btnUpdate.setPreferredSize(new Dimension(100, 30));
            btnDelete.setPreferredSize(new Dimension(100, 30));
            btnReset.setPreferredSize(new Dimension(100, 30));
            btnExit.setPreferredSize(new Dimension(100, 30));
            btnTotal.setPreferredSize(new Dimension(100, 30));
        
            buttonPanel.add(btnAdd);
            buttonPanel.add(btnUpdate);
            buttonPanel.add(btnDelete);
            buttonPanel.add(btnReset);
            buttonPanel.add(btnTotal); // Moved before Exit
            buttonPanel.add(btnExit);
        
            bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        
            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Positioned to the right
            JLabel searchLabel = new JLabel("Search: ");
            JTextField searchField = new JTextField(20);
            JButton searchButton = new JButton("Search");
        
            searchPanel.add(searchLabel);
            searchPanel.add(searchField);
            searchPanel.add(searchButton);

            searchPanel.add(lblTitle, BorderLayout.WEST); // Add the title to the left
            // searchPanel.add(searchPanel, BorderLayout.EAST); // Add the search bar to the right
            searchPanel.add(blueBar, BorderLayout.SOUTH); // Add the blue bar at the bottom
            searchPanel.add(lblLogout, BorderLayout.EAST);
        
            contentPanel.add(bottomPanel, BorderLayout.SOUTH);

            // Add action listener to search button
            searchButton.addActionListener(e -> {
                String query = searchField.getText().toLowerCase();
                if (!query.isEmpty()) {
                    // Clear the table before adding filtered results
                    model.setRowCount(0);
                    for (HotelFileSystem.Booking booking : hotelFileSystem.getAllBookings()) {
                        if (booking.firstName.toLowerCase().contains(query) ||
                            booking.lastName.toLowerCase().contains(query) ||
                            booking.transactionId.toLowerCase().contains(query)) {
                            model.addRow(new Object[]{
                                booking.transactionId, booking.firstName, booking.lastName, booking.roomNo,
                                booking.roomType, booking.roomRate, booking.checkInDate, booking.checkOutDate,
                                booking.noOfDays, booking.noOfAdults, booking.noOfChildren, booking.Meals,
                                booking.amenities, booking.totalCost, booking.status
                            });
                        }
                    }
                } else {
                    // Reset the table to show all bookings if the search field is empty
            model.setRowCount(0);
            for (HotelFileSystem.Booking booking : hotelFileSystem.getAllBookings()) {
                model.addRow(new Object[]{
                    booking.transactionId, booking.firstName, booking.lastName, booking.roomNo,
                    booking.roomType, booking.roomRate, booking.checkInDate, booking.checkOutDate,
                    booking.noOfDays, booking.noOfAdults, booking.noOfChildren, booking.Meals,
                    booking.amenities, booking.totalCost, booking.status
                });
            }
        }
    });
    
     // Add panels to the content
     contentPanel.add(searchPanel, BorderLayout.NORTH);
     contentPanel.add(centerPanel, BorderLayout.CENTER);



             // Load bookings from the backend and populate the table
            for (HotelFileSystem.Booking booking : hotelFileSystem.getAllBookings()) {
                model.addRow(new Object[]{
                booking.transactionId, booking.firstName, booking.lastName, booking.roomNo,
                booking.roomType, booking.roomRate, booking.checkInDate, booking.checkOutDate,
                booking.noOfDays, booking.noOfAdults, booking.noOfChildren,booking.Meals,
                booking.amenities, booking.totalCost, booking.status
                });
            }
            

            btnTotal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double total = 0;
                    if (cbWiFi.isSelected()) total += 10;
                    if (cbParking.isSelected()) total += 5;
                    if (cbPool.isSelected()) total += 15;
                    if (cbSpa.isSelected()) total += 20;
                    if (cbCoffeeMaker.isSelected()) total += 350;
                    if (cbToothbrush.isSelected()) total += 100;
                    if (cbToothpaste.isSelected()) total += 100;
                    if (cbBathroomTowels.isSelected()) total += 300;
                    if (cbBathrobe.isSelected()) total += 600;
                    if (cbFaceTowels.isSelected()) total += 200;
                    if (cbRazor.isSelected()) total += 100;
                    if (cbShavingCream.isSelected()) total += 100;
                    if (cbSoap.isSelected()) total += 100;
                    if (cbShampoo.isSelected()) total += 100;
                    if (cbDishwashingLiquid.isSelected()) total += 100;
                    if (cbFabricConditioner.isSelected()) total += 150;
                    if (cbInsideSlippers.isSelected()) total += 250;
                    if (cbPantry.isSelected()) total += 1000;
                    if (cbRefrigirator.isSelected()) total += 500;

                    int roomRate = 0;
                    switch ((String) cbRoomType.getSelectedItem()) {
                        case "Deluxe Room":
                            roomRate = 4500;
                            break;
                        case "First Class Room":
                            roomRate = 3000;
                            break;
                        case "Ordinary Room":
                            roomRate = 1000;
                            break;
                    }
                    int days = (int) cbNoOfDays.getSelectedItem();
                    total += roomRate * days;
                    String totalText = "₱" + total;
                    txtTotal.setText(totalText);
                    txtTotal.setText(totalText);
                }
            });

        
            txtCheckInDate.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    String text = txtCheckInDate.getText();
                    // Validate if the input matches MM/DD/YYYY format
                    if (!isValidDate(text) && !text.isEmpty()) {
                        txtCheckInDate.setBackground(Color.PINK); // Indicate invalid input
                    } else {
                        txtCheckInDate.setBackground(Color.WHITE); // Reset background for valid input
                    }
                }
            });
    
            
            txtCheckOutDate.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    String text = txtCheckOutDate.getText();
                    // Validate if the input matches MM/DD/YYYY format
                    if (!isValidDate(text) && !text.isEmpty()) {
                        txtCheckOutDate.setBackground(Color.PINK); // Indicate invalid input
                    } else {
                        txtCheckOutDate.setBackground(Color.WHITE); // Reset background for valid input
                    }
                }
            });
    
    
            txtCheckInDate.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (txtCheckInDate.getText().equals("MM/DD/YYYY")) {
                        txtCheckInDate.setText(""); // Clear the default text when the user clicks into the field
                    }
                }
                @Override
                public void focusLost(FocusEvent e) {
                    if (txtCheckInDate.getText().isEmpty()) {
                        txtCheckInDate.setText("MM/DD/YYYY"); // Restore default text if no input is given
                    }
                }
            });
            txtCheckOutDate.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (txtCheckOutDate.getText().equals("MM/DD/YYYY")) {
                        txtCheckOutDate.setText(""); // Clear the default text when the user clicks into the field
                    }
                }
                @Override
            public void focusLost(FocusEvent e) {
                if (txtCheckOutDate.getText().isEmpty()) {
                    txtCheckOutDate.setText("MM/DD/YYYY"); // Restore default text if no input is given
                }
            }
        });

            cbRoomType.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int days = (int) cbNoOfDays.getSelectedItem();
                    int roomRate = 0;
                    switch ((String) cbRoomType.getSelectedItem()) {
                        case "Deluxe Room":
                            roomRate = 4500;
                            break;
                        case "First Class Room":
                            roomRate = 3000;
                            break;
                        case "Ordinary Room":
                            roomRate = 1000;
                            break;
                    }
                    int totalRoomRate = roomRate * days;
                    txtRoomRate.setText("₱" + totalRoomRate);
                }
            });
            cbNoOfDays.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cbRoomType.getActionListeners()[0].actionPerformed(null);
                }
            });
            lblLogout.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Logging out...");
                    frame.dispose();
                }
            });
            
            btnAdd.addActionListener(new ActionListener() {
                // @Override
                public void actionPerformed(ActionEvent e) {
                    boolean isValid = true;
                    StringBuilder missingFields = new StringBuilder();
            
                    // Get input values
                    String roomNoInput = txtRoomNo.getText().trim();
                    String checkInDate = txtCheckInDate.getText().trim();
                    String checkOutDate = txtCheckOutDate.getText().trim();
                    String firstName = txtFirstname.getText().trim();
                    String lastName = txtLastname.getText().trim();
                    
            
                    // Check for empty fields
                    if (firstName.isEmpty()) {
                        missingFields.append("First Name, ");
                        isValid = false;
                    }
                    if (lastName.isEmpty()) {
                        missingFields.append("Last Name, ");
                        isValid = false;
                    }
                    if (roomNoInput.isEmpty()) {
                        missingFields.append("Room Number, ");
                        isValid = false;
                    }
                    if (checkInDate.isEmpty()) {
                        missingFields.append("Check-In Date, ");
                        isValid = false;
                    }
                    if (checkOutDate.isEmpty()) {
                        missingFields.append("Check-Out Date, ");
                        isValid = false;
                    }
            
                    // If there are missing fields, show a single message with all missing fields
                    if (!isValid) {
                        String missingFieldsMessage = "Please fill out the following fields: " +
                                missingFields.toString().replaceAll(", $", "");
                        JOptionPane.showMessageDialog(frame, missingFieldsMessage, "Error", JOptionPane.ERROR_MESSAGE);
                        return;  // Exit early if any required field is missing
                    }
            
                    // Validate room number format and range
                    int roomNo = -1;
                    try {
                        roomNo = Integer.parseInt(roomNoInput);
                        if (roomNo < 1 || roomNo > 30) {
                            JOptionPane.showMessageDialog(frame,
                                    "Room number must be between 1 and 30.",
                                    "Invalid Room Number",
                                    JOptionPane.ERROR_MESSAGE);
                            isValid = false;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame,
                                "Please enter a valid integer for the room number.",
                                "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                        isValid = false;
                    }
            
                    // Check for duplicate room number if not empty
                    if (isValid) {
                        for (int i = 0; i < model.getRowCount(); i++) {
                            if (model.getValueAt(i, 3).toString().equals(roomNoInput)) {
                                JOptionPane.showMessageDialog(frame,
                                        "Room number " + roomNoInput + " is already taken. Please choose another room.",
                                        "Room Unavailable",
                                        JOptionPane.WARNING_MESSAGE);
                                isValid = false;
                                break;
                            }
                        }
                    }
            
                    // Validate Check-In and Check-Out dates if not empty
                    if (isValid) {
                        if (!isValidDate(checkInDate)) {
                            JOptionPane.showMessageDialog(frame, "Invalid Check-In Date!", "Error", JOptionPane.ERROR_MESSAGE);
                            txtCheckInDate.setBackground(Color.PINK); // Highlight invalid input
                            isValid = false;
                        } else {
                            txtCheckInDate.setBackground(Color.WHITE); // Reset background if valid
                        }
            
                        if (!isValidDate(checkOutDate)) {
                            JOptionPane.showMessageDialog(frame, "Invalid Check-Out Date!", "Error", JOptionPane.ERROR_MESSAGE);
                            txtCheckOutDate.setBackground(Color.PINK); // Highlight invalid input
                            isValid = false;
                        } else {
                            txtCheckOutDate.setBackground(Color.WHITE); // Reset background if valid
                        }
                    }
                    StringBuilder amenities = new StringBuilder();
        if (cbWiFi.isSelected()) amenities.append("Wi-Fi, ");
        if (cbParking.isSelected()) amenities.append("Parking, ");
        if (cbPool.isSelected()) amenities.append("Pool, ");
        if (cbSpa.isSelected()) amenities.append("Spa, ");
        if (cbCoffeeMaker.isSelected()) amenities.append("Coffee Maker, ");
        if (cbToothbrush.isSelected()) amenities.append("Toothbrush, ");
        if (cbToothpaste.isSelected()) amenities.append("Toothpaste, ");
        if (cbBathroomTowels.isSelected()) amenities.append("2 Bathroom Towels, ");
        if (cbBathrobe.isSelected()) amenities.append("3 Bathrobes, ");
        if (cbFaceTowels.isSelected()) amenities.append("4 Face Towels, ");
        if (cbRazor.isSelected()) amenities.append("Razor, ");
        if (cbShavingCream.isSelected()) amenities.append("Shaving Cream, ");
        if (cbSoap.isSelected()) amenities.append("Soap, ");
        if (cbShampoo.isSelected()) amenities.append("Shampoo, ");
        if (cbDishwashingLiquid.isSelected()) amenities.append("Dishwashing Liquid, ");
        if (cbFabricConditioner.isSelected()) amenities.append("Fabric Conditioner, ");
        if (cbInsideSlippers.isSelected()) amenities.append("Inside Slippers, ");
        if (cbPantry.isSelected()) amenities.append("Pantry, ");
        if (cbRefrigirator.isSelected()) amenities.append("Refrigerator, ");
        if (amenities.length() > 0) {
            amenities.setLength(amenities.length() - 2); // Remove trailing comma and space
        }

            
                    // If all validations pass, proceed with adding the booking
                    if (isValid) {
                        String transactionId = generateTransactionID(); // Generate new ID
                        txtTransactionId.setText(transactionId); // Update label to show the new ID
            
                        String roomType = cbRoomType.getSelectedItem().toString();
                        double roomRate = Double.parseDouble(txtRoomRate.getText().replace("₱", "").trim());
                        int noOfDays = cbNoOfDays.getItemAt(cbNoOfDays.getSelectedIndex());
                        int noOfAdults = cbNoOfAdults.getItemAt(cbNoOfAdults.getSelectedIndex());
                        int noOfChildren = cbNoOfChildren.getItemAt(cbNoOfChildren.getSelectedIndex());
                        int meals = cbMeals.getItemAt(cbMeals.getSelectedIndex());
                        double totalCost = Double.parseDouble(txtTotal.getText().replace("₱", "").trim());
                        String status = cbStatus.getSelectedItem().toString();

                        // Add booking to backend (model)
                        HotelFileSystem.Booking booking = new HotelFileSystem.Booking(
                                transactionId, firstName, lastName, roomNoInput, roomType,
                                roomRate, checkInDate, checkOutDate, noOfDays, noOfAdults,
                                noOfChildren, meals, amenities.toString(), totalCost, status
                        );
                        hotelFileSystem.addBooking(booking);
            
                        // Update the table
                        Object[] row = {transactionId, firstName, lastName, roomNoInput, roomType, roomRate, checkInDate, checkOutDate,
                                noOfDays, noOfAdults, noOfChildren, meals, amenities.toString(), totalCost, status};
                        model.addRow(row);
            
                        JOptionPane.showMessageDialog(frame, "Booking added successfully!");
                    } else {
                        System.out.println("Booking not added due to validation failure."); // Debugging message
                    }
                }
            });
            
            
            
            
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.setValueAt(txtTransactionId.getText(), selectedRow, 0);
                    model.setValueAt(txtFirstname.getText(), selectedRow, 1);
                    model.setValueAt(txtLastname.getText(), selectedRow, 2);
                    model.setValueAt(txtRoomNo.getText(), selectedRow, 3);
                    model.setValueAt(cbRoomType.getSelectedItem(), selectedRow, 4);
                    model.setValueAt(txtCheckInDate.getText(), selectedRow, 6);
                    model.setValueAt(txtCheckOutDate.getText(), selectedRow, 7);
                    model.setValueAt(cbNoOfDays.getSelectedItem(), selectedRow, 8);
                    model.setValueAt(cbNoOfAdults.getSelectedItem(), selectedRow, 9);
                    model.setValueAt(cbNoOfChildren.getSelectedItem(), selectedRow, 10);
                    model.setValueAt(cbMeals.getSelectedItem(), selectedRow, 11);
                    model.setValueAt(amenitiesPanel, selectedRow, 12);
                    model.setValueAt(txtTotal.getText(), selectedRow, 13);
                    model.setValueAt(cbStatus.getSelectedItem(), selectedRow, 14);
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Get the transaction ID of the selected booking
                    String transactionId = model.getValueAt(selectedRow, 0).toString();
        
                    // Remove the booking from the backend
                    hotelFileSystem.deleteBooking(transactionId);
        
                    // Remove the row from the table
                    model.removeRow(selectedRow);
        
                    JOptionPane.showMessageDialog(frame, "Booking deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a booking to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTransactionId.setText("");
                txtFirstname.setText("");
                txtLastname.setText("");
                txtRoomNo.setText("");
                txtCheckInDate.setText("MM/DD/YYYY");
                txtCheckOutDate.setText("MM/DD/YYYY");
                txtCheckInDate.setBackground(Color.WHITE); // Reset background color
                txtCheckOutDate.setBackground(Color.WHITE); // Reset background color
                cbRoomType.setSelectedIndex(0);
                cbNoOfDays.setSelectedIndex(0);
                cbNoOfAdults.setSelectedIndex(0);
                cbNoOfChildren.setSelectedIndex(0);
                cbMeals.setSelectedIndex(0);
                cbWiFi.setSelected(false);
                cbParking.setSelected(false);
                cbPool.setSelected(false);
                cbSpa.setSelected(false);
                cbCoffeeMaker.setSelected(false);
                cbToothbrush.setSelected(false);
                cbToothpaste.setSelected(false);
                cbBathroomTowels.setSelected(false);
                cbBathrobe.setSelected(false);
                cbFaceTowels.setSelected(false);
                cbRazor.setSelected(false);
                cbShavingCream.setSelected(false);
                cbSoap.setSelected(false);
                cbShampoo.setSelected(false);
                cbDishwashingLiquid.setSelected(false);
                cbFabricConditioner.setSelected(false);
                cbInsideSlippers.setSelected(false);
                cbPantry.setSelected(false);
                cbRefrigirator.setSelected(false);
                txtTotal.setText("");
                cbStatus.setSelectedIndex(0);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int days = (int) cbNoOfDays.getSelectedItem();
                    int roomRate = 0;
                    switch ((String) cbRoomType.getSelectedItem()) {
                        case "Deluxe Room":
                            roomRate = 4500;
                            break;
                        case "First Class Room":
                            roomRate = 3000;
                            break;

                        case "Ordinary Room":
                            roomRate = 1000;
                            break;
                    }
                    int totalRoomRate = roomRate * days;
                    txtRoomRate.setText("₱" + totalRoomRate);
                    model.setValueAt(txtTransactionId.getText(), selectedRow, 0);
                    model.setValueAt(txtFirstname.getText(), selectedRow, 1);
                    model.setValueAt(txtLastname.getText(), selectedRow, 2);
                    model.setValueAt(txtRoomNo.getText(), selectedRow, 3);
                    model.setValueAt(cbRoomType.getSelectedItem(), selectedRow, 4);
                    model.setValueAt("₱" + totalRoomRate, selectedRow, 5);
                    model.setValueAt(txtCheckInDate.getText(), selectedRow, 6);
                    model.setValueAt(txtCheckOutDate.getText(), selectedRow, 7);
                    model.setValueAt(cbNoOfDays.getSelectedItem(), selectedRow, 8);
                    model.setValueAt(cbNoOfAdults.getSelectedItem(), selectedRow, 9);
                    model.setValueAt(cbNoOfChildren.getSelectedItem(), selectedRow, 10);
                    model.setValueAt(cbMeals.getSelectedItem(), selectedRow, 11);

                    String amenities = "";
                    if (cbWiFi.isSelected()) amenities += "WiFi, ";
                    if (cbParking.isSelected()) amenities += "Parking, ";
                    if (cbPool.isSelected()) amenities += "Pool, ";
                    if (cbSpa.isSelected()) amenities += "Spa, ";
                    if (cbCoffeeMaker.isSelected()) amenities += "Coffee Maker, ";
                    if (cbToothbrush.isSelected()) amenities += "Toothbrush, ";
                    if (cbToothpaste.isSelected()) amenities += "Toothpaste, ";
                    if (cbBathroomTowels.isSelected()) amenities += "2 Bathroom Towels, ";
                    if (cbBathrobe.isSelected()) amenities += "3 Bathrobes, ";
                    if (cbFaceTowels.isSelected()) amenities += "4 Face Towels, ";
                    if (cbRazor.isSelected()) amenities += "Razor, ";
                    if (cbShavingCream.isSelected()) amenities += "Shaving Cream, ";
                    if (cbSoap.isSelected()) amenities += "Soap, ";
                    if (cbShampoo.isSelected()) amenities += "Shampoo, ";
                    if (cbDishwashingLiquid.isSelected()) amenities += "Dishwashing Liquid, ";
                    if (cbFabricConditioner.isSelected()) amenities += "Fabric Conditioner, ";
                    if (cbInsideSlippers.isSelected()) amenities += "Inside Slippers, ";
                    if (cbPantry.isSelected()) amenities += "Pantry, ";
                    if (cbRefrigirator.isSelected()) amenities += "Refrigerator, ";
                    if (amenities.endsWith(", ")) amenities = amenities.substring(0, amenities.length() - 2);
                    model.setValueAt(amenities, selectedRow, 12);
                    model.setValueAt(txtTotal.getText(), selectedRow, 12);

                    if (amenities.endsWith(", ")) {
                        amenities = amenities.substring(0, amenities.length() - 2);
                    }
                }
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    txtTransactionId.setText(model.getValueAt(selectedRow, 0).toString());
                    txtFirstname.setText(model.getValueAt(selectedRow, 1).toString());
                    txtLastname.setText(model.getValueAt(selectedRow, 2).toString());
                    txtRoomNo.setText(model.getValueAt(selectedRow, 3).toString());
                    cbRoomType.setSelectedItem(model.getValueAt(selectedRow, 4).toString());
                    txtCheckInDate.setText(model.getValueAt(selectedRow, 6).toString());
                    txtCheckOutDate.setText(model.getValueAt(selectedRow, 7).toString());
                    cbNoOfDays.setSelectedItem(Integer.valueOf(model.getValueAt(selectedRow, 8).toString()));
                    cbNoOfAdults.setSelectedItem(Integer.valueOf(model.getValueAt(selectedRow, 9).toString()));
                    cbNoOfChildren.setSelectedItem(Integer.valueOf(model.getValueAt(selectedRow, 10).toString()));
                    cbMeals.setSelectedItem(Integer.valueOf(model.getValueAt(selectedRow, 11).toString()));
                    String amenities = model.getValueAt(selectedRow, 12).toString();
                    cbWiFi.setSelected(amenities.contains("WiFi"));
                    cbParking.setSelected(amenities.contains("Parking"));
                    cbPool.setSelected(amenities.contains("Pool"));
                    cbSpa.setSelected(amenities.contains("Spa"));
                    cbCoffeeMaker.setSelected(amenities.contains("Coffee Maker"));
                    cbToothbrush.setSelected(amenities.contains("Toothbrush"));
                    cbToothpaste.setSelected(amenities.contains("Toothpaste"));
                    cbBathroomTowels.setSelected(amenities.contains("2 Bathroom Towels"));
                    cbBathrobe.setSelected(amenities.contains("3 Bathrobes"));
                    cbFaceTowels.setSelected(amenities.contains("4 Face Towels"));
                    cbRazor.setSelected(amenities.contains("Razor"));
                    cbShavingCream.setSelected(amenities.contains("Shaving Cream"));
                    cbSoap.setSelected(amenities.contains("Soap"));
                    cbShampoo.setSelected(amenities.contains("Shampoo"));
                    cbDishwashingLiquid.setSelected(amenities.contains("Dishwashing Liquid"));
                    cbFabricConditioner.setSelected(amenities.contains("Fabric Conditioner"));
                    cbInsideSlippers.setSelected(amenities.contains("Inside Slippers"));
                    cbPantry.setSelected(amenities.contains("Pantry"));
                    cbRefrigirator.setSelected(amenities.contains("Refrigerator"));
                    txtTotal.setText(model.getValueAt(selectedRow, 13).toString());
                    cbStatus.setSelectedItem(model.getValueAt(selectedRow, 14).toString());
                }


            }
        });
        frame.setVisible(true);
        return mainPanel;
        } 
        public static Integer[] generateNumbers(int start, int end) {
            Integer[] numbers = new Integer[end - start + 1];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = start + i;
            }
            return numbers;
    }
                public static JPanel AdminGUI() {
                    
                        JFrame frame = new JFrame("Simple Frame");
                        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.getContentPane().setBackground(Color.WHITE);
                
                        JButton logOutButton = new JButton("Log Out");
                        logOutButton.addActionListener(e -> {
                            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(frame, "You have logged out.");
                                frame.dispose();
                                cardLayout.show(mainPanel, "MainMenu");
                            }
            });
    
            JComponent drawingPanel = new JComponent() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setColor(new Color(32, 66, 211));
                    g2d.setStroke(new BasicStroke(3));
                    g2d.drawLine(0, 70, getWidth(), 70);
                    g2d.drawLine(320, 70, 320, getHeight());
                    g2d.setFont(new Font("Arial", Font.BOLD, 24));
                    g2d.setColor(Color.BLUE);
                    g2d.drawString("Welcome, Admin!", 40, 45);
                    g2d.setFont(new Font("Arial", Font.BOLD, 18));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString("Admin", 40, 140);
                }
            };
    
            frame.setLayout(null);
            drawingPanel.setBounds(0, 0, 1920, 1080);
            frame.add(drawingPanel);
    
            JPanel sidebarPanel = new JPanel();
            sidebarPanel.setBackground(new Color(32, 66, 211));
            sidebarPanel.setLayout(null);
            sidebarPanel.setBounds(0, 200, 320, 1080);
            frame.add(sidebarPanel);
    
            JButton allUsersButton = new JButton("All Users");
            allUsersButton.setBounds(10, 20, 300, 40);
            allUsersButton.setBackground(new Color(32, 66, 211));
            allUsersButton.setFont(new Font("Arial", Font.BOLD, 20));
            allUsersButton.setForeground(Color.WHITE);
            sidebarPanel.add(allUsersButton);
    
            JButton userButton1 = new JButton("Manager");
            JButton userButton2 = new JButton("Staff");
            userButton1.setBounds(10, 70, 300, 40);
            userButton2.setBounds(10, 120, 300, 40);
            userButton1.setBackground(new Color(32, 66, 211));
            userButton2.setBackground(new Color(32, 66, 211));
            userButton1.setFont(new Font("Arial", Font.BOLD, 18));
            userButton2.setFont(new Font("Arial", Font.BOLD, 18));
            userButton1.setForeground(Color.WHITE);
            userButton2.setForeground(Color.WHITE);
            userButton1.setVisible(false);
            userButton2.setVisible(false);
            sidebarPanel.add(userButton1);
            sidebarPanel.add(userButton2);
    
            allUsersButton.addActionListener(e -> {
                boolean visible = !userButton1.isVisible();
                userButton1.setVisible(visible);
                userButton2.setVisible(visible);
                allUsersButton.setBackground(allUsersButton.getBackground().equals(new Color(32, 66, 211)) ? new Color(200, 200, 200) : new Color(32, 66, 211));
            });
    
            JButton systemSettingsButton = new JButton("System Settings");
            systemSettingsButton.setBounds(10, 220, 300, 40);
            systemSettingsButton.setBackground(new Color(32, 66, 211));
            systemSettingsButton.setFont(new Font("Arial", Font.BOLD, 20));
            systemSettingsButton.setForeground(Color.WHITE);
            sidebarPanel.add(systemSettingsButton);
    
    
            JButton settingOptionButton = new JButton("System Logs");
            settingOptionButton.setBounds(10, 270, 300, 40);
            settingOptionButton.setBackground(new Color(32, 66, 211));
            settingOptionButton.setFont(new Font("Arial", Font.BOLD, 18));
            settingOptionButton.setForeground(Color.WHITE);
            settingOptionButton.setVisible(false);
            sidebarPanel.add(settingOptionButton);
    
            systemSettingsButton.addActionListener(e -> settingOptionButton.setVisible(!settingOptionButton.isVisible()));
    
            userButton1.addActionListener(e -> showManagerPanel(frame, drawingPanel, sidebarPanel, logOutButton));
    
            userButton2.addActionListener(e -> showStaffPanel(frame, drawingPanel, sidebarPanel, logOutButton));
    
            settingOptionButton.addActionListener(e -> showOptionPanel(frame, drawingPanel, sidebarPanel, logOutButton));
    
            frame.add(logOutButton);
            frame.addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentResized(java.awt.event.ComponentEvent componentEvent) {
                    logOutButton.setBounds(frame.getWidth() - logOutButton.getPreferredSize().width - 20, 20, logOutButton.getPreferredSize().width, 30);
                }
            });
    
            frame.setSize(1920, 1080);
            frame.setVisible(true);
                        return sidebarPanel;
        }
    
        private static void showManagerPanel(JFrame frame, JComponent drawingPanel, JPanel sidebarPanel, JButton logOutButton) {
            frame.getContentPane().removeAll(); // Clear the frame content
        
            frame.add(drawingPanel); // Keep the drawingPanel
            frame.add(sidebarPanel); // Keep the sidebarPanel
        
            JPanel managerPanel = new JPanel();
            managerPanel.setBounds(350, 100, 600, 500);
            managerPanel.setLayout(null);
            managerPanel.setBackground(Color.WHITE);
        
            JLabel welcomeLabel = new JLabel("Manager");
            welcomeLabel.setBounds(190, 140, 200, 50);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        
            JLabel nameLabel = new JLabel("Name:");
            nameLabel.setBounds(70, 250, 120, 30);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
            JTextField nameField = new JTextField("");
            nameField.setBounds(260, 250, 200, 30);
            nameField.setEditable(true);
        
            JLabel emailLabel = new JLabel("Email Address:");
            emailLabel.setBounds(70, 300, 200, 30);
            emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
            JTextField emailField = new JTextField("");
            emailField.setBounds(260, 300, 200, 30);
            emailField.setEditable(true);
        
            JLabel mobileLabel = new JLabel("Mobile No:");
            mobileLabel.setBounds(70, 350, 150, 30);
            mobileLabel.setFont(new Font("Arial", Font.BOLD, 20));
            JTextField mobileField = new JTextField("");
            mobileField.setBounds(260, 350, 200, 30);
            mobileField.setEditable(true);
        
            JLabel genderLabel = new JLabel("Gender:");
            genderLabel.setBounds(70, 400, 120, 30);
            genderLabel.setFont(new Font("Arial", Font.BOLD, 20));
            JTextField genderField = new JTextField("");
            genderField.setBounds(260, 400, 200, 30);
            genderField.setEditable(true);
        
            managerPanel.add(welcomeLabel);
            managerPanel.add(nameLabel);
            managerPanel.add(nameField);
            managerPanel.add(emailLabel);
            managerPanel.add(emailField);
            managerPanel.add(mobileLabel);
            managerPanel.add(mobileField);
            managerPanel.add(genderLabel);
            managerPanel.add(genderField);
        
            JButton editButton = new JButton("Edit");
            editButton.setBounds(70, 20, 90, 30);
            editButton.setBackground(new Color(134, 157, 255));
            editButton.setForeground(Color.BLACK);
        
            JButton deleteButton = new JButton("Delete");
            deleteButton.setBounds(200, 20, 90, 30);
            deleteButton.setBackground(new Color(134, 157, 255));
            deleteButton.setForeground(Color.BLACK);
        
            managerPanel.add(editButton);
            managerPanel.add(deleteButton);
    
            
            // Edit button functionality
            editButton.addActionListener(e -> {
                if (editButton.getText().equals("Edit")) {
                    // Enable editing
                    nameField.setEditable(true);
                    emailField.setEditable(true);
                    mobileField.setEditable(true);
                    genderField.setEditable(true);
                    editButton.setText("Save");
                } else {
                    // Save the information and disable editing
                    nameField.setEditable(false);
                    emailField.setEditable(false);
                    mobileField.setEditable(false);
                    genderField.setEditable(false);
                    editButton.setText("Edit");
                    JOptionPane.showMessageDialog(frame, "Information saved successfully!");
                }
            });
        
            // Delete button functionality
            deleteButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete the information?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    nameField.setText("");
                    emailField.setText("");
                    mobileField.setText("");
                    genderField.setText("");
                    nameField.setEditable(true);
                    emailField.setEditable(true);
                    mobileField.setEditable(true);
                    genderField.setEditable(true);
                    editButton.setText("Save"); // Allow the user to input new data
                }
            });
        
            frame.add(managerPanel); // Add manager content
            frame.add(logOutButton);
            frame.revalidate();
            frame.repaint();
        }
        
        private static void showStaffPanel(JFrame frame, JComponent drawingPanel, JPanel sidebarPanel, JButton logOutButton) {
            frame.getContentPane().removeAll(); // Clear the frame content
            frame.add(drawingPanel); // Keep the drawingPanel
            frame.add(sidebarPanel); // Keep the sidebarPanel
        
            JPanel staffPanel = new JPanel();
            staffPanel.setBounds(350, 100, 600, 500);
            staffPanel.setLayout(null);
            staffPanel.setBackground(Color.WHITE);
        
            JLabel welcomeLabel = new JLabel("Staff");
            welcomeLabel.setBounds(190, 140, 200, 50);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        
            JLabel nameLabel = new JLabel("Name:");
            nameLabel.setBounds(70, 200, 120, 30);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
            JTextField nameField = new JTextField("");
            nameField.setBounds(260, 200, 200, 30);
        
            JLabel emailLabel = new JLabel("Email:");
            emailLabel.setBounds(70, 250, 120, 30);
            emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
            JTextField emailField = new JTextField("");
            emailField.setBounds(260, 250, 200, 30);
        
            JLabel mobileLabel = new JLabel("Mobile:");
            mobileLabel.setBounds(70, 300, 120, 30);
            mobileLabel.setFont(new Font("Arial", Font.BOLD, 20));
            JTextField mobileField = new JTextField("");
            mobileField.setBounds(260, 300, 200, 30);
        
            JLabel genderLabel = new JLabel("Gender:");
            genderLabel.setBounds(70, 350, 120, 30);
            genderLabel.setFont(new Font("Arial", Font.BOLD, 20));
            JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female"});
            genderComboBox.setBounds(260, 350, 200, 30);
        
            JButton addButton = new JButton("Add Staff");
            addButton.setBounds(70, 20, 90, 30);
            addButton.setBackground(new Color(134, 157, 255));
            addButton.setForeground(Color.BLACK);
        
            JButton viewButton = new JButton("View Staff");
            viewButton.setBounds(200, 20, 90, 30);
            viewButton.setBackground(new Color(134, 157, 255));
            viewButton.setForeground(Color.BLACK);
        
            // Action Listener for Add Button
            addButton.addActionListener(e -> {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String mobile = mobileField.getText().trim();
                String gender = genderComboBox.getSelectedItem().toString();
        
                if (name.isEmpty() || email.isEmpty() || mobile.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                // Add user to the HotelFileSystem
                HotelFileSystem hotelFileSystem = new HotelFileSystem();
                HotelFileSystem.User newUser = new HotelFileSystem.User("Staff", name, email, mobile, gender);
                hotelFileSystem.addUser(newUser);
        
                // Clear fields after adding
                nameField.setText("");
                emailField.setText("");
                mobileField.setText("");
                genderComboBox.setSelectedIndex(0);
        
                JOptionPane.showMessageDialog(frame, "Staff member added successfully!");
            });
        
            // Action Listener for View Button
            viewButton.addActionListener(e -> {
                StringBuilder staffList = new StringBuilder("Staff Members:\n");
                HotelFileSystem hotelFileSystem = new HotelFileSystem();
                for (HotelFileSystem.User user : hotelFileSystem.getAllUsers()) {
                    if (user.role.equals("Staff")) {
                        staffList.append(user.name).append(" - ").append(user.email).append("\n");
                    }
                }
                JOptionPane.showMessageDialog(frame, staffList.toString(), "Staff List", JOptionPane.INFORMATION_MESSAGE);
            });
        
            staffPanel.add(welcomeLabel);
            staffPanel.add(nameLabel);
            staffPanel.add(nameField);
            staffPanel.add(emailLabel);
            staffPanel.add(emailField);
            staffPanel.add(mobileLabel);
            staffPanel.add(mobileField);
            staffPanel.add(genderLabel);
            staffPanel.add(genderComboBox);
            staffPanel.add(addButton);
            staffPanel.add(viewButton);
        
            frame.add(staffPanel); // Add staff content
            frame.add(logOutButton);
            frame.revalidate();
            frame.repaint();
        }
        private static void showOptionPanel(JFrame frame, JComponent drawingPanel, JPanel sidebarPanel, JButton logOutButton) {
            frame.getContentPane().removeAll(); // Clear the frame content
        
            frame.add(drawingPanel); // Keep the drawingPanel
            frame.add(sidebarPanel); // Keep the sidebarPanel
        
            JPanel managerPanel = new JPanel();
            managerPanel.setBounds(350, 100, 800, 800);
            managerPanel.setLayout(null);
            managerPanel.setBackground(Color.WHITE);
        
            JLabel welcomeLabel = new JLabel("Filter:");
            welcomeLabel.setBounds(5, 2, 200, 50);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        
            // Date filters
            JLabel fromDateLabel = new JLabel("From (MM/DD/YYYY):");
            fromDateLabel.setBounds(20, 50, 200, 30);
            fromDateLabel.setFont(new Font("Arial", Font.BOLD, 12));
            JTextField fromDateField = new JTextField();
            fromDateField.setBounds(170, 55, 100, 25);
        
            JLabel toDateLabel = new JLabel("To (MM/DD/YYYY):");
            toDateLabel.setBounds(20, 90, 200, 30);
            toDateLabel.setFont(new Font("Arial", Font.BOLD, 12));
            JTextField toDateField = new JTextField();
            toDateField.setBounds(170, 90, 100, 25);
        
            // Log Type filter
            JLabel logTypeLabel = new JLabel("System Log Type:");
            logTypeLabel.setBounds(300, 50, 150, 30);
            logTypeLabel.setFont(new Font("Arial", Font.BOLD, 12));
            JComboBox<String> logTypeComboBox = new JComboBox<>(new String[]{"All", "Info", "Error", "Warning"});
            logTypeComboBox.setBounds(450, 55, 120, 25);
        
            // Search box for user or action
            JLabel searchLabel = new JLabel("Search User/Action:");
            searchLabel.setBounds(300, 90, 200, 30);
            searchLabel.setFont(new Font("Arial", Font.BOLD, 12));
            JTextField searchField = new JTextField();
            searchField.setBounds(450, 90, 100, 25);
        
            // Table to display logs
            DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Date", "User", "Action", "Log Type", "Details"}, 0);
            JTable logTable = new JTable(tableModel);
            JScrollPane tableScroll = new JScrollPane(logTable);
            tableScroll.setBounds(20, 150, 750, 300);
        
            // Search Button
            JButton searchButton = new JButton("Search");
            searchButton.setBounds(620, 70, 90, 25);
            searchButton.setBackground(new Color(134, 157, 255));
            searchButton.setForeground(Color.BLACK);
        
            // Add Action Listener for Search
            searchButton.addActionListener(e -> {
                String fromDate = fromDateField.getText().trim();
                String toDate = toDateField.getText().trim();
                String logType = (String) logTypeComboBox.getSelectedItem();
                String searchQuery = searchField.getText().trim();
        
                tableModel.setRowCount(0); // Clear existing data
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Date startDate = null, endDate = null;
        
                try {
                    if (!fromDate.isEmpty()) startDate = sdf.parse(fromDate);
                    if (!toDate.isEmpty()) endDate = sdf.parse(toDate);
                } catch (Exception ex) {
    
                    JOptionPane.showMessageDialog(frame, "Invalid date format. Please use MM/dd/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                // Sample Logs Data for demonstration (replace this with dynamic fetching logic)
                Object[][] logs = {
                    {"11/18/2024", "Admin", "Login", "Info", "Successful login"},
                    {"11/19/2024", "User1", "Logout", "Info", "User logout"},
                    {"11/20/2024", "Admin", "Delete", "Warning", "Deleted user record"},
                };
        
                for (Object[] log : logs) {
                    boolean matches = true;
        
                    try {
                        Date logDate = sdf.parse((String) log[0]);
        
                        if (startDate != null && logDate.before(startDate)) matches = false;
                        if (endDate != null && logDate.after(endDate)) matches = false;
        
                    } catch (Exception ignored) { }
        
                    if (matches && (!searchQuery.isEmpty() && !(log[1].toString().contains(searchQuery) || log[2].toString().contains(searchQuery)))) matches = false;
                    if (matches && (!logType.equals("All") && !logType.equals(log[3].toString()))) matches = false;
        
                    if (matches) tableModel.addRow(log);
                }
            });
            managerPanel.add(welcomeLabel);
            managerPanel.add(fromDateLabel);
            managerPanel.add(fromDateField);
            managerPanel.add(toDateLabel);
            managerPanel.add(toDateField);
            managerPanel.add(logTypeLabel);
            managerPanel.add(logTypeComboBox);
            managerPanel.add(searchLabel);
            managerPanel.add(searchField);
            managerPanel.add(tableScroll);
            managerPanel.add(searchButton);
        
            frame.add(managerPanel); // Add manager content
            frame.add(logOutButton);
            frame.revalidate();
            frame.repaint();

        }
    
        public Jpanel Manager() {
        @SuppressWarnings("unused")
        JFrame frame = new JFrame("Manager Dashboard");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.WHITE);

        JButton logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "You have logged out.");
                frame.dispose();
                MainMenu();
            }
        });

        // Set bounds for the logOutButton dynamically
        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent componentEvent) {
                // Update position for logOutButton
                logOutButton.setBounds(frame.getWidth() - logOutButton.getPreferredSize().width - 20, 20, logOutButton.getPreferredSize().width, 30);
            }
        });

        // Setup for the log out button
        logOutButton.setBounds(frame.getWidth() - logOutButton.getPreferredSize().width - 20, 20, logOutButton.getPreferredSize().width, 30);

        // Drawing Panel
        JComponent drawingPanel = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(32, 66, 211));
                g2d.setStroke(new BasicStroke(3));
                g2d.drawLine(0, 70, getWidth(), 70);
                g2d.drawLine(320, 70, 320, getHeight());
                g2d.setFont(new Font("Arial", Font.BOLD, 24));
                g2d.setColor(Color.BLUE);
                g2d.drawString("Welcome, Manager!", 40, 45);
                g2d.setFont(new Font("Arial", Font.BOLD, 18));
                g2d.setColor(Color.BLACK);
                g2d.drawString("Manager", 40, 140);
            }
        };

        frame.setLayout(null);
        drawingPanel.setBounds(0, 0, 1920, 1080);
        frame.add(drawingPanel);

        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(32, 66, 211));
        sidebarPanel.setLayout(null);
        sidebarPanel.setBounds(0, 200, 320, 1080);
        frame.add(sidebarPanel);

        JButton manageRoomsButton = new JButton("Manage Rooms");
        manageRoomsButton.setBounds(10, 20, 300, 40);
        manageRoomsButton.setBackground(new Color(32, 66, 211));
        manageRoomsButton.setFont(new Font("Arial", Font.BOLD, 20));
        manageRoomsButton.setForeground(Color.WHITE);
        sidebarPanel.add(manageRoomsButton);

        JButton EditRoomDetailsButton = new JButton("Edit Room Details");
        EditRoomDetailsButton.setBounds(10, 70, 300, 40);
        EditRoomDetailsButton.setBackground(new Color(32, 66, 211));
        EditRoomDetailsButton.setFont(new Font("Arial", Font.BOLD, 18));
        EditRoomDetailsButton.setForeground(Color.WHITE);
        EditRoomDetailsButton.setVisible(false);
        sidebarPanel.add(EditRoomDetailsButton);

        JButton exportReportButton = new JButton("Export Report");
        exportReportButton.setBounds(10, 320, 300, 40);
        exportReportButton.setBackground(new Color(32, 66, 211));
        exportReportButton.setFont(new Font("Arial", Font.BOLD, 20));
        exportReportButton.setForeground(Color.WHITE);
        sidebarPanel.add(exportReportButton);

        exportReportButton.addActionListener(e -> {
            try {
                hotelFileSystem.generateBookingReport();
                hotelFileSystem.generateRevenueReport();
                JOptionPane.showMessageDialog(frame, "Reports exported successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "An error occurred while exporting reports: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        



        manageRoomsButton.addActionListener(e -> {
            EditRoomDetailsButton.setVisible(!EditRoomDetailsButton.isVisible());
            manageRoomsButton.setBackground(manageRoomsButton.getBackground().equals(new Color(32, 66, 211)) ? new Color(200, 200, 200) : new Color(32, 66, 211));
        });
        JButton manageBookingsButton = new JButton("Manage Bookings");
        manageBookingsButton.setBounds(10, 220, 300, 40);
        manageBookingsButton.setBackground(new Color(32, 66, 211));
        manageBookingsButton.setFont(new Font("Arial", Font.BOLD, 20));
        manageBookingsButton.setForeground(Color.WHITE);
        sidebarPanel.add(manageBookingsButton);

        JButton editRoomDetailsChangesButton = new JButton("Booking Changes");
        editRoomDetailsChangesButton.setBounds(10, 270, 300, 40);
        editRoomDetailsChangesButton.setBackground(new Color(32, 66, 211));
        editRoomDetailsChangesButton.setFont(new Font("Arial", Font.BOLD, 18));
        editRoomDetailsChangesButton.setForeground(Color.WHITE);
        editRoomDetailsChangesButton.setVisible(false);
        sidebarPanel.add(editRoomDetailsChangesButton);

        
        manageBookingsButton.addActionListener(e -> {
            manageBookingsButton.setBackground(manageBookingsButton.getBackground().equals(new Color(32, 66, 211)) ? new Color(200, 200, 200) : new Color(32, 66, 211));
        });

        manageBookingsButton.addActionListener(e -> editRoomDetailsChangesButton.setVisible(!editRoomDetailsChangesButton.isVisible()));

        EditRoomDetailsButton.addActionListener(e -> showEditRoomDetailsPanel(frame, drawingPanel, sidebarPanel, logOutButton));

        editRoomDetailsChangesButton.addActionListener(e -> showEditRoomDetailsChangesPanel(frame, drawingPanel, sidebarPanel, logOutButton));

        frame.add(logOutButton);

        frame.setSize(1920, 1080);
        frame.setVisible(true);
        return null;
    }

    @SuppressWarnings("unused")
    private static void showEditRoomDetailsPanel(JFrame frame, JComponent drawingPanel, JPanel sidebarPanel, JButton logOutButton) {
        frame.getContentPane().removeAll(); // Clear the frame content
        frame.add(drawingPanel); // Keep the drawingPanel
        frame.add(sidebarPanel); // Keep the sidebarPanel

        JPanel managerPanel = new JPanel();
        managerPanel.setBounds(350, 100, 1000, 600); // Adjusted panel size to fit all buttons
        managerPanel.setLayout(null);
        managerPanel.setBackground(Color.WHITE);

        // Style the buttons
        JButton normalVipButton = new JButton("Room Type");
        normalVipButton.setBounds(353, 191, 450, 80);
        normalVipButton.setBackground(new Color(238, 238, 238));  // Light color for Normal / VIP button
        normalVipButton.setForeground(Color.BLACK); // Black text color
        normalVipButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Set font size and style

        JButton amenitiesButton = new JButton("Amenities");
        amenitiesButton.setBounds(353, 290, 450, 80);
        amenitiesButton.setBackground(new Color(238, 238, 238));  // Light blue color for Amenities button
        amenitiesButton.setForeground(Color.BLACK); // Black text color
        amenitiesButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Set font size and style

        JButton availabilityButton = new JButton("Availability");
        availabilityButton.setBounds(353, 389, 450, 80);
        availabilityButton.setBackground(new Color(238, 238, 238));  // Green color for Availability button
        availabilityButton.setForeground(Color.BLACK); // Black text color
        availabilityButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Set font size and style

        // Add the buttons to the panel
        managerPanel.add(normalVipButton);
        managerPanel.add(amenitiesButton);
        managerPanel.add(availabilityButton);

        frame.add(managerPanel); // Add manager content to the frame
        frame.add(logOutButton); // Keep logOutButton visible
        frame.revalidate();
        frame.repaint();
        normalVipButton.addActionListener(e -> showRoomDetailsPanel(frame, drawingPanel, sidebarPanel, logOutButton));
        amenitiesButton.addActionListener(e -> showAmenitiesPanel(frame, drawingPanel, sidebarPanel, logOutButton));
        availabilityButton.addActionListener(e -> showAvailabilityPanel(frame, drawingPanel, sidebarPanel, logOutButton));

    }
    @SuppressWarnings("unused")
    private static void showRoomDetailsPanel(JFrame frame, JComponent drawingPanel, JPanel sidebarPanel, JButton logOutButton) {
        frame.getContentPane().removeAll();
        frame.add(drawingPanel);
        frame.add(sidebarPanel);
    
        JPanel panel = new JPanel();
        panel.setBounds(424, 100, 1000, 600);
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
    
        // Table model with three columns for each room classification
        String[] columnNames = {"Ordinary", "First Class", "Deluxe"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
    
        // Add amenities as separate rows for each classification
        model.addRow(new Object[]{"AIRCONDITION", "KINGS BED", "2 KINGS BED"});
        model.addRow(new Object[]{"FAMILY BED", "SOFA", "SOFA"});
        model.addRow(new Object[]{"TELEVISION (w/ CHANNELS)", "SMART TV (NETFLIX & VIVAMAX)", "REFRIGERATOR"});
        model.addRow(new Object[]{"COMFORT ROOM (w/ SHOWER)", "MINI REFRIGIRATOR", "TERRACE"});
        model.addRow(new Object[]{"FREE WIFI", "DINING AREA", "DINING AREA"});
        model.addRow(new Object[]{"GAMING CHAIR", "COMFORT ROOM (WITH BATHTUB, HOT AND COLD SHOWER & BIDET)", "COMFORTROOM (WITH HUGE BATHTUB & BIDET SHOWER ROOM W/ HOT AND COLD-WATER TEMPERATURE)"});
        model.addRow(new Object[]{"DINING TABLE", "FREE WIFI", "FLAT SCREEN TV (W/ NETFLIX, VIVAMAX, HOLLYWOOD MOVIES)"});
        model.addRow(new Object[]{"", "FREE ACCESS TO POOL", "FREE ACCESS TO POOL"});
        model.addRow(new Object[]{"", "FREE BREAKFAST (EVERY MORNING & SNACKS)", "FREE WIFI"});
        model.addRow(new Object[]{"", "GAMING CHAIR", "BREAKFAST, LUNCH & DINNER"});
        model.addRow(new Object[]{"", "", "GAMING CHAIR"});
    
        // Add price row at the bottom with bold prices
        model.addRow(new Object[]{"(₱1000 – 2,200)", "(₱3,000 – 5,300)", "(₱4,500 – 10,650)"});
    
        // Apply bold font to the last row (price row)
        table.getColumnModel().getColumn(0).setCellRenderer(createBoldCellRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(createBoldCellRenderer());
        table.getColumnModel().getColumn(2).setCellRenderer(createBoldCellRenderer());
    
        // Buttons
        JPanel topPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back");
    
        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);
        topPanel.add(backButton);
    
        // Add button functionality
        addButton.addActionListener(e -> {
            // Open dialog to input new row
            String ordinary = JOptionPane.showInputDialog(frame, "Enter Ordinary Amenity:");

            String firstClass = JOptionPane.showInputDialog(frame, "Enter First Class Amenity:");
            String deluxe = JOptionPane.showInputDialog(frame, "Enter Deluxe Amenity:");
    
            // Add the new row to the table
            model.addRow(new Object[]{ordinary, firstClass, deluxe});
        });
    
        // Edit button functionality
        editButton.addActionListener(e -> {
            // Get selected row
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Prompt for new values
                String ordinary = JOptionPane.showInputDialog(frame, "Edit Ordinary Amenity:", model.getValueAt(selectedRow, 0));
                String firstClass = JOptionPane.showInputDialog(frame, "Edit First Class Amenity:", model.getValueAt(selectedRow, 1));
                String deluxe = JOptionPane.showInputDialog(frame, "Edit Deluxe Amenity:", model.getValueAt(selectedRow, 2));
    
                // Update the row with new values
                model.setValueAt(ordinary, selectedRow, 0);
                model.setValueAt(firstClass, selectedRow, 1);
                model.setValueAt(deluxe, selectedRow, 2);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Delete button functionality
        deleteButton.addActionListener(e -> {
            // Get selected row
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Confirm deletion
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this row?", "Delete Row", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Remove the selected row
                    model.removeRow(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Back button action
        backButton.addActionListener(e -> showEditRoomDetailsPanel(frame, drawingPanel, sidebarPanel, logOutButton));
    
        // Add components
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        frame.add(panel);
        frame.add(logOutButton);
        frame.revalidate();
        frame.repaint();
    }
    
    // Method to create a cell renderer for bold text
    private static DefaultTableCellRenderer createBoldCellRenderer() {
        DefaultTableCellRenderer boldRenderer = new DefaultTableCellRenderer();
        boldRenderer.setFont(new Font("Arial", Font.BOLD, 12));  // Set font to bold
        return boldRenderer;
    }
    
    
    @SuppressWarnings("unused")
    private static void showAmenitiesPanel(JFrame frame, JComponent drawingPanel, JPanel sidebarPanel, JButton logOutButton) {
        frame.getContentPane().removeAll();
        frame.add(drawingPanel);
        frame.add(sidebarPanel);
    
        JPanel panel = new JPanel();
        panel.setBounds(424, 100, 1000, 600);
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
    
        // Table model with 3 columns: Classification, Amenity, Price
        String[] columnNames = {"Classification", "Amenity", "Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
    
        // Adding rows for First Class
        model.addRow(new Object[]{"First Class", "TOOTHBRUSH", "₱100"});
        model.addRow(new Object[]{"First Class", "TOOTHPASTE", "₱100"});
        model.addRow(new Object[]{"First Class", "2 BATHROOM TOWELS", "₱300"});
        model.addRow(new Object[]{"First Class", "3 BATHROBE", "₱600"});
        model.addRow(new Object[]{"First Class", "4 FACE TOWEL", "₱200"});

        model.addRow(new Object[]{"First Class", "RAZOR", "₱100"});
        model.addRow(new Object[]{"First Class", "SHAVING CREAM", "₱200"});
        model.addRow(new Object[]{"First Class", "SOAP", "₱100"});
        model.addRow(new Object[]{"First Class", "SHAMPOO", "₱100"});
        model.addRow(new Object[]{"First Class", "DISWASHING LIQUID", "₱100"});
        model.addRow(new Object[]{"First Class", "FABRIC CONDITIONERS", "₱150"});
        model.addRow(new Object[]{"First Class", "INSIDE SLIPPERS", "₱250"});
    
        // Adding rows for Deluxe
        model.addRow(new Object[]{"Deluxe", "COFFEE MAKER", "₱350"});
        model.addRow(new Object[]{"Deluxe", "TOOTHBRUSH", "₱100"});
        model.addRow(new Object[]{"Deluxe", "TOOTHPASTE", "₱100"});
        model.addRow(new Object[]{"Deluxe", "2 BATHROOM TOWELS", "₱300"});
        model.addRow(new Object[]{"Deluxe", "3 BATHROBE", "₱600"});
        model.addRow(new Object[]{"Deluxe", "4 FACE TOWEL", "₱200"});
        model.addRow(new Object[]{"Deluxe", "RAZOR", "₱100"});
        model.addRow(new Object[]{"Deluxe", "SHAVING CREAM", "₱200"});
        model.addRow(new Object[]{"Deluxe", "SOAP", "₱100"});
        model.addRow(new Object[]{"Deluxe", "SHAMPOO", "₱100"});
        model.addRow(new Object[]{"Deluxe", "DISWASHING LIQUID", "₱100"});
        model.addRow(new Object[]{"Deluxe", "FABRIC CONDITIONERS", "₱150"});
        model.addRow(new Object[]{"Deluxe", "INSIDE SLIPPERS", "₱250"});
        model.addRow(new Object[]{"Deluxe", "MINI BAR", "₱1500/night"});
        model.addRow(new Object[]{"Deluxe", "GYM", "₱500/day"});
        model.addRow(new Object[]{"Deluxe", "PANTRY", "₱1000"});
        model.addRow(new Object[]{"Deluxe", "REFRIGERATOR", "₱500"});
    
        // Buttons
        JPanel topPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back");
    
        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);
        topPanel.add(backButton);
    
        // Add button functionality
        addButton.addActionListener(e -> {
            // Open dialog to input new row data
            String classification = JOptionPane.showInputDialog(frame, "Enter Classification (First Class/Deluxe):");
            String amenity = JOptionPane.showInputDialog(frame, "Enter Amenity:");
            String price = JOptionPane.showInputDialog(frame, "Enter Price:");
    
            // Add the new row to the table
            if (classification != null && amenity != null && price != null) {
                model.addRow(new Object[]{classification, amenity, price});
            }
        });
    
        // Edit button functionality
        editButton.addActionListener(e -> {
            // Get selected row
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Prompt for new values
                String classification = JOptionPane.showInputDialog(frame, "Edit Classification:", model.getValueAt(selectedRow, 0));
                String amenity = JOptionPane.showInputDialog(frame, "Edit Amenity:", model.getValueAt(selectedRow, 1));
                String price = JOptionPane.showInputDialog(frame, "Edit Price:", model.getValueAt(selectedRow, 2));
    
                // Update the row with new values
                model.setValueAt(classification, selectedRow, 0);
                model.setValueAt(amenity, selectedRow, 1);
                model.setValueAt(price, selectedRow, 2);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Delete button functionality
        deleteButton.addActionListener(e -> {
            // Get selected row
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Confirm deletion

                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this row?", "Delete Row", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Remove the selected row
                    model.removeRow(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Back button action
        backButton.addActionListener(e -> showEditRoomDetailsPanel(frame, drawingPanel, sidebarPanel, logOutButton));
    
        // Add components
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);
        frame.add(logOutButton);
        frame.revalidate();
        frame.repaint();
    }    
    @SuppressWarnings("unused")
    private static void showAvailabilityPanel(JFrame frame, JComponent drawingPanel, JPanel sidebarPanel, JButton logOutButton) {
        frame.getContentPane().removeAll();
        frame.add(drawingPanel);
        frame.add(sidebarPanel);
    
        JPanel panel = new JPanel();
        panel.setBounds(424, 100, 1000, 600);
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
    
        // Table model for Room Numbers and Availability
        String[] columnNames = {"Room", "Available (Yes)", "Available (No)"};
        DefaultTableModel roomModel = new DefaultTableModel(columnNames, 0);
        JTable roomTable = new JTable(roomModel);
        JScrollPane roomScrollPane = new JScrollPane(roomTable);
    
        // Add initial data (10 rooms)
        for (int i = 1; i <= 10; i++) {
            roomModel.addRow(new Object[]{"Room " + i, Boolean.TRUE, Boolean.FALSE}); // Default to "Yes" checked
        }
    
        // Set the second and third columns to be checkbox columns
        roomTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        roomTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JCheckBox()));
    
        // Add listeners to ensure that only one checkbox (Yes or No) can be checked at a time per room
        roomTable.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkbox = new JCheckBox();
                checkbox.setSelected((Boolean) value);
                checkbox.setEnabled(true);
                checkbox.addActionListener(e -> {
                    if (checkbox.isSelected()) {
                        // Disable the "No" checkbox for the same row
                        roomModel.setValueAt(Boolean.FALSE, row, 2);
                        roomTable.getColumnModel().getColumn(2).getCellEditor().getTableCellEditorComponent(roomTable, Boolean.FALSE, false, row, column).setEnabled(false);
                    }
                });
                return checkbox;
            }
        });
    
        roomTable.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkbox = new JCheckBox();
                checkbox.setSelected((Boolean) value);
                checkbox.setEnabled(true);
                checkbox.addActionListener(e -> {
                    if (checkbox.isSelected()) {
                        // Disable the "Yes" checkbox for the same row
                        roomModel.setValueAt(Boolean.FALSE, row, 1);
                        roomTable.getColumnModel().getColumn(1).getCellEditor().getTableCellEditorComponent(roomTable, Boolean.FALSE, false, row, column).setEnabled(false);
                    }
                });

                return checkbox;
            }
        });
    
        // Button panel
        JPanel topPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back");
    
        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);
        topPanel.add(backButton);
    
        // Add button functionality
        addButton.addActionListener(e -> {
            // Open dialog to input new room number
            String roomNumber = JOptionPane.showInputDialog(frame, "Enter Room Number:");
            if (roomNumber != null) {
                // Add new row with room and default availability (Yes checked)
                roomModel.addRow(new Object[]{roomNumber, Boolean.TRUE, Boolean.FALSE});
            }
        });
    
        // Edit button functionality
        editButton.addActionListener(e -> {
            int selectedRoomRow = roomTable.getSelectedRow();
            if (selectedRoomRow != -1) {
                // Edit room number
                String roomNumber = JOptionPane.showInputDialog(frame, "Edit Room Number:", roomModel.getValueAt(selectedRoomRow, 0));
                roomModel.setValueAt(roomNumber, selectedRoomRow, 0);
    
                // Availability can still be changed via checkboxes
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Delete button functionality
        deleteButton.addActionListener(e -> {
            int selectedRoomRow = roomTable.getSelectedRow();
            if (selectedRoomRow != -1) {
                // Confirm deletion
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this row?", "Delete Row", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Remove the selected row
                    roomModel.removeRow(selectedRoomRow);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Back button action
        backButton.addActionListener(e -> showEditRoomDetailsPanel(frame, drawingPanel, sidebarPanel, logOutButton));
    
        // Add components
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(roomScrollPane, BorderLayout.CENTER);
    
        frame.add(panel);
        frame.add(logOutButton);
        frame.revalidate();
        frame.repaint();
    }    
    @SuppressWarnings("unused")
    private static void showEditRoomDetailsChangesPanel(JFrame frame, JComponent drawingPanel, JPanel sidebarPanel, JButton logOutButton) {
        frame.getContentPane().removeAll(); // Clear the frame content
        frame.add(drawingPanel); // Keep the drawingPanel
        frame.add(sidebarPanel); // Keep the sidebarPanel


        JPanel managerPanel = new JPanel();
        managerPanel.setBounds(550, 160, 1000, 600); // Adjusted panel size to fit all buttons
        managerPanel.setLayout(null);
        managerPanel.setBackground(Color.white);
        JLabel welcomeLabel = new JLabel("Filter:");
        welcomeLabel.setBounds(5, 2, 200, 50);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 15));
    
        // Date filters
        JLabel fromDateLabel = new JLabel("From (MM/DD/YYYY):");
        fromDateLabel.setBounds(20, 50, 200, 30);
        fromDateLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField fromDateField = new JTextField();
        fromDateField.setBounds(170, 55, 100, 25);
    
        JLabel toDateLabel = new JLabel("To (MM/DD/YYYY):");
        toDateLabel.setBounds(20, 90, 200, 30);
        toDateLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField toDateField = new JTextField();
        toDateField.setBounds(170, 90, 100, 25);
    
        // Log Type filter
        JLabel logTypeLabel = new JLabel("Select Type:");
        logTypeLabel.setBounds(300, 50, 150, 30);
        logTypeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JComboBox<String> logTypeComboBox = new JComboBox<>(new String[]{"New Booking", "Booking Modified", "Booking Canceled", "Booking No-Show", "Booking Reinstated", "Partial Booking Modification", "Booking Transfered", "Booking Upgraded/Downgraded"});
        logTypeComboBox.setBounds(500, 55, 120, 25);
    
        // Search box for user or action
        JLabel searchLabel = new JLabel("Search Booking ID/Guest Name:");
        searchLabel.setBounds(300, 90, 200, 30);
        searchLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JTextField searchField = new JTextField();
        searchField.setBounds(500, 90, 100, 25);
    
        // Table to display logs
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Date", "Booking ID", "Guest Name", "Action", "User", "Details"}, 0);
        JTable logTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(logTable);
        tableScroll.setBounds(20, 150, 750, 300);
    
        // Search Button
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(680, 70, 90, 25);
        searchButton.setBackground(new Color(134, 157, 255));
        searchButton.setForeground(Color.BLACK);
    
        // Add Action Listener for Search
        searchButton.addActionListener(e -> {
            String fromDate = fromDateField.getText().trim();
            String toDate = toDateField.getText().trim();
            String logType = (String) logTypeComboBox.getSelectedItem();
            String searchQuery = searchField.getText().trim();
    
            tableModel.setRowCount(0); // Clear existing data
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date startDate = null, endDate = null;
    
            try {
                if (!fromDate.isEmpty()) startDate = sdf.parse(fromDate);
                if (!toDate.isEmpty()) endDate = sdf.parse(toDate);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid date format. Please use MM/dd/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Sample Logs Data for demonstration (replace this with dynamic fetching logic)
            Object[][] logs = {
                {"11/18/2024", "BK20241118-001", "Loki Laufeyson", "Booking Modified", "Front Desk", "Extended Stay"},
                {"11/19/2024", "BK-20241119-001", "Wanda Maximoff", "New Booking", "Front Desk", "Reservation for 2 nights"}
            };
            for (Object[] log : logs) {
                boolean matches = true;
    
                try {
                    Date logDate = sdf.parse((String) log[0]);
    
                    if (startDate != null && logDate.before(startDate)) matches = false;
                    if (endDate != null && logDate.after(endDate)) matches = false;

                } catch (Exception ignored) { }
    
                if (matches && (!searchQuery.isEmpty() && !(log[1].toString().contains(searchQuery) || log[2].toString().contains(searchQuery)))) matches = false;
                if (matches && (!logType.equals("All") && !logType.equals(log[3].toString()))) matches = false;
    
                if (matches) tableModel.addRow(log);
            }
        });
        managerPanel.add(welcomeLabel);
        managerPanel.add(fromDateLabel);
        managerPanel.add(fromDateField);
        managerPanel.add(toDateLabel);
        managerPanel.add(toDateField);
        managerPanel.add(logTypeLabel);
        managerPanel.add(logTypeComboBox);
        managerPanel.add(searchLabel);
        managerPanel.add(searchField);
        managerPanel.add(tableScroll);
        managerPanel.add(searchButton);
    
        frame.add(managerPanel); // Add manager content
        frame.add(logOutButton);
        frame.revalidate();
        frame.repaint();
        
    }    


        public static void main(String[] args) {    
         new HFS_GUI(null);
        }

    private final class MouseAdapterExtension extends MouseAdapter {
            private final JCheckBox cbToothpaste;
            private final JTextField txtCheckInDate;
            private final JCheckBox cbDishwashingLiquid;
            private final JTextField txtLastname;
            private static JCheckBox cbParking = null;
            private final JCheckBox cbToothbrush;
            private final JTextField txtRoomNo;
            private final JComboBox<Integer> cbNoOfDays;
            private final JCheckBox cbBathrobe;
            private final JCheckBox cbRazor;
            private final JCheckBox cbPool;
            private final JTextField txtTotal;
            private final JCheckBox cbRefrigirator;
            private final JLabel txtTransactionId;
            private final JTextField txtFirstname;
            private final JCheckBox cbShampoo;
            private final JCheckBox cbPantry;
            private final JComboBox<Integer> cbNoOfChildren;
            private final JComboBox<Integer> cbNoOfAdults;
            private final JComboBox<String> cbRoomType;
            private final JCheckBox cbFabricConditioner;
            private final JCheckBox cbShavingCream;
            private final DefaultTableModel model;
            private final JTable table;                       
            private final JComboBox<Integer> cbMeals;
            private final JCheckBox cbInsideSlippers;
            private final JCheckBox cbFaceTowels;
            private static JCheckBox cbWiFi = null;
            private final JCheckBox cbSpa;                                                                       
            private final JCheckBox cbBathroomTowels;
            private final JTextField txtCheckOutDate;
            private final JCheckBox cbSoap;
            private final JTextField txtRoomRate;
            private final JCheckBox cbCoffeeMaker;
                                    
            private MouseAdapterExtension(JCheckBox cbToothpaste, JTextField txtCheckInDate,
            JCheckBox cbDishwashingLiquid, JTextField txtLastname, JCheckBox cbParking, JCheckBox cbToothbrush,
            JTextField txtRoomNo, JComboBox<Integer> cbNoOfDays, JCheckBox cbBathrobe, JCheckBox cbRazor,
            JCheckBox cbPool, JTextField txtTotal, JCheckBox cbRefrigirator, JLabel txtTransactionId,
            JTextField txtFirstname, JCheckBox cbShampoo, JCheckBox cbPantry, JComboBox<Integer> cbNoOfChildren,
            JComboBox<Integer> cbNoOfAdults, JComboBox<String> cbRoomType, JCheckBox cbFabricConditioner,
            JCheckBox cbShavingCream, DefaultTableModel model, JTable table, JComboBox<Integer> cbMeals,
            JCheckBox cbInsideSlippers, JCheckBox cbFaceTowels, JCheckBox cbWiFi, JCheckBox cbSpa,
            JCheckBox cbBathroomTowels, JTextField txtCheckOutDate, JCheckBox cbSoap, JTextField txtRoomRate,
            JCheckBox cbCoffeeMaker) {
                this.cbToothpaste = cbToothpaste;
                this.txtCheckInDate = txtCheckInDate;
                this.cbDishwashingLiquid = cbDishwashingLiquid;
                this.txtLastname = txtLastname;
                this.cbParking = cbParking;
                this.cbToothbrush = cbToothbrush;
                this.txtRoomNo = txtRoomNo;
                this.cbNoOfDays = cbNoOfDays;
                this.cbBathrobe = cbBathrobe;                                                                                                                                    
                this.cbRazor = cbRazor;
                this.cbPool = cbPool;
                this.txtTotal = txtTotal;
                this.cbRefrigirator = cbRefrigirator;
                this.txtTransactionId = txtTransactionId;
                this.txtFirstname = txtFirstname;
                this.cbShampoo = cbShampoo;
                this.cbPantry = cbPantry;
                this.cbNoOfChildren = cbNoOfChildren;
                this.cbNoOfAdults = cbNoOfAdults;
                this.cbRoomType = cbRoomType;
                this.cbFabricConditioner = cbFabricConditioner;
                this.cbShavingCream = cbShavingCream;
                this.model = model;
                this.table = table;
                this.cbMeals = cbMeals;
                this.cbInsideSlippers = cbInsideSlippers;
                this.cbFaceTowels = cbFaceTowels;
                this.cbWiFi = cbWiFi;                                                                                                    
                this.cbSpa = cbSpa;
                this.cbBathroomTowels = cbBathroomTowels;
                this.txtCheckOutDate = txtCheckOutDate;
                this.cbSoap = cbSoap;
                this.txtRoomRate = txtRoomRate;
                this.cbCoffeeMaker = cbCoffeeMaker;
            }
                                                
                                                                    @Override
                                                                    public void mouseClicked(java.awt.event.MouseEvent e) {
                                                                        int selectedRow = table.getSelectedRow();
                                                                        if (selectedRow != -1) {
                                                                            txtTransactionId.setText(model.getValueAt(selectedRow, 0).toString());
                                                                            txtFirstname.setText(model.getValueAt(selectedRow, 1).toString());
                                                                            txtLastname.setText(model.getValueAt(selectedRow, 2).toString());
                                                                            txtRoomNo.setText(model.getValueAt(selectedRow, 3).toString());
                                                                            cbRoomType.setSelectedItem(model.getValueAt(selectedRow, 4).toString());
                                                                            txtCheckInDate.setText(model.getValueAt(selectedRow, 6).toString());
                                                                            txtCheckOutDate.setText(model.getValueAt(selectedRow, 7).toString());
                                                                            cbNoOfDays.setSelectedItem(Integer.valueOf(model.getValueAt(selectedRow, 8).toString()));
                                                                            cbNoOfAdults.setSelectedItem(Integer.valueOf(model.getValueAt(selectedRow, 9).toString()));
                                                                            cbNoOfChildren.setSelectedItem(Integer.valueOf(model.getValueAt(selectedRow, 10).toString()));
                                                                            cbMeals.setSelectedItem(Integer.valueOf(model.getValueAt(selectedRow, 11).toString()));
                                                                            String amenities = model.getValueAt(selectedRow, 12).toString();
                                                                            cbWiFi.setSelected(amenities.contains("WiFi"));
                                                                            cbParking.setSelected(amenities.contains("Parking"));
                                                                            cbPool.setSelected(amenities.contains("Pool"));
                                                                            cbSpa.setSelected(amenities.contains("Spa"));
                                                                            cbCoffeeMaker.setSelected(amenities.contains("Coffee Maker"));
                                                                            cbToothbrush.setSelected(amenities.contains("Toothbrush"));
                                                                            cbToothpaste.setSelected(amenities.contains("Toothpaste"));
                                                                            cbBathroomTowels.setSelected(amenities.contains("2 Bathroom Towels"));
                                                                            cbBathrobe.setSelected(amenities.contains("3 Bathrobes"));
                                                                            cbFaceTowels.setSelected(amenities.contains("4 Face Towels"));
                                                                            cbRazor.setSelected(amenities.contains("Razor"));
                                                                            cbShavingCream.setSelected(amenities.contains("Shaving Cream"));
                                                                            cbSoap.setSelected(amenities.contains("Soap"));
                                                                            cbShampoo.setSelected(amenities.contains("Shampoo"));
                                                                            cbDishwashingLiquid.setSelected(amenities.contains("Dishwashing Liquid"));
                                                                            cbFabricConditioner.setSelected(amenities.contains("Fabric Conditioner"));
                                                                            cbInsideSlippers.setSelected(amenities.contains("Inside Slippers"));
                                                                            cbPantry.setSelected(amenities.contains("Pantry"));
                                                                            cbRefrigirator.setSelected(amenities.contains("Refrigerator"));
                                                                            txtTotal.setText(model.getValueAt(selectedRow, 13).toString());
                                                                        }
                                                                        
                                                                        
                                                                    }
                                                        
          
        }

    private static class Jpanel {

        public Jpanel() {
        }
    }
    
}