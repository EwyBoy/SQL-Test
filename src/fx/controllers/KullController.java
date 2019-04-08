package fx.controllers;

import database.DatabaseManager;
import fx.models.KullModel;
import fx.models.ModelTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class KullController implements Initializable {

    @FXML
    private TableView<KullModel> k_table;

    @FXML
    private TableColumn<KullModel, String> tab_student_list;

    @FXML
    private TextField f_name;

    @FXML
    private TextField f_nr;

    @FXML
    private TextField f_kull;

    private ObservableList<KullModel> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        final String[] name = new String[1];

        k_table.setRowFactory( tv -> {
            TableRow<KullModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    KullModel rowData = row.getItem();

                    if (rowData.getList() != null)  {
                        name[0] = rowData.getList();
                        f_name.setText(rowData.getList());

                        System.out.println(Arrays.toString(name));

                        try {
                            Connection connection = DatabaseManager.connect(Main.database);
                            ResultSet kull = connection.createStatement().executeQuery("SELECT kull FROM Student WHERE navn=" + Arrays.toString(name) + ";");
                            ResultSet nr = connection.createStatement().executeQuery("SELECT nr FROM Student WHERE navn=" + Arrays.toString(name) + ";");

                            f_kull.setText(kull.getString("kull"));
                            f_nr.setText(nr.getString("nr"));

                        }  catch (SQLException e) {
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                        }

                    }
                }
            });
            return row ;
        });

        try {
            Connection connection = DatabaseManager.connect(Main.database);
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Student WHERE kull='2019V';");

            while (rs.next()) {
                oblist.add(
                        new KullModel(
                                rs.getString("navn")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        tab_student_list.setCellValueFactory(new PropertyValueFactory<>("list"));
        k_table.setItems(oblist);
    }
}
