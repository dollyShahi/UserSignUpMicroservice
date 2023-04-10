package com.user;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

@SpringBootTest
class UserSignUpMicroserviceApplicationTests {
	

	
	@Test
	void contextLoads() {
	}


	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		
		String jsonBody="{\"email_id\":\"hasmat@gmail.com\",\"full_name\":\"Akash Paul\",\"phone_number\":\"9679297493\",\"adress\":\"guma\",\"pin\":\"743704\",\"password\":\"123\"}";
		 String status=   given()
		         .header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
		         .body(jsonBody)
		         .when()
		         .post("http://localhost:8008/registration").asString();
		       
		 assertEquals(status, "Added");
		   
//		   
//		 mvc.perform( MockMvcRequestBuilders
//			      .post("/registration")
//			      .content(asJsonString(new Model("akashpaul092@gmailcom","Akash Paul","9679297493","Guma","743704","123")))
//			      .contentType(ContentType.APPLICATION_JSON)
//			      .accept(MediaType.APPLICATION_JSON))
//		      .andExpect(status().isCreated())
//		      .andExpect(MockMvcResultMatchers.jsonPath("$.email_id").exists());
	}

	
}
