package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.data.Educator;


public class EducatorDialogController {

    @FXML
    private TextField fioField;
    @FXML
    private TextField educationField;
    @FXML
    private TextField positionField;
    @FXML
    private TextField workExperienceField;
    @FXML
    private TextField phoneField;


    private Stage dialogStage;
    private Educator educator;
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
    public void setEducator(Educator educator) {
        this.educator = educator;

        fioField.setText(educator.getFio());
        educationField.setText(educator.getEducation());
        workExperienceField.setText(Integer.toString(educator.getWorkExperience()));
        positionField.setText(educator.getPosition());
        phoneField.setText(educator.getPhone());
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
            educator.setFio(fioField.getText());
            educator.setEducation(educationField.getText());
            educator.setWorkExperience(Integer.parseInt(workExperienceField.getText()));
            educator.setPosition(positionField.getText());
            educator.setPhone(phoneField.getText());

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

        if (fioField.getText() == null || fioField.getText().length() == 0) {
            errorMessage += "Введите полное название детского сада!\n";
        }
        if (educationField.getText() == null || educationField.getText().length() == 0) {
            errorMessage += "Введите короткое название детского сада!\n";
        }
        if (positionField.getText() == null || positionField.getText().length() == 0) {
            errorMessage += "Введите адрес\n";
        }

//        if (workPhoneField.getText() == null || workPhoneField.getText().length() == 0) {
//            errorMessage += "Введите номер телефона!\n";
//        }

//            if (fioField.getText() == null || fioField.getText().length() == 0) {
//                errorMessage += "Введите ф!\n";
//            }

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
