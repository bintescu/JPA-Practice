package Main;

import Entity.Department;
import Entity.Employee;
import Entity.JobCategory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main1 {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();


        Department department1 = new Department();
        department1.setName("IT");

        Department department2 = new Department();
        department2.setName("FINANCE");

        JobCategory jobCategory1 = new JobCategory();
        jobCategory1.setName("Consultant");

        JobCategory jobCategory2 = new JobCategory();
        jobCategory2.setName("Programmer");

        JobCategory jobCategory3 = new JobCategory();
        jobCategory3.setName("Finance-man");

        Employee employee1 = new Employee("Mircea" , "Popescu" , jobCategory1, department1, false , LocalDate.of(2007,5,12),null,true,"Str. Mihai Bravu nr 34","00234","0748234984","mirceai@yahoo.com",LocalDate.of(1999,05,12),0,4500.22,"University of Bolognia","03345",false);
        Employee employee2 = new Employee("Stelian","Vlad",jobCategory1 ,department2,false,LocalDate.of(2009,5,10),LocalDate.of(2018,8,20),false,"Bld. Eroilor nr 40","9942134","072894853","stelian@yahoo.com",LocalDate.of(1999,04,20),0,5000.45,"University of Bucharest","094321214",true);
        Employee employee3 = new Employee("Popa","Adrian",jobCategory1,department2,true,LocalDate.of(2013,10,10),LocalDate.of(2017,5,15),false,"Bld. Eroilor nr 56","444321","0728443215","popas@yahoo.com",LocalDate.of(1997,5,13),1,7600.45,"University of Bucharest","02131214",true);
        Employee employee4 = new Employee("Grigore","Pandaru",jobCategory3,department2,false,LocalDate.of(2019,7,10),null,true,"Bld. Trandafirilor nr 45","854322","073885921","grigore34@yahoo.com",LocalDate.of(1996,2,13),0,5400.45,"Politehnica - Faculty of Energetic","04211214",true);

//        List<Employee> employeesDep1 = new ArrayList<>();
//        employeesDep1.add(employee1);
//        department1.setEmployees(employeesDep1);
//
//        List<Employee> employeesDep2 = new ArrayList<>();
//        employeesDep2.add(employee2);
//        employeesDep2.add(employee3);
//        employeesDep2.add(employee4);
//        department2.setEmployees(employeesDep2);
//
//        List<Employee> employeesJob1 = new ArrayList<>();
//        employeesJob1.add(employee1);
//        employeesJob1.add(employee2);
//        employeesJob1.add(employee3);
//        jobCategory1.setEmployees(employeesJob1);
//
//
//        List<Employee> employeesJob2 = new ArrayList<>();
//        employeesJob2.add(employee4);
//
//        jobCategory3.setEmployees(employeesJob2);
        entityTransaction.begin();

        entityManager.persist(department1);
        entityManager.persist(department2);

        entityManager.persist(jobCategory1);
        entityManager.persist(jobCategory2);
        entityManager.persist(jobCategory3);

        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);
        entityManager.persist(employee4);

        entityTransaction.commit();
        entityManager.close();


    }
}
