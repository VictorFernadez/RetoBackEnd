package com.victor.fernandez.client.service.impl;

import com.victor.fernandez.client.entity.Client;
import com.victor.fernandez.client.repository.ClientRepository;
import com.victor.fernandez.client.service.ClientService;
import com.victor.fernandez.client.util.EncriptadorAES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);
    @Value("${secret.key}")
    String claveEncriptacion;
    @Autowired
    private ClientRepository repository;


    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        log.info("Ejecuta el método {} findAll");
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findByCodigoUnico(String codigoUnico) {
        log.info("Ejecuta el método findByCodigoUnico");
        return repository.findByCodigoUnico(codigoUnico);
    }
    @Override
    @Transactional
    public Client save(Client client) throws UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        log.info("Ejecuta el método save : {}", client.toString());
        EncriptadorAES encriptador = new EncriptadorAES();
        String encriptado = encriptador.encriptar(client.getCodigoUnico(), claveEncriptacion);
        Optional<Client> findByCodigoUnico = findByCodigoUnico(encriptado);
        if(findByCodigoUnico.isPresent()){
            log.error("Método save () - error : Cliente {} ya existe",client.toString());
            throw new ResponseStatusException(HttpStatus.CONFLICT,String.format("Cliente %s already exists",client.getNombres()));
        }else{
            client.setCodigoUnico(encriptador.encriptar(client.getCodigoUnico(), claveEncriptacion));
            log.info("Guarda al cliente");
            return repository.save(client);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findByNumeroDocumento(String numDocu) throws UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        log.info("Ejecuta el método findByNumeroDocumento");
        Optional<Client> client = repository.findByNumeroDocumento(numDocu);

        if(client.isPresent()){
            log.info("Existe el cliente con número : {}", numDocu);
            EncriptadorAES encriptador = new EncriptadorAES();
            String cadenaEncriptada = client.get().getCodigoUnico();
            String desencriptado = encriptador.desencriptar(cadenaEncriptada, claveEncriptacion);
            client.get().setCodigoUnico(desencriptado);

        }else{
            log.error("Número de documento {} no existe en la BD", numDocu);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("NumDocum no existe %s",numDocu));
        }

        return client;

    }

    @Override
    @Transactional
    public Client update(Long id, Client client) throws UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        log.info("Ejecuta el método update con id {}", id);
        EncriptadorAES encriptador = new EncriptadorAES();
        String encriptado = encriptador.encriptar(client.getCodigoUnico(), claveEncriptacion);
        System.out.println(encriptado);
        Client clientDB = null;
        Optional<Client> clientOptional = repository.findById(id);
        if(clientOptional.isPresent()){
            log.info("Existe el cliente en la BD");
            clientDB= clientOptional.get();
            clientDB.setNombres(client.getNombres());
            clientDB.setApellidos(client.getApellidos());
            clientDB.setNumeroDocumento(client.getNumeroDocumento());
            clientDB.setTipoDocumento(client.getTipoDocumento());
            clientDB.setCodigoUnico(encriptado);
        }else{
            log.error("No se encuentra el cliente {} con el id {} en la BD", id,client.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("NumDocum no existe %s",client.getNombres()));
        }
        return repository.save(clientDB);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Ejecuta el método delete para el cliente con id {}", id);
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findById(Long id) {
        log.info("Ejecuta el método findById para el cliente con id {}", id);
       return repository.findById(id);
    }
}
