package model;

import util.*;

import java.io.File;
import java.io.FileNotFoundException;
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

    private CircularTechnicianList technicianList = new CircularTechnicianList(); //circular list of technicians

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
    if ("D".equals(cmd)) {
        handleOfficeAppointment(tokens);
    }
    else if("T".equals(cmd)){
        handleImagingAppointment(tokens);
    }
    else if("C".equals(cmd)){
        handleCancelAppointment(tokens);
    }
    else if("R".equals(cmd)){
        handleRescheduleAppointment(tokens);

    }
    else if("PO".equals(cmd)){
        displayOfficeAppointments();
    }
    else if("PI".equals(cmd)){
        displayImagingAppointments();
    }
    else  if("PC".equals(cmd)){
        displayProviderCredits();
    }
    else if("PA".equals(cmd)){
        handlePrintByAppointment();
    }
    else if("PP".equals(cmd)){
        handlePrintByPatient();
    }
    else  if("PL".equals(cmd)){
        handlePrintByLocation();
    }
    else if("PS".equals(cmd)){
        handlePrintBilling();
    }
    else if("Q".equals(cmd)){
        System.out.println("Clinic Manager terminated.");
        System.exit(0);
    }
    else{
        System.out.println("Invalid command!");
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

            
            Sort.provider(providerList);
            System.out.println("Providers loaded and sorted.");
            //print the provider list
            for (Provider provider : providerList) {
                System.out.println(provider);
            }
            LoadTechinicianList();
            technicianList.resetCurrentTechnician();


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
            System.out.println("Missing data tokens.");
            return;
        }
        // Parse the date, time, patient info, and provider NPI
        Date appointmentDate = parseDate(tokens[1]);
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
       else if (!isValidTimeslot(tokens[2])) {
            System.out.println(tokens[2] + " is not a valid time slot");
            return;  // Stop if the timeslot is invalid
        }
        //check if the patient dob is today or after today 
        else if(!dob.isTodayOrPast()){
            System.out.println("Patient dob: " + dob + " is today or a date after today");
            return;
        }
       else if (!isValidDob(dob)) {
            System.out.println("Patient dob: " + dob + " is not a valid calendar date.");
            return;
        }
       else if (tokens.length != 7) {
            System.out.println("Missing data tokens.");
            return;
        }
     else{
        Timeslot timeslot = Timeslot.fromSlotNumber(Integer.parseInt(tokens[2]));

        //check if the appominet with the same paitent profile, date and timeslot already exists
        //use list iterator to iterate through the list of appointments
        for (Appointment appointment : appointmentList) {
            if (appointment.getPatient().getProfile().getFirstName().equals(firstName) && appointment.getPatient().getProfile().getLastName().equals(lastName) && appointment.getPatient().getProfile().getDateOfBirth().equals(dob) && appointment.getDate().equals(appointmentDate) && appointment.getTimeslot().equals(timeslot)) {
                
                System.out.println(appointment.getPatient().toString()+ " has an existing appointment at the same time slot.");
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
           System.out.println(provider.toString() + "is not available at slot "+ tokens[2]);
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
        Visit visit = new Visit(((Doctor) provider).rate());
        patient.addVisit(visit);
       
        System.out.println(appointment.toString() + " booked.");

    }

    }
    /**
     * Handles scheduling an imaging appointment (T command).
     * @param tokens The command tokens.
     */
    private void handleImagingAppointment(String[] tokens) {
        if (tokens.length != 7) {
            System.out.println("Missing data tokens.");
            return;
        }
        // Parse the date, time, patient info, and room type
        Date appointmentDate = parseDate(tokens[1]);
        String firstName = tokens[3];
        String lastName = tokens[4];
        Date dob = parseDate(tokens[5]);
        if(!isValidRoom(tokens[6])){
            System.out.println(tokens[6] + " - imaging service not provided");
            return;
        }
        if (!appointmentDate.isValid()) {
            System.out.println("Appointment Date: " + appointmentDate + " is not a valid calendar date.");
        } else if (appointmentDate.isTodayOrPast()) {
            System.out.println("Appointment Date: " + appointmentDate + " is today or a date before today");
        } else if (appointmentDate.isWeekend()) {
            System.out.println("Appointment Date: " + appointmentDate + " is a Saturday or Sunday.");
        } else if (!appointmentDate.SixMonths()) {
            System.out.println("Appointment Date: " + appointmentDate + " is not within six months.");
        }
       else if (!isValidTimeslot(tokens[2])) {
            System.out.println(tokens[2] + " is not a valid time slot");
            return;  // Stop if the timeslot is invalid
        }
        else if(!dob.isTodayOrPast()){
            System.out.println("Patient dob: " + dob + " is today or a date after today");
            return;
        }
        else if (!isValidDob(dob)) {
            System.out.println("Patient dob: " + dob + " is not a valid calendar date.");
            return;
        }
        else if (tokens.length != 7) {
            System.out.println("Missing data tokens.");
            return;
        }
        else{
            Radiology room = Radiology.valueOf(tokens[6].toUpperCase()); 

            Timeslot timeslot = Timeslot.fromSlotNumber(Integer.parseInt(tokens[2]));

        //check if the appominet with the same paitent profile, date and timeslot already exists
        //use list iterator to iterate through the list of appointments
        for (Appointment appointment : appointmentList) {
            if (appointment.getPatient().getProfile().getFirstName().equals(firstName) && appointment.getPatient().getProfile().getLastName().equals(lastName) && appointment.getPatient().getProfile().getDateOfBirth().equals(dob) && appointment.getDate().equals(appointmentDate) && appointment.getTimeslot().equals(timeslot)) {
                
                System.out.println(appointment.getPatient().toString()+ " has an existing appointment at the same time slot.");
                return;
            }
        }
        if (isNewPatient(firstName, lastName, dob)) {
            Profile profile = new Profile(firstName, lastName, dob);
            Patient patient = new Patient(profile);
            PatientProfile.add(patient);
        }
        Patient patient = findPatient(firstName, lastName, dob);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        // Initialize to find the next available technician using the circular list
        Technician assignedTechnician = null;
        Technician startingTechnician = technicianList.getCurrentTechnician();
        Technician currentTechnician = startingTechnician;
        
        // Loop through technicians in a circular fashion
        do {
            Location location = ((Technician) currentTechnician).getLocation();
            // Check if the technician is available for this timeslot and the imaging room
            if (isTechnicianAvailable(currentTechnician, appointmentDate, timeslot) && isRoomAvailable(room, location, appointmentDate, timeslot)) {
                assignedTechnician = currentTechnician;
                break;
            }
            // Move to the next technician in the rotation
            currentTechnician = technicianList.getNextTechnician();
        } while (currentTechnician != startingTechnician);  // Stop when we’ve looped through all technicians
        if (assignedTechnician == null) {
            System.out.println("Cannot find an available technician at all locations for " + room.name() + " at slot " + tokens[2]);
            startingTechnician = technicianList.gethead();
            return;
        }
        // Create an imaging appointment with the parsed information
        Imaging newAppointment = new Imaging(appointmentDate, timeslot, patient, assignedTechnician, room);
        appointmentList.add(newAppointment);
        Visit visit = new Visit(assignedTechnician.rate());
        patient.addVisit(visit);
        System.out.println(newAppointment.toString() + " booked.");
        technicianList.getNextTechnician();
    }
    }
    /**
     * Handles canceling an appointment (C command).
     * @param tokens The command tokens.
     */
    private void handleCancelAppointment(String[] tokens) {
        if (tokens.length != 6) {
            System.out.println("Missing data tokens.");
            return;
        }

            Date appointmentDate = parseDate(tokens[1]);
            Timeslot timeslot = Timeslot.fromSlotNumber(Integer.parseInt(tokens[2]));
            Patient patientProfile = new Patient(new Profile(tokens[3], tokens[4], parseDate(tokens[5])));
            Appointment dummyAppointment = new Appointment(appointmentDate, timeslot, patientProfile, null);
            
            // Check if the appointment exists in the appointment list
            if (appointmentList.contains(dummyAppointment)) {
                Patient patient =  findPatient(patientProfile.getProfile().getFirstName(), patientProfile.getProfile().getLastName(), patientProfile.getProfile().getDateOfBirth());
                if (patient != null) {
                   Visit Visit = patient.getVisits();
                    patient.removeVisit(Visit);

                    appointmentList.remove(dummyAppointment);  // Remove the appointment from the list
                    String cancellationMessage = String.format("%s %s %s %s - appointment has been canceled.",
                            appointmentDate.toString(),
                            timeslot.formatTime(),
                            patientProfile.getProfile().getFirstName() + " " + patientProfile.getProfile().getLastName(),
                            patientProfile.getProfile().getDateOfBirth().toString());
                    System.out.println(cancellationMessage);

                }
            } else {
                String message = String.format("%s %s %s %s - appointment does not exist.",
                        appointmentDate.toString(),
                        timeslot.formatTime(),
                        patientProfile.getProfile().getFirstName() + " " + patientProfile.getProfile().getLastName(),
                        patientProfile.getProfile().getDateOfBirth().toString());
                System.out.println(message);
            }
        }
    

    /**
     * Handles rescheduling an appointment (R command).
     * @param tokens The command tokens.
     */
    private void handleRescheduleAppointment(String[] tokens) {
       
        if (tokens.length != 7) {
            System.out.println(" Missing Data tokens");
            return;
        }
        Date appointmentDate = parseDate(tokens[1]);
        Timeslot oldTimeslot = Timeslot.fromSlotNumber(Integer.parseInt(tokens[2]));
        Patient patientProfile = new Patient(new Profile(tokens[3], tokens[4], parseDate(tokens[5])));
        Timeslot newTimeslot = Timeslot.fromSlotNumber(Integer.parseInt(tokens[6]));
        Appointment oldAppointment = new Appointment(appointmentDate, oldTimeslot, patientProfile, null);
        // Find the existing appointment
        Appointment dummyAppointment = new Appointment(appointmentDate, oldTimeslot, patientProfile, null);
        if(!appointmentList.contains(oldAppointment)){
            String message = String.format("%s %s %s %s does not exist",appointmentDate.toString(),oldTimeslot.formatTime(),patientProfile.getProfile().getFirstName() + " " + patientProfile.getProfile().getLastName(),
                    patientProfile.getProfile().getDateOfBirth().toString()  );
            System.out.println(message);
            return;
        }
        // Find the existing appointment
        Appointment existingAppointment = null;
        for (Appointment appointment : appointmentList) {
            if (appointment.equals(dummyAppointment)) {
                existingAppointment = appointment;
                break;
            }
        }
        Doctor provider = (Doctor) existingAppointment.getProvider();

        if(!isAvailable(provider, appointmentDate, newTimeslot)){
            return;
        }
        if(!isValidTimeslot(tokens[6])){
            System.out.println(tokens[6] + " is not a valid time slot");
            return;}

        else if (appointmentList.contains(oldAppointment)) {
            appointmentList.remove(oldAppointment);
            Appointment newAppointment = new Appointment(appointmentDate, newTimeslot, patientProfile, null);
            appointmentList.add(newAppointment);

            String newapp = String.format(
                    "Rescheduled to %s %s %s %s",
                    appointmentDate.toString(),
                    newTimeslot.formatTime(),
                    patientProfile.toString(),
                    provider.toString()
                    

                    )
            ;
            System.out.println(newapp);
            return;

        }
       
    }

    /**
     * Displays the list of office appointments ordered by location.
     */
    private void displayOfficeAppointments() {
       if(appointmentList.isEmpty()){
        System.out.println("Schedule calendar is empty.");
        return;
       }
        System.out.println("** List of office appointments ordered by county/date/time.");
        Sort.sortbyLocation(appointmentList, providerList);

        for (Appointment appointment : appointmentList) {
            if (appointment instanceof Appointment) {
                System.out.println(appointment);
            }
        }

        System.out.println("** end of list **");
    }

    /**
     * Displays the list of imaging appointments ordered by location.
     */
    private void displayImagingAppointments() {
        if(appointmentList.isEmpty()){
            System.out.println("Schedule calendar is empty.");
            return;
           }
        Sort.sortbyLocation(appointmentList, providerList);
        System.out.println("** List of radiology appointments ordered by county/date/time.");
        for (Appointment appointment : appointmentList) {
            if (appointment instanceof Imaging) {
                System.out.println(appointment);
            }
        }
       System.out.println("** end of list **");
        
    }

    /**
     * Displays the expected credit amounts for each provider.
     */
    private void displayProviderCredits() {
        if(appointmentList.isEmpty()){
            System.out.println("Schedule calendar is empty.");
            return;
        }
        System.out.println("** Credit amount ordered by provider. **");
        for (Provider provider : providerList) {
            if (provider instanceof Doctor) {
                Doctor doctor = (Doctor) provider;
                int totalCharge = 0;
                for (Appointment appointment : appointmentList) {
                    if (appointment.getProvider().equals(doctor)) {
                        totalCharge += doctor.rate();
                    }
                }
                if (totalCharge > 0) {
                   System.out.println(provider.toString() + "[ credit amount: $" + totalCharge + "]");
            }
        }
        if(provider instanceof Technician){
            Technician technician = (Technician) provider;
            int totalCharge = 0;
            for (Appointment appointment : appointmentList) {
                if (appointment.getProvider().equals(technician)) {
                    totalCharge += technician.rate();
                }
            }
            if (totalCharge > 0) {
                System.out.println(provider.toString() + "[ credit amount: $" + totalCharge + "]");
            }
      } 
     } 
       System.out.println("** end of list **");
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
        try {
            int timeslotNumber = Integer.parseInt(slot);  // Try to convert input to an integer
            if (timeslotNumber >= 1 && timeslotNumber <= 12) {
                return true;  // Valid timeslot number between 1 and 6
            } else {
                return false;  // Timeslot number is out of range
            }
        } catch (NumberFormatException e) {
            return false;  // Input was not a valid number
        }
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
       if(appointmentList.isEmpty()){
           System.out.println("Schedule calendar is empty.");
           return;
       }
       System.out.println("** List of appointments, ordered by date/time/provider.");
        for (Appointment appointment : appointmentList) {
            System.out.println(appointment);
        }
        System.out.println("** end of list **");
    }

    /**
     * Prints all appointments sorted by patient name.
     */
    private void handlePrintByPatient() {
        Sort.appointment(appointmentList, 'p');
        if(appointmentList.isEmpty()){
            System.out.println("Schedule calendar is empty.");
            return;
        }
        for (Appointment appointment : appointmentList) {
            System.out.println(appointment);
        }
    }

    /**
     * Prints all appointments sorted by provider location.
     */
    private void handlePrintByLocation() {
       Sort.sortbyLocation(appointmentList, providerList);
        if(appointmentList.isEmpty()){
            System.out.println("Schedule calendar is empty.");
            return;
        }
       System.out.println("** List of appointments, ordered by county/date/time.");
        for (Appointment appointment : appointmentList) {
            System.out.println(appointment);
        }
        System.out.println("** end of list **");
    }
 /**
     * Prints billing statements for all patients.
     * The total charge is calculated based on the visits stored in the medical record.
     */
    private void handlePrintBilling() {
        if(appointmentList.isEmpty()){
            System.out.println("Schedule calendar is empty.");
            return;
        }

    //sort the patients by names, if name is equal compare date 
    Sort.patient(PatientProfile);  
    System.out.println("** Billing statement ordered by patient. ** ");
       for(Person patient : PatientProfile){
              if(patient instanceof Patient){
                Patient p = (Patient) patient;
                Visit current = p.getVisits();
                int totalCharge = 0;
                while(current != null){
                     totalCharge += current.getCharge();
                     current = current.getNext();
                }
               System.out.println(p.toString() + "[due: $" + totalCharge + "]");
              }
       }
         System.out.println("** end of list **");

         //empty the appointmentlist
        for (Appointment appointment : appointmentList) {
            appointmentList.remove(appointment);
        }
    }



   

 private boolean isTechnicianAvailable(Technician technician, Date appointmentDate, Timeslot timeslot) {
    // Check for conflicts in existing appointments for the same technician, timeslot, and room
    for (Appointment appointment : appointmentList) {
        if (appointment instanceof Imaging) {
            Imaging imgApp = (Imaging) appointment;
            if (imgApp.getProvider().equals(technician) &&
                imgApp.getDate().equals(appointmentDate) &&
                imgApp.getTimeslot().equals(timeslot)
                ) {
                return false;  // Technician or room is busy at this timeslot
            }
        }
    }
    return true;  // Technician is available
}
 

    private void LoadTechinicianList(){
        System.out.println("Rotation list for the technicians.");
                        // i want a specfic order to list, so add if statemnt that check the name of the technician and add to the list
                
                        //JENNY PATEL (BRIDGEWATER) --> MONICA FOX (BRIDGEWATER) --> CHARLES BROWN (BRIDGEWATER) --> FRANK LIN (PISCATAWAY) --> BEN JERRY (PISCATAWAY) --> GARY JOHNSON (PISCATAWAY)
                    Technician jenny = findTechician("JENNY");
                    Technician monica = findTechician("MONICA");
                    Technician charles = findTechician("CHARLES");
                    Technician frank = findTechician("FRANK");
                    Technician ben = findTechician("BEN");
                    Technician gary = findTechician("GARY");
                    technicianList.addTechnician(jenny);
                    technicianList.addTechnician(monica);
                    technicianList.addTechnician(charles);
                    technicianList.addTechnician(frank);
                    technicianList.addTechnician(ben);
                    technicianList.addTechnician(gary);
                    for (int i = 0; i < 5; i++) {
                        Technician currentTechnician = technicianList.getCurrentTechnician();
                        System.out.print(currentTechnician.getProfile().getFirstName() + " " + currentTechnician.getProfile().getLastName() + " (" + currentTechnician.getLocation().name() + ")-->");
                        technicianList.getNextTechnician();
                    }
                    Technician currentTechnician = technicianList.getCurrentTechnician();
                    System.out.println(currentTechnician.getProfile().getFirstName() + " " + currentTechnician.getProfile().getLastName() + " (" + currentTechnician.getLocation().name() + ")");

                    

        }      

    private Technician findTechician(String name) {
        for (Provider provider : providerList) {
            if (provider instanceof Technician && provider.getProfile().getFirstName().equals(name)) {
                return (Technician) provider;
            }
        }
        return null;
    }
    private boolean isValidRoom(String room) {
        try {
            Radiology.valueOf(room.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isRoomAvailable(Radiology room, Location location, Date date, Timeslot timeslot) {
       
        for (Appointment appointment : appointmentList) {
          // check if the room on that date and time is avaible or not at the given location 
            if (appointment instanceof Imaging) {
                Imaging imgApp = (Imaging) appointment;
                Provider provider = (Provider) imgApp.getProvider();

                if (imgApp.getRoom() == room && imgApp.getDate().equals(date) && imgApp.getTimeslot().equals(timeslot) && provider.getLocation() == location) {
                    return false;  // Room is busy at this timeslot
                }
            }
        }
        return true;  // Technician is available
    }

   
}



