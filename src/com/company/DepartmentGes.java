package com.company;

import com.company.model.dao.DaoFactory;
import com.company.model.dao.DepartmentDao;
import com.company.model.entities.Department;
import java.util.List;
import java.util.Scanner;

public class DepartmentGes {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TEST 1: Department findById =====");
        Department department = departmentDao.findById(3);
        System.out.println(department);

        System.out.println("\n=== TEST 2: Department findBySeller =====");
        department= departmentDao.findBySellerId(2);

        System.out.println(department);

        System.out.println("\n=== TEST 3: Department findAll =====");
        List<Department> list = departmentDao.findAll();

        list.forEach(System.out::println);

        System.out.println("\n=== TEST 4: Department insert =====");
        Department newDep = new Department
                (null, "Food");
        departmentDao.insert(newDep);
        System.out.println("Inserted! New id = " + newDep.getId());

        System.out.println("\n=== TEST 5: Department update =====");
        department = departmentDao.findById(1);
        department.setName("Wine");
        departmentDao.update(department);
        System.out.print("Update Completed");

        System.out.println("\n=== TEST 6: Department delete =====");
        System.out.print("Enter id for delete test: ");
        int id = input.nextInt();
        departmentDao.delete(id);
        System.out.println("Delete completed");
    }
}
