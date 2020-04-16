package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.dao.GroupDAO;
import sample.data.Child;
import sample.data.Group;
import sample.service.GroupService;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class ChildDialogController {
    private GroupService service = new GroupService();

    @FXML
    private TextField fioField;
    @FXML
    private DatePicker dateOfBirthField;
    @FXML
    private TextField placeOfBirthField;
    @FXML
    private TextField homePhoneField;
    @FXML
    private TextField homeAddressField;
    @FXML
    private TextField healthStatusField;
    @FXML
    private CheckBox socialAssistanceField;
    @FXML
    private ComboBox<Group> groupField;
    @FXML
    private GridPane detailsPanel;


    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private Stage dialogStage;
    private Child child;
    private boolean okClicked = false;

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        groupField.setConverter(new GroupDAO.GroupConverter());
        groupField.setItems(service.getAll());
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
    public void setChild(Child child) {
        this.child = child;

        fioField.setText(child.getName());
        placeOfBirthField.setText(child.getPlaceOfBirth());
        Date dateOfBirth = child.getDateOfBirth();
        dateOfBirthField.setValue(dateOfBirth != null ? dateOfBirth.toLocalDate() : null);
        homePhoneField.setText(child.getHomePhone());
        homeAddressField.setText(child.getHomeAddress());
        healthStatusField.setText(child.getHealthStatus());
        socialAssistanceField.setSelected(child.isSocialAssistance());
        groupField.setValue(child.getGroup());
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
            child.setName(fioField.getText());
            child.setDateOfBirth(Date.valueOf(dateOfBirthField.getValue()));
            child.setPlaceOfBirth(placeOfBirthField.getText());
            child.setHomePhone(homePhoneField.getText());
            child.setHomeAddress(homeAddressField.getText());
            child.setHealthStatus(healthStatusField.getText());
            child.setSocialAssistance(socialAssistanceField.isSelected());
            Group group = groupField.getValue();

            child.setTitleGroup(group.getTitle());
            child.setId_ay(group.getAcademicYearId());
            child.setIdKinderGarten(group.getKinderGartenId());

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
            errorMessage += "Введите ФИО ребенка!\n";
        }
//        if (educationField.getText() == null || educationField.getText().length() == 0) {
//            errorMessage += "Введите короткое название детского сада!\n";
//        }
//        if (positionField.getText() == null || positionField.getText().length() == 0) {
//            errorMessage += "Введите адрес\n";
//        }
//
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
