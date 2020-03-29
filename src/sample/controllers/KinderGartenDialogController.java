package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.data.KinderGarten;

public class KinderGartenDialogController {

    @FXML
    private TextField fullTitleField;
    @FXML
    private TextField shortTitleField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField fioField;

    private Stage dialogStage;
    private KinderGarten kinderGarten;
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
    public void setKinderGarten(KinderGarten kinderGarten) {
        this.kinderGarten = kinderGarten;

        fullTitleField.setText(kinderGarten.getFullTitle());
        shortTitleField.setText(kinderGarten.getShortTitle());
        addressField.setText(kinderGarten.getAddress());
        phoneField.setText(kinderGarten.getPhone());
        fioField.setText(kinderGarten.getFio());
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
            kinderGarten.setFullTitle(fullTitleField.getText());
            kinderGarten.setShortTitle(shortTitleField.getText());
            kinderGarten.setAddress(addressField.getText());
            kinderGarten.setPhone(phoneField.getText());
            kinderGarten.setFio(fioField.getText());

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

        if (fullTitleField.getText() == null || fullTitleField.getText().length() == 0) {
            errorMessage += "Введите полное название детского сада!\n";
        }
        if (shortTitleField.getText() == null || shortTitleField.getText().length() == 0) {
            errorMessage += "Введите короткое название детского сада!\n";
        }
        if (addressField.getText() == null || addressField.getText().length() == 0) {
            errorMessage += "Введите адрес\n";
        }

        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            errorMessage += "Введите номер телефона!\n";
        }

//            if (fioField.getText() == null || fioField.getText().length() == 0) {
//                errorMessage += "Введите ф!\n";
//            }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Введите корректные значения полей!");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
