package Main;

import Entity.Department;
import Entity.Employee;
import Entity.JobCategory;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main2 {
    private static Scanner scanner = new Scanner(System.in);
    private static StringBuilder querry;
    private static List<Employee> employeeList = new ArrayList<>();
    private static List<Department> departmentList = new ArrayList<>();
    private static List<JobCategory> jobCategoryList = new ArrayList<>();
    private static EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("persistence-unit");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static EntityTransaction entityTransaction = entityManager.getTransaction();
    private static Main2 singleTon;

    private static final Logger lgr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void main(String[] args) {

        setupLogger();
        entityTransaction.begin();
        selectOption();
        entityTransaction.commit();
        entityManager.close();




    }

    public static void setupLogger() {
        LogManager.getLogManager().reset();
        lgr.setLevel(Level.INFO);

        try {

            FileHandler fh = new FileHandler("fileLogger",true);
            lgr.addHandler(fh);
            fh.setLevel(Level.FINE);
        }
        catch (IOException e){
            lgr.log(Level.SEVERE,"The logger file was not create");
        }
    }

    public static void selectOption(){
        int response = 0;
        int secondResponse;
        do {
            System.out.println(" What table do you want to interrogate ? \n 1 - Employees. \n 2 - Departments.\n 3 - JobCategories. \n 4 - Employees by department \n ");
            secondResponse = scanner.nextInt();
            scanner.reset();
            if(secondResponse >=1 && secondResponse<= 4) {
                if(secondResponse == 2 || secondResponse == 3){
                    System.out.println("\n\n Choose a type of querry: Search - 1 , Update - 2 , Delete - 3 ");
                }
                else if(secondResponse == 1){
                    System.out.println("\n\n Choose a type of querry: Search - 1 , Update - 2 , Delete - 3 , Employees sorted by name and/or salary - 4");
                }
                else if(secondResponse == 4){
                    System.out.println("\n\n Choose a type of querry: Update - 2 , Delete - 3 ");
                }
                response = scanner.nextInt();
                if (response >= 1 && response <= 4) {
                    switch (response) {
                        case 1: {
                            doSearch(secondResponse);
                            if (secondResponse == 1) {
                                for (Employee e : employeeList) {
                                    System.out.println(e);
                                }
                            } else if (secondResponse == 2) {
                                for (Department d : departmentList) {
                                    System.out.println(d);
                                }
                            } else {
                                for (JobCategory j : jobCategoryList) {
                                    System.out.println(j);
                                }
                            }
                            break;
                        }
                        case 2: {
                            doUpdate(secondResponse);
                            break;
                        }

                        case 3: {
                            doDelete(secondResponse);
                            break;
                        }
                        case 4:{
                            boolean lastName;
                            boolean salary ;
                            scanner.reset();
                            System.out.println("\n \n    Sorted by salary (Enter true or false ");
                            salary = scanner.nextBoolean();
                            scanner.reset();
                            System.out.println(" Sorted by Last Name (Enter true or false");
                            lastName = scanner.nextBoolean();
                            getSortedEmployees(lastName,salary);
                            scanner.reset();
                            break;
                        }
                        default: {
                            System.out.println("This is not an option.");
                        }
                    }
                } else {
                    System.out.println("This is not a valid table.");
                }
            }
            else {
                System.out.println("Good bye !");
                lgr.info("Invalid table choice !");
            }
        }while(response >=1 && secondResponse >=1 && secondResponse <=4);
    }

    private static void doDelete(int secondResponse){
        doSearch(secondResponse);
        if(secondResponse == 1){
            if(employeeList.size() > 0 ) {
                for (Employee e : employeeList) {
                    entityManager.remove(e);
                }
            }
            makeCommit();
            printEmployees();
        }
        else if(secondResponse == 2) {
            if(departmentList.size() > 0 ) {
                for (Department d : departmentList) {
                    entityManager.remove(d);
                }
            }
            makeCommit();
            printDepartments(false);
            printEmployees();
        }
        else if(secondResponse == 3) {
            if(jobCategoryList.size() > 0) {
                for (JobCategory j : jobCategoryList) {
                    entityManager.remove(j);
                }
            }
            makeCommit();
            printJobCategory(false);
            printEmployees();
        }

    }
    private static void doUpdate(int secondResponse) {
        doSearch(secondResponse);
        String value;
        switch (secondResponse){
            case 1 : case 4 :{
                try {
                    int column;
                    System.out.println("Select the column that you want to update for the selected employees.");
                    System.out.println(" 1 - firstname \n 2 - lastname \n 3 - isManager;\n" +
                            "    4 - startDate;\n" +
                            "    5 - endDate;\n" +
                            "    6 - active;\n" +
                            "    7 -  address;\n" +
                            "    8 - CP;\n" +
                            "    9 - telephone;\n" +
                            "    10 - email;\n" +
                            "    11 - birthday;\n" +
                            "    12 - noChildren;\n" +
                            "    13 - salary;\n" +
                            "    14 - socialSecurityNumber;\n" +
                            "    15 - hasDrivingLicense;");
                    scanner.reset();
                    column = scanner.nextInt();
                    scanner.reset();
                    switch (column) {
                        case 1: {
                            System.out.println("Enter the new firstname");
                            value = scanner.next();
                            for (Employee e : employeeList) {
                                e.setFirstName(value);
                            }
                            break;
                        }
                        case 2: {
                            System.out.println("Enter the new lastname");
                            value = scanner.next();
                            for (Employee e : employeeList) {
                                e.setLastName(value);
                            }
                            break;
                        }
                        case 3: {
                            System.out.println("Make it manager ? 1- Yes , 0 - No");
                            int manager = scanner.nextInt();
                            boolean newStatus;
                            newStatus = manager == 1;
                            for (Employee e : employeeList) {
                                e.setManager(newStatus);
                            }
                            break;
                        }
                        case 4: {
                            System.out.println("Enter the new start date:");
                            value = scanner.next();
                            LocalDate date = LocalDate.parse(value);
                            for (Employee e : employeeList) {
                                e.setStartDate(date);
                            }
                            break;

                        }
                        case 5: {
                            System.out.println("Enter the new end date");
                            value = scanner.next();
                            LocalDate date = LocalDate.parse(value);
                            for (Employee e : employeeList) {
                                e.setEndDate(date);
                            }
                            break;
                        }
                        case 6: {
                            System.out.println("Is it active ? 1- Yes , 0 - No");
                            int active = scanner.nextInt();
                            boolean newStatus;
                            newStatus = active == 1;
                            for (Employee e : employeeList) {
                                e.setManager(newStatus);
                            }
                            break;

                        }
                        case 7: {
                            System.out.println("Enter the new address");
                            scanner.nextLine();
                            value = scanner.nextLine();
                            for (Employee e : employeeList) {
                                e.setAddress(value);
                            }
                            break;

                        }
                        case 8: {
                            System.out.println("Enter the new CP");
                            value = scanner.next();
                            for (Employee e : employeeList) {
                                e.setCP(value);
                            }
                            break;
                        }
                        case 9: {
                            System.out.println("Enter the new telephone");
                            value = scanner.next();
                            for (Employee e : employeeList) {
                                e.setTelephone(value);
                            }
                            break;
                        }
                        case 10: {
                            System.out.println("Enter the new email");
                            value = scanner.next();
                            for (Employee e : employeeList) {
                                e.setEmail(value);
                            }
                            break;

                        }
                        case 11: {
                            System.out.println("Enter the new birthday");
                            value = scanner.next();
                            LocalDate date = LocalDate.parse(value);
                            for (Employee e : employeeList) {
                                e.setBirthday(date);
                            }
                            break;
                        }
                        case 12: {
                            System.out.println("Enter the new number of children");
                            int number = scanner.nextInt();
                            for (Employee e : employeeList) {
                                e.setNoChildren(number);
                            }
                            break;

                        }
                        case 13: {
                            System.out.println("Enter the new salary");
                            Double salary = scanner.nextDouble();
                            for (Employee e : employeeList) {
                                e.setSalary(salary);
                            }
                            break;

                        }
                        case 14: {
                            System.out.println("Enter new Social Security Number");
                            value = scanner.next();
                            for (Employee e : employeeList) {
                                e.setSocialSecurityNumber(value);
                            }
                            break;

                        }
                        case 15: {
                            System.out.println("Does he have driving license ? 1- Yes , 0 - No");
                            int lincese = scanner.nextInt();
                            boolean newStatus;
                            newStatus = lincese == 1;
                            for (Employee e : employeeList) {
                                e.setManager(newStatus);
                            }
                            break;
                        }
                        default: {
                            System.out.println("That wan`t an option.");
                        }
                    }
                }
                catch (NullPointerException e){
                    System.out.println("Employees list is clear");
                }
                makeCommit();
                printEmployees();
                break;
            }
            case 2:{
                System.out.println("Enter the new name for selected department");
                value = scanner.next();
                for (Department d: departmentList) {
                    d.setName(value);
                }
                makeCommit();
                printDepartments(false);
                break;
            }
            case 3:{
                System.out.println("Enter the new name for selected jobcategory");
                value = scanner.next();
                for (JobCategory j: jobCategoryList) {
                    j.setName(value);
                }
                makeCommit();
                printJobCategory(false);
                break;
            }
            default:{
                System.out.println("That was not a valid option.");
            }
        }

    }

    public static void doSearch(int response){
        employeeList.clear();
        departmentList.clear();
        jobCategoryList.clear();
        scanner.reset();
        int id;
        switch (response){
            case 1:{
                do {
                    Employee employee;
                    id = printOption();
                    if(id > 0 ){
                        employee = entityManager.find(Employee.class, id);
                        employeeList.add(employee);
                    }

                }while (id >0 );
                break;
            }
            case 2:{
                do {
                    Department department;
                    id = printOption();
                    if(id > 0 ){
                        department = entityManager.find(Department.class, id);
                        departmentList.add(department);
                    }
                }while (id > 0);
                break;
            }
            case 3:{
                do {
                    JobCategory jobCategory;
                    id = printOption();
                    if(id > 0 ){
                        jobCategory = entityManager.find(JobCategory.class,id);
                        jobCategoryList.add(jobCategory);
                    }
                }while (id > 0);
                break;
            }
            case 4:{
                System.out.println("Enter the department id:");
                int departmentId =  scanner.nextInt();
                printEmployees(departmentId);
                break;
            }
        }
    }

    public static int printOption(){
        int id;
        System.out.println("Enter a bad id(<0) if you want to stop.");
        System.out.println("Enter the id : ");
        id = scanner.nextInt();
        return id;
    }

    public static void printEmployees(){
        List<Employee> result = entityManager.createQuery("from Employee", Employee.class).getResultList();
        for (Employee employee : result) {
            System.out.println(employee + "\n");

        }
    }
    public static void printEmployees(int departmentId) {
        Query query = entityManager.createQuery("select e from Employee e where e.department = ?1", Employee.class);
        query.setParameter(1, entityManager.find(Department.class, departmentId));
        employeeList = query.getResultList();
        for (Employee employee : employeeList) {
            System.out.println(employee + "\n");

        }
    }

    public static String setJql(int option){
        switch (option){
            case 1 :{
                return "Select e from Employee as e order by e.lastName , e.salary ";
            }
            case 2:{
                return "Select e from Employee as e order by e.lastName ";

            }
            case 3:{
                return "Select e from Employee as e order by e.salary ";
            }
            default:{
                return "Select e from Employee";
            }
        }
    }
    public static void getSortedEmployees(boolean lastName, boolean salary) {
        String jql;
        if (lastName && salary) {
            jql = setJql(1);
        } else if (lastName && !salary) {
            jql = setJql(2);
        } else if (!lastName && salary) {
            jql = setJql(3);
        } else {
            jql = setJql(4);
        }

        Query query = entityManager.createQuery(jql);
        employeeList = query.getResultList();
        for (int i = 0; i < employeeList.size(); i++) {
            Employee employee = employeeList.get(i);
            System.out.println(employee + "\n");

        }
    }

    public static void printDepartments(boolean returnAll){
        List<Department> result = entityManager.createQuery( "from Department", Department.class ).getResultList();
        for (Department department : result) {
            System.out.println(department);

        }
        if(returnAll){
            departmentList.addAll(result);
        }
    }

    public static void printJobCategory(boolean returnAll){
        List<JobCategory> result = entityManager.createQuery( "from JobCategory", JobCategory.class ).getResultList();
        for(int i=0;i<result.size();i++)
        {
            JobCategory jobCategory = result.get(i);
            System.out.println(jobCategory);

        }
        if(returnAll){
            jobCategoryList.addAll(result);
        }
    }

    public static void makeCommit(){
        entityTransaction.commit();
        entityTransaction.begin();
    }



}
