package com.electronicHealthRecord.pharmacy.service;

import com.electronicHealthRecord.pharmacy.model.DeletedInventoryModel;
import com.electronicHealthRecord.pharmacy.model.InventoryModel;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    InventoryModel addInventory(InventoryModel inventoryModel);
    List<InventoryModel> getAllInventory();

    DeletedInventoryModel deleteInventory(Long inventoryId, DeletedInventoryModel deletedInventoryModel);

    InventoryModel editInventoryWithId(Long inventoryId, InventoryModel inventoryModel);

    InventoryModel getInventoryWithGivenId(Long inventoryId);
}
