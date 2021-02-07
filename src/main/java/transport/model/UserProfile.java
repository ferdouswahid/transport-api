package transport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String mobile;

    @Column(name = "user_name",nullable = false, unique = true, length = 50)
    private String userName;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy")
    private UserProfile createdBy;


    @JsonIgnore
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updatedBy")
    private UserProfile updatedBy;

    @JsonIgnore
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deletedBy")
    private UserProfile deletedBy;

    @JsonIgnore
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deletedAt;


    @JsonIgnore
    @OneToMany(mappedBy = "userProfile",fetch = FetchType.LAZY)
    private List<UserRole> userRoleList;


    public UserProfile(Long id) {
        this.id = id;
    }

    public UserProfile(String userName, String password, boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    public UserProfile(String name, String email, String mobile, String userName, String password, boolean enabled) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    @PrePersist
    public void onCreate() {
        this.enabled = true;
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @PreRemove
    protected void onRemove() {
        this.deletedAt = LocalDateTime.now();
    }
}
