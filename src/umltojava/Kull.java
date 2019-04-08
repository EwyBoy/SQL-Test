package umltojava;

public class Kull {

    private static String skole;
    private static String kode;

    public Kull(String kode) {
        Kull.kode = kode;
        skole = Skole.getNavn();
    }

    public String getSkole() {
        return skole;
    }

    public String getKode() {
        return kode;
    }
}
