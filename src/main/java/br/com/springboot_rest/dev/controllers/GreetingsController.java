package br.com.springboot_rest.dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    /*Mapear a url na requisição verbo Post */
    @PostMapping(value = "salvar")
    /*Retornará da descrição resposta */
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){ //Recebe os dados para salvar
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    
    
    
    /*Mapear a url na requisição verbo Delete */
    @DeleteMapping(value = "delete")
    /*Retornará da descrição resposta */
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){ //Recebe os dados para salvar
    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
    	
    }
    
    
    /*Mapear a url na requisição verbo Get */
    @GetMapping(value = "buscaruserid")
    /*Retornará da descrição resposta */
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserid(@RequestBody @RequestParam(name = "iduser") Long iduser){ //Recebe os dados para consultar
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	
    }
    
    
    /*Mapear a url na requisição verbo Post */
    @PutMapping(value = "atualizar")
    /*Retornará da descrição resposta */
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){ //Recebe os dados para salvar
    	
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não foi informado.", HttpStatus.OK);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario); //atualizar
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
    
    
    
    /*Mapear a url na requisição verbo Get */
    @GetMapping(value = "buscarPorNome")
    /*Retornará da descrição resposta */
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){ //Recebe os dados para consultar
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    	
    }
}
