import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusikSammlungVerwaltung {
    private static List<Album> alben = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Musiksammlung Verwaltung ---");
            System.out.println("1. Album hinzufügen");
            System.out.println("2. Album löschen");
            System.out.println("3. Track zu Album hinzufügen");
            System.out.println("4. Track von Album löschen");
            System.out.println("5. Album anzeigen");
            System.out.println("6. Alle Alben anzeigen");
            System.out.println("7. Programm beenden");
            System.out.print("Wählen Sie eine Option: ");

            int wahl = scanner.nextInt();
            scanner.nextLine();

            switch (wahl) {
                case 1:
                    albumHinzufuegen();
                    break;
                case 2:
                    albumLoeschen();
                    break;
                case 3:
                    trackZuAlbumHinzufuegen();
                    break;
                case 4:
                    trackVonAlbumLoeschen();
                    break;
                case 5:
                    albumAnzeigen();
                    break;
                case 6:
                    alleAlbenAnzeigen();
                    break;
                case 7:
                    System.out.println("Programm beendet.");
                    return;
                default:
                    System.out.println("Ungültige Wahl. Bitte wählen Sie eine Zahl zwischen 1 und 7.");
            }
        }
    }

    private static void albumHinzufuegen() {
        while (true) {
            try {
                System.out.print("Album ID: ");
                int albumID = scanner.nextInt();
                scanner.nextLine(); // Eingabepuffer leeren

                System.out.print("Titel: ");
                String titel = scanner.nextLine();

                System.out.print("Interpret: ");
                String interpret = scanner.nextLine();

                Album album = new Album(albumID, titel, interpret);
                alben.add(album);
                System.out.println("Album hinzugefügt.");
                break; // Beendet die Schleife nach erfolgreicher Eingabe
            } catch (IllegalArgumentException e) {
                System.out.println("Fehler: " + e.getMessage());
                System.out.println("Bitte versuchen Sie es erneut.");
            } catch (Exception e) {
                System.out.println("Fehler bei der Eingabe. Bitte versuchen Sie es erneut.");
                scanner.nextLine(); // Eingabepuffer leeren, um Fehler zu beheben
            }
        }
    }


    private static void albumLoeschen() {
        while (true) {
            try {
                System.out.print("Album ID des zu löschenden Albums: ");
                int albumID = scanner.nextInt();
                scanner.nextLine(); // Eingabepuffer leeren

                boolean removed = alben.removeIf(album -> album.getAlbumID() == albumID);
                if (removed) {
                    System.out.println("Album gelöscht.");
                } else {
                    System.out.println("Album mit dieser ID wurde nicht gefunden.");
                }
                break; // Beendet die Schleife nach erfolgreicher Eingabe
            } catch (Exception e) {
                System.out.println("Fehler bei der Eingabe. Bitte versuchen Sie es erneut.");
                scanner.nextLine(); // Eingabepuffer leeren
            }
        }
    }


    private static void trackZuAlbumHinzufuegen() {
        while (true) {
            try {
                System.out.print("Album ID: ");
                int albumID = scanner.nextInt();
                scanner.nextLine(); // Eingabepuffer leeren

                Album album = findeAlbum(albumID);
                if (album != null) {
                    System.out.print("Track ID: ");
                    int trackID = scanner.nextInt();
                    scanner.nextLine(); // Eingabepuffer leeren

                    System.out.print("Titel: ");
                    String titel = scanner.nextLine();

                    System.out.print("MP3 Dateiname: ");
                    String dateiname = scanner.nextLine();

                    System.out.print("Länge (in Sekunden): ");
                    int laenge = scanner.nextInt();
                    scanner.nextLine(); // Eingabepuffer leeren

                    Track track = new Track(trackID, titel, dateiname, laenge);
                    album.addTrack(track);
                    System.out.println("Track hinzugefügt.");
                    break; // Beendet die Schleife nach erfolgreicher Eingabe
                } else {
                    System.out.println("Album nicht gefunden.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Fehler: " + e.getMessage());
                System.out.println("Bitte versuchen Sie es erneut.");
            } catch (Exception e) {
                System.out.println("Fehler bei der Eingabe. Bitte versuchen Sie es erneut.");
                scanner.nextLine(); // Eingabepuffer leeren
            }
        }
    }


    private static void trackVonAlbumLoeschen() {
        while (true) {
            try {
                System.out.print("Album ID: ");
                int albumID = scanner.nextInt();
                scanner.nextLine(); // Eingabepuffer leeren

                Album album = findeAlbum(albumID);
                if (album != null) {
                    System.out.print("Track ID des zu löschenden Tracks: ");
                    int trackID = scanner.nextInt();
                    scanner.nextLine(); // Eingabepuffer leeren

                    album.removeTrack(trackID);
                    System.out.println("Track gelöscht.");
                    break; // Beendet die Schleife nach erfolgreicher Eingabe
                } else {
                    System.out.println("Album nicht gefunden.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Fehler: " + e.getMessage());
                System.out.println("Bitte versuchen Sie es erneut.");
            } catch (Exception e) {
                System.out.println("Fehler bei der Eingabe. Bitte versuchen Sie es erneut.");
                scanner.nextLine(); // Eingabepuffer leeren
            }
        }
    }


    private static void albumAnzeigen() {
        try {
            System.out.print("Album ID: ");
            int albumID = scanner.nextInt();
            scanner.nextLine();

            Album album = findeAlbum(albumID);
            if (album != null) {
                album.albumDatenAnzeigen();
            } else {
                System.out.println("Album nicht gefunden.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    private static void alleAlbenAnzeigen() {
        if (alben.isEmpty()) {
            System.out.println("Keine Alben vorhanden.");
        } else {
            for (Album album : alben) {
                album.albumDatenAnzeigen();
                System.out.println("----------------------------");
            }
        }
    }

    private static Album findeAlbum(int albumID) {
        for (Album album : alben) {
            if (album.getAlbumID() == albumID) {
                return album;
            }
        }
        return null;
    }
}
