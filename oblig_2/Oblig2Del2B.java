import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Oblig2Del2B {
    private static final int ANTALL_TRÅDER = 8;

    // statisk metode som leser metadatafilen
    public static ArrayList<String> lesMetadata(String filnavn) {
        ArrayList<String> filnavnListe = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filnavn))) {
            while (scanner.hasNextLine()) {
                filnavnListe.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Kunne ikke finne metadatafilen: " + filnavn);
        }
        return filnavnListe;
    }

    public static void main(String[] args) {
        // sjekker at man har gitt et argument
        if (args.length < 1) {
            System.out.println("Vennligst angi mappenavnet som inneholder filene som parameter til programmet.");
            return;
        }

        // henter mappenavnet fra argumentet og leser metadatafilen med hjelpemetoden
        // lesMetadata
        String mappeNavn = args[0];
        ArrayList<String> filnavnListe = lesMetadata(mappeNavn + "/metadata.csv");
        Monitor2 monitor = new Monitor2(); // lager en monitor
        ArrayList<Thread> tråder = new ArrayList<>(); // lager en liste for tråder

        // går gjennom listen med filnavn og lager en tråd for hver fil, starter trådene
        for (String filnavn : filnavnListe) {
            String fullFilnavn = mappeNavn + "/" + filnavn;
            Thread tråd = new Thread(new LeseTrad(fullFilnavn, monitor));
            tråder.add(tråd);
            tråd.start();
        }

        // venter til alle lesetråder er ferdige
        for (Thread tråd : tråder) {
            try {
                tråd.join();
            } catch (InterruptedException e) {
                System.out.println(" Tråden ble avbrutt!");
            }
        }

        // TILSTAND: det er minst en HashMap i monitor
        Thread[] flettetråder = new Thread[ANTALL_TRÅDER]; // lager en liste for flettetråder - 8 ifølge oppgaven

        // lager og starter flettetråder
        for (int i = 0; i < ANTALL_TRÅDER; i++) {
            flettetråder[i] = new Thread(new FletteTrad(monitor));
            flettetråder[i].start(); // starter tråden
        }

        // lar all flettingen bli ferdig før vi fortsetter
        for (Thread tråd : flettetråder) {
            try {
                tråd.join();
            } catch (InterruptedException e) {
                System.out.println(" En tråd ble avbrutt!");
            }
        }

        // TILSTAND: det er kun én HashMap i monitor
        monitor.ferdigFlettet(); // signaliserer at flettingen er ferdig

        // finner og skriver ut subsekvensen som har flest forekomster
        HashMap<String, Subsekvens> resultatMap = monitor.taUt(0);
        if (resultatMap.isEmpty()) {
            System.out.println("Ingen subsekvenser i mappa");
            return;
        }

        Subsekvens mestOfteSub = null;
        int høyesteForekomst = 0;

        for (Subsekvens sub : resultatMap.values()) {
            int nåværendeAntall = sub.hentAntallForekomster();
            if (nåværendeAntall > høyesteForekomst) {
                mestOfteSub = sub;
                høyesteForekomst = nåværendeAntall;
            }
        }
        // skriver ut resultatet
        if (mestOfteSub != null) {
            System.out.println("Mappe: " + args[0] + ". Har flest av subsekvensen: " +
                    mestOfteSub.subsekvens + " med " + mestOfteSub.hentAntallForekomster() + " forekomster.");
        } else {
            System.out.println("Fant ingen subsekvenser i mappa.");
        }
    }
}
