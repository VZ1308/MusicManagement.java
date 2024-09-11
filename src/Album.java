import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Album {
    private static Set<Integer> albumIDs = new HashSet<>(); // Stellt sicher, dass Album-IDs einzigartig sind

    private int albumID;
    private String titel;
    private String interpret;
    private List<Track> tracks;

    // Konstruktor der Klasse Album
    public Album(int albumID, String titel, String interpret) {
        if (albumID <= 0) {
            throw new IllegalArgumentException("Album ID muss eine positive Zahl sein.");
        }
        if (albumIDs.contains(albumID)) {
            throw new IllegalArgumentException("Album ID muss einzigartig sein.");
        }
        if (titel == null || titel.isEmpty()) {
            throw new IllegalArgumentException("Titel darf nicht null oder leer sein.");
        }
        if (interpret == null || interpret.isEmpty()) {
            throw new IllegalArgumentException("Interpret darf nicht null oder leer sein.");
        }

        this.albumID = albumID;
        this.titel = titel;
        this.interpret = interpret;
        this.tracks = new ArrayList<>();
        albumIDs.add(albumID);
    }

    // Methode zum Hinzufügen eines Tracks zum Album
    public void addTrack(Track track) {
        if (track == null) {
            throw new IllegalArgumentException("Track darf nicht null sein.");
        }
        if (track.getTrackID() <= 0) {
            throw new IllegalArgumentException("Track ID muss eine positive Zahl sein.");
        }
        for (Track existingTrack : tracks) {
            if (existingTrack.getTrackID() == track.getTrackID()) {
                throw new IllegalArgumentException("Track ID muss einzigartig sein.");
            } // HASHmap
        }
        tracks.add(track);
    }

    // Methode zum Entfernen eines Tracks aus dem Album
    public void removeTrack(int trackID) {
        if (trackID <= 0) {
            throw new IllegalArgumentException("Track ID muss eine positive Zahl sein.");
        }

        Track trackToRemove = null;
        for (Track track : tracks) {
            if (track.getTrackID() == trackID) {
                trackToRemove = track;
                break;
            }
        }

        if (trackToRemove != null) {
            tracks.remove(trackToRemove);
        } else {
            throw new IllegalArgumentException("Track mit der ID " + trackID + " wurde nicht gefunden.");
        }
    }


    // Methode zur Berechnung der Gesamtlänge des Albums
    public int getGesamtLaenge() {
        int gesamtLaenge = 0;
        for (Track track : tracks) {
            gesamtLaenge += track.getLaenge();
        }
        return gesamtLaenge;
    }


    // Methode zum Anzeigen der Albumdaten
    public void albumDatenAnzeigen() {
        if (tracks.isEmpty()) {
            System.out.println(this);
            System.out.println("Trackliste:");
            System.out.println("---------------------");
            System.out.println("Dieses Album enthält keine Tracks.\n");
        } else {
            System.out.println(this);
            System.out.println("Trackliste:");
            System.out.println("---------------------");
            for (Track track : tracks) {
                System.out.println(track);
                System.out.println("---------------------");
            }
        }
    }

    // Getter-Methoden
    public int getAlbumID() {
        return albumID;
    }

    // toString-Methode zur Darstellung des Album-Objekts als String
    @Override
    public String toString() {
        return "Album Information:\n" +
                "=====================\n" +
                "Album ID   : " + albumID + "\n" +
                "Titel      : " + titel + "\n" +
                "Interpret  : " + interpret + "\n" +
                "Gesamtlänge: " + getGesamtLaenge() + " Sekunden\n" +
                "Tracks     : " + tracks.size() + "\n" +
                "=====================\n";
    }
}
