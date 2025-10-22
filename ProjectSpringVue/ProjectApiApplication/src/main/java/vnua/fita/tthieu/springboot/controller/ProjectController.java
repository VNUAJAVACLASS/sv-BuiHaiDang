package vnua.fita.tthieu.springboot.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vnua.fita.tthieu.springboot.entity.Project;
import vnua.fita.tthieu.springboot.repository.ProjectRepository;

@RestController  // Lớp chứa các API
@RequestMapping("/api/projects") // Các path sẽ được xử lý bởi lớp này
public class ProjectController {

    // Khai báo sử dụng đối tượng truy xuất dữ liệu
	private final ProjectRepository projectRepository;

    // Cách này cần viết constructor với tham số để SpringBoot engine gọi
	// tạo đối tượng cho, cách khác đơn giản hơn sử dụng @AutoWired
	public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping   // method GET dùng path cấu hình chung bên trên
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    
    // ví dụ path: /api/projects/5
    // Nhờ @PathVariable id tự động được gán giá trị 5
    // Kiểu giá trị của id (Long) cần được khai báo cho phù hợp giá trị truyền trong path
    // findById(id) trả về Optional<Project> vì có thể tìm thấy project với id đó, hoặc không
	// Optional giúp tránh lỗi NullPointerException bằng cách bắt buộc bạn xử lý trường hợp giá trị không tồn tại (empty).
    // Nếu Optional có giá trị (project), gọi hàm ResponseEntity.ok(project) để tạo ResponseEntity với HTTP status 200 và body là project đó
    // Xem method updateProject bên dưới để dễ hiểu hơn bản chất
    // ResponseEntity::ok -> lệnh tương đương: ResponseEntity.ok(project);
    // map(Function) phương thức của Optional đẩy giá trị trong Optional vào Function xử lý chuyển đổi project > ResponseEntity
    // , trả về một Optional mới chứa ResponseEntity với status 200 hoặc rỗng (empty)
    // orElse là phương thức gọi trên Optional mới do map trả về
    // orElse trả về ResponseEntity (status 200) hoặc nếu rỗng thì xử lý trả về ResponseEntity với status 404
    @GetMapping("/{id}") 
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
    	return projectRepository.findById(id)
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Nhờ @RequestBody dữ liệu từ Json object phía client gửi sang được tự động đưa vào đối tượng Project
    // Yêu cầu các trường trong JsonObject và Project phải có tên giống nhau và kiểu tương hợp
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    // map(Function): nội dung trong map là hàm mũi tên với tham số project lấy từ Optional trả bởi findById
    // nếu findById(id) trả về Optional rỗng, map sẽ không chạy nên ko sợ lỗi NullPointerException
    @PatchMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(updatedProject.getName());
                    project.setDescription(updatedProject.getDescription());
                    return ResponseEntity.ok(projectRepository.save(project));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Phương thức delete xử lý chật chẽ hơn cần try-catch ngoại lệ hoặc
    // thiết kế cơ chế xử lý ngoại lệ toàn cục (@ControllerAdvice) cho ứng dụng
    // public <T> ResponseEntity<T> build(); (phương thức generic)
    // nếu chỉ gọi build() phải tự suy luận kiểu T, gọi <Void>build() là gọi tường minh với T là Void
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(project -> {
                    projectRepository.delete(project);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

