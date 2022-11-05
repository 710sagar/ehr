package com.electronicHealthRecord.pharmacy.repository;

import com.electronicHealthRecord.pharmacy.model.DeletedInventoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedInventoryRepository extends JpaRepository <DeletedInventoryModel, Long> {
}
