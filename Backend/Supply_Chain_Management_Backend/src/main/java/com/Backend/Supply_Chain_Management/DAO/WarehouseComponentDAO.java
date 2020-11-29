package com.Backend.Supply_Chain_Management.DAO;

import com.Backend.Supply_Chain_Management.Model.Orders.WarehouseComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseComponentDAO extends JpaRepository<WarehouseComponent, String> {

    //This method will further enhanced when we have more then one warehouse at
    //different location.
    @Query("select war.availableCount from WarehouseComponent war where war.componentName =:c")
    int findByComponentName( @Param("c") String componentName);
}
