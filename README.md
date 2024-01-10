# Hotel System API

## Overview
About 		: Java Rest API using MySQL, for CRUD actions upon API requests\
Team size	: 2\
Members	: Wint Nandar Lwin, Chit Ko Ko\
Skills		: Java, Springboot, JWT, Rest API, MySQL, Github, Postman\
Project Type	: Side Project [Not associated with school]\
Duration	: 1 month\
Phases		: Project idea, Designing API, Set-up, Coding, Manual Testing with Postman

## Folders
config		: configurations for JWT\
controller	: API end points\
service		: business logic\
repo		: data retrieval\
model		: data classes\
constants	: constant variables

## Some Use cases
Note: No authorization / validation

Create a booking
1.	New db-record in visitor table [about visitor], booking table [about booking], room transition table [about room information related to this booking]
2.	Send 2 emails, one for booking confirmation, another one for payment receipt [assume payment is made successfully]
3.	Return booking object created, otherwise Internal Server Error

Cancel booking
1.	Update booking record [perform soft-delete on the booking record]
2.	Send cancellation email to the customer
3.	Return Booking record status
4.	Otherwise, e.g. booking record not found or update action failed, return Internal Server Error

Find all booking
1.	Retrieve all bookings by providing a param whether they are cancelled or not
