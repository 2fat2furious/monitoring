package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public void showKinderGartens() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("controllers/kinder-garten-info.controller.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Сведения о детских садах");
        stage.setScene(scene);
        stage.show();
    }

    public void showParents() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("controllers/child-info.controller.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Сведения о детях");
        stage.setScene(scene);
        stage.show();
    }

    public void showAcademicYear() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("controllers/academic-year.controller.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Сведения об учебных годах");
        stage.setScene(scene);
        stage.show();
    }

    public void showTypesOfDevelopmentProgram() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("controllers/type-of-development-program.controller.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Сведения о программах развития");
        stage.setScene(scene);
        stage.show();
    }

    public void showTypesResult() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("controllers/type-result.controller.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Сведения о типах оценивания");
        stage.setScene(scene);
        stage.show();
    }

}
