/**
 * @author Jeet Soni, Ansh Patel
 */

package model;
import util.Date;


/**
 * The Profile class represents a patient's profile containing first name, last name, and date of birth.
 * It implements the Comparable interface to allow comparisons based on last name, first name, and date of birth.
 * @author Ansh Patel, Jeet Soni
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Constructs a Profile object with the specified first name, last name, and date of birth.
     *
     * @param fname The first name of the patient.
     * @param lname The last name of the patient.
     * @param dob   The date of birth of the patient (using a custom Date class).
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Returns the first name of the patient.
     *
     * @return The first name of the patient.
     */
    public String getFirstName() {
        return fname;
    }

    /**
     * Returns the last name of the patient.
     *
     * @return The last name of the patient.
     */
    public String getLastName() {
        return lname;
    }

    /**
     * Returns the date of birth of the patient.
     *
     * @return The date of birth of the patient.
     */
    public Date getDateOfBirth() {
        return dob;
    }

    /**
     * Compares this profile with another object for equality.
     * Two profiles are considered equal if their first names, last names (case-insensitive),
     * and dates of birth are the same.
     *
     * @param obj The object to compare this profile to.
     * @return true if the profiles are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Profile other = (Profile) obj;
        return fname.equalsIgnoreCase(other.fname) &&
                lname.equalsIgnoreCase(other.lname) &&
                dob.equals(other.dob);
    }

    /**
     * Compares this profile to another profile for ordering.
     * The comparison is made first by last name (case-insensitive),
     * then by first name (case-insensitive), and finally by date of birth.
     *
     * @param other The other profile to compare this profile to.
     * @return A negative integer, zero, or a positive integer as this profile is
     *         less than, equal to, or greater than the other profile.
     */
    @Override
    public int compareTo(Profile other) {
        int lastNameComparison = lname.compareToIgnoreCase(other.lname);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        int firstNameComparison = fname.compareToIgnoreCase(other.fname);
        if (firstNameComparison != 0) {
            return firstNameComparison;
        }

        return dob.compareTo(other.dob);
    }

    /**
     * Returns a string representation of the profile, including the first name, last name, and date of birth.
     *
     * @return A string representing the profile.
     */
    @Override
    public String toString() {
        return String.format("%s %s %s", fname, lname, dob.toString());  // Using Date class's toString method
    }



}

