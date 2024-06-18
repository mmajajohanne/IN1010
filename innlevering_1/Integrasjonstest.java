public class Integrasjonstest {
    public static void main(String[] args) {
        // instanser av leger
        Lege lege1 = new Lege("Hans");
        Lege lege2 = new Lege("Trine");
        Spesialist spesialist = new Spesialist("Petter", "abcd");

        // instanser av legemidler
        Vanlig vanligObj = new Vanlig("Paracet", 50, 500);
        Vanedannende vaneObj = new Vanedannende("kodein", 100, 30, 2);
        Narkotisk narkObj = new Narkotisk("morfin", 200, 10, 10);

        // instanser av resepter
        Resept blaaResept = new Blaa(vanligObj, lege1, 1, 5);
        Resept hvitResept = new Hvit(vaneObj, lege2, 2, 3);
        Resept milResept = new MilResept(narkObj, spesialist, 3);
        Resept pResept = new PResept(vaneObj, lege1, 4, 2);

        // skriver ut informasjon om reseptene
        // (der info om legemiddelet og lege også skrives ut)
        System.out.println("Info om reseptene:");
        System.out.println();
        System.out.println(blaaResept.toString());
        System.out.println();
        System.out.println(hvitResept.toString());
        System.out.println();
        System.out.println(milResept.toString());
        System.out.println();
        System.out.println(pResept.toString());
    }
}
