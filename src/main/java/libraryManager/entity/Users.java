package libraryManager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;

    @Column(name = "userName", unique = true, nullable = false)
    private String userName;

    @Column(name ="passWord", nullable = false)
    @JsonIgnore
    private String passWord;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "createDate", nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date createDate;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name ="phone", nullable = false, unique = true)
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userId")
        ,inverseJoinColumns = @JoinColumn (name = "roleId"))
    private Set<Roles> listRoles =new HashSet<>();

}
