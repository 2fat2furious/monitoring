package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.data.AcademicYear;

public class AcademicYearDialogController {


    @FXML
    private TextField yearInitialField;
    @FXML
    private TextField yearEndField;


    private Stage dialogStage;
    private AcademicYear academicYear;
    private boolean okClicked = false;

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Устанавливает сцену для этого окна.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Задаёт адресата, информацию о котором будем менять.
     */
    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;

        yearInitialField.setText(Integer.toString(academicYear.getYearInitial()));
        yearEndField.setText(Integer.toString(academicYear.getYearEnd()));
    }

    /**
     * Returns true, если пользователь кликнул OK, в другом случае false.
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            academicYear.setYearInitial(Integer.parseInt(yearInitialField.getText()));
            academicYear.setYearEnd(Integer.parseInt(yearEndField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     *
     * @return true, если пользовательский ввод корректен
     */
    private boolean isInputValid() {
        String errorMessage = "";


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Введите корректные значения полей!");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
