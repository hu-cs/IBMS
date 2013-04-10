IBMS Functional Requirement Specification and UI
================================================
In this project we have three main use cases, which they are consist of:
1. Stock
--------
2. Consumption
----------------
3. Customers Account
---------------------
For presenting of these elements as responsively as possible and completely, we have designed a main UI and several user interfaces for each part.
The main UI is composed of a tabbed system with the material list, but its main design will be done in the second version.

![industries Accounting](http://i.imgur.com/xL6fPZ9.jpg "Optional title")

Now, lest talk about main three functional requirements:
##Stock, Material List and Sell Factor##
***A: Material List:*** According to the needs, we decided to have the following points in the material list (Material Name, Material Unit, Material Base cost, Material sell cost).

![Material List](http://i.imgur.com/5lmSzuW.jpg "Optional title")

This part of program, allows user to add new material to the list by given characteristics, remove the added material from the list and edit the added material by this interface.
***A.1: Adding New Material:*** For adding new material to the list, after the clicking on the related button, the related window will be opened.

![Add new Material](http://i.imgur.com/u96NsWc.jpg "Optional title")

 ***A.2: Deleting Inserted Material:*** For deleting any added material from the list, after the row selection, click on the delete material button and with after the asking from user, it will be deleted.
And also for editing the material details, after row selection, click on the edit button that a window that is the same with adding material window will be appeared.
***B: Sell Factor:*** for selling the materials purpose, we need to have a strong design that automatically subtract the sold material from the stock list and add them to the customer account.
For this purpose, we designed a responsive interface that is shown in below:

![Sells Factore](http://i.imgur.com/s2h5EkM.jpg "Optional title")

The customer name will be chosen by a combo box, each customer has a special factor number that after the customer name selection, it will automatically be set to the factor. And also time and date will be set.
The table by default has a row that according to the needs, user will create a new row that the material name will be chosen by a combo box, and the unit and the cost will be set dynamically according to the material quantity and the total cost will be calculated and written at itself position.
After clicking on ok button, various changes will occur on, customer account, stock list and sold material list.
***C: Stock Account:*** Stock account will cover the existing materials account, stored material account history and sold material account history.

![Stock Account](http://i.imgur.com/YnRAZ7u.jpg "Optional title")

This purposes are implemented by a strong and useful software design.

![Storing Materials to the stock](http://i.imgur.com/43Fs3gu.jpg "Optional title")

***C.1: Storing materials to the stock:*** For adding any material to the stock list, click on add to stock button, then the following interface will appear.
For storing material to stock, we must select material name from the combo box and write the quantity, then by clicking on the ok button, the written quantity will be added to the stock and also some changes will occur to the store account history with date and time.
****C.2: Stored material history:***  For seeing the store history we can select the material and click on the related button:

![Stored Material History](http://i.imgur.com/pTzCS7p.jpg "Optional title")

***C.3: Sold material history:*** And for seeing the sold material history, select the related row and click on the related button.

![Sold Material History](http://i.imgur.com/GfIpccr.jpg "Optional title")

In this section we can see that this kind of material is bought by someone and how many.
***D: Material sold list:*** For managing of the company as well as possible, we must to know that in the recent month or year, how many and what kinds of materials are sold.
For this purpose, we designed an interface that after each transaction, will be changed.

![Material sold list](http://i.imgur.com/FIPbUdj.jpg "Optional title")

##Customers Account##
One of the important use cases of IBMS is the Customer’s Account. 
This part of design, covers all aspect of customers like (their transactions account, customers list and all of their details).
***A: Customers List:*** This part contains (customer name, Last name, father name, ID, address, transactions and his/her indebting to the company).

![Customers List](http://i.imgur.com/kxIfHSS.jpg "Optional title")

***A.1: Adding new customer to the list:*** for adding new customer to the list, we must to click on the related button.

![Adding new customer to the list](http://i.imgur.com/PNs31Sg.jpg "Optional title")

For adding new customer, just inserting the name, last name, father name and address is necessary, because the transaction details will be added after the first transaction.
***A.2: Customer deletion:*** for customer deletion, after row selection, the related button must be clicked but, after confirmation of a JoptionPane, because after deletion, all of deleted customer’s account will be deleted.
***A.3: Customer details:*** In this part, we can see all transactions of the customers with details.
For this purpose implementation, a high level system design is done; as you saw in the sell factor, after each customer shopping, the total cost will be added to his/her account.
To see the customer details, first of all, a customer must to be selected and click on the related button.

![Ali Raza Ahmadi Account](http://i.imgur.com/Uu6RMhJ.jpg "Optional title")

As you see, in this part we have three main part (giving money, receiving materials and sell factor).
***A.3.1: Receiving Material:*** In this part, all of customer shopping is listed according to sell factor number and its cost; we can see each factor that we want and also we have a total cost in this section, but for more information we can see it.
The factor content is rendered in the factor interface.
***A.3.2: Giving money:*** In this part all of the given money is listed and we can see its total value in the left side of the customer details interface. This is used for calculating the customer indebting account.

![Add Ali Raza Ahmadi Account](http://i.imgur.com/xBkEAkK.jpg "Optional title")

***A.3.3: Adding new money order to the customer account:*** for this purpose, we must to click on the related button. 
In here, the date will be set dynamically.
And also, we can see the company demand in the totaled label.
##Consumption##
One of the most important phases of IBMS is consumption calculation, because it is going to measure the company profit in the future.
There are several consumption accounts, they are consist of:
Corruptions, raw material, necessities, company consumption (water, electricity, internet, phone and gas); all these consumptions will be added to a general account that we will talk about later.
***A: Corruption:*** Corruption consumption is so important that is divided in four part (Machinery, Building, precinct and sundries).

![Corruption](http://i.imgur.com/mR2gXaf.jpg "Optional title")

All of the corruption consumptions have the same details in their recording, so we render their data in a joint interface.
***A.1: Adding new subject:*** After each consumption, we must to save it in the system, so we must to click on its related button; we have a total value also here for each corruption.

![Adding new subject](http://i.imgur.com/qe5t7Ec.jpg "Optional title")

***A.2: Deleting any subject:*** when something wrong happens, we need to delete it, for doing that we must to select the row and click on the related button.
***B: Buying raw material:*** The most important account in measuring the profit is raw material.
We designed a structure for recording raw materials; it is a tabbed system that according to the user need, material types can be added (it is completely user driven).
In this part also we need to add and remove transactions records to raw material consumption list.
To perform these tasks, we designed a responsive interface for material list management.

![Buying raw material](http://i.imgur.com/1CSUfka.jpg "Optional title")

***B.1: Adding new subject:*** after shopping any raw material, we use it to record it consumption in the list.
It is collected with other points and will be written in the total value label.

![Adding new subject](http://i.imgur.com/q05AUk3.jpg "Optional title")

***B.2: Deleting added material:*** To delete ant saved record, select any row, then delete it.

![Deleting added material](http://i.imgur.com/Fv6Uhfo.jpg "Optional title")

***B.3: Creating new kind of raw material:*** because IBMS is a user driven program, user must be able to create any new kind of raw material, so we will do this by clicking on related button.
By inserting a name and do OK, a new tab will be generated that contains an empty table.
***C: Necessities:*** It is also one of the important account witch must to be calculated; in this part also we add and remove its subjects by using below interface.

![Necessities](http://i.imgur.com/vNk4HxC.jpg "Optional title")

***C.1: Adding new subject:*** for adding new subject, just we click on the related button, then a new interface appears and after the insertion, generates a new row that contains the inserted values with specified details but the date will be added dynamically.

![Adding new subject](http://i.imgur.com/4wFGjzf.jpg "Optional title")

According to the interviews; they need to record any transaction’s factor number with its cost and date just, then if any problem happens, they will check the original factor.
***C.2: Deleting the Subject:*** for deleting the subject, we jest select the row and click on the delete button.
***D: Company Consumption:*** These are also important for calculation; the company consumption is consist of (water, electricity, gas, phone and internet).
Because all of them have the same details and usage, we designed a system for them that calculate them in a joint place but separately; these are also going to be added to the consumptions list.

![Comapany Consumption](http://i.imgur.com/qd237XX.jpg "Optional title")

Each part has a total value that all of the added subject will be added to, but separately.
In here also we can add or remove any new or added subject to/from the list.
***D.1: Adding new Subject:*** For adding new subject, inserting the factor number and cost is enough, and date will be added dynamically.

![Adding new Subject](http://i.imgur.com/fruNQGH.jpg "Optional title")

***D.2: Deleting the Inserted Subject:*** Select the row and click on delete button.
***E: General Consumption List:*** in this part all of consumptions will be listed in a unique interface and a total value label.

![Genral Consumption List](http://i.imgur.com/b5NNVwi.jpg "Optional title")


