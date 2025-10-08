package com.pluralsight;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String filepath = "employees.csv"; //file path
        try (BufferedReader bufReader = new BufferedReader(new FileReader("employees.csv"))) {
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
                // System.out.printf("ID: %s, Name: %16s, Gross Pay: $%.2f%n",
                //  employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
                // Instead of printing to screen, write to console
                // create a FileWriter
                FileWriter fileWriter = new FileWriter("punchCard.txt");
                // create a BufferedWriter
                BufferedWriter bufWriter = new BufferedWriter(fileWriter);
                // write to the file
                while ((input = bufReader.readLine()) != null) {
                    input = String.format(employee + "\n");
                    bufWriter.write(input);
                    bufWriter.close();
                }
            }}
        catch (IOException e) {
            // display stack trace if there was an error
            System.out.println("sorry! Something went wrong: " + e.getMessage());}
            }
        }
