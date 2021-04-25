package com.company.model.dao;

import com.company.model.entities.Department;
import com.company.model.entities.Seller;

import java.util.List;

public interface DepartmentDao {
    void insert(Department obj);
    void update(Department obj);
    void delete(Integer id);

    Department findById(Integer id);
    List<Department> findAll();
    Department findBySellerId(int id);
}
