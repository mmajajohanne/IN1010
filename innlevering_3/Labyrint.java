import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint {
    private Rute[][] ruter;
    public int antKolonner = 0;
    private int antRader = 0;

    public Labyrint(String filnavn) {
        lesFil(filnavn);
        // System.out.println(this);
        ordneNaboer();

    }

    private void lesFil(String filnavn) {
        File fil = new File(filnavn);
        try {
            Scanner s = new Scanner(fil);
            int linjeteller = -1;

            // går gjennom fila
            while (s.hasNextLine()) {
                String[] biter = s.nextLine().split("");

                // har ikke lest første linje enda:
                if (antKolonner == 0 && antRader == 0) {
                    antRader = Integer.parseInt(biter[0]);
                    antKolonner = Integer.parseInt(biter[2]);
                    ruter = new Rute[antRader][antKolonner];

                } else { // man har kommet til selve labyrinten, går gjennom radene:
                    for (int i = 0; i < antKolonner; i++) {
                        if (biter[i].equals("#")) {
                            ruter[linjeteller][i] = new SortRute(linjeteller, i, this);
                        } else if (biter[i].equals(".")) {
                            ruter[linjeteller][i] = new HvitRute(linjeteller, i, this);
                        }
                    }
                }
                linjeteller++;
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke filen");
        }
    }

    private void ordneNaboer() {
        // går gjennom ruter og setter naboer
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                // sjekker om den er ved noen av hjørnene
                if (i == 0 && j == 0) {
                    ruter[i][j].settNabo(ruter[i + 1][j], Posisjon.SYD);
                    ruter[i][j].settNabo(ruter[i][j + 1], Posisjon.OEST);
                } else if (i == 0 && j == (antKolonner - 1)) {
                    ruter[i][j].settNabo(ruter[i + 1][j], Posisjon.SYD);
                    ruter[i][j].settNabo(ruter[i][j - 1], Posisjon.VEST);
                } else if (i == (antRader - 1) && j == 0) {
                    ruter[i][j].settNabo(ruter[i - 1][j], Posisjon.NORD);
                    ruter[i][j].settNabo(ruter[i][j + 1], Posisjon.OEST);
                } else if (i =)

            }

        }
    }

    }

    @Override
    public String toString() {
        String retur = "";
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                retur += (ruter[i][j]);
            }
            retur += "\n";
        }
        return retur;
    }
}