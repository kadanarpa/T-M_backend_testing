
package co.edu.usco.TM.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@Data
@Entity
@Table(name = "carers")
public class Owner implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;
    
    @Size(min = 3, max = 20)
    @NotEmpty
    @Column(name = "car_first_name")
    private String firstName;
    
    @Size(min = 2, max = 25)
    @NotEmpty
    @Column(name = "car_last_name")
    private String lastName;
    
    @Size(min = 8, max = 50)
    @NotEmpty
    @Column(name = "car_address")
    private String address;
    
    @Size(min = 10, max = 10)
    @NotEmpty
    @Column(name = "car_phone")
    private String phone;
    
    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "car_id")
    private User user;
}
