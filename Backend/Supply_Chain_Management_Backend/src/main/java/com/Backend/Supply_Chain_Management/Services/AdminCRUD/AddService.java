package com.Backend.Supply_Chain_Management.Services.AdminCRUD;

import com.Backend.Supply_Chain_Management.Constants;
import com.Backend.Supply_Chain_Management.DAO.*;
import com.Backend.Supply_Chain_Management.Model.*;
import com.Backend.Supply_Chain_Management.Model.CompositeKey.ManufacturerIdentity;
import com.Backend.Supply_Chain_Management.Model.CompositeKey.RetailerIdentity;
import com.Backend.Supply_Chain_Management.Model.CompositeKey.TransporterIdentity;
import com.Backend.Supply_Chain_Management.Services.Generator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AddService {

    @Autowired
    private RetailerDAO retailerDAO;
    @Autowired
    private TransporterDAO transporterDAO;
    @Autowired
    private ManufacturerDAO manufacturerDAO;
    @Autowired
    private AdminDAO adminDAO;
    @Autowired
    private LoginDAO loginDAO;
    @Autowired
    private Generator generator;

    public String addUser(User user)
    {
        String response = "";
        String id = user.getType().substring(0,3) + generator.generateID(7);
        String email = generator.generateEmail(user.getName());
        if( user.getType().equals( Constants.Manufacturer_type))
        {
            Manufacturer manufacturer = Manufacturer.builder()
                                        .id(id)
                                        .email(email)
                                        .manufacturerIdentity(ManufacturerIdentity.builder()
                                                .name( user.getName())
                                                .location(user.getLocation())
                                                .component(user.getComponent())
                                                .build())
                                        .build();
            List<Manufacturer> manufacturers = manufacturerDAO
                    .findByNameAndLocationAndComponent(manufacturer.getManufacturerIdentity().getName(),
                            manufacturer.getManufacturerIdentity().getLocation(),
                            manufacturer.getManufacturerIdentity().getComponent());
            if(manufacturers.isEmpty() == true)
            {
                manufacturerDAO.save(manufacturer);
                Login manufacturerLogin = Login.builder()
                        .email( manufacturer.getEmail())
                        .passwd("12344321")
                        .id( manufacturer.getId())
                        .build();
                loginDAO.save( manufacturerLogin);
                log.info("Manufacturer added : {}", manufacturer);
                response = Constants.SUCCESS;
            }
            else
            {
                log.info("Manufacturer already Exists");
                response = Constants.FAILURE;
            }
        }
        else if( user.getType().equals( Constants.Transporter_type))
        {
            Transporter transporter = Transporter.builder()
                                .id(id)
                                .email( email)
                                .transporterIdentity(TransporterIdentity.builder()
                                        .location( user.getLocation())
                                        .name(user.getName())
                                        .build())
                                .build();
            List<Transporter> transporters = transporterDAO
                    .findByNameAndLocation( transporter.getTransporterIdentity().getName(),
                            transporter.getTransporterIdentity().getLocation());
            if(transporters.isEmpty() == true)
            {
                transporterDAO.save( transporter);
                Login transporterLogin = Login.builder()
                        .email( transporter.getEmail())
                        .passwd("12344321")
                        .id( transporter.getId())
                        .build();
                loginDAO.save( transporterLogin);
                log.info("Transporter added : {}", transporter);
                response = Constants.SUCCESS;
            }
            else
            {
                log.info("Transporter already Exists");
                response = Constants.FAILURE;
            }
        }
        else if( user.getType().equals( Constants.Retailer_type))
        {
            Retailer retailer = Retailer.builder()
                    .id(id)
                    .email( email)
                    .retailerIdentity(RetailerIdentity.builder()
                            .location( user.getLocation())
                            .name(user.getName())
                            .build())
                    .build();
            List<Retailer> retailers = retailerDAO
                    .findByNameAndLocation( retailer.getRetailerIdentity().getName(),
                            retailer.getRetailerIdentity().getLocation());
            if(retailers.isEmpty() == true)
            {
                retailerDAO.save(retailer);
                Login retailerLogin = Login.builder()
                        .email( retailer.getEmail())
                        .passwd("12344321")
                        .id( retailer.getId())
                        .build();
                loginDAO.save( retailerLogin);
                log.info("Tra_Ret added : {}", retailer);
                response = Constants.SUCCESS;
            }
            else
            {
                log.info("Retailer already Exists");
                response = Constants.FAILURE;
            }
        }
        else if(user.getType().equals(Constants.Admin_type))
        {
            Admin admin = Admin.builder()
                    .id( id)
                    .email( email)
                    .name( user.getName())
                    .location( user.getLocation())
                    .build();
            List<Admin> admins = adminDAO.findByName( admin.getName());
            if(admins.isEmpty() == true)
            {
                adminDAO.save( admin);
                Login adminLogin = Login.builder()
                        .email( admin.getEmail())
                        .passwd("12344321")
                        .id( admin.getId())
                        .build();
                loginDAO.save( adminLogin);
                log.info("Admin added : {}", admin);
                response = Constants.SUCCESS;
            }
            else
            {
                log.info("Admin already Exists");
                response = Constants.FAILURE;
            }
        }
        else
        {
            log.info("Not valid type of user : {}",user);
            response = Constants.FAILURE;
        }
        return response;
    }
}
