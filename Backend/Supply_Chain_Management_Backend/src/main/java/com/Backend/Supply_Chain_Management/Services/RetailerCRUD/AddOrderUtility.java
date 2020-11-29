package com.Backend.Supply_Chain_Management.Services.RetailerCRUD;

import com.Backend.Supply_Chain_Management.DAO.ManufacturerDAO;
import com.Backend.Supply_Chain_Management.DAO.Order_ManufacturerDAO;
import com.Backend.Supply_Chain_Management.DAO.Order_RetailerDAO;
import com.Backend.Supply_Chain_Management.DAO.WarehouseComponentDAO;
import com.Backend.Supply_Chain_Management.Model.CompositeKey.OrderManufacturerIdentity;
import com.Backend.Supply_Chain_Management.Model.Orders.Catalog;
import com.Backend.Supply_Chain_Management.Model.Orders.Order_Manufacturer;
import com.Backend.Supply_Chain_Management.Model.Orders.WarehouseComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AddOrderUtility {

    @Autowired
    WarehouseComponentDAO warehouseComponentDAO;
    @Autowired
    ManufacturerDAO manufacturerDAO;
    @Autowired
    Order_ManufacturerDAO order_manufacturerDAO;
    @Autowired
    Order_RetailerDAO order_retailerDAO;

    public void orderToManufacturer(String orderID, List<Catalog> catalogs, int piece)
    {
        log.info("Placing Order to Manufacturers for order of : {} of given Amount : {}",
                catalogs.get(0).getCatalogIdentity().getProduct(), piece);
        for(Catalog c : catalogs)
        {
            int neededCount = piece * c.getCount();
            log.info("Needed Component of :{} are:{}.",
                    c.getCatalogIdentity().getComponent(), neededCount);
            //In Further enhancement we will add warehouses at different locations, so
            //that part of logic will also added.(v2)
            int availableCount = warehouseComponentDAO.findByComponentName( c.getCatalogIdentity().getComponent());
            log.info("Available Component of :{} are:{}.",
                    c.getCatalogIdentity().getComponent(), availableCount);
            if(neededCount > availableCount && availableCount >= 0)
            {
                neededCount = neededCount - availableCount;
                if(availableCount > 0)
                {
                    WarehouseComponent warehouseComponent = WarehouseComponent.builder()
                            .componentName( c.getCatalogIdentity().getComponent())
                            .availableCount(0)
                            .build();
                    warehouseComponentDAO.save(warehouseComponent);
                }
                //In further enhancement, we have to add optimizer which will do some
                //optimization based on ratings of each manufacturer for similar
                //component.(v2)
                String manufacturerID =
                        manufacturerDAO.findByComponent(c.getCatalogIdentity().getComponent());
                log.info("Manufacturer ID:{} which are producing component:{}",
                        manufacturerID, c.getCatalogIdentity().getComponent());
                Order_Manufacturer order_manufacturer = Order_Manufacturer.builder()
                            .orderManufacturerIdentity(OrderManufacturerIdentity.builder()
                                    .componentName( c.getCatalogIdentity().getComponent())
                                    .manufacturerID( manufacturerID)
                                    .orderID( orderID)
                                    .build())
                            .isCompleted(false)
                            .adminID( order_retailerDAO.findAdminIDByOrderID( orderID))
                            .amount( neededCount)
                            .build();
                order_manufacturerDAO.save( order_manufacturer);
                log.info("Placed Component Order info :{}", order_manufacturer);
                //here we have call re-order level Logic.
                //Further we can also add Notification kind logic.(tra, man)(v2)
            }
            else
            {
                log.info("Component:{} :{} piece needed whereas :{} piece available at WareHouse",
                        c.getCatalogIdentity().getComponent(), neededCount, availableCount);
                int remainderCount = availableCount - neededCount;
                WarehouseComponent warehouseComponent = WarehouseComponent.builder()
                        .componentName( c.getCatalogIdentity().getComponent())
                        .availableCount( remainderCount)
                        .build();
                warehouseComponentDAO.save( warehouseComponent);
                //Here We have to call re-order level Logic.
                //Further we can also add Notification kind logic.(tra)(v2)
            }
        }
    }
}
