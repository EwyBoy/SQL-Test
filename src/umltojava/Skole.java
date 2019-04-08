package umltojava;

public class Skole {

    private static String navn;

    public Skole(String navn) {
        Skole.navn = navn;
    }

    public static String getNavn() {
        return navn;
    }
}
