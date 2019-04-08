package umltojava;

public class Kurs {

    private static String kode;
    private static String navn;
    private static String skole;
    private static int karakter;

    public Kurs(String kode, String navn) {
        Kurs.kode = kode;
        Kurs.navn = navn;
        skole = Skole.getNavn();
        karakter = Karakter.getId();
    }

    public static String getKode() {
        return kode;
    }

    public static String getNavn() {
        return navn;
    }

    public static String getSkole() {
        return skole;
    }

    public static int getKarakter() {
        return karakter;
    }
}
