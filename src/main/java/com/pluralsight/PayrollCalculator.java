package com.pluralsight; // Declares the package name

import java.io.*;         // Imports classes for file input/output
import java.util.Scanner; // Imports Scanner for user input

public class PayrollCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object to read user input from the console

        // Prompt the user to enter the name of the employee file to read
        System.out.print("Enter the name of the employee file to process: ");
        String inputFileName = scanner.nextLine(); // Store the user's input as the input file name

        // Prompt the user to enter the name of the payroll file to create
        System.out.print("Enter the name of the payroll file to create: ");
        String outputFileName = scanner.nextLine(); // Store the user's input as the output file name

        // Try-with-resources block to automatically close file readers/writers
        try (
                BufferedReader bufReader = new BufferedReader(new FileReader(inputFileName)); // Open the input file for reading
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName)) // Open the output file for writing
        ) {
            // Write the header line to the output payroll file
            bufferedWriter.write("id|name|gross pay");
            bufferedWriter.newLine(); // Move to the next line

            String inputLine; // Variable to hold each line read from the input file

            // Read each line from the input file until there are no more lines
            while ((inputLine = bufReader.readLine()) != null) {
                // Convert the line of text into an Employees object
                Employees employee = parseEmployee(inputLine);

                // Calculate the employee's gross pay using their hours and pay rate
                double grossPay = employee.getGrossPay();

                // Format the output line in the required format: id|name|gross pay
                String outputLine = String.format("%d|%s|%.2f",
                        employee.getEmployeeId(), // Employee ID
                        employee.getName(),       // Employee name
                        grossPay);                // Gross pay rounded to 2 decimal places
                // Write the formatted line to the output payroll file
                bufferedWriter.write(outputLine);
                bufferedWriter.newLine(); // Move to the next line
            }

            // Notify the user that the payroll file was created successfully
            System.out.println("Payroll file created successfully: " + outputFileName);

        } catch (IOException e) {
            // Handle any file-related errors and display a message
            System.out.println("Error processing files: " + e.getMessage());
        }
    }

    // Helper method to convert a line of text into an Employees object
    private static Employees parseEmployee(String line) {
        // Split the line into parts using the | character as the delimiter
        String[] parts = line.split("\\|");

        // Parse each part into the appropriate data type
        int id = Integer.parseInt(parts[0]);           // Employee ID
        String name = parts[1];                        // Employee name
        double hoursWorked = Double.parseDouble(parts[2]); // Hours worked
        double payRate = Double.parseDouble(parts[3]);     // Hourly pay rate

        // Create and return a new Employees object using the parsed data
        return new Employees(id, name, hoursWorked, payRate);
    }
}