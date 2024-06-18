import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Oblig2Hele {
    public static final int ANTALL_TRÅDER = 8; // konstant for antall tråder
    private static final int TERSKEL_VERDI = 7; // konstant for å definere terskel for dominans

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Vennligst angi mappenavnet som inneholder filene som parameter til programmet.");
            return;
        }

        String mappenavn = args[0]; // henter mappenavnet fra argumentet
        // oppretter monitorer for de som har hatt sykdom og de som ikke har hatt sykdom
        Monitor2 ikkeHattSykdom = new Monitor2();
        Monitor2 hattSykdom = new Monitor2();
        // oppretter en liste for tråder
        ArrayList<Thread> tråder = new ArrayList<>();

        // leser metadatafilen og legger til i monitorer med hjelpemetoden lesMetadata
        tråder = lesMetadata(mappenavn, hattSykdom, ikkeHattSykdom);

        // starter lesetrådene og venter til de er ferdige
        for (Thread tråd : tråder) {
            tråd.start();
        }
        for (Thread traad : tråder) {
            try {
                traad.join();
            } catch (InterruptedException e) {
                System.out.println("Avbrutt tråd!");
            }
        }

        // TILSTAND: det er minst en HashMap i hver monitor, og lesetråder er ferdige
        // fletter sammen i de to monitorene med hjelpemetoden startOgVentFletteTråder
        startOgVentFletteTråder(hattSykdom);
        startOgVentFletteTråder(ikkeHattSykdom);
        ikkeHattSykdom.ferdigFlettet(); // signaliserer at flettingen er ferdig

        // finner dominante subsekvenser med hjelpemetoden finnSkrivUtDomSubsekvenser
        HashMap<String, Subsekvens> flestSykdom = hattSykdom.taUt(0);
        HashMap<String, Subsekvens> flestIkkeSykdom = ikkeHattSykdom.taUt(0);

        finnSkrivUtDomSubsekvenser(flestSykdom, flestIkkeSykdom);
    }

    // ----- HJELPEMETODER -----

    // hjelpemetode for å lese metadatafilen og legge til i monitorer
    public static ArrayList<Thread> lesMetadata(String mappenavn, Monitor2 hattSykdom, Monitor2 ikkeHattSykdom) {
        ArrayList<Thread> tråder = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(mappenavn + "/metadata.csv"));
            while (sc.hasNextLine()) {
                String[] biter = sc.nextLine().split(",");
                if (biter[1].equals("False")) { // har ikke hatt sykdom
                    Thread tråd = new Thread(new LeseTrad(mappenavn + "/" + biter[0], ikkeHattSykdom));
                    tråder.add(tråd);
                } else if (biter[1].equals("True")) { // har hatt sykdom
                    Thread tråd = new Thread(new LeseTrad(mappenavn + "/" + biter[0], hattSykdom));
                    tråder.add(tråd);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke filen: " + mappenavn + "/metadata.csv");
        }
        return tråder;
    }

    // hjelpemetode for å starte og vente på flettingstråder
    private static void startOgVentFletteTråder(Monitor2 monitor) {
        List<Thread> threads = new ArrayList<>(); // liste for tråder
        // oppretter og starter tråder for den gitte monitoren
        for (int i = 0; i < ANTALL_TRÅDER; i++) {
            Thread thread = new Thread(new FletteTrad(monitor));
            threads.add(thread);
            thread.start();
        }
        // venter på at alle tråder skal fullføre
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Tråd avbrutt: " + e.getMessage());
            }
        }
    }

    // hjelpemetode for å finne og skrive ut dominante subsekvenser
    // (bruker metode skrivUtSubsekvens)
    public static void finnSkrivUtDomSubsekvenser(HashMap<String, Subsekvens> syke,
            HashMap<String, Subsekvens> friske) {
        for (Map.Entry<String, Subsekvens> entry : syke.entrySet()) {
            String subsekvens = entry.getKey();
            Subsekvens subData = entry.getValue();

            int antallSyke = subData.hentAntallForekomster();
            // sjekker om subsekvensen finnes i friske
            int antallFriske;
            if (friske.containsKey(subsekvens)) {
                Subsekvens tempSubsekvens = friske.get(subsekvens);
                antallFriske = tempSubsekvens.hentAntallForekomster();
            } else {
                antallFriske = 0;
            }
            int differanse = antallSyke - antallFriske; // regner ut differansen

            // skriver ut info om subsekvensen hvis differansen er større enn terskelverdien
            if (differanse >= TERSKEL_VERDI) {
                skrivUtSubsekvens(subsekvens, antallSyke, antallFriske, differanse);
            }
        }
    }

    // hjelpemetode for å skrive ut informasjon om subsekvens
    private static void skrivUtSubsekvens(String subsekvens, int antallSyke, int antallFriske, int differanse) {
        System.out.println("---Dominante sekvenser:---");
        System.out.println("Subsekvens: " + subsekvens);
        System.out.println("Forekomst blant de som ikke har hatt sykdommen: " + antallFriske);
        System.out.println("Forekomst blant de som har hatt sykdommen: " + antallSyke);
        System.out.println("Differanse: " + differanse);
        System.out.println();
    }

}