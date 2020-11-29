package com.Backend.Supply_Chain_Management.DAO;

import com.Backend.Supply_Chain_Management.Model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginDAO extends JpaRepository<Login, String> {

    //This method will validate by e-mail and password.
    Login findByEmailAndPasswd(String email, String passwd);
}
