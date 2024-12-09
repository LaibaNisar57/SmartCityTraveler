import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class SmartCityTraveler {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Smart City Traveler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new GridBagLayout());

        // Set custom colors
        Color backgroundColor = new Color(103, 56, 248);
        Color textColor = Color.WHITE;
        Color buttonColor = new Color(255, 96, 247);
        Color buttonTextColor = Color.WHITE;

        frame.getContentPane().setBackground(backgroundColor);

        // Create components with updated styling
        JLabel titleLabel = new JLabel("Smart City Traveler");
        titleLabel.setFont(new Font("Brush Script MT", Font.PLAIN, 45));
        titleLabel.setForeground(textColor);

        JLabel locationLabel = new JLabel("Enter Location:");
        locationLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        locationLabel.setForeground(textColor);

        JTextField locationField = new JTextField(20);
        locationField.setFont(new Font("Times New Roman", Font.BOLD, 16));

        JButton getPlacesButton = new JButton("Find Famous Places");
        getPlacesButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        getPlacesButton.setBackground(buttonColor);
        getPlacesButton.setForeground(buttonTextColor);
        getPlacesButton.setFocusPainted(false);

        JLabel sourceLabel = new JLabel("Source:");
        sourceLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        sourceLabel.setForeground(textColor);

        JTextField sourceField = new JTextField(20);
        sourceField.setFont(new Font("Times New Roman", Font.BOLD, 16));

        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        destinationLabel.setForeground(textColor);

        JTextField destinationField = new JTextField(20);
        destinationField.setFont(new Font("Times New Roman", Font.BOLD, 16));

        JButton getDirectionsButton = new JButton("Get Directions");
        getDirectionsButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        getDirectionsButton.setBackground(buttonColor);
        getDirectionsButton.setForeground(buttonTextColor);
        getDirectionsButton.setFocusPainted(false);

        // Add components to the frame
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(locationLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(locationField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(getPlacesButton, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(sourceLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 3;
        frame.add(sourceField, gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(destinationLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 4;
        frame.add(destinationField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(getDirectionsButton, gbc);

        // Add action listeners to the buttons
        getPlacesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String location = locationField.getText().trim();

                if (location.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a location.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Construct the Google Maps URL for famous places search
                    String url = "https://www.google.com/maps/search/famous+places+in+" + location.replace(" ", "+");
                    Desktop.getDesktop().browse(new URI(url));

                    // Save to file
                    saveToFile("locations.txt", "Famous places search: " + location);
                } catch (IOException | URISyntaxException ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to open places search: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        getDirectionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String source = sourceField.getText().trim();
                String destination = destinationField.getText().trim();

                if (source.isEmpty() || destination.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter both source and destination.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Construct the Google Maps URL for directions
                    String url = "https://www.google.com/maps/dir/" + source.replace(" ", "+") + "/" + destination.replace(" ", "+");
                    Desktop.getDesktop().browse(new URI(url));

                    // Save to file
                    saveToFile("locations.txt", "Directions search: From " + source + " to " + destination);
                } catch (IOException | URISyntaxException ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to open directions: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // Show the frame
        frame.setVisible(true);
    }

    // Helper method to save data to a file
    private static void saveToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
