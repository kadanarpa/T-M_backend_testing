
package co.edu.usco.TM.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
@Entity
@Table(name="users")
public class User implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;
    
    @Size(min = 3, max = 15)
    private String username;
    
    @Email
    @Size(min = 5, max = 100, message="")
    @NotEmpty
    @Column(name = "usr_email")
    private String email;
    
    @Size(min = 8, max = 100)
    @Column(name = "usr_password")
    private String password;
    
    @OneToOne(fetch=FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="usr_rol_id")
    private Rol rol;

    public User() {
    }
    
}
