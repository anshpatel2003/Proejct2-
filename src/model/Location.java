/**
 * @author Jeet Soni, Ansh Patel
 */

package model;

/**
 * Represents various locations where clinics are located.
 * Each location is associated with a specific county and zip code.
 */
public enum Location {
    BRIDGEWATER("Somerset", "08807"),
    EDISON("Middlesex", "08817"),
    PISCATAWAY("Middlesex", "08854"),
    PRINCETON("Mercer", "08542"),
    MORRISTOWN("Morris", "07960"),
    CLARK("Union", "07066");

    private final String county;
    private final String zip;

    /**
     * Constructor for the Location enum.
     *
     * @param county The county in which the location is situated.
     * @param zip The zip code of the location.
     */
    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    /**
     * Gets the county for the location.
     *
     * @return The county associated with the location.
     */
    public String getCounty() {
        return county;
    }

    /**
     * Gets the zip code for the location.
     *
     * @return The zip code associated with the location.
     */
    public String getZip() {
        return zip;
    }

    public String getName() {
        return name();
    }

    
    /**
     * Returns a string representation of the location in the format:
     * "LocationName (County, ZipCode)".
     *
     * @return A string describing the location.
     */
    @Override
    public String toString() {
        return " " +name() + ", " + county + " " + zip + "";
    }
}

