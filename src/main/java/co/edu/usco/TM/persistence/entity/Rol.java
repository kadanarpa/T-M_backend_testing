
package co.edu.usco.TM.persistence.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Rol implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private int rolId;
    
    @Column(name = "rol_name")
    private String rolName;
    
    @Column(name = "rol_status")
    private Long rolStatus;
    
}
