package com.victor.fernandez.client;

import com.victor.fernandez.client.entity.Client;
import com.victor.fernandez.client.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceClientApplicationTests {
	@Autowired
	private ClientRepository repository;

	@Test
	void testGuardarCliente() {
		Client  client = new Client(1L,"vfq3030","Victor","Fernández","DNI","75823025");
		repository.save(client);

		Client clientGuardado = repository.findById(client.getId()).orElse(null);
		assertNotNull(clientGuardado);
		assertEquals(client.getNombres(), clientGuardado.getNombres());
		assertEquals(client.getApellidos(), clientGuardado.getApellidos());
	}

	@Test
	void testActualizarCliente() {
		Client  client = new Client(1L,"vfq3030","Victor","Fernández","DNI","75823025");
		repository.save(client);

		Client clientActualizado = new Client(1L,"vfq3030","Victor2","Fernández Quintana","DNI","75823026");
		clientActualizado.setId(client.getId());
		repository.save(clientActualizado);

		Client clientEncontrado = repository.findById(client.getId()).orElse(null);
		assertNotNull(clientEncontrado);
		assertEquals(clientActualizado.getNombres(), clientEncontrado.getNombres());
		assertEquals(clientActualizado.getApellidos(), clientEncontrado.getApellidos());
	}

	@Test
	public void testEliminarCliente() {
		Client  client = new Client(1L,"vfq3030","Victor","Fernández","DNI","75823025");
		repository.save(client);

		repository.deleteById(client.getId());

		Client clientEncontrado = repository.findById(client.getId()).orElse(null);
		assertNull(clientEncontrado);
	}

}
