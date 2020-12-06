# Ergo clients API


### How to run the application
Build jar file by executing this command:  
`mvn install` 

Let's run this command from the same folder:   
`java -jar target/clients-0.0.1-SNAPSHOT.jar com.ergo.clients.ClientsApplication`


### Test the application    
POST http://localhost:8085/api/clients   
{  
    "firstName" : "Name 1",  
    "lastName" : "Surname 1",  
    "birthDate": "15-08-1991",  
    "gender": "Male"  
}  
GET  http://localhost:8085/api/clients/  
GET  http://localhost:8085/api/clients/?startDate=10-08-1991&endDate=18-09-1991   
GET  http://localhost:8085/api/clients/1  

### Some more details about the implementation
Open API documentation could be access by this link  
<a href="http://localhost:8085/swagger-ui.html" target="_blank">http://localhost:8085/swagger-ui.html</a>  

OpenAPI definition:  
<a href="http://localhost:8085/api-docs" target="_blank">http://localhost:8085/api-docs</a> 


Data base could be accessed by this link:      
<a href="http://localhost:8085/h2-console/" target="_blank">http://localhost:8085/h2-console/</a>  
Data base connection details:  
JDBC Url: jdbc:h2:mem:clients  
user: sa  
password: sa  