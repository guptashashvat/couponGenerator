# couponGenerator
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
This project is a simple coupon generator and utilizer application which is still in a development phase. On root level the application 
has three types of user pages- Admin, Users & Merchants.
The application starts with a home page that contains muliple radio button options asking you for how you want to see the application 
and based on your selection it shows you the corresponding page(When you'll run the application it'll start with some dummy data of 
some coupons and few users). The following are the options/functionalities on home page:

Admin- As an admin you will be able to see multiple tables, one containing information of all the coupons present in the application,
another containing information of coupon mapping to the users' phone numbers and then there is a table where you can assign active
coupons to the existing users. There is also a button at the top of admin page clicking on which you can start the coupon generator
utility that will generate a new random unique coupon every minute. And you can refresh the admin page to see the newly generated coupon
in table entry.

Existing User- On selecting this option you'll be displayed with a page where you'll be asked to enter your userId. Existing users can
enter their userId to see their profile page. On the user profile page there is user information, a table that shows all the coupons
assigned to the user and a button that will redirect the user to the merchant list page where the user will select the merchant and
click the redeem button, this will take the user to a template where user will enter the couponId and the coupon will be redeemed.
(You can enter 'SHAS12', 'SHAS13', 'SHAS14',  or 'SHAS15' as userId which are just dummy users created during start of application)

New User- The option will take you to the page to register yourself as a new user. After entering your information, click the submit
button and you'll be redirected to your profile page.

Add Merchant- This option is provided to add a new merchant.

See Merchant List- This option will simply display the list of existing merchants.

	
## Technologies
Project is created with:
* Spring boot version: 2.2.4
* Thymeleaf (for template)
* H2 Database (Inbuilt)
	
## Setup
To run this project, simply download/copy the executable 'couponGenerator-0.0.1-SNAPSHOT.jar' file present in repository in your local
system, and run it using cli with the following commands:

```
$ cd <directory where the above downloaded jar file is present>
$ java -jar couponGenerator-0.0.1-SNAPSHOT.jar
```
(The command will run the application on 8080 port. To see the running application, open the browser window and enter localhost:8080)
