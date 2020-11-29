package com.Backend.Supply_Chain_Management.DAO;

import com.Backend.Supply_Chain_Management.Model.CompositeKey.TransporterIdentity;
import com.Backend.Supply_Chain_Management.Model.Retailer;
import com.Backend.Supply_Chain_Management.Model.Transporter;
import com.Backend.Supply_Chain_Management.UtilInterfaces.UserInter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransporterDAO extends JpaRepository<Transporter, TransporterIdentity> {

    //This will return all transporter present in SCM System.
    @Query("select tra from Transporter tra where tra.id like 'tra%'")
    List<UserInter> getAllTransporter();

    //This will find Transporter By location.
    @Query("select tra.id from Transporter tra where tra.transporterIdentity.location=:l")
    String findIdByLocation( @Param("l") String location);

    //This will find transporter with his UniqueID.
    @Query("select tra from Transporter tra where tra.id=:i")
    Transporter findByUniqueId(@Param("i") String id);

    //This will find transporter with given name and location.
    @Query("select tra from Transporter tra where tra.transporterIdentity.name=:n " +
            "and tra.transporterIdentity.location=:l")
    List<Transporter> findByNameAndLocation(@Param("n") String name, @Param("l") String Location);
}
