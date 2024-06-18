import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//forstod ikke fra oppgaveteksten i oppgave 5 om programmet skulle kunne ta inn en eller to mapper om gangen
//dette programmet tar inn 2 mapper, mens resten av main-metodene fra de andre oppgavene tar inn 1
public class Oblig2Del1 {

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
        // henter inn mappenavnene som kjøreparametere
        if (args.length < 2) {
            System.out.println(
                    "Vennligst angi to mappenavn som første argument.");
            return;
        }
        String mappenavn1 = args[0];
        String mappenavn2 = args[1];

        // leser inn filnavnenene i mappene og oppretter SubsekvensRegistre
        // 1
        ArrayList<String> filnavnListe1 = lesMetadata(mappenavn1 + "/metadata.csv");
        SubsekvensRegister register1 = new SubsekvensRegister();
        // 2
        ArrayList<String> filnavnListe2 = lesMetadata(mappenavn2 + "/metadata.csv");
        SubsekvensRegister register2 = new SubsekvensRegister();

        // leser filer og oppretter HashMaps
        // 1
        for (String filnavn : filnavnListe1) {
            HashMap<String, Subsekvens> subsekvenser = SubsekvensRegister
                    .lesFraFilOgLagreSubsekvenser(mappenavn1 + "/" + filnavn);
            register1.settInn(subsekvenser);
        }
        // 2
        for (String filnavn : filnavnListe2) {
            HashMap<String, Subsekvens> subsekvenser = SubsekvensRegister
                    .lesFraFilOgLagreSubsekvenser(mappenavn2 + "/" + filnavn);
            register2.settInn(subsekvenser);
        }

        // fletter sammen HashMap-ene til det bare er én igjen
        // 1
        while (register1.hentAntall() > 1) {
            HashMap<String, Subsekvens> map11 = register1.taUtHashMap(0);
            HashMap<String, Subsekvens> map12 = register1.taUtHashMap(0); // map2 får indeks 0 etter at map1 er fjernet
            HashMap<String, Subsekvens> slåttSammenMap = SubsekvensRegister.slåSammenHashMaps(map11, map12);
            register1.settInn(slåttSammenMap);
        }
        // 2
        while (register2.hentAntall() > 1) {
            HashMap<String, Subsekvens> map21 = register2.taUtHashMap(0);
            HashMap<String, Subsekvens> map22 = register2.taUtHashMap(0);
            HashMap<String, Subsekvens> slåttSammenMap = SubsekvensRegister.slåSammenHashMaps(map21, map22);
            register2.settInn(slåttSammenMap);
        }

        // finner subsekvensen med flest forekomster
        // 1
        if (register1.hentAntall() == 1) {
            HashMap<String, Subsekvens> sisteMap = register1.taUtHashMap(0);
            String mestForekomstSubsekvens = null;
            int mestForekomstAntall = -1;

            System.out.println("lengde sistemap" + sisteMap.size());
            for (Map.Entry<String, Subsekvens> entry : sisteMap.entrySet()) {
                if (entry.getValue().hentAntallForekomster() > mestForekomstAntall) {
                    mestForekomstSubsekvens = entry.getKey();
                    mestForekomstAntall = entry.getValue().hentAntallForekomster();
                }
            }

            if (mestForekomstSubsekvens != null) {
                System.out.println(
                        "Mappe:" + mappenavn1 + ". Subsekvensen med flest forekomster: " + mestForekomstSubsekvens
                                + " forekommer " + mestForekomstAntall + " ganger");
            }
        }
        // 2
        if (register2.hentAntall() == 1) {
            HashMap<String, Subsekvens> sisteMap = register2.taUtHashMap(0);
            String mestForekomstSubsekvens = null;
            int mestForekomstAntall = -1;

            for (Map.Entry<String, Subsekvens> entry : sisteMap.entrySet()) {
                if (entry.getValue().hentAntallForekomster() > mestForekomstAntall) {
                    mestForekomstSubsekvens = entry.getKey();
                    mestForekomstAntall = entry.getValue().hentAntallForekomster();
                }
            }

            if (mestForekomstSubsekvens != null) {
                System.out.println(
                        "Mappe:" + mappenavn2 + ". Subsekvensen med flest forekomster: " + mestForekomstSubsekvens
                                + " forekommer " + mestForekomstAntall + " ganger");
            }
        }

    }

}
