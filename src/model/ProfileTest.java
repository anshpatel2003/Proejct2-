/**
 * @author Jeet Soni, Ansh Patel
 */

package model;

import org.junit.Test;
import util.Date;

import static org.junit.Assert.*;

/**
 * Unit tests for the Profile class, specifically testing the compareTo method.
 */
public class ProfileTest {

    /**
     * Test case for comparing two profiles with the same last name but different first names.
     * It should return -1 if the first profile's first name is lexicographically smaller.
     */
    @Test
    public void compareTo_SameLastNameDifferentFirstName() {
        Date dob1 = new Date(2000, 1, 1);
        Date dob2 = new Date(2001, 1, 1);
        Profile profile1 = new Profile("Alice", "Smith", dob1);
        Profile profile2 = new Profile("Bob", "Smith", dob2);

        assertTrue(profile1.compareTo(profile2) < 0);  // Alice < Bob
    }

    /**
     * Test case for comparing two profiles with different last names.
     * It should return -1 if the first profile's last name is lexicographically smaller.
     */
    @Test
    public void compareTo_DifferentLastName_Smaller() {
        Date dob = new Date(2000, 1, 1);
        Profile profile1 = new Profile("Alice", "Brown", dob);
        Profile profile2 = new Profile("Alice", "Smith", dob);

        assertTrue(profile1.compareTo(profile2) < 0);  // Brown < Smith
    }

    /**
     * Test case for comparing two profiles with the same name but different dates of birth.
     * It should return -1 if the first profile's date of birth is earlier.
     */
    @Test
    public void compareTo_SameNameEarlierDOB() {
        Date dob1 = new Date(1990, 1, 1);
        Date dob2 = new Date(1995, 1, 1);
        Profile profile1 = new Profile("Alice", "Smith", dob1);
        Profile profile2 = new Profile("Alice", "Smith", dob2);

        assertTrue(profile1.compareTo(profile2) < 0);  // 1990 < 1995
    }

    /**
     * Test case for comparing two profiles with the same last name and first name but different dates of birth.
     * It should return 1 if the first profile's date of birth is later.
     */
    @Test
    public void compareTo_SameLastNameFirstNameLaterDOB() {
        Date dob1 = new Date(1995, 1, 1);
        Date dob2 = new Date(1990, 1, 1);
        Profile profile1 = new Profile("Alice", "Smith", dob1);
        Profile profile2 = new Profile("Alice", "Smith", dob2);

        assertTrue(profile1.compareTo(profile2) > 0);  // 1995 > 1990
    }

    /**
     * Test case for comparing two profiles with different last names.
     * It should return 1 if the first profile's last name is lexicographically larger.
     */
    @Test
    public void compareTo_DifferentLastName_Larger() {
        Date dob = new Date(2000, 1, 1);
        Profile profile1 = new Profile("Alice", "Smith", dob);
        Profile profile2 = new Profile("Alice", "Brown", dob);

        assertTrue(profile1.compareTo(profile2) > 0);  // Smith > Brown
    }

    /**
     * Test case for comparing two profiles with the same last name but different first names.
     * It should return 1 if the first profile's first name is lexicographically larger.
     */
    @Test
    public void compareTo_DifferentFirstNameSameLastName() {
        Date dob = new Date(2000, 1, 1);
        Profile profile1 = new Profile("Bob", "Smith", dob);
        Profile profile2 = new Profile("Alice", "Smith", dob);

        assertTrue(profile1.compareTo(profile2) > 0);  // Bob > Alice
    }

    /**
     * Test case for comparing two identical profiles.
     * It should return 0 if both profiles are exactly the same.
     */
    @Test
    public void compareTo_SameProfile() {
        Date dob = new Date(2000, 1, 1);
        Profile profile1 = new Profile("Alice", "Smith", dob);
        Profile profile2 = new Profile("Alice", "Smith", dob);

        assertEquals(0, profile1.compareTo(profile2));  // Same profiles
    }
}
