package br.com.springboot_rest.dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.com.springboot_rest.dev.model.Usuario;
import br.com.springboot_rest.dev.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */ 
@RestController
public class GreetingsController {
	
	@Autowired //Injeção de dependência  
	private UsuarioRepository usuarioRepository;
	
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Dev SpringBoot API " + name + "!";
    }
    
    @RequestMapping(value = "/fulano/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaFulano(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	
    	usuarioRepository.save(usuario); //grava no BD
    	
    	return "Ola fulano " + nome;
    	
    }
    
    
    @GetMapping(value = "listatodos") //tem a mesma função do @RequestMapping só que mais enxuto
    @ResponseBody //Retorna os dados para o corpo da response JSON
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	//findAll retorna uma lista de usuario
    	List<Usuario> usuarios = usuarioRepository.findAll(); //executa a conulta no banco de dados
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); //Retornar a lista em JSON
    }
}
