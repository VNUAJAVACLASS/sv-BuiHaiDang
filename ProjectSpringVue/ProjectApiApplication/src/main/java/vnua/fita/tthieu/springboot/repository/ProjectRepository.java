package vnua.fita.tthieu.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import vnua.fita.tthieu.springboot.entity.Project;

// Định nghĩa interface, trường hợp đơn giản, chỉ đơn thuần extends JpaRepository LÀ ĐỦ
// Spring Data JPA sẽ tự động sinh ra một class cài đặt interface ProjectRepository này khi chạy ứng dụng
// Các phương thức có sẵn của JpaRepository: findAll, findById, save, delete,...
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	// Viết thêm các phương thức khác nếu muốn theo quy cách đặt tên của JPA, JPA sẽ tự viết code cho ta
	// Vd: Dựa vào các tên trường như name, description và quy tắc JPA có thể viết thêm
	// List<Project> findByName(String name);
	// List<Project> findByNameStartingWith(String prefix);
	// List<Project> findByDescriptionContaining(String keyword);
	// List<Project> findByIdGreaterThan(Long id);
	// List<Project> findByNameAndDescription(String name, String description);
	
	/*
	 * Trong một số truy vấn phức tạp, có thể cần chuyển về dạng SQL native để tối ưu hiệu năng
	 * Dùng native SQL giúp bạn viết câu query phức tạp với join, group by, order by chính xác theo yêu cầu
	 * Sử dụng cơ chế có sẵn của JPA có thể tạo ra rất nhiều truy vấn dư thừa, khó hoặc không thực hiện được. 
	 * Ví dụ:
	 * @Query(value = "SELECT p.*, COUNT(t.id) as task_count " +
                   "FROM projects p LEFT JOIN tasks t ON p.id = t.project_id " +
                   "WHERE p.name LIKE %:keyword% " +
                   "GROUP BY p.id " +
                   "ORDER BY task_count DESC", nativeQuery = true)
    	List<Project> findProjectsWithTaskCountOrderByTasksDesc(@Param("keyword") String keyword);
     * Dùng JPA có thể dùng chung cho mọi loại CSDL
     * Câu truy vấn native phải viết chuẩn SQL theo DB bạn dùng (MySQL, PostgreSQL, Oracle...).
	 * Khi trả về entity, Hibernate sẽ tự map, nhưng nếu trả về các cột tùy chỉnh (như task_count), 
	 * ta cần dùng DTO hoặc interface projection để nhận kết quả
	 */
}
