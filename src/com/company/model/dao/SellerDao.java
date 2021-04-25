package com.company.model.dao;

import com.company.model.entities.Department;
import com.company.model.entities.Seller;

import java.util.List;

public interface SellerDao {
    void insert(Seller obj);
    void update(Seller obj);
    void delete(Integer id);

    Seller findById(Integer id);
    List<Seller> findAll();
    List<Seller> findByDepartment(Department department);
}
