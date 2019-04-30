# spring-boot-data-jpa-postgresql


1.Set your data base details in application.properties 

#PostgreSql

spring.datasource.url= jdbc:postgresql://localhost:5432/local
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
#server port by default 8080 if you want me can use our own port

server.port=1234

#creates tables automatically

spring.jpa.generate-ddl=true

2.mvn clean compile install

3.Run DemoApplication

4.Save Products Data alog with Department data 

POST:http://localhost:1234/products/save

	Request:
	{

	"productId":"1",
	"productName":"prevencia",
	"productDescription":"lens",
	"department":{
	  "departmentId":"102",
	  "departmentName":"manufactoring",
	  "departmentDescription":"manufactoring in a lab"
	  }
	}

5.select productdata

Request:http://localhost:1234/products?product_id=1

    Response:
    {
    "productId": 1,
    "productName": "prevencia",
    "productDescription": "lens",
      "department": {
      "departmentId": 102,
      "departmentName": "manufactoring",
      "departmentDescription": "manufactoring in a lab"
      }
    }
		

6.see the data which is saved in data base through 

Post Request

http://localhost:1234/departments/save

	{
	  "departmentId":104,
	  "departmentName":"manufactoring",
	  "departmentDescription":"manufactoring in a lab"
	 }
 
7.Get:http://localhost:1234/departments?department_id=102

Response:

	{
	departmentId: 102,
	departmentName: "manufactoring",
	departmentDescription: "manufactoring in a lab",

	    products: [
		{
		productId: 2,
		productName: "prevencia",
		productDescription: "lens"
		},
		{
		productId: 1,
		productName: "prevencia",
		productDescription: "lens"
		}
	    ]
	}
