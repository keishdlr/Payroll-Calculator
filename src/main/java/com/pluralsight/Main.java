package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filepath = "employees.csv"; //file path
        try (
                BufferedReader bufReader = new BufferedReader(new FileReader("employees.csv"))) {
            //file reader object and buffer reader to manage input
            String input = null; //Placeholder for every line of text to be read

            while ((input = bufReader.readLine()) != null) { // Read until there is no more lines
                // Split it into individual fields using the | character as the delimiter
                String[] attributes = input.split("\\|");
                // Copy the values from the tokens array into variables that match the data
                //and then use them to create a new Employee object
                int id = Integer.parseInt(attributes[0]);
                String name = attributes[1];
                double hoursWorked = Double.parseDouble(attributes[2]);
                double payRate = Double.parseDouble(attributes[3]);
                Employees employee = new Employees(id, name, hoursWorked, payRate);
                System.out.printf("ID: %s, Name: %16s, Gross Pay: $%.2f%n",
                        employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
            }
        } catch (IOException e) {
            // display stack trace if there was an error
            System.out.println("sorry! Something went wrong: " + e.getMessage());
        }

    }}

    //employee object
//        Employees employee1 = new Employees( int id, String name,double hoursWorked, double payRate);
//        Employees employee2 = new Employees( int id, String name,double hoursWorked, double payRate);
//        Employees employee3 = new Employees( int id, String name,double hoursWorked, double payRate);
//        Employees employee4 = new Employees( int id, String name,double hoursWorked, double payRate);
//
//        //Display the employee using a print and by calling the employee's
//        //getEmployeeId(), getName(), and getGrossPay() methods
//
//        System.out.printf("ID: %s, Name: %s, Gross Pay: $%.2f%n",
//                employee2.getEmployeeId(), employee2.getName(), employee2.getGrossPay());
//        System.out.printf("ID: %s, Name: %s, Gross Pay: $%.2f%n",
//                employee3.getEmployeeId(), employee3.getName(), employee3.getGrossPay());
//        System.out.printf("ID: %s, Name: %s, Gross Pay: $%.2f%n",
//                employee4.getEmployeeId(), employee4.getName(), employee4.getGrossPay());

    // close the stream and release the resources
