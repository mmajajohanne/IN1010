import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Map;

public class SubsekvensRegister {
    private ArrayList<HashMap<String, Subsekvens>> register = new ArrayList<>();

    // sett inn ny HashMap
    public void settInn(HashMap<String, Subsekvens> hm) {
        register.add(hm);
    }

    // hente ut på indeks
    public HashMap<String, Subsekvens> taUtHashMap(int index) {
        if (index < 0 || index >= register.size()) {
            throw new IndexOutOfBoundsException("Index " + index + " er utenfor grensene til hashBeholder");
        }
        HashMap<String, Subsekvens> retur = register.get(index);
        register.remove(index);
        return retur;
    }

    // hent antall HashMaps
    public int hentAntall() {
        return register.size();
    }

    // lese fra fil (oppgave 3)
    public static HashMap<String, Subsekvens> lesFraFilOgLagreSubsekvenser(String filnavn) {
        HashMap<String, Subsekvens> subsekvenser = new HashMap<>();

        try {
            Scanner sc = new Scanner(new File(filnavn));

            while (sc.hasNextLine()) {
                String linje = sc.nextLine();

                for (int i = 0; i <= linje.length() - 3; i++) {
                    String sub = linje.substring(i, i + 3);

                    // bare legg til subsekvensen hvis den ikke allerede finnes i HashMap-en
                    if (sub.length() == 3 && !subsekvenser.containsKey(sub)) {
                        subsekvenser.put(sub, new Subsekvens(sub, 1));
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke filen: " + filnavn);
        }
        return subsekvenser;
    }

    // flette sammen (oppgave 4)
    public static HashMap<String, Subsekvens> slåSammenHashMaps(
            HashMap<String, Subsekvens> map1, HashMap<String, Subsekvens> map2) {

        HashMap<String, Subsekvens> resultatMap = new HashMap<>();

        // iterer over map1 og slår sammen med eventuelle verdier fra map2
        for (Map.Entry<String, Subsekvens> e : map1.entrySet()) {
            String key = e.getKey();
            Subsekvens subsekvens = e.getValue();

            if (map2.containsKey(key)) {
                Subsekvens subsekvens2 = map2.get(key);
                resultatMap.put(key,
                        new Subsekvens(key, subsekvens.hentAntallForekomster() + subsekvens2.hentAntallForekomster()));
                map2.remove(key); // fjerner elementet fra map2 for å unngå å behandle det igjen
            } else {
                resultatMap.put(key, subsekvens);
            }
        }
        resultatMap.putAll(map2); // legg til gjenstående elementer fra map2

        return resultatMap;
    }

}
