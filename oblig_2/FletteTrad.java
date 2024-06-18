import java.util.HashMap;
import java.util.ArrayList;

public class FletteTrad implements Runnable {
    public static final int ANTALL_TRAADER = 8;
    private Monitor2 monitor;

    public FletteTrad(Monitor2 monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (monitor.antallSubsekvenser() > 1) {
            flettOgLeggTilbake();
        }
    }

    private void flettOgLeggTilbake() {
        ArrayList<HashMap<String, Subsekvens>> hashMaps = monitor.hentToForFletting();
        if (hashMaps.size() < 2)
            return; // sjekker at det faktisk er to maps å flette

        HashMap<String, Subsekvens> hash1 = hashMaps.get(0);
        HashMap<String, Subsekvens> hash2 = hashMaps.get(1);

        HashMap<String, Subsekvens> resultat = SubsekvensRegister.slåSammenHashMaps(hash1, hash2);
        monitor.leggInn(resultat);
    }
}
