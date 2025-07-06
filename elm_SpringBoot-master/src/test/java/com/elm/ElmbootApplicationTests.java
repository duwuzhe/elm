package com.elm;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;


@SpringBootTest
@AutoConfigureMockMvc
class ElmbootApplicationTests {

	@Resource
	private MockMvc mockMvc;

	@Resource
	private ObjectMapper objectMapper;


	@Test
	public void testUserRegistrationAndLogin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"userId\": \"1111\", \"password\": \"1111111\"," +
								"\"userName\": \"小李\",\"userSex\": \"1\"}"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"));

		mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
						.contentType(MediaType.APPLICATION_JSON)
						.param("userId", "1111")
						.param("password", "1111111")
						.content("{\"userId\": \"1111\", \"password\": \"1111111\""))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"));

	}


	@Test
	public void testLoginAndLoadStoreList() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
						.contentType(MediaType.APPLICATION_JSON)
						.param("userId", "1111")
						.param("password", "1111111")
						.content("{\"userId\": \"1111\", \"password\": \"1111111\""))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"));

		mockMvc.perform(MockMvcRequestBuilders.get("/business/businessLists")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userId\": \"1111\", \"password\": \"1111111\""))

				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"));
	}

	@Test
	public void CartIntegrationTest1 () throws Exception {
		String token = "rx5U3s/FHky52JLSboL9cwPxi31cdcP66KG0D1gWAFucGS3vHo1GFJavP8HzUL/GXYlYIl5rNbh2cvT6ny0jqFxRfJtZDq2PPuW2GwJMWA2vrIp141aTcnwrIWHsn2P3vPrt06dYZ8Eu88Hi3Qr2N151DHOqt28nBqVOWgDJT50NNtlIRCqQQKstyibB2qqBz6zlvXWlHPBhW+WkPBJYF3D2tsnWS/PnXiNnvHOe2W8YcuTlEawcA5PNHFQYt6mlD60/yAh2hQFU3h510h35s4TCT124f/yEwEHvZ1bO27E=";
		mockMvc.perform(MockMvcRequestBuilders.post("/cart/newCarts")
						.contentType(MediaType.APPLICATION_JSON)
						.header("token", "rx5U3s/FHky52JLSboL9cwPxi31cdcP66KG0D1gWAFvN5DlKL2Z5WkUhA1sqg8wbpEUuCW+j4Z4cftV5gdsYaYk7/KJCy2+UTzGHuCWz6utz7pZ5OcUWD9KzbH5JpejJvPrt06dYZ8Eu88Hi3Qr2Nxgr8rxiQ8Q/EJdeqwNBcg4NNtlIRCqQQKstyibB2qqBqVwgo0BouGw69NztNH8IRBYyAtr0cbt/pnOklRl4jI4gy4IhF1mi1N3scDeHhKZCLiEYMWCCVFQQxC72ZycwBITCT124f/yEwEHvZ1bO27E=")
						.param("businessId", "10001")
						.param("foodId", "5")
						.content("{\"userId\": \"1111\", \"password\": \"1111111\""))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"));


		mockMvc.perform(MockMvcRequestBuilders.post("/cart/updated-carts")
						.contentType(MediaType.APPLICATION_JSON)
						.header("token", "r" + token)
						.param("businessId", "10001")
						.param("foodId", "5")
						.param("quantity", "3")
						.content("{\"userId\": \"1111\", \"password\": \"1111111\"}"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"));
	}


	@Test
	public void CartIntegrationTest2 () throws Exception {
		String token = "rx5U3s/FHky52JLSboL9cwPxi31cdcP66KG0D1gWAFucGS3vHo1GFJavP8HzUL/GXYlYIl5rNbh2cvT6ny0jqFxRfJtZDq2PPuW2GwJMWA2vrIp141aTcnwrIWHsn2P3vPrt06dYZ8Eu88Hi3Qr2N151DHOqt28nBqVOWgDJT50NNtlIRCqQQKstyibB2qqBz6zlvXWlHPBhW+WkPBJYF3D2tsnWS/PnXiNnvHOe2W8YcuTlEawcA5PNHFQYt6mlD60/yAh2hQFU3h510h35s4TCT124f/yEwEHvZ1bO27E=";
		mockMvc.perform(MockMvcRequestBuilders.post("/cart/newCarts")
						.contentType(MediaType.APPLICATION_JSON)
						.header("token", "rx5U3s/FHky52JLSboL9cwPxi31cdcP66KG0D1gWAFvN5DlKL2Z5WkUhA1sqg8wbpEUuCW+j4Z4cftV5gdsYaYk7/KJCy2+UTzGHuCWz6utz7pZ5OcUWD9KzbH5JpejJvPrt06dYZ8Eu88Hi3Qr2Nxgr8rxiQ8Q/EJdeqwNBcg4NNtlIRCqQQKstyibB2qqBqVwgo0BouGw69NztNH8IRBYyAtr0cbt/pnOklRl4jI4gy4IhF1mi1N3scDeHhKZCLiEYMWCCVFQQxC72ZycwBITCT124f/yEwEHvZ1bO27E=")
						.param("businessId", "10001")
						.param("foodId", "5")
						.content("{\"userId\": \"1111\", \"password\": \"1111111\""))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"));


		mockMvc.perform(MockMvcRequestBuilders.delete("/cart/removed-carts")
						.contentType(MediaType.APPLICATION_JSON)
						.header("token", "r" + token)
						.param("businessId", "10001")
						.param("foodId", "5")
						.content("{\"userId\": \"1111\", \"password\": \"1111111\"}"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"));

//
	}
	@Test
	public void OrderManagementIntegrationTest () throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/deliveryAddress/newDA")
						.contentType(MediaType.APPLICATION_JSON)
						.header("token", "rx5U3s/FHky52JLSboL9cwPxi31cdcP66KG0D1gWAFvN5DlKL2Z5WkUhA1sqg8wbpEUuCW+j4Z4cftV5gdsYaYk7/KJCy2+UTzGHuCWz6utz7pZ5OcUWD9KzbH5JpejJvPrt06dYZ8Eu88Hi3Qr2Nxgr8rxiQ8Q/EJdeqwNBcg4NNtlIRCqQQKstyibB2qqBqVwgo0BouGw69NztNH8IRBYyAtr0cbt/pnOklRl4jI4gy4IhF1mi1N3scDeHhKZCLiEYMWCCVFQQxC72ZycwBITCT124f/yEwEHvZ1bO27E=")
						.param("contactName", "杜源")
						.param("contactSex", "1")
						.param("contactTel", "15640938528")
						.param("address", "昆明市呈贡区云南大学呈贡校区")
						.content("{\"userId\": \"1111\", \"password\": \"1111111\""))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"));

		mockMvc.perform(MockMvcRequestBuilders.post("/orders/newOrders")
						.contentType(MediaType.APPLICATION_JSON)
						.header("token", "rx5U3s/FHky52JLSboL9cwPxi31cdcP66KG0D1gWAFvN5DlKL2Z5WkUhA1sqg8wbpEUuCW+j4Z4cftV5gdsYaYk7/KJCy2+UTzGHuCWz6utz7pZ5OcUWD9KzbH5JpejJvPrt06dYZ8Eu88Hi3Qr2Nxgr8rxiQ8Q/EJdeqwNBcg4NNtlIRCqQQKstyibB2qqBqVwgo0BouGw69NztNH8IRBYyAtr0cbt/pnOklRl4jI4gy4IhF1mi1N3scDeHhKZCLiEYMWCCVFQQxC72ZycwBITCT124f/yEwEHvZ1bO27E=")
						.param("businessId", "10001")
						.param("daId", "4")
						.param("orderTotal", "120")
						.content("{\"userId\": \"1111\", \"password\": \"1111111\""))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"));


		mockMvc.perform(MockMvcRequestBuilders.post("/orders/newStates")
						.contentType(MediaType.APPLICATION_JSON)
						.header("token", "rx5U3s/FHky52JLSboL9cwPxi31cdcP66KG0D1gWAFvN5DlKL2Z5WkUhA1sqg8wbpEUuCW+j4Z4cftV5gdsYaYk7/KJCy2+UTzGHuCWz6utz7pZ5OcUWD9KzbH5JpejJvPrt06dYZ8Eu88Hi3Qr2Nxgr8rxiQ8Q/EJdeqwNBcg4NNtlIRCqQQKstyibB2qqBqVwgo0BouGw69NztNH8IRBYyAtr0cbt/pnOklRl4jI4gy4IhF1mi1N3scDeHhKZCLiEYMWCCVFQQxC72ZycwBITCT124f/yEwEHvZ1bO27E=")
						.param("orderId", "100")
						.param("orderState", "1")
						.content("{\"userId\": \"1111\", \"password\": \"1111111\""))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"));


	}





}
