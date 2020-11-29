package com.Backend.Supply_Chain_Management.DAO;

import com.Backend.Supply_Chain_Management.Model.Admin;
import com.Backend.Supply_Chain_Management.UtilInterfaces.UserInter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminDAO extends JpaRepository<Admin, String> {

    //This method will find admin by Name.
    List<Admin> findByName(String name);

    //This method will find admin by Unique ID.
    @Query("select ad from Admin ad where ad.id=:a")
    Admin findByUniqueId( @Param("a") String id);

    //This method will return all admin present in SCM System.
    @Query("select ad from Admin ad")
    List<UserInter> getAllAdmin();

    //This method will find admin by location.
    @Query("select ad.id from Admin ad where ad.location=:l")
    String findIdByLocation( @Param("l") String location);

    //This method will find location of admin by UniqueID.
    @Query("select ad.location from Admin ad where ad.id=:i")
    String findLocationById( @Param("i") String adminID);
}
