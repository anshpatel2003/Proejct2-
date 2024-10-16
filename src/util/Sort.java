package util;

import model.Appointment;
import model.Location;
import model.Person;
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
        // for key d sort the list of appointments ordered by date, timeslot, and provider name.
        if (key == 'd') {
            sortbydate(list);
        }
        if(key == 'p'){
            sortbyPatient(list);
        }

    }

    public static void sortbydate(List<Appointment> list) {
        //sort the list of appointments ordered by date, timeslot, and provider name.
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                Appointment a1 = list.get(j);
                Appointment a2 = list.get(j + 1);

                
                if (a1.getDate().compareTo(a2.getDate()) > 0) {
                    list.set(j, a2);  // Swap
                    list.set(j + 1, a1);
                } else if (a1.getDate().compareTo(a2.getDate()) == 0) {
                    //if date is equal then sort by time
                   if(a1.getProvider().compareTo(a2.getProvider()) > 0){
                       list.set(j, a2);  // Swap
                       list.set(j + 1, a1);
                }
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

                
                if (p1.getProfile().compareTo(p2.getProfile()) > 0) {
                    list.set(j, p2);  // Swap
                    list.set(j + 1, p1);
                }
            }
        }
    }

    //sort by patient names, if name is same comapre date, and then compare with timeslot 
    public static void sortbyPatient(List<Appointment> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                Appointment a1 = list.get(j);
                Appointment a2 = list.get(j + 1);
                
                
                if (a1.getPatient().getProfile().compareTo(a2.getPatient().getProfile()) > 0) {
                    list.set(j, a2);  // Swap
                    list.set(j + 1, a1);
                } else if (a1.getPatient().getProfile().compareTo(a2.getPatient().getProfile()) == 0) {
                    //if date is equal then sort by time
                    if (a1.getDate().compareTo(a2.getDate()) > 0) {
                        list.set(j, a2);  // Swap
                        list.set(j + 1, a1);
                    } else if (a1.getDate().compareTo(a2.getDate()) == 0) {
                        if (a1.getTimeslot().compareTo(a2.getTimeslot()) > 0) {
                            list.set(j, a2);  // Swap
                            list.set(j + 1, a1);
                        }
                    }
                }
            }
        }
    }


    
//ordered by county/date/time.
    public static void sortbyLocation(List<Appointment> list,List<Provider> providerList) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                Appointment a1 = list.get(j);
                Appointment a2 = list.get(j + 1);
                Provider p1 = null;
                Provider p2 = null;
                for(Provider p : providerList){
                    if(p.getProfile().equals(a1.getProvider().getProfile())){
                        p1 = p;
                    }
                    if(p.getProfile().equals(a2.getProvider().getProfile())){
                        p2 = p;
                    }
                }
                
                if (p1.getLocation().getCounty().compareTo(p2.getLocation().getCounty()) > 0) {
                    list.set(j, a2);  // Swap
                    list.set(j + 1, a1);
                } else if (p1.getLocation().compareTo(p2.getLocation()) == 0) {
                    //if date is equal then sort by time
                    if (a1.getDate().compareTo(a2.getDate()) > 0) {
                        list.set(j, a2);  // Swap
                        list.set(j + 1, a1);
                    } else if (a1.getDate().compareTo(a2.getDate()) == 0) {
                        if (a1.getTimeslot().compareTo(a2.getTimeslot()) > 0) {
                            list.set(j, a2);  // Swap
                            list.set(j + 1, a1);
                        }
                    }
                }
            }
        }
    }

    /**
     * Sorts a list of patients.
     * This can be sorted by profile or other relevant attributes.
     * @param list The list of patients to sort.
     */
    public static void patient(List<Person> list) {
        // Basic bubble sort (can be optimized or changed to another sort)
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                Person p1 = list.get(j);
                Person p2 = list.get(j + 1);

                
                if (p1.getProfile().compareTo(p2.getProfile()) > 0) {
                    list.set(j, p2);  // Swap
                    list.set(j + 1, p1);
                }
            }
        }
    }

   
}
