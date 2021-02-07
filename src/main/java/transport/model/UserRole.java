package transport.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userProfile_id", nullable = false)
    private UserProfile userProfile;

    @Column(name = "userProfile_id", insertable = false, updatable = false)
    private Long userProfileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "role_id", insertable = false, updatable = false)
    private Long roleId;

    public UserRole(UserProfile userProfile, Role role) {
        this.userProfile = userProfile;
        this.role = role;
    }
}
