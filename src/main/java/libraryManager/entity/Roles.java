package libraryManager.entity;

import jakarta.persistence.*;
import libraryManager.entity.Enum.ERole;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private int roleId;

    @Column(name = "roleName")
    @Enumerated(EnumType.STRING)
    private ERole roleName;
}
