import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Oblig2Del2A {

    public static ArrayList<String> lesMetadata(String filnavn) {
        ArrayList<String> filnavnListe = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filnavn))) {
            while (scanner.hasNextLine()) {
                filnavnListe.add(scanner.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Kunne ikke finne metadatafilen: " + filnavn);
        }
        return filnavnListe;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Vennligst angi mappenavnet som inneholder filene som parameter til programmet.");
            return;
        }

        // henter mappenavnet fra argumentet og leser metadatafilen med hjelpemetoden
        // lesMetadata
        String mappeNavn = args[0];
        ArrayList<String> filnavnListe = lesMetadata(mappeNavn + "/metadata.csv");
        Monitor1 monitor = new Monitor1();
        ArrayList<Thread> threads = new ArrayList<>();

        // starter en tråd for hver fil
        for (String filnavn : filnavnListe) {
            String fullFilnavn = mappeNavn + "/" + filnavn;
            Thread thread = new Thread(new LeseTrad(fullFilnavn, monitor));
            threads.add(thread);
            thread.start();
        }

        // venter på at alle lesetråder skal avslutte
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // fletter sammen subsekvensene til en hashmap
        while (monitor.hentAntall() > 1) {
            HashMap<String, Subsekvens> map1 = monitor.taUtHashMap(0);
            HashMap<String, Subsekvens> map2 = monitor.taUtHashMap(0);
            HashMap<String, Subsekvens> slåttSammenMap = Monitor1.slåSammenHashMaps(map1, map2);
            monitor.settInn(slåttSammenMap);
        }

        // finner subsekvensen med flest forekomster og skriv ut resultatet
        if (monitor.hentAntall() == 1) {
            HashMap<String, Subsekvens> sisteMap = monitor.taUtHashMap(0);
            Map.Entry<String, Subsekvens> mestForekomstEntry = null;

            for (Map.Entry<String, Subsekvens> entry : sisteMap.entrySet()) {
                if (mestForekomstEntry == null || entry.getValue().hentAntallForekomster() > mestForekomstEntry
                        .getValue().hentAntallForekomster()) {
                    mestForekomstEntry = entry;
                }
            }

            if (mestForekomstEntry != null) {
                System.out.println("Subsekvensen med flest forekomster: " + mestForekomstEntry.getKey() +
                        " forekommer " + mestForekomstEntry.getValue().hentAntallForekomster() + " ganger");
            } else {
                System.out.println("Ingen subsekvenser funnet.");
            }
        }
    }
}
