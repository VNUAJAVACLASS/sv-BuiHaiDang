package application;

import java.net.URL;

import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class HomeController implements Initializable {
	// User
	@FXML
	private TableView<User> userTable;

	@FXML
	private TableColumn<User, Integer> userIDCol;

	@FXML
	private TableColumn<User, String> fullNameCol;

	@FXML
	private TableColumn<User, Boolean> userTypeCol;

	@FXML
	private TableColumn<User, String> emailCol;

	@FXML
	private TableColumn<User, LocalDate> birthdayCol;

	@FXML
	private TableColumn<User, Boolean> genderCol;

	@FXML
	private javafx.scene.control.Button addBtn;

	@FXML
	private javafx.scene.control.Button updateBtn;

	@FXML
	private javafx.scene.control.Button deleteBtn;

	@FXML
	private javafx.scene.control.Button deleteAllBtn;

	@FXML
	private TextField userIDTF;

	@FXML
	private TextField fullNameTF;

	@FXML
	private RadioButton userTypeTFGV;

	@FXML
	private RadioButton userTypeTFSV;

	@FXML
	private TextField emailTF;

	@FXML
	private DatePicker birthdayPK;

	@FXML
	private RadioButton genderTFNam;

	@FXML
	private RadioButton genderTFNu;

	private UserDao userDao = new UserDao();

	// User Subject
	@FXML
	private TableView<User_Subject> user_subjectTable;

	@FXML
	private TableColumn<User_Subject, Integer> user_SubjectIDCol;

	@FXML
	private TableColumn<User_Subject, Integer> userID1Col;

	@FXML
	private TableColumn<User_Subject, Integer> subjectIDCol;

	@FXML
	private TableColumn<User_Subject, Float> attendancePointCol;

	@FXML
	private TableColumn<User_Subject, Float> point1Col;

	@FXML
	private TableColumn<User_Subject, Float> point2Col;

	@FXML
	private TableColumn<User_Subject, Float> point3Col;

	@FXML
	private TableColumn<User_Subject, Float> finalPointCol;

	@FXML
	private TableColumn<User_Subject, Float> sumCol;

	@FXML
	private TextField user_SubjectIDTF;

	@FXML
	private TextField userID1TF;

	@FXML
	private TextField subjectIDTF;

	@FXML
	private TextField attendancePointTF;

	@FXML
	private TextField point1TF;

	@FXML
	private TextField point2TF;

	@FXML
	private TextField point3TF;

	@FXML
	private TextField finalPointTF;

	private User_SubjectDao user_SubjectDao = new User_SubjectDao();

	// Subject

	@FXML
	private TableView<Subject> subjectTable;

	@FXML
	private TableColumn<Subject, Integer> subjectIDCol2;

	@FXML
	private TableColumn<Subject, String> subjectNameCol;

	@FXML
	private TableColumn<Subject, Float> creditCol;

	@FXML
	private TableColumn<Subject, Float> attendancePoint2Col;

	@FXML
	private TableColumn<Subject, Float> point1Col2;

	@FXML
	private TableColumn<Subject, Float> point2Col2;

	@FXML
	private TableColumn<Subject, Float> point3Col2;

	@FXML
	private TableColumn<Subject, Float> finalPointCol2;

	@FXML
	private TextField subjectIDTF2;

	@FXML
	private TextField subjectNameTF;

	@FXML
	private TextField creditTF;

	@FXML
	private TextField attendancePointTF2;

	@FXML
	private TextField point1TF2;

	@FXML
	private TextField point2TF2;

	@FXML
	private TextField point3TF2;

	@FXML
	private TextField finalPointTF2;

	private SubjectDao subjectDao = new SubjectDao();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// user
		userIDCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("userID"));
		fullNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("fullName"));
		userTypeCol.setCellValueFactory(new PropertyValueFactory<User, Boolean>("userType"));
		emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		birthdayCol.setCellValueFactory(new PropertyValueFactory<User, LocalDate>("birthday"));
		genderCol.setCellValueFactory(new PropertyValueFactory<User, Boolean>("gender"));
		loadUserList();

		// user subject
		user_SubjectIDCol.setCellValueFactory(new PropertyValueFactory<User_Subject, Integer>("user_SubjectID"));
		userID1Col.setCellValueFactory(new PropertyValueFactory<User_Subject, Integer>("userID"));
		subjectIDCol.setCellValueFactory(new PropertyValueFactory<User_Subject, Integer>("subjectID"));
		attendancePointCol.setCellValueFactory(new PropertyValueFactory<User_Subject, Float>("attendancePoint"));
		point1Col.setCellValueFactory(new PropertyValueFactory<User_Subject, Float>("point1"));
		point2Col.setCellValueFactory(new PropertyValueFactory<User_Subject, Float>("point2"));
		point3Col.setCellValueFactory(new PropertyValueFactory<User_Subject, Float>("point3"));
		finalPointCol.setCellValueFactory(new PropertyValueFactory<User_Subject, Float>("finalPoint"));
		sumCol.setCellValueFactory(new PropertyValueFactory<User_Subject, Float>("sum"));
		loadUserSubjectList();

		// Subject
		subjectIDCol2.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("subjectID"));
		subjectNameCol.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectName"));
		creditCol.setCellValueFactory(new PropertyValueFactory<Subject, Float>("credit"));
		attendancePoint2Col.setCellValueFactory(new PropertyValueFactory<Subject, Float>("attendancePoint"));
		point1Col2.setCellValueFactory(new PropertyValueFactory<Subject, Float>("point1"));
		point2Col2.setCellValueFactory(new PropertyValueFactory<Subject, Float>("point2"));
		point3Col2.setCellValueFactory(new PropertyValueFactory<Subject, Float>("point3"));
		finalPointCol2.setCellValueFactory(new PropertyValueFactory<Subject, Float>("finalPoint"));
		loadSubjectList();

	}

	private void loadUserList() {
		ObservableList<User> users = FXCollections.observableArrayList(userDao.getAllUsers());
		userTable.setItems(users);
	}

	// add user
	@FXML
	private void onClickAddBtn() {
		int userID = Integer.parseInt(userIDTF.getText());
		String fullName = fullNameTF.getText();
		boolean userType = userTypeTFGV.isSelected();
		String email = emailTF.getText();
		LocalDate birthday = birthdayPK.getValue();
		boolean gender = genderTFNam.isSelected();

		User newUser = new User(userID, fullName, userType, email, birthday, gender);

		if (userDao.addUser(newUser)) {
			loadUserList();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thông báo");
			alert.setHeaderText("Thành công");
			alert.setContentText("Dữ liệu đã được thêm thành công!");
			alert.showAndWait();
			clearUserForm();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thông báo");
			alert.setHeaderText("Lỗi");
			alert.setContentText("Dữ liệu thêm không thành công!");
			alert.showAndWait();
		}

	}

	// update user
	@FXML
	private void onClickUpdateBtn() {
		User selectUser = userTable.getSelectionModel().getSelectedItem();
		if (selectUser != null) {
			selectUser.setUserID(Integer.parseInt(userIDTF.getText()));
			selectUser.setFullName(fullNameTF.getText());
			selectUser.setUserType(userTypeTFGV.isSelected());
			selectUser.setEmail(emailTF.getText());
			selectUser.setBirthday(birthdayPK.getValue());
			selectUser.setGender(genderTFNam.isSelected());

			if (userDao.updateUser(selectUser)) {
				loadUserList();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thông báo");
				alert.setHeaderText("Thành công");
				alert.setContentText("Cập nhật thành công!");
				alert.showAndWait();
				clearUserForm();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thông báo");
				alert.setHeaderText("Lỗi");
				alert.setContentText("Cập nhật không thành công!");
				alert.showAndWait();
			}

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thông báo");
			alert.setHeaderText("Lỗi");
			alert.setContentText("Chưa chọn người dùng để cập nhật!");
			alert.showAndWait();
		}

	}

	// delete user
	@FXML
	private void onClickDeleteBtn() {
		User selectUser = userTable.getSelectionModel().getSelectedItem();
		if (selectUser != null) {

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Xác minh");
			alert.setHeaderText("Bạn chắc chắn muốn xóa?");
			alert.setContentText("..");

			// Hiển thị alert và chờ người dùng chọn
			Optional<ButtonType> option = alert.showAndWait();

			// Kiểm tra nếu người dùng nhấn OK
			if (option.isPresent() && option.get() == ButtonType.OK) {
				if (userDao.deleteUser(selectUser.getUserID())) {
					loadUserList();
					Alert alert1 = new Alert(AlertType.INFORMATION);
					alert1.setTitle("Thông báo");
					alert1.setHeaderText("Thành công");
					alert1.setContentText("Xóa thành công!");
					alert1.showAndWait();
					clearUserForm();
				} else {
					Alert alert1 = new Alert(AlertType.INFORMATION);
					alert1.setTitle("Thông báo");
					alert1.setHeaderText("Lôi!");
					alert1.setContentText("Xóa Không thành công!");
					alert1.showAndWait();
					clearUserForm();
				}
			} else {
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setTitle("Thông báo");
				alert1.setHeaderText("Lôi!");
				alert1.setContentText("Chưa chọn người dùng để xóa");
				alert1.showAndWait();
			}

		}
	}

	// on click delete all text
	@FXML
	private void onClickDeleteAll() {
		clearUserForm();
	}

	// click row user
	@FXML
	private void onClickRow(javafx.scene.input.MouseEvent event) {
		User selectUser = userTable.getSelectionModel().getSelectedItem();
		if (selectUser != null) {
			showUserDetails(selectUser);
		}
	}

	// show user
	private void showUserDetails(User user) {
		userIDTF.setText(String.valueOf(user.getUserID()));
		fullNameTF.setText(user.getFullName());
		if (user.isUserType()) {
			userTypeTFGV.setSelected(true);
		} else {
			userTypeTFGV.setSelected(true);
		}
		emailTF.setText(user.getEmail());
		birthdayPK.setValue(user.getBirthday());
		if (user.isGender()) {
			genderTFNam.setSelected(true);
		} else {
			genderTFNu.setSelected(true);
		}

	}

	// clear user
	private void clearUserForm() {
		userIDTF.clear();
		fullNameTF.clear();
		userTypeTFGV.setSelected(false);
		emailTF.clear();
		birthdayPK.setValue(null);
		genderTFNam.setSelected(false);
	}

	// Load user subject
	private void loadUserSubjectList() {
		ObservableList<User_Subject> user_Subjects = FXCollections
				.observableArrayList(user_SubjectDao.getAllUserSubjects());
		user_subjectTable.setItems(user_Subjects);
	}

	// add user_subject
	@FXML
	private void onClickAddUserSubjectBtn() {
		int user_SubjectID = Integer.parseInt(user_SubjectIDTF.getText());
		int userID = Integer.parseInt(userID1TF.getText());
		int subjectID = Integer.parseInt(subjectIDTF.getText());
		float attendancePoint = Float.parseFloat(attendancePointTF.getText());
		float point1 = Float.parseFloat(point1TF.getText());
		float point2 = Float.parseFloat(point2TF.getText());
		float point3 = Float.parseFloat(point3TF.getText());
		float finalPoint = Float.parseFloat(finalPointTF.getText());
		User_Subject user_Subject = new User_Subject(user_SubjectID, userID, subjectID, attendancePoint, point1, point2,
				point3, finalPoint);

		if (user_SubjectDao.addSubjectToUser(user_Subject)) {
			loadUserSubjectList();
			loadSubjectList();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thông báo");
			alert.setHeaderText("Thành công");
			alert.setContentText("Dữ liệu đã được thêm thành công!");
			alert.showAndWait();
			clearUserSubject();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thông báo");
			alert.setHeaderText("Lỗi");
			alert.setContentText("Dữ liệu thêm không thành công!");
			alert.showAndWait();
		}

	}

	@FXML
	private void onClickUpdateUserSubject(MouseEvent event) {
		User_Subject selectUser = user_subjectTable.getSelectionModel().getSelectedItem();
		if (selectUser != null) {
			selectUser.setUser_SubjectID(Integer.parseInt(user_SubjectIDTF.getText()));
			selectUser.setUserID(Integer.parseInt(userID1TF.getText()));
			selectUser.setSubjectID(Integer.parseInt(subjectIDTF.getText()));
			selectUser.setAttendancePoint(Float.parseFloat(attendancePointTF.getText()));
			selectUser.setPoint1(Float.parseFloat(point1TF.getText()));
			selectUser.setPoint2(Float.parseFloat(point2TF.getText()));
			selectUser.setPoint3(Float.parseFloat(point3TF.getText()));
			selectUser.setFinalPoint(Float.parseFloat(finalPointTF.getText()));

			if (user_SubjectDao.updateUserSubject(selectUser)) {
				loadUserSubjectList();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thông báo");
				alert.setHeaderText("Thành công");
				alert.setContentText("Cập nhật thành công!");
				alert.showAndWait();
				clearUserSubject();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thông báo");
				alert.setHeaderText("Lỗi");
				alert.setContentText("Cập nhật không thành công!");
				alert.showAndWait();
			}

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thông báo");
			alert.setHeaderText("Lỗi");
			alert.setContentText("Chưa chọn người dùng để cập nhật!");
			alert.showAndWait();
		}
	}

	@FXML
	private void onClickDeleteUserSubject(MouseEvent event) {
		User_Subject select = user_subjectTable.getSelectionModel().getSelectedItem();
		if (select != null) {

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Xác minh");
			alert.setHeaderText("Bạn chắc chắn muốn xóa?");
			alert.setContentText("..");

			// Hiển thị alert và chờ người dùng chọn
			Optional<ButtonType> option = alert.showAndWait();

			// Kiểm tra nếu người dùng nhấn OK
			if (option.isPresent() && option.get() == ButtonType.OK) {
				if (user_SubjectDao.deleteUserSubject(select.getUser_SubjectID())) {
					loadUserSubjectList();
					Alert alert1 = new Alert(AlertType.INFORMATION);
					alert1.setTitle("Thông báo");
					alert1.setHeaderText("Thành công");
					alert1.setContentText("Xóa thành công!");
					alert1.showAndWait();
					clearUserSubject();
				} else {
					Alert alert1 = new Alert(AlertType.INFORMATION);
					alert1.setTitle("Thông báo");
					alert1.setHeaderText("Lôi!");
					alert1.setContentText("Xóa Không thành công!");
					alert1.showAndWait();
					clearUserSubject();
				}
			} else {
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setTitle("Thông báo");
				alert1.setHeaderText("Lôi!");
				alert1.setContentText("Chưa chọn người dùng để xóa");
				alert1.showAndWait();
			}

		}
	}

	@FXML
	private void onClickRowUserSubject(MouseEvent event) {
		User_Subject selectUser_Subject = user_subjectTable.getSelectionModel().getSelectedItem();
		if (selectUser_Subject != null) {
			showUserSubjectDetails(selectUser_Subject);
		}
	}

	private void showUserSubjectDetails(User_Subject user_Subject) {
		user_SubjectIDTF.setText(String.valueOf(user_Subject.getUser_SubjectID()));
		userID1TF.setText(String.valueOf(user_Subject.getUserID()));
		subjectIDTF.setText(String.valueOf(user_Subject.getSubjectID()));
		attendancePointTF.setText(String.valueOf(user_Subject.getAttendancePoint()));
		point1TF.setText(String.valueOf(user_Subject.getPoint1()));
		point2TF.setText(String.valueOf(user_Subject.getPoint2()));
		point3TF.setText(String.valueOf(user_Subject.getPoint3()));
		finalPointTF.setText(String.valueOf(user_Subject.getFinalPoint()));
	}

	@FXML
	private void onClickClearUserSubject(MouseEvent event) {
		clearUserSubject();
	}

	private void clearUserSubject() {
		user_SubjectIDTF.clear();
		userID1TF.clear();
		subjectIDTF.clear();
		attendancePointTF.clear();
		point1TF.clear();
		point2TF.clear();
		point3TF.clear();
		finalPointTF.clear();
	}

	// load Subject
	private void loadSubjectList() {
		ObservableList<Subject> subject = FXCollections.observableArrayList(subjectDao.getAllSubject());
		subjectTable.setItems(subject);
	}

	@FXML
	private void onClickRowSubject(MouseEvent event) {
		Subject selectSubject = subjectTable.getSelectionModel().getSelectedItem();
		if (selectSubject != null) {
			showSubjectDetails(selectSubject);
		}
	}

	private void showSubjectDetails(Subject subject) {
		subjectIDTF2.setText(String.valueOf(subject.getSubjectID()));
		subjectNameTF.setText(String.valueOf(subject.getSubjectName()));
		creditTF.setText(String.valueOf(subject.getCredit()));
		attendancePointTF2.setText(String.valueOf(subject.getAttendancePoint()));
		point1TF2.setText(String.valueOf(subject.getPoint1()));
		point2TF2.setText(String.valueOf(subject.getPoint2()));
		point3TF2.setText(String.valueOf(subject.getPoint3()));
		finalPointTF2.setText(String.valueOf(subject.getFinalPoint()));

	}

	@FXML
	private void onClickAddSubjectBtn(MouseEvent event) {
		int subjectID = Integer.parseInt(subjectIDTF2.getText());
		String subjectName = subjectNameTF.getText();
		int credit = Integer.parseInt(creditTF.getText());
		float attendancePoint = Float.parseFloat(attendancePointTF2.getText());
		float point1 = Float.parseFloat(point1TF2.getText());
		float point2 = Float.parseFloat(point2TF2.getText());
		float point3 = Float.parseFloat(point3TF2.getText());
		float finalPoint = Float.parseFloat(finalPointTF2.getText());

		Subject subject = new Subject(subjectID, subjectName, credit, attendancePoint, point1, point2, point3,
				finalPoint);

		if (subjectDao.addSubject(subject)) {
			loadSubjectList();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thông báo");
			alert.setHeaderText("Thành công");
			alert.setContentText("Dữ liệu đã được thêm thành công!");
			alert.showAndWait();
			clearSubject();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thông báo");
			alert.setHeaderText("Lỗi");
			alert.setContentText("Dữ liệu thêm không thành công!");
			alert.showAndWait();
		}
	}

	@FXML
	private void onClickDeleteSubjectBtn(MouseEvent event) {

		Subject selectSubject = subjectTable.getSelectionModel().getSelectedItem();
		if (selectSubject != null) {

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Xác minh");
			alert.setHeaderText("Bạn chắc chắn muốn xóa?");
			alert.setContentText("..");

			// Hiển thị alert và chờ người dùng chọn
			Optional<ButtonType> option = alert.showAndWait();

			// Kiểm tra nếu người dùng nhấn OK
			if (option.isPresent() && option.get() == ButtonType.OK) {
				if (subjectDao.deleteSubject(selectSubject.getSubjectID())) {
					loadSubjectList();
					Alert alert1 = new Alert(AlertType.INFORMATION);
					alert1.setTitle("Thông báo");
					alert1.setHeaderText("Thành công");
					alert1.setContentText("Xóa thành công!");
					alert1.showAndWait();
					clearSubject();
				} else {
					Alert alert1 = new Alert(AlertType.INFORMATION);
					alert1.setTitle("Thông báo");
					alert1.setHeaderText("Lôi!");
					alert1.setContentText("Xóa không thành công!");
					alert1.showAndWait();
					clearSubject();
				}
			} else {
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setTitle("Thông báo");
				alert1.setHeaderText("Lôi!");
				alert1.setContentText("Chưa chọn người dùng để xóa");
				alert1.showAndWait();
			}

		}
	}

	@FXML
	private void onClickUpdateSubjectbtn(MouseEvent event) {
		Subject selectSubject = subjectTable.getSelectionModel().getSelectedItem();

		if (selectSubject != null) {
			selectSubject.setSubjectID((int) Integer.parseInt(subjectIDTF2.getText()));
			selectSubject.setSubjectName(subjectNameTF.getText());
			selectSubject.setCredit((int) Integer.parseInt(creditTF.getText()));
			selectSubject.setAttendancePoint(Float.parseFloat(attendancePointTF2.getText()));
			selectSubject.setPoint1(Float.parseFloat(point1TF2.getText()));
			selectSubject.setPoint2(Float.parseFloat(point2TF2.getText()));
			selectSubject.setPoint3(Float.parseFloat(point3TF2.getText()));
			selectSubject.setFinalPoint(Float.parseFloat(finalPointTF2.getText()));

			if (subjectDao.updateSubject(selectSubject)) {
				loadSubjectList();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thông báo");
				alert.setHeaderText("Thành công");
				alert.setContentText("Cập nhật thành công!");
				alert.showAndWait();
				clearSubject();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Thông báo");
				alert.setHeaderText("Khong thành công");
				alert.setContentText("Cập nhật không thành công!");
				alert.showAndWait();
				clearSubject();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Thông báo");
			alert.setHeaderText("Lỗi ");
			alert.setContentText("Chưa chọn người dùng để cập nhật!");
			alert.showAndWait();
			clearSubject();
		}
	}

	@FXML
	private void onClickDeleteSubjectAllBtn(MouseEvent event) {
		clearSubject();
	}

	private void clearSubject() {
		subjectIDTF2.clear();
		subjectNameTF.clear();
		creditTF.clear();
		attendancePointTF2.clear();
		point1TF2.clear();
		point2TF2.clear();
		point3TF2.clear();
		finalPointTF2.clear();
	}

}
