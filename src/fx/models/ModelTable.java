package fx.models;

public class ModelTable {

    private String kurskode;
    private String karakter;
    private String year;
    private String student;

    public ModelTable(String kurskode, String karakter, String year, String student) {
        this.kurskode = kurskode;
        this.karakter = karakter;
        this.year = year;
        this.student = student;
    }

    public String getKurskode() {
        return kurskode;
    }

    public void setKurskode(String kurskode) {
        this.kurskode = kurskode;
    }

    public String getKarakter() {
        return karakter;
    }

    public void setKarakter(String karakter) {
        this.karakter = karakter;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }
}
