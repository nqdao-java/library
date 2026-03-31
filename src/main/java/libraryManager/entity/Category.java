package libraryManager.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "category")
@Data
public class Category {
    @Id
    @Column(name = "idCategory")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategory;

    @Column(name = "name", unique = true,nullable = false)
    private String name;

    @Column(name = "description", length = 2000)
    private String description;
}
