Intuit Test
==========

Problem Statement:
==========

Implement a service that fetches and stores “User Profile” information.
·        POST API: Given a list of 5 street addresses (as below), obtain the postal code using Google Maps Geocoding API. The API should return the user names along with complete addresses of the users (including postal code).
o   Name: Dominick Desoto
Address: 2700 Coast Ave, Mountain View CA
o   Name: Lakendra Mcandrew
Address: 5601 Headquarters Drive, Plano Texas
o   Name: Scottie Abraham
Address: 7535 Torrey Santa Fe Road San Diego, CA
o   Name: Alphonse Heiss
Address: 21215 Burbank Boulevard Woodland Hills, CA
o   Name: Chan Pullman
Address: 2800 E. Commerce Center Place Tucson, AZ
·        GET API: Given a postal code, return all the users in that postal code (The API should return user name along with complete address for that postal code)

Assumptions
==========

Handle duplicates,
No validation of input : firstname, lastname, address can be blanks
If zipcode was not found, or an error was encountered, the input record will be stored in a zipcode 00000


How it works
------------
it is built using https://spring.io/guides/gs/spring-boot/ framework.  Very easy to build and run in any environment.
Redis needs to be running locally at port 7777.
Ass

How to Use it
-------------
run ./run_intuit_test

POST Api:
POST : http://127.0.0.1:8080/user/profiles
payload : [{"firstName":"Biren","lastName":"Barodia","address":"701 First Avenue, Sunnyvale"}]
response : [{"firstName":"Biren","lastName":"Barodia","address":"701 First Avenue, Sunnyvale", "zipCode" :94089}]

GET Api:
POST : http://127.0.0.1:8080/94089/user/profiles
response : [{"firstName":"Biren","lastName":"Barodia","address":"701 First Avenue, Sunnyvale", "zipCode" :94089}]
