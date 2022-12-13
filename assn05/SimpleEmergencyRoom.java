package assn05;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // TODO: dequeue
    public Patient dequeue() {
        for (int i = patients.size() - 1; i > 0; i--) {
            if (patients.get(i).getPriority().compareTo((patients.get(i - 1)).getPriority()) < 0) {
                Patient tempP = patients.get(i - 1);
                patients.set(i - 1, patients.get(i));
                patients.set(i, tempP);
            }
        }
        Patient rp = patients.get(0);
        patients.remove(0);
        return rp;
        }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}
