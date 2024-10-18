
package co.edu.usco.TM.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@Data
@Entity
@Table(name = "owners")
public class Owner implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "own_id")
    private Long id;
    
    @Size(min = 3, max = 20)
    @NotEmpty
    @Column(name = "own_first_name")
    private String firstName;
    
    @Size(min = 2, max = 25)
    @NotEmpty
    @Column(name = "own_last_name")
    private String lastName;
    
    @Size(min = 8, max = 50)
    @NotEmpty
    @Column(name = "own_address")
    private String address;
    
    @Size(min = 10, max = 10)
    @NotEmpty
    @Column(name = "own_phone")
    private String phone;
    
    @Valid
    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL})
    @JoinColumn(name = "own_id")
    private User user;
}
