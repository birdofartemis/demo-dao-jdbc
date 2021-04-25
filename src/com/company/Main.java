package com.company;

import com.company.model.dao.DaoFactory;
import com.company.model.dao.SellerDao;
import com.company.model.entities.Department;
import com.company.model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("=== TEST 1: Seller findById =====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n=== TEST 2: Seller findDepartment =====");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);

        list.forEach(System.out::println);

        System.out.println("\n=== TEST 3: Seller findAll =====");
        list = sellerDao.findAll();

        list.forEach(System.out::println);

        System.out.println("\n=== TEST 4: Seller insert =====");
        Seller newSeller = new Seller
                (null, "Greg", "greg@gmial.com", new Date(), 4000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());

        System.out.println("\n=== TEST 5: Seller update =====");
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
        System.out.print("Update Completed");

        System.out.println("\n=== TEST 6: Seller delete =====");
        System.out.print("Enter id for delete test: ");
        int id = input.nextInt();
        sellerDao.delete(id);
        System.out.println("Delete completed");
    }
}
