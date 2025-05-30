package hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.transaction.SystemException;

public class StudentDAO {

    @SuppressWarnings("unchecked")
    public static List<Student> getAllStudent() throws IllegalStateException, SystemException {
        Transaction transaction = null;
        List<Student> listOfStudent = null;

        try (Session session = CreditHiberateUtil.getSessionFactory().openSession()) {
            // Bắt đầu transaction
            transaction = (Transaction) session.beginTransaction();

            // Thực hiện truy vấn HQL để lấy toàn bộ sinh viên
            listOfStudent = session.createQuery("FROM Student").getResultList();

            // Hoàn tất transaction nếu thành công
            transaction.commit();
        } catch (Exception e) {
            // Nếu có lỗi thì rollback transaction
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return listOfStudent;
    }
    
    public static Student getStudent(int id) throws IllegalStateException, SystemException {
        Transaction transaction = null;
        Student student = null;

        try (Session session = CreditHiberateUtil.getSessionFactory().openSession()) {
            // Bắt đầu transaction
            transaction = (Transaction) session.beginTransaction();

            // Truy vấn lấy sinh viên theo id
            student = session.get(Student.class, id);

            // Commit transaction nếu thành công
            transaction.commit();
        } catch (Exception e) {
            // Nếu có lỗi, rollback transaction
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return student;
    }

    
    public static void main(String[] args) throws IllegalStateException, SystemException {
		List<Student> studentList = StudentDAO.getAllStudent();
		studentList.forEach(std->System.out.println(std));
	}
}
