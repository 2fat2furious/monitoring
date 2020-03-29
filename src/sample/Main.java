package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(com.sun.javafx.runtime.VersionInfo.getRuntimeVersion());
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("АИС мониторинга детского развития");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        stage = primaryStage;
        stage.setWidth(800);
        stage.setHeight(400);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void setTitle(String title) {
        stage.setTitle(title);
    }
}
