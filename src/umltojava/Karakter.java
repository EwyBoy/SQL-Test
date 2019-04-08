package umltojava;

public class Karakter {

    private static int id;
    private static String karakter;
    private static int year;
    private static String student;

    public Karakter(int id, String karakter, int year) {
        Karakter.id = id;
        Karakter.karakter = karakter;
        Karakter.year = year;
        student = Student.getNr();
    }

    public static int getId() {
        return id;
    }

    public static String getKarakter() {
        return karakter;
    }

    public static int getYear() {
        return year;
    }

    public static String getStudent() {
        return student;
    }
}
