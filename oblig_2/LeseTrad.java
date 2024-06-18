import java.util.HashMap;

public class LeseTrad extends Thread {
    private final String filnavn;
    private final Monitor2 monitor2;
    private final Monitor1 monitor1;

    public LeseTrad(String filnavn, Monitor1 monitor) {
        this.filnavn = filnavn;
        this.monitor1 = monitor;
        monitor2 = null;
    }

    public LeseTrad(String filnavn, Monitor2 monitor) {
        this.filnavn = filnavn;
        this.monitor2 = monitor;
        monitor1 = null;
    }

    @Override
    public void run() {
        HashMap<String, Subsekvens> subsekvenser = SubsekvensRegister.lesFraFilOgLagreSubsekvenser(filnavn);
        if (monitor2 != null)
            monitor2.leggInn(subsekvenser);
        else
            monitor1.settInn(subsekvenser);
    }
}
