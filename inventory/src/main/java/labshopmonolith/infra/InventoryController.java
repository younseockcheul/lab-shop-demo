package labshopmonolith.infra;
import labshopmonolith.domain.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;


@RestController
// @RequestMapping(value="/inventories")
@Transactional
public class InventoryController {
    @Autowired
    InventoryRepository inventoryRepository;




    @RequestMapping(value = "inventories/{id}/decreatestock",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Inventory decreateStock(@PathVariable(value = "id") Long id, @RequestBody DecreateStockCommand decreateStockCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /inventory/decreateStock  called #####");
            Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
            
            optionalInventory.orElseThrow(()-> new Exception("No Entity Found"));
            Inventory inventory = optionalInventory.get();
            inventory.decreateStock(decreateStockCommand);
            
            inventoryRepository.save(inventory);
            return inventory;
            
    }
    



}
