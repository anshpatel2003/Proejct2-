package model;

import org.junit.Test;
import util.Date;

import static org.junit.Assert.*;
public class ProfileTest {

    @Test
    public void compareTo_SameLastNameDifferentFirstName() {
        Date dob1 = new Date(2000, 1, 1);
        Date dob2 = new Date(2001, 1, 1);
        Profile profile1 = new Profile("Alice", "Smith", dob1);
        Profile profile2 = new Profile("Bob", "Smith", dob2);

        assertTrue(profile1.compareTo(profile2) < 0);  // Alice < Bob
    }

    @Test
    public void compareTo_DifferentLastName() {
        Date dob = new Date(2000, 1, 1);
        Profile profile1 = new Profile("Alice", "Brown", dob);
        Profile profile2 = new Profile("Alice", "Smith", dob);

        assertTrue(profile1.compareTo(profile2) < 0);  // Brown < Smith
    }

    @Test
    public void compareTo_SameNameDifferentDOB() {
        Date dob1 = new Date(2000, 1, 1);
        Date dob2 = new Date(2001, 1, 1);
        Profile profile1 = new Profile("Alice", "Smith", dob1);
        Profile profile2 = new Profile("Alice", "Smith", dob2);

        assertTrue(profile1.compareTo(profile2) < 0);  // 2000-01-01 < 2001-01-01
    }

    @Test
    public void compareTo_AllSame() {
        Date dob = new Date(2000, 1, 1);
        Profile profile1 = new Profile("Alice", "Smith", dob);
        Profile profile2 = new Profile("Alice", "Smith", dob);

        assertEquals(0, profile1.compareTo(profile2));  // Same profiles
    }

    @Test
    public void compareTo_EqualNamesDifferentCasing() {
        Date dob = new Date(2000, 1, 1);
        Profile profile1 = new Profile("Alice", "Smith", dob);
        Profile profile2 = new Profile("alice", "smith", dob);

        assertEquals(0, profile1.compareTo(profile2));  // Same names, different casing
    }

    @Test
    public void compareTo_DifferentDOBOrder() {
        Date dob1 = new Date(1990, 1, 1);
        Date dob2 = new Date(1995, 1, 1);
        Profile profile1 = new Profile("Charlie", "Johnson", dob1);
        Profile profile2 = new Profile("Charlie", "Johnson", dob2);

        assertTrue(profile1.compareTo(profile2) < 0);  // 1990-01-01 < 1995-01-01
    }
}