public class TestResept {
    private static boolean TestHentID(Resept resept, int forventetID) {
        if (resept.hentID() == forventetID) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // Opprett Legemiddel-objekter
        Vanlig vanligObj = new Vanlig("Paracet", 50, 500);
        Vanedannende vaneObj = new Vanedannende("kodein", 100, 30, 2);
        Narkotisk narkObj = new Narkotisk("morfin", 200, 10, 10);

        // Opprett en lege
        Lege drHansen = new Lege("Dr. Hansen");

        // Opprett resepter
        Resept blaaResept = new Blaa(vanligObj, drHansen, 1, 5);
        Resept hvitResept = new Hvit(vaneObj, drHansen, 2, 3);
        Resept milResept = new MilResept(narkObj, drHansen, 3);
        Resept pResept = new PResept(vaneObj, drHansen, 4, 2);

        // tester hentID
        if (TestHentID(blaaResept, 0) == false) {
            System.out.println("HentID feilet for resept:" + blaaResept.farge());
        }
        if (TestHentID(hvitResept, 1) == false) {
            System.out.println("HentID feilet for resept:" + hvitResept.farge());
        }
        if (TestHentID(milResept, 2) == false) {
            System.out.println("HentID feilet for resept:" + milResept.farge());
        }
        if (TestHentID(pResept, 3) == false) {
            System.out.println("HentID feilet for resept:" + pResept.farge());
        }

        // tester bruk-metoden og at reit i MilResept = 3

        // tester toString() på de forskjellige objektene
        System.out.println(blaaResept.toString());
        System.err.println("");
        System.out.println(hvitResept.toString());
        System.err.println("");
        System.out.println(milResept.toString());
        System.err.println("");
        System.out.println(pResept.toString());

    }
}
