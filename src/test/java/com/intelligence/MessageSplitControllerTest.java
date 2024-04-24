package com.intelligence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageSplitController.class)
public class MessageSplitControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testStoreMessage() throws Exception {
		String deviceId = "1";
		String jsonBody = "{ \"message\": [\"Parte 1\", \"Parte 2\"] }"; // Cuerpo JSON para la solicitud POST

		mockMvc.perform(post("/message_split/" + deviceId) // Realiza una solicitud POST al endpoint
						.contentType(MediaType.APPLICATION_JSON) // Tipo de contenido JSON
						.content(jsonBody)) // Cuerpo de la solicitud
				.andExpect(status().isOk()) // Verifica que el estado es OK (200)
				.andExpect(MockMvcResultMatchers.content().string("Mensaje almacenado")); // Verifica el mensaje de respuesta
	}

	@Test
	public void testGetReconstructedMessage() throws Exception {
		mockMvc.perform(get("/message_split/")) // Realiza una solicitud GET al endpoint
				.andExpect(status().isOk()); // Verifica que el estado es OK (200)
	}
}