package com.Backend.Supply_Chain_Management.DAO;

import com.Backend.Supply_Chain_Management.Model.CompositeKey.CatalogIdentity;
import com.Backend.Supply_Chain_Management.Model.Orders.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogDAO extends JpaRepository<Catalog, CatalogIdentity> {

    //This method will return all components needed to manufacture given product.
    @Query("select cata from Catalog cata where cata.catalogIdentity.product = :p")
    List<Catalog> findByProductName(@Param("p") String product);
}
