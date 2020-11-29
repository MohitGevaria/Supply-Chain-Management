package com.Backend.Supply_Chain_Management.Services.AdminCRUD;

import com.Backend.Supply_Chain_Management.Constants;
import com.Backend.Supply_Chain_Management.DAO.*;
import com.Backend.Supply_Chain_Management.Model.Admin;
import com.Backend.Supply_Chain_Management.Model.Manufacturer;
import com.Backend.Supply_Chain_Management.Model.Retailer;
import com.Backend.Supply_Chain_Management.Model.Transporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteService {

    @Autowired
    private ManufacturerDAO manufacturerDAO;
    @Autowired
    private RetailerDAO retailerDAO;
    @Autowired
    private TransporterDAO transporterDAO;
    @Autowired
    private AdminDAO adminDAO;
    @Autowired
    private LoginDAO loginDAO;

    public String deleteUser(String id)
    {
        String response = "";
        if( id.substring(0, 3).equals("man"))
        {
            Manufacturer manufacturer = manufacturerDAO.findByUniqueId( id);
            if(manufacturer != null)
            {
                manufacturerDAO.delete( manufacturer);
                loginDAO.deleteById( manufacturer.getId());
                log.info("Removed Manufacturer : {}", manufacturer);
                response = Constants.SUCCESS;
            }
            else
            {
                log.info("No Manufacturer exist with given ID : {}", id);
                response = Constants.FAILURE;
            }
        }
        else if( id.substring(0, 3).equals("tra"))
        {
            Transporter transporter = transporterDAO.findByUniqueId( id);
            if(transporter != null)
            {
                transporterDAO.delete( transporter);
                loginDAO.deleteById( transporter.getId());
                log.info("Removed Transporter : {}", transporter);
                response = Constants.SUCCESS;
            }
            else
            {
                log.info("No Transporter exist with given ID : {}", id);
                response = Constants.FAILURE;
            }
        }
        else if( id.substring(0, 3).equals("ret"))
        {
            Retailer retailer = retailerDAO.findByUniqueId( id);
            if(retailer != null)
            {
                retailerDAO.delete( retailer);
                loginDAO.deleteById( retailer.getId());
                log.info("Removed Retailer : {}", retailer);
                response = Constants.SUCCESS;
            }
            else
            {
                log.info("No Retailer exist with given ID : {}", id);
                response = Constants.FAILURE;
            }
        }
        else if( id.substring(0, 3).equals("adm"))
        {
            Admin admin = adminDAO.findByUniqueId( id);
            if(admin != null)
            {
                adminDAO.delete(admin);
                loginDAO.deleteById( admin.getId());
                log.info("Removed Admin : {}", admin);
                response = Constants.SUCCESS;
            }
            else
            {
                log.info("No Admin exist with given ID : {}", id);
                response = Constants.FAILURE;
            }
        }
        else
        {
            log.info("Not Valid ID : {}", id);
            response = Constants.FAILURE;
        }
        return response;
    }
}
