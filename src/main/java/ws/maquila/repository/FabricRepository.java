package ws.maquila.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ws.maquila.model.Fabric;

public interface FabricRepository extends JpaRepository<Fabric, Integer> {
    
    //@Query(value = "SELECT * FROM tejido WHERE composicion = ?1", nativeQuery = true)
    @Query(value = "SELECT * FROM tejido WHERE composicion = :composition", nativeQuery = true)
    List<Fabric> getFabricsByComposition(@Param("composition") String composition);

    @Query(value = "SELECT f FROM Fabric f WHERE f.composition = :composition")
    List<Fabric> getFabricsByCompositionJPQL(@Param("composition") String composition);

    
}
