package util;

import model.Appointment;
import model.Provider;

/**
 * Utility class that provides sorting methods for appointments and providers.
 * Contains static methods to sort lists by various keys.
 * @author Your Name
 */
public class Sort {

    /**
     * Sorts a list of appointments based on a given key.
     * For example, sort by date, then by time.
     * @param list The list of appointments to sort.
     * @param key The sorting key (e.g., date, time).
     */
    public static void appointment(List<Appointment> list, char key) {
        // Basic bubble sort (can be optimized or changed to another sort)
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                Appointment app1 = list.get(j);
                Appointment app2 = list.get(j + 1);

                int comparison = 0;
                switch (key) {
                    case 'd':  // Sort by date
                        comparison = app1.getDate().compareTo(app2.getDate());
                        break;
                    case 't':  // Sort by time slot
                        comparison = app1.getTimeslot().getHour() - app2.getTimeslot().getHour();
                        break;
                }

                if (comparison > 0) {
                    list.set(j, app2);  // Swap
                    list.set(j + 1, app1);
                }
            }
        }
    }

    /**
     * Sorts a list of providers.
     * This can be sorted by profile or other relevant attributes.
     * @param list The list of providers to sort.
     */
    public static void provider(List<Provider> list) {
        // Basic bubble sort (can be optimized or changed to another sort)
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                Provider p1 = list.get(j);
                Provider p2 = list.get(j + 1);

                // Assuming provider comparison is based on their profile
                if (p1.getProfile().compareTo(p2.getProfile()) > 0) {
                    list.set(j, p2);  // Swap
                    list.set(j + 1, p1);
                }
            }
        }
    }
}
