package ws.maquila.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="proveedor")
public class Provider {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor")
    @JsonProperty("idProveedor")
    private Integer id;

    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 500, message = "The content must be at most 500 characters, and has at least one character")
    @Column(name = "nombre")
    @JsonProperty("nombre")    
    private String name;
}
