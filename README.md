# Run 2 Services of a Spring Boot Application (Account Domain) using H2 Database in Different Containers and Communicate through REST API 

## Domain
`Account`

## Services
`User Service`
`Transaction Service`

## H2 Database Configurations in _application.properties_ files 

### User Service
    spring.datasource.url=jdbc:h2:mem:users
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=
 
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
    spring.jpa.hibernate.ddl-auto= update

    spring.h2.console.enabled=true
    spring.h2.console.settings.web-allow-others=true
    
    spring.h2.console.path=/h2-ui

### Tranasction Service
    spring.datasource.url=jdbc:h2:mem:transactions
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=

    server.port=8081
 
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
    spring.jpa.hibernate.ddl-auto= update

    spring.h2.console.enabled=true
    spring.h2.console.settings.web-allow-others=true

    spring.h2.console.path=/h2-ui

### Build Services  

MacOS/Linux:  
`./mvnw spring-boot:run`.    

Windows:  
`mvnw spring-boot:run` 

## Check Databases

### User Service
`http://localhost:8080/h2-ui`

### Transaction Service
`http://localhost:8081/h2-ui`

## Using Swagger to Create API Documentations

### Including Swagger Maven dependencies in pom.xml

    <dependency>
    	<groupId>io.springfox</groupId>
    	<artifactId>springfox-swagger2</artifactId>
    	<version>3.0.0</version>
	</dependency>

    <dependency>
    	<groupId>io.springfox</groupId>
    	<artifactId>springfox-boot-starter</artifactId>
    	<version>3.0.0</version>
	</dependency>
	
	<dependency>
    	<groupId>io.springfox</groupId>
    	<artifactId>springfox-swagger-ui</artifactId>
    	<version>2.9.2</version>
	</dependency>
	
### Integrating Swagger 2 Into the Project

    @Configuration
        public class SpringFoxConfig {                                    
        @Bean
        public Docket api() { 
            return new Docket(DocumentationType.SWAGGER_2)  
            .select()                                  
            .apis(RequestHandlerSelectors.any())              
            .paths(PathSelectors.any())                          
            .build();                                           
        }
    }
    
### Swagger Verification

#### User Service
`http://localhost:8080/v3/api-docs`

#### Transaction Service
`http://localhost:8081/v3/api-docs`

### Swagger UI

#### User Service
`http://localhost:8080/swagger-ui/`

#### Transaction Service
`http://localhost:8081/swagger-ui/`


## Using Apache Http Client for Communication between the 2 Services through REST API 

### Including Http Client Maven dependencies in pom.xml file of User Service Project

    <dependency>
    	<groupId>org.apache.httpcomponents</groupId>
    	<artifactId>httpclient</artifactId>
    	<version>4.5</version>
	</dependency>
		
	<dependency>  
     	<groupId>com.fasterxml.jackson.core</groupId>  
     	<artifactId>jackson-databind</artifactId>  
     	<version>2.12.1</version>  
	</dependency>  
	
### Complete Code for POST and PUT Methods in HttpRequest.java file in User Service Project

    package com.httpclient;

    import java.io.IOException;

    import org.apache.http.HttpEntity;
    import org.apache.http.client.ClientProtocolException;
    import org.apache.http.client.ResponseHandler;
    import org.apache.http.client.methods.HttpPost;
    import org.apache.http.client.methods.HttpPut;
    import org.apache.http.entity.StringEntity;
    import org.apache.http.impl.client.CloseableHttpClient;
    import org.apache.http.impl.client.HttpClients;
    import org.apache.http.util.EntityUtils;

    import com.user.model.AddAccountInputModel;
    import com.user.model.DeactivateAccountInputModel;
    import com.user.model.UpdateAccountInputModel;
    import com.fasterxml.jackson.databind.ObjectMapper;  

    public class HttpRequest {

	    public String addAccount(AddAccountInputModel inputModel) throws IOException {
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpPost httpPost = new HttpPost("http://transaction:8080/api/account");
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
            
                ObjectMapper Obj = new ObjectMapper();  
            
                String json = Obj.writeValueAsString(inputModel);  
            
                StringEntity stringEntity = new StringEntity(json);
                httpPost.setEntity(stringEntity);

                ResponseHandler < String > responseHandler = response -> {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                };

                String responseBody = httpclient.execute(httpPost, responseHandler);
            
                return responseBody;
            }
        }
	
	    public String updateAccount(UpdateAccountInputModel inputModel) throws IOException {
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpPut httpPut = new HttpPut("http://transaction:8080/api/account");
                httpPut.setHeader("Accept", "application/json");
                httpPut.setHeader("Content-type", "application/json");
            
                ObjectMapper Obj = new ObjectMapper();  
            
                String json = Obj.writeValueAsString(inputModel);  
            
                StringEntity stringEntity = new StringEntity(json);
                httpPut.setEntity(stringEntity);

                ResponseHandler < String > responseHandler = response -> {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                };
                String responseBody = httpclient.execute(httpPut, responseHandler);
         
                return responseBody;
            }
        }
	
	    public String deactivateAccount(DeactivateAccountInputModel inputModel) throws IOException {
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpPut httpPut = new HttpPut("http://transaction:8080/api/account/deactive");
                httpPut.setHeader("Accept", "application/json");
                httpPut.setHeader("Content-type", "application/json");
            
                ObjectMapper Obj = new ObjectMapper();  
            
                String json = Obj.writeValueAsString(inputModel);  
            
                StringEntity stringEntity = new StringEntity(json);
                httpPut.setEntity(stringEntity);

                ResponseHandler < String > responseHandler = response -> {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                };
                String responseBody = httpclient.execute(httpPut, responseHandler);
         
                return responseBody;
            }
        }
    }

## Running the Services in Docker Containers

### Building Service Images

#### User Service
`docker build -t user-service .`

#### Transaction Service
`docker build -t transaction-service .`

### Establishing Network between the 2 Services using Docker Compose

Create __docker-compose.yml__ File in User Service Project Root Directory
    
    version: '3'

    services: 

    user:
        image: user-service
        networks:
            - account
        depends_on:
            - transaction
        ports:
            - 8080:8080

    transaction:
        image: transaction-service
        networks:
            - account
        ports:
            - 8081:8080

    networks:
        account:

### Run both the Containers establishing Network between them

`docker-compose up -d`        

### Test the Services

Use the APIs from Swagger UI to Test the Services 


### ### An issue running the Services in Container
The Services can't be run in Container Ports other than Port: 8080
