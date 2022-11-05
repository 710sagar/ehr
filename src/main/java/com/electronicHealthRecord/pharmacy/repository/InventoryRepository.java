package com.electronicHealthRecord.pharmacy.repository;

import com.electronicHealthRecord.pharmacy.model.InventoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryModel, Long> {
}
