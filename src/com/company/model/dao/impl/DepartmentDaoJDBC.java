package com.company.model.dao.impl;

import com.company.db.DB;
import com.company.db.DbException;
import com.company.model.dao.DepartmentDao;
import com.company.model.entities.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("INSERT INTO department " +
                    "(Name) " +
                    "VALUES " +
                    "(?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());

           int rowsAffected = st.executeUpdate();

           if(rowsAffected>0){
               ResultSet resultSet = st.getGeneratedKeys();
               if(resultSet.next()){
                   int id = resultSet.getInt(1);
                   obj.setId(id);
               }
               DB.closeResultSet(resultSet);
           }
           else{
               throw new DbException("Unexpected error! No rows were affected!");
           }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {

            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement("UPDATE department " +
                    "SET Name = ? " +
                    "WHERE Id = ?");

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void delete(Integer id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement("DELETE FROM department WHERE Id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = connection.prepareStatement("SELECT * from department " +
                    "WHERE Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                return instantiateDepartment(rs);
            }
            return null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));

        return dep;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = connection.prepareStatement("SELECT * from department " +
                    "ORDER BY Name");

            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();

            while(rs.next()){
                list.add(instantiateDepartment(rs)); //our sellers;
                }
            return list;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    @Override
    public Department findBySellerId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = connection.prepareStatement("SELECT seller.*, department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.id = ? " +
                    "ORDER BY Name");

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                return new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
            }
            return null;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
