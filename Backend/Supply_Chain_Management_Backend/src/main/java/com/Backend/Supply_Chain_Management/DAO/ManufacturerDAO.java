package com.Backend.Supply_Chain_Management.DAO;

import com.Backend.Supply_Chain_Management.Model.CompositeKey.ManufacturerIdentity;
import com.Backend.Supply_Chain_Management.Model.Manufacturer;
import com.Backend.Supply_Chain_Management.UtilInterfaces.UserInter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerDAO extends JpaRepository<Manufacturer, ManufacturerIdentity> {

    //This method will return manufacturers with given name, location and component.
    @Query("select m from Manufacturer m where m.manufacturerIdentity.name=:n " +
            "and m.manufacturerIdentity.location=:l and m.manufacturerIdentity.component=:c")
    List<Manufacturer> findByNameAndLocationAndComponent(@Param("n") String name,
                                                         @Param("l") String location, @Param("c") String component);

    //This method will find manufacturer with UniqueID.
    @Query("select man from Manufacturer man where man.id=:i")
    Manufacturer findByUniqueId(@Param("i") String id);

    //This method will return all manufacturers.
    @Query("select man from Manufacturer man")
    List<UserInter> getAllManufacturer();

    //It will further Enhanced when we have more then one manufacturer with Similar Component.
    @Query("select man.id from Manufacturer man where man.manufacturerIdentity.component=:c")
    String findByComponent( @Param("c") String Component);

    //This method will return location of Manufacturer by manufacturerID.
    @Query("select man.manufacturerIdentity.location from Manufacturer man " +
            "where man.id=:i")
    String findLocationByID( @Param("i") String ID);
}
