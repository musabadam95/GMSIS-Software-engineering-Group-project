========================
GM-SIS README FIRST
========================
GM-SIS PROJECT

GM-SIS (Garage Management Information System) is a system that consists of different modules. The aim of this project was to create and supply a stand-alone java based implementation of a GM-SIS.

========================
Customer Module
========================
The customer module stores all information about a customer. 
This includes all vehicles owned, bookings per vehicles and parts used per booking.
It also contains the account of a customer that shows all the bills per booking and vehicle and whether they have been paid or not (payment status). 

Steps on how to use this module.

-User should log into the system with valid details and click the customer tab.

-User can add a new customer by clicking the add button, entering valid information and clicking create.

-User can edit the customer by selecting the customer, clicking the edit button, changing information and clicking save.

-User can view customer account by selecting the customer and clicking the account button.

-User can change payment status in the account by selecting the bill, choosing from the choice box and clicking save.

-User can view vehicles owned by the customer by selecting a customer.

-User can view vehicle details and bookings by clicking on a vehicle from the vehicles owned.

-User can view parts used per vehicle booking by clicking on the bookings.

-User can delete customer record by selecting a customer, clicking delete button and clicking ok when the alert box is shown.

-User can search existing customer by typing into the search bar, pressing enter on the keyboard. User can also clear search and reload table by clearing search bar and pressing enter on the keyboard
========================
OrderParts
========================
This Module allows the ordering and installation of parts onto selected vehicles
Steps on how to use this module

-User should Select Customer from the Customer Drop down menu or use the search box to filter the drop down menu 

-User should then select the vehicle via the vehicle reg drop down menu of the selected customer

-User should then select the booking slot via the booking time drop down menu of the selected Vehicle

-User can now select what parts to install via the part drop down menu and once selected, should click addpart

-User can also edit the part they have installed by clicking on the record in the table view of the part they would like to edit and then select the part to replace with from the part dropdown menu then click on edit part

-User can also delete the part they have installed by clicking on the record in the table view and then clicking on delete, This will add the part back into the stock and will refund the value back to the customer

-please use refresh button if you have added a brand new stock part into the stock table to update the part drop down box
========================
Stock
========================
This module allows the control of parts stock 
Steps on how to use this module

-User should enter the new part name, part cost, part quantity and part description into the top left text boxes then click on addnewstock button

-User should select part to to increase quantity then select the value they want to add via the dropdown boxes then click add stock button

-User should select part to delete from table view via the table view, then click on delete button

-User should select part to edit from table view via the table view, then change any relevent text from the bottom left text fields ,then click on edit button

-User should always hit refreshall button after any possible changes with parts (adding parts to vehicles)
========================
Vehicle
========================

This module allows for the user to search for vehicles by type or vehicle registration, and to view bookings and parts associated. Adding, editing and deleting of vehicles can also be done. 
Steps on how to use Vehicles module:
-Select Vehicles from tabs.

-Search for type using the type textfield and clicking the search button underneath it. This will display vehicles with the type entered by the user.

-Search for vehicle registration using the vehicle registration textfield and clicking the search button underneath it. This will display vehicles with the vehicle registration entered by the user.

-Select vehicle from vehicle table and click 'View Parts and Bookings' to view the parts and bookings associated with that vehicle. These will be displayed in the two tables below.

-Add vehicle by clicking the add button. This will open a popup. Dates and vehicle registration must be entered.

-Edit vehicle by selecting a vehicle and clicking the edit button. This will open a popup. Dates and vehicle registration must be entered if removed.

-Delete vehicle by selecting a vehicle and clicking the edit button. A message will appear asking to confirm, type 'y' for yes and 'n' for no.

========================
Bookings
========================

In order to add a booking , the user must initially be signed into the system. The user then presses "add" button and is taken to a pop up
and shown a list of all customers in the system. The user is able to search through these customers through; first name, last name, vehicle registration and vehice make.
The user must then select the customer whose details are then loaded on to the screen. The user then must select a date (unable to select any date in the past or any sunday),
a booking time (limited times on sunday) and a type (either diagnosis and repair or scheduled maintenance). Once complete the pop up is closed and the user must click to
refresh the bookings table in order to display the new bookings. The user is also able to edit bookings on the main bookings tab. The user may search for and select a booking,
and edit the booking date,time, mileage and duration.