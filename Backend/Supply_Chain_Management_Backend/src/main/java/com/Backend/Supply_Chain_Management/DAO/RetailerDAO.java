package com.Backend.Supply_Chain_Management.DAO;

import com.Backend.Supply_Chain_Management.Model.CompositeKey.RetailerIdentity;
import com.Backend.Supply_Chain_Management.Model.Retailer;
import com.Backend.Supply_Chain_Management.UtilInterfaces.UserInter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RetailerDAO extends JpaRepository<Retailer, RetailerIdentity> {

    //This method will find retailers with given name and location.
    @Query("select ret from Retailer ret where ret.retailerIdentity.name=:n " +
            "and ret.retailerIdentity.location=:l")
    List<Retailer> findByNameAndLocation(@Param("n") String name, @Param("l") String Location);

    //This method will find retailer with given UniqueID.
    @Query("select ret from Retailer ret where ret.id=:i")
    Retailer findByUniqueId(@Param("i") String id);

    //This method will return all retailers of SCM system.
    @Query("select ret from Retailer ret where ret.id like 'ret%'")
    List<UserInter> getAllRetailer();

}
