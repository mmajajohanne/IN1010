import java.util.Scanner;

public class GameOfLife {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Velkommen til Game of Life! \n Skriv inn antall rader i rutenettet:");
        int rader = s.nextInt(); // input
        System.out.println("Skriv inn antall kolonner");
        int kolonner = s.nextInt(); // input
        Verden v = new Verden(rader, kolonner);

        int svar = 1;
        while (svar == 1) {
            v.tegn();
            v.oppdatering();
            System.out.print("Vil du fortsette med en ny generasjon? \n skriv 1 for ja og 0 for nei:");
            svar = s.nextInt();
        }
    }
}
