package model;

import util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.Calendar;
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
    private final List<Person> PatientProfile = new List<>();  // List of patients
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
            case "PA":
            handlePrintByAppointment();
            case "PP":
            handlePrintByPatient();
            case "PL":
            handlePrintByLocation();
            case "PS": 
            handlePrintBilling();

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
                    String firstName = tokens[1];
                    String lastName = tokens[2];
                    Date dob = parseDate(tokens[3]);
                    Location location = Location.valueOf(tokens[4]);
                    Specialty specialty = Specialty.valueOf(tokens[5]);
                    String npi = tokens[6];
                    Profile profile = new Profile(firstName, lastName, dob);
                    // Create a Doctor object with the parsed information
                    Doctor doctor = new Doctor(profile, location, specialty, npi);
                    providerList.add(doctor);

                } else if (tokens[0].equals("T")) {
                    String firstName = tokens[1];
                    String lastName = tokens[2];
                    Date dob = parseDate(tokens[3]);
                    Location location = Location.valueOf(tokens[4]);
                    int rate = Integer.parseInt(tokens[5]);
                    Profile profile = new Profile(firstName, lastName, dob);
                    Technician technician = new Technician(profile, location, rate);
                    // Create a Technician object with the parsed information
                
                    providerList.add(technician);
                }
            }
            fileScanner.close();

            // Sort providers by profile (assuming Provider implements Comparable)
            Sort.provider(providerList);
            System.out.println("Providers loaded and sorted.");
            //print the provider list
            for (Provider provider : providerList) {
                System.out.println(provider);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: providers.txt not found.");
        }
    }

    /**
     * Handles scheduling an office appointment (D command).
     * @param tokens The command tokens.
     */
    private void handleOfficeAppointment(String[] tokens) {
      
        // Parse the date, time, patient info, and provider NPI
        Date appointmentDate = parseDate(tokens[1]);
        Timeslot timeslot = Timeslot.fromSlotNumber(Integer.parseInt(tokens[2]));
        String firstName = tokens[3];
        String lastName = tokens[4];
        Date dob = parseDate(tokens[5]);
        String npi = tokens[6];
        if (!appointmentDate.isValid()) {
            System.out.println("Appointment Date: " + appointmentDate + " is not a valid calendar date.");
        } else if (appointmentDate.isTodayOrPast()) {
            System.out.println("Appointment Date: " + appointmentDate + " is today or a date before today");
        } else if (appointmentDate.isWeekend()) {
            System.out.println("Appointment Date: " + appointmentDate + " is a Saturday or Sunday.");
        } else if (!appointmentDate.SixMonths()) {
            System.out.println("Appointment Date: " + appointmentDate + " is not within six months.");
        }
        if (!isValidTimeslot(tokens[2])) {
            System.out.println(tokens[2] + " is not a valid time slot");
            return;  // Stop if the timeslot is invalid
        }
        if (!isValidDob(dob)) {
            System.out.println("Patient dob: " + dob + " is not a valid calendar date.");
            return;
        }
        if (tokens.length != 7) {
            System.out.println("Missing data tokens.");
            return;
        }
     

        //check if the appominet with the same paitent profile, date and timeslot already exists
        //use list iterator to iterate through the list of appointments
        for (Appointment appointment : appointmentList) {
            if (appointment.getPatient().getProfile().getFirstName().equals(firstName) && appointment.getPatient().getProfile().getLastName().equals(lastName) && appointment.getPatient().getProfile().getDateOfBirth().equals(dob) && appointment.getDate().equals(appointmentDate) && appointment.getTimeslot().equals(timeslot)) {
                System.out.println("Appointment already exists.");
                return;
            }
        }
        Provider provider = findProvider(npi);
        if (provider == null) {
            System.out.println( npi + " - provider doesn't exist.");
            return;
        }
   
        //check if doctor is available for that slot
       if(!isAvailable((Doctor) provider, appointmentDate, timeslot)){
           System.out.println("Doctor is not available for that slot");
           return;
           }
      //check if patient is new, else add to patient list
        if (isNewPatient(firstName, lastName, dob)) {
            Profile profile = new Profile(firstName, lastName, dob);
            Patient patient = new Patient(profile);
            PatientProfile.add(patient);
        }
        
        Patient patient = findPatient(firstName, lastName, dob);
        // Create an office appointment with the parsed information
        Appointment appointment = new Appointment(appointmentDate, timeslot, patient, provider);
        appointmentList.add(appointment);
        Visit visit = new Visit(0);
        patient.addVisit(visit);
        Doctor doctor = (Doctor) provider;
        System.out.println(appointment.getDate() + " " + appointment.getTimeslot() + " " + appointment.getPatient().getProfile() + " " + appointment.getProvider().getProfile() + provider.getLocation() + doctor.getSpecialty().name() + doctor.getNpi() +" booked.");    }

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
 // find docotor using npi method
    private Doctor findProvider(String npi) {
        for (Provider provider : providerList) {
            if (provider instanceof Doctor && ((Doctor) provider).getNpi().equals(npi)) {
                return (Doctor) provider;
            }
        }
        return null;
    }
    /**
     * The main method creates a ClinicManager object and starts the clinic manager.
     * @param args The command-line arguments.
     */
    private boolean isValidTimeslot(String slot) {
        int slotNumber = Integer.parseInt(slot);
        return slotNumber >= 1 && slotNumber <= 12;
    }
       /**
     * Validates a date of birth.
     * Ensures the date of birth is not in the future and is a valid calendar date.
     *
     * @param dob The date of birth to validate.
     * @return true if the DOB is valid, false otherwise.
     */
    private boolean isValidDob(Date dob) {
        // Check if the DOB is a valid date
        if (!dob.isValid()) {

            return false;
        }

        Calendar today = Calendar.getInstance();
        Calendar dobCalendar = Calendar.getInstance();
        dobCalendar.set(dob.getYear(), dob.getMonth() - 1, dob.getDay());  // Month is 0-based in Calendar

        if (dobCalendar.equals(today)) {
            return false;
        }
        if (dobCalendar.after(today)) {
            return false;
        }

        return true;
    }

    //make a method to ask if the patient is a new patient or not
    public boolean isNewPatient(String firstName, String lastName, Date dob) {
        for (Person person : PatientProfile) {
            if (person.getProfile().getFirstName().equals(firstName) && person.getProfile().getLastName().equals(lastName) && person.getProfile().getDateOfBirth().equals(dob)) {
                return false;
            }
        }
        return true;
    }
    //make a method to find the patient in the list
    public Patient findPatient(String firstName, String lastName, Date dob) {
        for (Person person : PatientProfile) {
            if (person.getProfile().getFirstName().equals(firstName) && person.getProfile().getLastName().equals(lastName) && person.getProfile().getDateOfBirth().equals(dob)) {
                return (Patient) person;
            }
        }
        return null;
    }
    //make a method to check if docotor is aviable for that slot 
    public boolean isAvailable(Doctor doctor, Date date, Timeslot timeslot) {
        for (Appointment appointment : appointmentList) {
            if (appointment.getProvider().equals(doctor) && appointment.getDate().equals(date) && appointment.getTimeslot().equals(timeslot)) {
                return false;
            }
        }
        return true;
    }

    // handlePrintByAppointment();
    /**
     * Prints all appointments sorted by appointment time.
     */
    private void handlePrintByAppointment() {

       Sort.appointment(appointmentList, 'd');
        for (Appointment appointment : appointmentList) {
            System.out.println(appointment);
        }
    }

    /**
     * Prints all appointments sorted by patient name.
     */
    private void handlePrintByPatient() {
        appointmentList.printByPatient();
    }

    /**
     * Prints all appointments sorted by provider location.
     */
    private void handlePrintByLocation() {
        appointmentList.printByLocation();
    }

    private void handlePrintBilling() {
        appointmentList.printBilling();
    }
   
}


