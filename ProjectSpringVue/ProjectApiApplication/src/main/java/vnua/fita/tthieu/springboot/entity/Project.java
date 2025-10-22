package vnua.fita.tthieu.springboot.entity;

import jakarta.persistence.*;

// Đối tượng JavaObject ánh xạ với bảng projects trong CSDL qua cơ chế của JPA
// Nếu có cấu hình dll-auto update trong application.properties sẽ được dùng để tự động tạo bảng
@Entity
@Table(name = "projects")
public class Project {

    @Id  // Khóa chính, mỗi Entity yêu cầu phải có khóa chính tạo nên bởi một hoặc nhiều trường
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Mặc định ánh xạ với cột 'name', sử dụng @Column nếu cột mapping trong bảng khác tên
    // @Column(name = "project_name")
    private String name;
    private String description;

    // Constructors
    public Project() {}

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters & Setters, nếu ko muốn viết code phần này có thể sử dụng thư viện Lombok
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

