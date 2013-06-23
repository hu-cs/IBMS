#IBMS Iteration#2(Customers and cell factor)#


Tamim Ahmad 20606<br/>
Mustafa  20595

###Second iterations is divided into two main use cases###

1. Customers.
2. Sell factor.

###User stories####

**(1-Customers)**<br/>
a.    Officer must to be able to add new customer to the list by the fix details.<br/>
b.    Officer must to be able to remove the added customer from the list.<br/>
c.    Officer must to be able to see the customer account.<br/>
d.    Officer must to be able to add to the customer payment account.<br/>
e.    Officer must to be able to see the customer selected factor.<br/>
f.    Officer must to be able to see the customer shopping account.<br/>
g.    Officer must to be able to see the company demand.<br/>
h.    The customers ID must to be an auto increment number.<br/>
i.    The customer shopping factors must to be according the factor number.<br/>

**(2-Sell factor)** 

a.  Officer must to be able to set the customer name by a combo box to the factor.<br/>
b.    Officer must to be able to select the material name by a combo box.<br/>
c.    Officer must to be able to see the time, date and factor number according the customer name that is sequential.<br/>
d.    Officer must to be able to see the material unit and total cost after the material selection and inserting the material quantity.<br/>
e.    Officer must to be able to see the total value of the factor.<br/>
f.    Officer must to be able to see the changes in the stock account after the selling the materials.<br/>
g.    Officer must to be able to see the changes in the sold(X) material history and the sold materials list.<br/>
h.    Officer must to be able to see the changes in the selected customer account after the selling.<br/>

**(Detailed Use Case)**
**1. Customer**

***Requirements:***

| REQ NO.       | Title               |  Description                 |
| :------------ | :-----------        | :--------------------------: |
| **REQ 1**     | Add Customer        |                              |
| **REQ 2**     | Remove Customer     |                              |
| **REQ 3**     | Edit Customer       |                              |
| **REQ 4**     |See Customer Account |                              |
| **REQ 4.1**   |Payment Account      |                              |
| **REQ 4.2**   |Shopping Account     |                              |
| **REQ 5**     |See Factor           |                              |
| **REQ 6**     |Company Demand       |                              |


***Use Cases:***

| UC NO.        | Title                    |  Related REQ                 |
| :------------ | :-----------             | :------------------          |
| **UC 1**      | Customer Management      |     |REQ1, REQ2, REQ3        |
| **UC 2**      | Customer Registration    |      REQ1                    |
| **UC3**       | Customer Edition         |  REQ3                        |
| **UC 4**      |Customer Deletion         |    REQ2                      |
| **UC 5**      |Customer Account          | REQ4,REQ4.1,REQ4.2,REQ5,REQ6 |

**2.Sell Factor:**<br/>
*Requirements:*

| REQ NO.       | Title                      |  Description         |
| :------------ | :-----------               | -------------------: |
| **REQ 1**     | Change to the Stock        |                      |
| **REQ 2**     | Sold Material list Changes |                      |
| **REQ 3**     | Customer Account Changes   |                      |
| **REQ 4**     |Calculate Total Cost        |                      |
| **REQ 5**     |Set to Each Material Price  |                      |
| **REQ 6**     |Has Date And Time           |                      |
| **REQ 7**     |Has Special Factor Number   |                      |

***Use Cases:***

| UC NO.        | Title              |  Related REQ             |
| :------------ | :-----------       | :-------------------     |
| **UC 1**      | Changes            |     REQ1, REQ2, REQ3     |
| **UC 2**      | Characteristics    | REQ4, REQ5, REQ6, REQ 7  |

***Customer Use Cases*** <br/>
**UseCase 1: Customer Mnagement**
  
|Title                  |  Description                           |
| :------------         | :-----------                           | 
|Related Requirements   |    REQ1, REQ2, REQ3                    |
|Initiating Actors      |  Officer, Data base                    | 
|Actor's Goal           |   To add, remove and edit the customer profissions |
|Participatig Actors    |       Customer                                     |
|Preconditions          | To add new customer msut to open(add new customer), and also for remove and editing them must to open the customer list. |
|PostConditions         |  After inserting the cutomer details(for adding) or cha-nging them (for edit) the data will be stored in the database and will remove the removed customer. |
|Flow of events         |>> The officer will request the  list. for the registration, remove or changes. << The system wiil offer him/her the requested form. >> Officer will insert the requested elements and ok. << the system will save them in the data base and give a confirmation message. |

**UseCase2: Customer Registration**

|Title                  |  Description         |
| :------------         | :-----------         |
|Related Requirements   |      REQ1            |
|Initiating Actors      |   Officer, Data base |
|Actor's Goal           |    To add a new customer to the list.  |
|Participatig Actors    |  Customer            |
|Preconditions          |   A sequential customer Id number will be set to the new customer from the database.        |
|PostConditions         |  After inserting the cutomer details(for adding) the data will be stored in the database.   |
|Flow of events         |   >> The officer will request the  list. for the registration. << The system wiil offer him/her the requested form. >> Officer will insert the requested elements and ok. << the system will save them in the data base and give a confirmation message.  |

**UseCase 3: Customer Edition**

Title               |  Description            |
| :------------     | :-----------            |
|Related Requirements    |   REQ3               |
|Initiating Actors       |  Officer, Data base  | 
|Actor's Goal            |  To edit the added customer profissions from the list.    |
|Participatig Actors     |   Customer           |
|Preconditions           |  A list of customer must to be loaded from the data base  |
|PostConditions          |  After inserting the cutomer details(for adding) or cha-nging them (for edit) the data will be stored in the database and will remove the removed customer.|
|Flow of events          |  >> The officer will request the  list. for the  changes. << The system wiil offer him/her the requested form. >> Officer will insert the requested elements and ok. << the system will save them in the data base and give a confirmation message.    |

**UseCase 4: Customer Deletion**

| Title               |  Description         |
| :------------       | :-----------         |
|Related Requirements |  REQ2                |
|  Initiating Actors  |  Officer, Data base  |
|Actor's Goal         |  To remove any customer from the list.  |
|Participatig Actors  |  Customer                               |
|Preconditions        |  A list of customer must to be loaded from the data base |
|PostConditions       |  After the customer selection and deletion, the customer will be deleted from the lsit.   |
|Flow of events       |    >> The officer will request the  list. for the remove. << The system wiil offer him/her the requested form. >> Officer will insert the requested elements and ok. << the system will save them in the data base and give a confirmation message.      |

**UseCase 5 Customer Account**

|Title                 |  Description                    |
| :------------        | :-----------                    |
|Related Requirements  |  REQ4, REQ4.1,REQ4.2,REQ5,REQ6. |
|Initiating Actors     |   Officer and Database          |
|Actor's Goal          |  To manage the customer account like: payment, shopping, see the selected factor and company demand.  |
|Participatig Actors   |  Customer   |
|Preconditions         |   The system needs to load the customer account info from database (payment and shopping)   |
|PostConditions        |    The officer will change them by adding new subject.   |
|Flow of events        |  >> The officer will request the  list. for adding the payment and shopping. << The system wiil offer him/her the requested form. >> Officer will insert the requested elements and ok. << the system will save them in the data base and give a confirmation message.     |

###Sell Factor ####
**UseCase 1: Changes**

|  Title                |  Description           |
| :------------         | :-----------           |
|Related Requirements   |  REQ1, REQ2, REQ3      |
|Initiating Actors      |  Officer and DataBase  |
|Actor's Goal           |  To save the changes to the related database after the sell factor emission |
|Participatig Actors    |  Customer              |
|Preconditions          |  A customer, Stock and meterial list is required to use them in the factor, and a primay sold material list.   |
|PostConditions         |   Subtract from the stock list, or sum to the customer  account and sold material list.   |
|Flow of events         |  >> Officer will request for the factor. << system will offer it with the specified information. >> Officer will write the requested information and ok. << the system will save them in the database with a confirmation message and subtract them from the lists.   |

**Use Case 2: Characteristics**

|  Title               |  Description                                     |
| :------------        | :-----------                                    |
|Related Requirements  |  REQ4, REQ5, REQ6, REQ 7                         |
|Initiating Actors     |   System and Data base                           |
|Actor's Goal          |  o set the more information values to the factor |
|Participatig Actors   |                                                  |
|Preconditions         |   Loads specified sequential numbers from database. |
|PostConditions        |  Sets the new increamented factor numer to database |
|Flow of events        |                                                |

IBMS Main Diagrmas
---------------

![IBMS](http://i.imgur.com/eacYbKP.jpg "IBMS Use case Diagram")

**Customer account_usecase diagram**

![IBMS](http://i.imgur.com/0bAGiBs.jpg "Customer Account_Usecase Diagram")


**Customer Creation_Sequence Diagram**

![IBMS](http://i.imgur.com/5GtPFcS.jpg"Customer Creation_Sequence Diagram")


**Customer Deletion_activity Diagram**

![IBMS](http://i.imgur.com/047LvsX.jpg"Customer Deletion_activity Diagram")


**Customer Deletion_Sequence Diagram**

![IBMS](http://i.imgur.com/BRrR2KU.jpg"Customer Deletion_Sequence Diagram")


**Customer Edition_activity Diagram**

![IBMS](http://i.imgur.com/3pxJAGK.jpg"Customer Edition_activity Diagram")


**Customer Edition_Sequence Diagram**

![IBMS](http://i.imgur.com/CV6t1tA.jpg"Customer Edition_Sequence Diagram")


**Customer Management_Use case Diagram**

![IBMS](http://i.imgur.com/brGgYBt.jpg"Customer management_Use case Diagram")


**Customer Registration_activity Diagram**

![IBMS](http://i.imgur.com/fTPrqAw.jpg"Customer Registration_activity diagram")


**Diagram**

![IBMS](http://i.imgur.com/4N0Nbc7.jpg"Diagram")


**Invoice Changes_activity Diagram**

![IBMS](http://i.imgur.com/2EOK1of.jpg"Invoice Changes_activity diagram")


**Invoice Changes_Sequence Diagram**

![IBMS](http://i.imgur.com/uVTfyR5.jpg"Invoice Changes_Sequence Diagram")


**Invoice Changes_Use case Diagram**

![IBMS](http://i.imgur.com/jPBi7jN.jpg"Invoice Changes_Use case Diagram")


**Invoice Charactrictics_activity Diagram**

![IBMS](http://i.imgur.com/D5gQL9N.jpg"Invoice Charactrictics_activity diagram")
