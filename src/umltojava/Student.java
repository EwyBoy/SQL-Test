package umltojava;

public class Student {

    private static String nr;
    private static String navn;
    private static String kull;

    public Student(String nr, String navn, String kull) {
        Student.nr = nr;
        Student.navn = navn;
        Student.kull = kull;
    }

    public static String getNr() {
        return nr;
    }

    public static String getNavn() {
        return navn;
    }

    public static String getKull() {
        return kull;
    }
}
