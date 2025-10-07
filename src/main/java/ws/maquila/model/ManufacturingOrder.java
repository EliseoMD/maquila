package ws.maquila.model;

import java.sql.Date;
import java.util.List;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ordenproduccion")
public class ManufacturingOrder {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrdenProduccion")
    @JsonProperty("idOrdenProduccion")
    private Integer id;

    @Column(name = "fechaSolicitud")
    @JsonProperty("fechaSolicitud")
    private Date requesDate;

    @Column(name = "fechaEstimadaEntrega")
    @JsonProperty("fechaEstimadaEntrega")
    private Date expectedDeliveryDate;

    @Column(name = "subtotal")
    @JsonProperty("subtotal")
    private Double subtotal;

    @Column(name = "total")
    @JsonProperty("total")
    private Double total;

    @OneToOne
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    @JsonProperty("idProveedor")
    private Provider provider;

    @JsonManagedReference
    @OneToMany(mappedBy = "manufacturingOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name="idOrdenProduccion")
    private List<ManufacturingOrderFabric> manufacturingOrderFabrics;
}