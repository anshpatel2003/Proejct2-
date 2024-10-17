package model;


/**
 * Represents a person in the clinic system.
 * This is the superclass for both Patient and Provider.
 * Each person has a profile which contains their information.
 * @author Your Name
 */
public class Person implements Comparable<Person> {

    protected Profile profile;  // Profile information of the person

    /**
     * Constructs a Person object with the specified profile.
     * @param profile The profile of the person.
     */
    public Person(Profile profile) {
        this.profile = profile;
    }

    /**
     * Compares this Person to another Person based on their profiles.
     * @param other The other person to compare with.
     * @return A negative integer, zero, or a positive integer as this person
     *         is less than, equal to, or greater than the specified person.
     */
    @Override
    public int compareTo(Person other) {
        return this.profile.compareTo(other.profile);  // Compare based on profile information
    }

    /**
     * Checks if this Person is equal to another object.
     * Two people are considered equal if their profiles are the same.
     * @param obj The object to compare with.
     * @return True if the persons are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person other = (Person) obj;
        return profile.equals(other.profile);
    }

    /**
     * Returns a string representation of the Person.
     * @return A string representing the person's profile.
     */
    @Override
    public String toString() {
        return profile.toString();
    }

    /**
     * Gets the profile of the person.
     * @return The profile of the person.
     */
    public Profile getProfile() {
        return profile;
    }

   



}
