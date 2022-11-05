package com.electronicHealthRecord.pharmacy.controller;


import com.electronicHealthRecord.pharmacy.Constants;
import com.electronicHealthRecord.pharmacy.model.ApiResponseModel;
import com.electronicHealthRecord.pharmacy.model.DeletedInventoryModel;
import com.electronicHealthRecord.pharmacy.model.InventoryModel;
import com.electronicHealthRecord.pharmacy.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping(value = "/getAll")
    public ApiResponseModel getInventory() {
        ApiResponseModel apiResponseModel = new ApiResponseModel();
        try{
            List<InventoryModel> inventoryModelList = inventoryService.getAllInventory();
            if(inventoryModelList.isEmpty()) {
                apiResponseModel.setStatusCode(HttpStatus.NO_CONTENT.ordinal());
                apiResponseModel.setMessage("No Inventory found");
            } else{
                apiResponseModel.setStatusCode(HttpStatus.OK.value());
                apiResponseModel.setMessage("Inventory Found");
                apiResponseModel.setData(inventoryModelList);
            }
        } catch (Exception exception){
            apiResponseModel.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponseModel.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return apiResponseModel;
    }

    @PostMapping(value = "/addInventory")
    public ApiResponseModel saveInventory(@RequestBody InventoryModel inventoryModel){
        ApiResponseModel apiResponseModel = new ApiResponseModel();
        try{
            InventoryModel response = inventoryService.addInventory(inventoryModel);
            log.info("Inventory Added "+response.toString());
            if(response!=null){
                apiResponseModel.setStatusCode(HttpStatus.CREATED.value());
                apiResponseModel.setMessage("Inventory Added Successfully");
                apiResponseModel.setData(response);
            }
        } catch (Exception ex) {
            apiResponseModel.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponseModel.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return apiResponseModel;
    }

    @DeleteMapping(value = "/delete/{inventoryId}")
    public ApiResponseModel deleteInventory(@PathVariable("inventoryId") Long inventoryId, @RequestBody DeletedInventoryModel deletedInventoryModel){
        ApiResponseModel apiResponseModel = new ApiResponseModel();
        try{
            DeletedInventoryModel response = inventoryService.deleteInventory(inventoryId, deletedInventoryModel);
            if(response == null) {
                apiResponseModel.setStatusCode(HttpStatus.NOT_FOUND.value());
                apiResponseModel.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
            } else {
                apiResponseModel.setStatusCode(HttpStatus.OK.value());
                apiResponseModel.setMessage("Inventory with Id " + inventoryId + " deleted successfully");
                apiResponseModel.setData(response);
            }
        } catch (Exception exception) {
            apiResponseModel.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponseModel.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return apiResponseModel;
    }

    @PutMapping(value = "/editInventory/{inventoryId}")
    public ApiResponseModel editInventoryWithId(@PathVariable("inventoryId") Long inventoryId, @RequestBody InventoryModel inventoryModel){
        ApiResponseModel apiResponseModel = new ApiResponseModel();
        try {
            InventoryModel response = inventoryService.editInventoryWithId(inventoryId, inventoryModel);
            if(response == null){
                apiResponseModel.setStatusCode(HttpStatus.NO_CONTENT.value());
                apiResponseModel.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
            } else {
                apiResponseModel.setStatusCode(HttpStatus.ACCEPTED.value());
                apiResponseModel.setMessage("Inventory Updated Successfully");
                apiResponseModel.setData(response);
            }
        } catch (Exception exception){
            apiResponseModel.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponseModel.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return apiResponseModel;
    }

    @GetMapping(value = "/getInventory/{inventoryId}")
    @ResponseBody
    public ApiResponseModel getInventoryWithId(@PathVariable("inventoryId") Long inventoryId) {
        ApiResponseModel apiResponseModel = new ApiResponseModel();
        try{
            InventoryModel response = inventoryService.getInventoryWithGivenId(inventoryId);
            if(response == null) {
                apiResponseModel.setStatusCode(HttpStatus.NO_CONTENT.ordinal());
                apiResponseModel.setMessage("No Inventory found");
            } else{
                apiResponseModel.setStatusCode(HttpStatus.OK.value());
                apiResponseModel.setMessage("Inventory Found");
                apiResponseModel.setData(response);
            }
        } catch (Exception exception){
            apiResponseModel.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponseModel.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return apiResponseModel;
    }
}
