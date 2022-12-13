package assn05;


import java.lang.reflect.Array;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        /*
        Part 1 - Write some tests to convince yourself that your code for Part 1 is working
         */
        SimpleEmergencyRoom aqueue = new SimpleEmergencyRoom();
        aqueue.addPatient(1, 3);
        aqueue.addPatient(2, 2);
        aqueue.addPatient(3, 4);
        aqueue.addPatient(4, 4);
        aqueue.dequeue();



       /*
        Part 2 - Write some tests to convince yourself that your code for Part 2 is working
         */
        MinBinHeapER lqueue = new MinBinHeapER();
        lqueue.enqueue(0, 0);
        lqueue.enqueue(1, 3);
        lqueue.enqueue(2, 120);
        lqueue.enqueue(3);
        lqueue.dequeue();
        lqueue.dequeue();
        lqueue.dequeue();



        /*
        Part 3
         */
        MinBinHeapER transfer = new MinBinHeapER(makePatients());
        transfer.enqueue(1, 12);
        transfer.enqueue(2, 87);
        transfer.enqueue(3, 1234);
        transfer.enqueue(4, 1);
        transfer.enqueue(5, 2);
        transfer.enqueue(6, 0);
        transfer.enqueue(7, 38);
        transfer.enqueue(8, 13);
        transfer.dequeue();
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }

        compareRuntimes();
        compareRuntimesp3();

    }

    public static void fillER (MinBinHeapER complexER){
        for (int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillERbig (MinBinHeapER complexER){
        for (int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }

    public static void fillERmed (MinBinHeapER complexER){
        for (int i = 0; i < 50000; i++) {
            complexER.enqueue(i);
        }
    }

    public static void fillERsmall (MinBinHeapER complexER){
        for (int i = 0; i < 10000; i++) {
            complexER.enqueue(i);
        }
    }

        public static void fillER (SimpleEmergencyRoom simpleER){
            for (int i = 0; i < 100000; i++) {
                simpleER.addPatient(i);
            }
        }

        public static Patient[] makePatients () {
            Patient[] patients = new Patient[10];
            for (int i = 0; i < 10; i++) {
                patients[i] = new Patient(i);
            }
            return patients;
        }

        public static double[] compareRuntimes () {
            // Array which you will populate as part of Part 4
            double[] results = new double[4];

            SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
            fillER(simplePQ);

            double bd = System.nanoTime();
            simplePQ.dequeue();
            double ad = System.nanoTime();
            results[0] = ad - bd;
            results[1] = (results[0] / 100000);


            MinBinHeapER binHeap = new MinBinHeapER();
            fillER(binHeap);

            bd = System.nanoTime();
            binHeap.dequeue();
            ad = System.nanoTime();
            results[2] = ad - bd;
            results[3] = (results[2] / 100000);


            return results;
        }


    public static void compareRuntimesp3 () {
        // Array which you will populate as part of Part 3

        MinBinHeapER transferbig = new MinBinHeapER(makePatients());
        MinBinHeapER transfermed = new MinBinHeapER(makePatients());
        MinBinHeapER transfersmall = new MinBinHeapER(makePatients());
        System.out.println(System.nanoTime());
        fillERsmall(transfersmall);
        System.out.println(System.nanoTime());
        fillERmed(transfermed);
        System.out.println(System.nanoTime());
        fillERbig(transferbig);
        System.out.println(System.nanoTime());

    }
    }




