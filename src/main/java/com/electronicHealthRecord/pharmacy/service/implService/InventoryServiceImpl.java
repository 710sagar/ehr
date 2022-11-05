package com.electronicHealthRecord.pharmacy.service.implService;

import com.electronicHealthRecord.pharmacy.model.DeletedInventoryModel;
import com.electronicHealthRecord.pharmacy.model.InventoryModel;
import com.electronicHealthRecord.pharmacy.repository.DeletedInventoryRepository;
import com.electronicHealthRecord.pharmacy.repository.InventoryRepository;
import com.electronicHealthRecord.pharmacy.service.InventoryService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private DeletedInventoryRepository deletedInventoryRepository;


    @Override
    public List<InventoryModel> getAllInventory(){
        return inventoryRepository.findAll();
    }

    @Override
    public InventoryModel addInventory(InventoryModel inventoryModel) {
        Calendar calendar = Calendar.getInstance();
        inventoryModel.setCreate_date(calendar.getTime());
        inventoryRepository.save(inventoryModel);
        return inventoryModel;
    }

    @Override
    public DeletedInventoryModel deleteInventory(Long inventoryId, DeletedInventoryModel deletedInventoryModel) {
        Calendar calendar = Calendar.getInstance();
        InventoryModel existingInventoryModel = getInventoryWithGivenId(inventoryId);
        if(existingInventoryModel != null){
            deletedInventoryModel = createDeletedInventoryModel(existingInventoryModel, deletedInventoryModel);
            deletedInventoryModel.setDeleted_date(calendar.getTime());
            inventoryRepository.deleteById(inventoryId);
            deletedInventoryRepository.save(deletedInventoryModel);
            return deletedInventoryModel;
        } else {
            return null;
        }
    }

    private DeletedInventoryModel createDeletedInventoryModel(InventoryModel existingInventoryModel, DeletedInventoryModel deletedInventoryModel){
        if(existingInventoryModel.getBatch_no() != null) {
            deletedInventoryModel.setBatch_no(existingInventoryModel.getBatch_no());
        }
        if(existingInventoryModel.getMed_name() != null){
            deletedInventoryModel.setMed_name(existingInventoryModel.getMed_name());
        }
        if(existingInventoryModel.getStrength() != null){
            deletedInventoryModel.setStrength(existingInventoryModel.getStrength());
        }
        if(existingInventoryModel.getQoh() != 0){
            deletedInventoryModel.setQoh(existingInventoryModel.getQoh());
        }
        if(existingInventoryModel.getWarehouse_id() != null){
            deletedInventoryModel.setWarehouse_id(existingInventoryModel.getWarehouse_id());
        }
        if(existingInventoryModel.getPrice() != 0){
            deletedInventoryModel.setPrice(existingInventoryModel.getPrice());
        }
        if(existingInventoryModel.getManufacturer() != null){
            deletedInventoryModel.setManufacturer(existingInventoryModel.getManufacturer());
        }
        if(existingInventoryModel.getExpiry_date() != null){
            deletedInventoryModel.setExpiry_date(existingInventoryModel.getExpiry_date());
        }
        if(existingInventoryModel.getCreated_by() != null){
            deletedInventoryModel.setCreated_by(existingInventoryModel.getCreated_by());
        }
        if(existingInventoryModel.getUpdated_by() != null){
            deletedInventoryModel.setUpdated_by(existingInventoryModel.getUpdated_by());
        }
        if(existingInventoryModel.getCreate_date() != null){
            deletedInventoryModel.setCreate_date(existingInventoryModel.getCreate_date());
        }
        if(existingInventoryModel.getUpdate_date() != null){
            deletedInventoryModel.setUpdate_date(existingInventoryModel.getUpdate_date());
        }
        return deletedInventoryModel;
    }

    @Override
    public InventoryModel editInventoryWithId (Long inventoryId, InventoryModel inventoryModel){
        Calendar calendar = Calendar.getInstance();
        InventoryModel existingInventoryModel = getInventoryWithGivenId(inventoryId);
        if(existingInventoryModel != null){
            existingInventoryModel = updateInventory(existingInventoryModel, inventoryModel);
            existingInventoryModel.setUpdate_date(calendar.getTime());
            existingInventoryModel.setUpdated_by(inventoryModel.getUpdated_by());
            inventoryRepository.save(existingInventoryModel);
            return existingInventoryModel;
        } else {
            return null;
        }
    }
    private InventoryModel updateInventory(InventoryModel existingInventoryModel, InventoryModel inventoryModel){
        if(inventoryModel.getBatch_no() != null) {
            existingInventoryModel.setBatch_no(inventoryModel.getBatch_no());
        }
        if(inventoryModel.getMed_name() != null){
            existingInventoryModel.setMed_name(inventoryModel.getMed_name());
        }
        if(inventoryModel.getStrength() != null){
            existingInventoryModel.setStrength(inventoryModel.getStrength());
        }
        if(inventoryModel.getQoh() != 0){
            existingInventoryModel.setQoh(inventoryModel.getQoh());
        }
        if(inventoryModel.getWarehouse_id() != null){
            existingInventoryModel.setWarehouse_id(inventoryModel.getWarehouse_id());
        }
        if(inventoryModel.getPrice() != 0){
            existingInventoryModel.setPrice(inventoryModel.getPrice());
        }
        if(inventoryModel.getManufacturer() != null){
            existingInventoryModel.setManufacturer(inventoryModel.getManufacturer());
        }
        if(inventoryModel.getExpiry_date() != null){
            existingInventoryModel.setExpiry_date(inventoryModel.getExpiry_date());
        }
        return existingInventoryModel;
    }

    @Override
    public InventoryModel getInventoryWithGivenId(Long inventoryId) {
        if(inventoryRepository.findById(inventoryId).isPresent()){
            InventoryModel existingInventoryModel = inventoryRepository.findById(inventoryId).get();
            return existingInventoryModel;
        } else {
            return null;
        }

    }
}
