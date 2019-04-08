package fx.controllers;

import database.DatabaseManager;
import fx.models.ModelStudentOne;
import fx.models.ModelStudentTwo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    private TextField tab_name;

    @FXML
    private TableView<ModelStudentOne> stable0;

    @FXML
    private TableColumn<ModelStudentOne, String> tab_kursnavn;

    @FXML
    private TableColumn<ModelStudentOne, String> tab_kurs;

    @FXML
    private TableColumn<ModelStudentOne, String> tab_karakter;

    @FXML
    private TableColumn<ModelStudentOne, String> tab_year;

    @FXML
    private TableView<ModelStudentTwo> stable1;

    @FXML
    private TableColumn<ModelStudentTwo, String> tab_skole;

    @FXML
    private TableColumn<ModelStudentTwo, String> tab_kull;

    private ObservableList<ModelStudentOne> tableOneList = FXCollections.observableArrayList();
    private ObservableList<ModelStudentTwo> tableTwoList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tab_name.setText("Arne Treholt");

        try(
            Connection connection = DatabaseManager.connect(Main.database);
            Statement statement = connection.createStatement()
        ) {

            ResultSet kursnavn = statement.executeQuery("SELECT kurskode FROM Karakter WHERE student=1;");
            ResultSet kurs = statement.executeQuery("SELECT Kurs.kode FROM Kurs, Skole, Kull, Student WHERE Kurs.skole=Skole.navn AND Kull.skole=Skole.navn AND Kull.kode = kull AND Student.nr=1;");
            ResultSet karakter = statement.executeQuery("SELECT karakter FROM Karakter WHERE student=1;");
            ResultSet year = statement.executeQuery("SELECT year FROM Karakter WHERE student=1;");

            tableOneList.add(
                    new ModelStudentOne(
                            "test",     /*kursnavn.getString("kursnavn"),*/
                            "test",        /*kurs.getString("kurs"),*/
                            karakter.getString("karakter"),
                            year.getString("year"))
            );

            ResultSet skole = statement.executeQuery("SELECT skole FROM Kull WHERE skole='UiB';");
            ResultSet kull = statement.executeQuery("SELECT skole FROM Student, Kull WHERE kode=kull AND nr=1;");

            tableTwoList.add(
                    new ModelStudentTwo(
                            skole.getString("skole"),
                            kull.getString("skole")
                    )
            );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        tab_kursnavn.setCellValueFactory(new PropertyValueFactory<>("kursnavn"));
        tab_kurs.setCellValueFactory(new PropertyValueFactory<>("kurs"));
        tab_karakter.setCellValueFactory(new PropertyValueFactory<>("karakter"));
        tab_year.setCellValueFactory(new PropertyValueFactory<>("year"));

        tab_skole.setCellValueFactory(new PropertyValueFactory<>("skole"));
        tab_kull.setCellValueFactory(new PropertyValueFactory<>("kull"));

        stable0.setItems(tableOneList);
        stable1.setItems(tableTwoList);

    }
}
