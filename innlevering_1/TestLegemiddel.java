public class TestLegemiddel {
    // test-metoder
    private static boolean testLegemiddelID(Legemiddel legemiddel, int forventetID) {
        return legemiddel.ID == (forventetID);
    }

    private static boolean testLegemiddelPris(Legemiddel legemiddel, int forventetPris) {
        return legemiddel.hentPris() == forventetPris;
    }

    private static boolean testLegemiddelNyPris(Legemiddel legemiddel, int nyPris, int forventetNyPris) {
        legemiddel.settNyPris(nyPris);
        return legemiddel.hentPris() == forventetNyPris;
    }

    private static void testToString(Legemiddel legemiddel) {
        System.out.println(legemiddel.toString());
    }

    public static void main(String[] args) {
        Vanlig vanligObj = new Vanlig("Paracet", 50, 500);
        Vanedannende vaneObj = new Vanedannende("kodein", 100, 30, 2);
        Narkotisk narkObj = new Narkotisk("morfin", 200, 10, 10);

        // tester hver instans med testene
        // vanlig
        testToString(vanligObj);
        if (testLegemiddelID(vanligObj, 0) == false) {
            System.out.println("TestLegemiddelID feilet for" + vanligObj.navn);
        }
        if (testLegemiddelPris(vanligObj, 50) == false) {
            System.out.println("TestLegemiddelPris feilet for" + vanligObj.navn);
        }
        if (testLegemiddelNyPris(vanligObj, 30, 30) == false) {
            System.out.println("TestLegemiddelNyPris feilet for" + vanligObj.navn);
        }

        // vanedannende
        testToString(vaneObj);
        if (testLegemiddelID(vaneObj, 1) == false) {
            System.out.println("TestLegemiddelID feilet for" + vanligObj.navn);
        }
        if (testLegemiddelPris(vaneObj, 100) == false) {
            System.out.println("TestLegemiddelPris feilet for" + vanligObj.navn);
        }
        if (testLegemiddelNyPris(vaneObj, 90, 90) == false) {
            System.out.println("TestLegemiddelNyPris feilet for" + vanligObj.navn);
        }

        // narkotiske
        testToString(narkObj);
        if (testLegemiddelID(narkObj, 2) == false) {
            System.out.println("TestLegemiddelID feilet for" + vanligObj.navn);
        }
        if (testLegemiddelPris(narkObj, 200) == false) {
            System.out.println("TestLegemiddelPris feilet for" + vanligObj.navn);
        }
        if (testLegemiddelNyPris(narkObj, 400, 400) == false) {
            System.out.println("TestLegemiddelNyPris feilet for" + vanligObj.navn);
        }

    }
}
