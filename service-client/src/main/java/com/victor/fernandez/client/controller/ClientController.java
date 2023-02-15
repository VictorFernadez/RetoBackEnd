package com.victor.fernandez.client.controller;

import com.victor.fernandez.client.entity.Client;
import com.victor.fernandez.client.service.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/client")
public class ClientController {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ClientService service;

    @GetMapping
    @ApiOperation(value = "Muestra todos los clientes", response = Client.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message ="Successfully retrieved list") })
    public List<Client> findAll(){
        log.info("Ejecuta findAll");
        return service.findAll();
    }

    @GetMapping("/{codigoUnico}")
    public ResponseEntity<?> findByCodigoUnico(@PathVariable("codigoUnico") String codigoUnico){
        log.info("Ejecuta findByCodigoUnico con codigoUnico {}", codigoUnico);
        Optional<Client> client = service.findByCodigoUnico(codigoUnico);
        if(client.isPresent()){
            log.info("Encontró al cliente con codigoUnico {}", codigoUnico);
            return ResponseEntity.ok(client.get());
        }
        log.warn("No encontró al cliente con codigoUnico {}", codigoUnico);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/detail/{numDocumento}")
    public ResponseEntity<?> findByNumeroDocumento(@PathVariable("numDocumento") String numDocumento) throws UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        log.info("Ejecuta el método findByNumeroDocumento con numDocumento {}", numDocumento);
        Optional<Client> client = service.findByNumeroDocumento(numDocumento);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Client client) throws UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        log.info("Ejecuta el método save");
        return new ResponseEntity<Client>(service.save(client),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody Client client) throws UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        log.info("Ejecuta el método update para el cliente con id {}", id);
        return new ResponseEntity<Client>(service.update(id,client),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable("id") Long id){
        log.info("Ejecuta el método delete para el cliente con id {}",id );
        Optional<Client> optionalClient = service.findById(id);
        if(optionalClient.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
