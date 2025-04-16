Electronic House Manager


Overview

This project, Electronic House Manager, is a Java-based application designed to support professional property management companies in maintaining multi-family residential buildings. The system automates the management of building data, tenant charges, employee assignments, and payment tracking through a relational database and a service layer.


Features

Building and Apartment Management
- Building Data Management:
  - Create, edit, and delete building records with details such as address, number of floors, number of apartments, built-up area, and shared spaces.
- Apartment Data Management:
  - Maintain records of apartments, including apartment numbers, floor location, area in square meters, and details about owners and residents.

Fee Calculation and Payment Management
- Maintenance Fee Calculation:
  - Calculate monthly fees based on the apartment's area and additional charges:
    - Per resident over 7 years old who uses the elevator.
    - For pets using the building's shared spaces.
- Monthly Payments:
  - Input and record fees paid by building residents.
  - Save detailed payment records, including company, employee, building, apartment, amount, and payment date, in a file.

Employee and Company Management
- Employee Assignment:
  - Each building is serviced by one employee from the management company.
  - Newly contracted buildings are assigned to the employee with the least number of buildings.
  - Reassign buildings in the event of an employee's departure.
- Company Data Management:
  - Create, edit, and delete company records.
  - Manage data such as company name, contact information, and total revenue from collected fees.

Data Filtering, Sorting, and Reporting
- Data Insights:
  - Filter and sort by:
    - Companies by revenue (collected fees).
    - Employees by name or the number of buildings assigned.
    - Residents by name or age.
- Detailed Reporting:
  - Generate detailed and summary reports for:
    - Buildings serviced by each employee within a company.
    - Apartments and residents in a specific building.
    - Monthly fee amounts for each company, building, and employee.
    - Paid fees for each company, building, and employee.

Robust Data Storage and Validation
- Data Storage:
  - Leverage a relational database to store, retrieve, and manage all application data.
- Input Validation:
  - Ensure data integrity through validation for all input and managed records.

Technologies Used
- Programming Language: Java
- Database: Relational database for persistent storage of building, apartment, employee, and payment information.
- File Management: Store and read payment records in structured file formats.
- Validation: Implement input validation for reliable data processing.

Objectives

This project aims to create a fully functional application for managing building-related data and processes. It includes automating maintenance fee calculations, recording payments, managing employee assignments, and generating insightful reports for professional property management companies. With built-in data validation and reporting capabilities, the application provides a comprehensive solution for day-to-day operations.
