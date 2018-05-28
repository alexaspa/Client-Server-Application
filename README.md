## Client Server Application
#### Description
Application that simulates the functions provided by the ATM interface with the bank. Communication is done via sockets and is based on client-server architecture. The client and the server only exchange objects.
The client application is able to make requests to create a new account, to withdraw and deposit funds into the account.
The bank server has an ArrayList to store account details. For each customer, the bank needs to know the customer's name, a unique account number that the bank automatically assigns to it when creating the account and its balance.

