package model;

import org.junit.Test;
import util.Date;

import static org.junit.Assert.*;

public class ProfileTest {

    // Three test cases returning -1
    @Test
    public void compareTo_SameLastNameDifferentFirstName() {
        Date dob1 = new Date(2000, 1, 1);
        Date dob2 = new Date(2001, 1, 1);
        Profile profile1 = new Profile("Alice", "Smith", dob1);
        Profile profile2 = new Profile("Bob", "Smith", dob2);

        assertTrue(profile1.compareTo(profile2) < 0);  // Alice < Bob
    }

    @Test
    public void compareTo_DifferentLastName_Smaller() {
        Date dob = new Date(2000, 1, 1);
        Profile profile1 = new Profile("Alice", "Brown", dob);
        Profile profile2 = new Profile("Alice", "Smith", dob);

        assertTrue(profile1.compareTo(profile2) < 0);  // Brown < Smith
    }

    @Test
    public void compareTo_SameNameEarlierDOB() {
        Date dob1 = new Date(1990, 1, 1);
        Date dob2 = new Date(1995, 1, 1);
        Profile profile1 = new Profile("Alice", "Smith", dob1);
        Profile profile2 = new Profile("Alice", "Smith", dob2);

        assertTrue(profile1.compareTo(profile2) < 0);  // 1990 < 1995
    }

    // Three test cases returning 1
    @Test
    public void compareTo_SameLastNameFirstNameLaterDOB() {
        Date dob1 = new Date(1995, 1, 1);
        Date dob2 = new Date(1990, 1, 1);
        Profile profile1 = new Profile("Alice", "Smith", dob1);
        Profile profile2 = new Profile("Alice", "Smith", dob2);

        assertTrue(profile1.compareTo(profile2) > 0);  // 1995 > 1990
    }

    @Test
    public void compareTo_DifferentLastName_Larger() {
        Date dob = new Date(2000, 1, 1);
        Profile profile1 = new Profile("Alice", "Smith", dob);
        Profile profile2 = new Profile("Alice", "Brown", dob);

        assertTrue(profile1.compareTo(profile2) > 0);  // Smith > Brown
    }

    @Test
    public void compareTo_DifferentFirstNameSameLastName() {
        Date dob = new Date(2000, 1, 1);
        Profile profile1 = new Profile("Bob", "Smith", dob);
        Profile profile2 = new Profile("Alice", "Smith", dob);

        assertTrue(profile1.compareTo(profile2) > 0);  // Bob > Alice
    }

    // One test case returning 0
    @Test
    public void compareTo_SameProfile() {
        Date dob = new Date(2000, 1, 1);
        Profile profile1 = new Profile("Alice", "Smith", dob);
        Profile profile2 = new Profile("Alice", "Smith", dob);

        assertEquals(0, profile1.compareTo(profile2));  // Same profiles
    }
}
