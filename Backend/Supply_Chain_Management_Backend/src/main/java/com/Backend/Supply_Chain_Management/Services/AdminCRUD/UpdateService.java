package com.Backend.Supply_Chain_Management.Services.AdminCRUD;

import com.Backend.Supply_Chain_Management.Constants;
import com.Backend.Supply_Chain_Management.DAO.AdminDAO;
import com.Backend.Supply_Chain_Management.DAO.ManufacturerDAO;
import com.Backend.Supply_Chain_Management.DAO.RetailerDAO;
import com.Backend.Supply_Chain_Management.DAO.TransporterDAO;
import com.Backend.Supply_Chain_Management.Model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateService {

    @Autowired
    private ManufacturerDAO manufacturerDAO;
    @Autowired
    private RetailerDAO retailerDAO;
    @Autowired
    private TransporterDAO transporterDAO;
    @Autowired
    private AdminDAO adminDAO;

    public String updateUser(String id, User user)
    {
        String response = "";
        if( id.contains("man"))
        {
            Manufacturer manufacturer = manufacturerDAO.findByUniqueId(id);
            if(manufacturer != null)
            {
                log.info("Fetched Manufacturer with given ID : {}", manufacturer);
                if(user.getComponent() != null)
                {
                    manufacturer.getManufacturerIdentity().setComponent(user.getComponent());
                }
                if(user.getLocation() != null)
                {
                    manufacturer.getManufacturerIdentity().setLocation(user.getLocation());
                }
                if(user.getName() != null)
                {
                    manufacturer.getManufacturerIdentity().setName(user.getName());
                }
                manufacturerDAO.save(manufacturer);
                response = Constants.SUCCESS;
                log.info("Updated Manufacturer : {}", manufacturer);
            }
            else
            {
                log.info("No Manufacturer Exist with given ID : {}", id);
                response = Constants.FAILURE;
            }
        }
        else if( id.contains("tra"))
        {
            Transporter transporter = transporterDAO.findByUniqueId( id);
            if(transporter != null)
            {
                log.info("Fetched Transporter with given ID : {}", transporter);
                if(user.getLocation() != null)
                {
                    transporter.getTransporterIdentity().setLocation( user.getLocation());
                }
                if(user.getName() != null)
                {
                    transporter.getTransporterIdentity().setName( user.getName());
                }
                transporterDAO.save(transporter);
                response = Constants.SUCCESS;
                log.info("Updated Transporter : {}", transporter);
            }
            else
            {
                log.info("No Transporter Exist with given ID : {}", id);
                response = Constants.FAILURE;
            }
        }
        else if( id.contains("ret"))
        {
            Retailer retailer = retailerDAO.findByUniqueId( id);
            if( retailer != null)
            {
                log.info("Fetched Retailer with given ID : {}", retailer);
                if(user.getLocation() != null)
                {
                    retailer.getRetailerIdentity().setLocation( user.getLocation());
                }
                if(user.getName() != null)
                {
                    retailer.getRetailerIdentity().setName( user.getName());
                }
                retailerDAO.save(retailer);
                response = Constants.SUCCESS;
                log.info("Updated Retailer : {}", retailer);
            }
            else
            {
                log.info("No Retailer Exist with given ID : {}", id);
                response = Constants.FAILURE;
            }
        }
        else if( id.contains("adm"))
        {
            Admin admin = adminDAO.findByUniqueId( id);
            if(admin != null)
            {
                log.info("Fetched Tra_Ret with given ID : {}", admin);
                if(user.getLocation() != null)
                {
                    admin.setLocation( user.getLocation());
                }
                if(user.getName() != null)
                {
                    admin.setName( user.getName());
                }
                adminDAO.save( admin);
                response = Constants.SUCCESS;
                log.info("Updated Admin : {}", admin);
            }
            else
            {
                log.info("No Admin Exist with given ID : {}", id);
                response = Constants.FAILURE;
            }
        }
        return response;
    }
}
