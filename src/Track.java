import java.util.regex.Pattern;

public class Track {
    private int trackID;
    private String titel;
    private String mp3Dateiname;
    private int laenge;

    // Konstruktor zur Initialisierung eines Track-Objekts
    public Track(int trackID, String titel, String mp3Dateiname, int laenge) {
        // Validierung der Track-ID
        if (trackID <= 0) {
            throw new IllegalArgumentException("Track ID muss eine positive Zahl sein.");
        }
        // Validierung des Titels
        if (titel == null || titel.isEmpty()) {
            throw new IllegalArgumentException("Titel darf nicht null oder leer sein.");
        }
        // Validierung des MP3-Dateinamens
        if (!isValidMp3(mp3Dateiname)) {
            throw new IllegalArgumentException("Ungültiges MP3-Format. Der Dateiname muss auf '.mp3' enden.");
        }
        // Validierung der Länge
        if (laenge <= 0) {
            throw new IllegalArgumentException("Länge muss eine positive Zahl sein.");
        }
        // Zuweisung der Werte, wenn alle Validierungen bestanden sind
        this.trackID = trackID;
        this.titel = titel;
        this.mp3Dateiname = mp3Dateiname;
        this.laenge = laenge;
    }

    // Methode zur Überprüfung, ob der MP3-Dateiname gültig und nicht null ist
    private boolean isValidMp3(String mp3Dateiname) {
        String regex = ".*\\.mp3$";
        return mp3Dateiname != null && Pattern.matches(regex, mp3Dateiname); // Sicherheitsmaßnahme, um NullPointerExceptions zu verhindern
    }

    // Getter-Methoden
    public int getTrackID() {
        return trackID;
    }

    public int getLaenge() {
        return laenge;
    }

    // toString-Methode zur Darstellung des Track-Objekts als String
    @Override
    public String toString() {
        return "Track{" +
                "trackID=" + trackID +
                ", titel='" + titel + '\'' +
                ", mp3Dateiname='" + mp3Dateiname + '\'' +
                ", laenge=" + laenge + " Sekunden" +
                '}';
    }
}
