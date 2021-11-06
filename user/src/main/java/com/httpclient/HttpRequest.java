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

	// public void addAccount(AddAccountInputModel inputModel) throws IOException {
	public String addAccount(AddAccountInputModel inputModel) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://transaction:8080/api/account");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            /*
            String json = "{\r\n" +
                "  \"userId\": \"0\",\r\n" +
                "  \"accountName\": \"User\"\r\n" +
                "}";
            */
            
            ObjectMapper Obj = new ObjectMapper();  
            
            String json = Obj.writeValueAsString(inputModel);  
            
            // String json = inputModel.toString();
            StringEntity stringEntity = new StringEntity(json);
            httpPost.setEntity(stringEntity);

            // System.out.println("Executing request " + httpPost.getRequestLine());

            // Create a custom response handler
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
            
            // System.out.println(responseBody);
            
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

            // Create a custom response handler
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

            // Create a custom response handler
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
