package ws.maquila.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="tejido")
public class Fabric {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTejido")
    @JsonProperty("idTejido")
    private Integer id;

    @Column(name = "composicion")
    @JsonProperty("composicion")
    private String composition;

    @OneToOne
    @JoinColumn(name = "tipoTejido", referencedColumnName = "idTipoTejido")
    @JsonProperty("tipoTejido")
    private FabricType fabricType;

    @Column(name = "gramaje")
    @JsonProperty("gramaje")
    private Double weight;

    @Column(name = "precioUnitario")
    @JsonProperty("precioUnitario")
    private Double price;
}