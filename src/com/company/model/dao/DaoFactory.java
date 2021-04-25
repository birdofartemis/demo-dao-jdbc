package com.company.model.dao;

import com.company.db.DB;
import com.company.model.dao.impl.DepartmentDaoJDBC;
import com.company.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }
    public static DepartmentDao createDepartmentDao(){
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}
