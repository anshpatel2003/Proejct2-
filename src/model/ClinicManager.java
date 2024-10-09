package model;

import util.Date;
import util.*;
import util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The ClinicManager class processes command-line inputs to manage appointments and provider information.
 * It replaces the Scheduler class from Project 1, and processes multiple commands at a time.
 * Commands include scheduling appointments, printing appointments, and more.
 * @author Your Name
 */
public class ClinicManager {

    private final List<Appointment> appointmentList = new List<>();  // A list to manage appointments
    private final List<Provider> providerList = new List<>();  // List of providers

    /**
     * Starts the Clinic Manager by loading providers and processing commands.
     */
    public void run() {

        // Load providers from the file
        loadProviders();
        System.out.println("Clinic Manager is running.");
        // Process command lines until "Q" is entered
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String commandLine = scanner.nextLine().trim();
            if (commandLine.isEmpty()) continue;  // Ignore empty lines

            // Split the command line by commas
            String[] tokens = commandLine.split(",");
            processCommand(tokens[0], tokens);  // Process the command
        }
    }

    /**
     * Processes the given command based on the first token (D, T, C, R, PO, PI, PC, Q).
     *
     * @param cmd The command token (e.g., D, T, C, etc.)
     * @param tokens The array of strings representing the command parameters.
     */
    private void processCommand(String cmd, String[] tokens) {
        switch (cmd) {
            case "D":
                handleOfficeAppointment(tokens);
                break;
            case "T":
                handleImagingAppointment(tokens);
                break;
            case "C":
                handleCancelAppointment(tokens);
                break;
            case "R":
                handleRescheduleAppointment(tokens);
                break;
            case "PO":
                displayOfficeAppointments();
                break;
            case "PI":
                displayImagingAppointments();
                break;
            case "PC":
                displayProviderCredits();
                break;
            case "Q":
                System.out.println("Clinic Manager terminated.");
                break;
            default:
                System.out.println("Invalid command.");
        }
    }

    /**
     * Loads providers (doctors and technicians) from the file (providers.txt) and adds them to the provider list.
     */
    private void loadProviders() {
        try {
            File providerFile = new File("providers.txt");
            Scanner fileScanner = new Scanner(providerFile);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;  // Skip empty lines
                // Split the line by spaces
                String[] tokens = line.split("\\s+");
                // Determine if the line is for a Doctor (D) or Technician (T)
                if (tokens[0].equals("D")) {
                    // Create a Doctor object
                    String firstName = tokens[1];
                    String lastName = tokens[2];
                    Date dob = parseDate(tokens[3]);
                    String location = tokens[4];
                    String specialty = tokens[5];
                    String npi = tokens[6];
                    Profile doc = new Profile(firstName,lastName,dob);
                    Doctor doctor = new Doctor(doc, location, specialty, npi);
                    providerList.add(doctor);

                } else if (tokens[0].equals("T")) {
                    // Create a Technician object
                    String firstName = tokens[1];
                    String lastName = tokens[2];
                    Date dob = parseDate(tokens[3]);
                    String location = tokens[4];
                    int ratePerVisit = Integer.parseInt(tokens[5]);

                    // Create a Technician object with the parsed information
                    Technician technician = new Technician(firstName, lastName, dob, location, ratePerVisit);
                    providerList.add(technician);
                }
            }
            fileScanner.close();

            // Sort providers by profile (assuming Provider implements Comparable)
            providerList.sort();
            System.out.println("Providers loaded and sorted.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: providers.txt not found.");
        }
    }

    /**
     * Handles scheduling an office appointment (D command).
     * @param tokens The command tokens.
     */
    private void handleOfficeAppointment(String[] tokens) {
        if (tokens.length != 7) {
            System.out.println("Invalid input for office appointment.");
            return;
        }
        // Parse the date, time, patient info, and provider NPI
        Date appointmentDate = parseDate(tokens[1]);
        Timeslot timeslot = Timeslot.fromSlotNumber(Integer.parseInt(tokens[2]));
        String firstName = tokens[3];
        String lastName = tokens[4];
        Date dob = parseDate(tokens[5]);
        int npi = Integer.parseInt(tokens[6]);

        // Additional logic to create and validate an office appointment
        System.out.println("Office appointment scheduled.");
    }

    /**
     * Handles scheduling an imaging appointment (T command).
     * @param tokens The command tokens.
     */
    private void handleImagingAppointment(String[] tokens) {
        if (tokens.length != 7) {
            System.out.println("Invalid input for imaging appointment.");
            return;
        }
        // Parse the date, time, patient info, and room type
        Date appointmentDate = parseDate(tokens[1]);
        Timeslot timeslot = Timeslot.fromSlotNumber(Integer.parseInt(tokens[2]));
        String firstName = tokens[3];
        String lastName = tokens[4];
        Date dob = parseDate(tokens[5]);
        String roomType = tokens[6].toLowerCase();

        // Additional logic to create and validate an imaging appointment
        System.out.println("Imaging appointment scheduled.");
    }

    /**
     * Handles canceling an appointment (C command).
     * @param tokens The command tokens.
     */
    private void handleCancelAppointment(String[] tokens) {
        if (tokens.length != 7) {
            System.out.println("Invalid input for cancellation.");
            return;
        }
        // Parse the date, time, and patient info for cancellation
        System.out.println("Appointment canceled.");
    }

    /**
     * Handles rescheduling an appointment (R command).
     * @param tokens The command tokens.
     */
    private void handleRescheduleAppointment(String[] tokens) {
        if (tokens.length != 7) {
            System.out.println("Invalid input for rescheduling.");
            return;
        }
        // Parse the original date, new time, and patient info for rescheduling
        System.out.println("Appointment rescheduled.");
    }

    /**
     * Displays the list of office appointments ordered by location.
     */
    private void displayOfficeAppointments() {
        System.out.println("Displaying office appointments.");
        // Logic to display appointments ordered by location
    }

    /**
     * Displays the list of imaging appointments ordered by location.
     */
    private void displayImagingAppointments() {
        System.out.println("Displaying imaging appointments.");
        // Logic to display imaging appointments ordered by location
    }

    /**
     * Displays the expected credit amounts for each provider.
     */
    private void displayProviderCredits() {
        System.out.println("Displaying provider credits.");
        // Logic to display credits
    }

    /**
     * Parses a date from the string input in "MM/DD/YYYY" format.
     * @param dateString The date string to parse.
     * @return A Date object.
     */
    private Date parseDate(String dateString) {
        String[] parts = dateString.split("/");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return new Date(year, month, day);
    }
}

