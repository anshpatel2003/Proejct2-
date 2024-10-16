package util;

import static org.junit.Assert.*;

import model.*;
import org.junit.Before;
import org.junit.Test;

public class ListTest {
    private List<Provider> providerList;
    private Doctor doctor;
    private Technician technician;

    @Before
    public void setUp() {
        providerList = new List<>();
        Profile doctorProfile = new Profile("John", "Doe", new Date(1980, 1, 1));
        doctor = new Doctor(doctorProfile, Location.BRIDGEWATER, Specialty.FAMILY, "1234567890");
        Profile techProfile = new Profile("Jane", "Smith", new Date(1985, 5, 5));
        technician = new Technician(techProfile, Location.EDISON, 50);
    }

    @Test
    public void testAddDoctor() {
        providerList.add(doctor);
        assertEquals(1, providerList.size());
        assertTrue(providerList.contains(doctor));
        Provider retrievedProvider = providerList.get(0);
        assertTrue(retrievedProvider instanceof Doctor);
        Doctor retrievedDoctor = (Doctor) retrievedProvider;
        assertEquals(Location.BRIDGEWATER, retrievedDoctor.getLocation());
        assertEquals(Specialty.FAMILY, retrievedDoctor.getSpecialty());
        assertEquals("1234567890", retrievedDoctor.getNpi());
    }

    @Test
    public void testAddTechnician() {
        providerList.add(technician);
        assertEquals(1, providerList.size());
        assertTrue(providerList.contains(technician));
        Provider retrievedProvider = providerList.get(0);
        assertTrue(retrievedProvider instanceof Technician);
        Technician retrievedTechnician = (Technician) retrievedProvider;
        assertEquals(Location.EDISON, retrievedTechnician.getLocation());
        assertEquals(50, retrievedTechnician.getRatePerVisit());
    }

    @Test
    public void testRemoveDoctor() {
        providerList.add(doctor);
        providerList.add(technician);
        assertEquals(2, providerList.size());
        assertTrue(providerList.contains(doctor));

        providerList.remove(doctor);

        assertEquals(1, providerList.size());
        assertFalse(providerList.contains(doctor));
        assertTrue(providerList.contains(technician));
    }

    @Test
    public void testRemoveTechnician() {
        providerList.add(doctor);
        providerList.add(technician);
        assertEquals(2, providerList.size());
        assertTrue(providerList.contains(technician));

        providerList.remove(technician);

        assertEquals(1, providerList.size());
        assertFalse(providerList.contains(technician));
        assertTrue(providerList.contains(doctor));
    }
}}