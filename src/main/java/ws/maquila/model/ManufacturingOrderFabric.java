package ws.maquila.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ordenproducciontejido")
@IdClass( ManufacturingOrderFabricPK.class )
// Alternative to @JsonBackReference and @JsonManagedReference
//@JsonIgnoreProperties({"manufacturingOrder"})
public class ManufacturingOrderFabric {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idOrdenProduccion")
    @JsonBackReference
    private ManufacturingOrder manufacturingOrder;
    @Id
    @OneToOne
    @JoinColumn(name = "idTejido", referencedColumnName = "idTejido")
    private Fabric idFabric;
    @Column(name = "color")
    private String color;
    @Column(name = "totalKilosSolicitados")
    private Double requestedKilograms;    
}
