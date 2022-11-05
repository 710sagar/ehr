package com.electronicHealthRecord.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "inventory_table")
public class InventoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String med_name;
    private String batch_no;
    private String strength;
    private long qoh;
    private String warehouse_id;
    private long price;
    private String manufacturer;
    private String created_by;
    private String updated_by;
    @JsonFormat(pattern="dd/MM/yy")
    private Date expiry_date;
    @JsonFormat(pattern="dd/MM/yy")
    private Date create_date;
    @JsonFormat(pattern="dd/MM/yy")
    private Date update_date;

}
