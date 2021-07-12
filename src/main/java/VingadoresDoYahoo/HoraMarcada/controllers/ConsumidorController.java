package VingadoresDoYahoo.HoraMarcada.controllers;

import VingadoresDoYahoo.HoraMarcada.repositories.*;
import VingadoresDoYahoo.HoraMarcada.util.Util;
import VingadoresDoYahoo.HoraMarcada.exceptions.*;
import VingadoresDoYahoo.HoraMarcada.models.*;

import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller

public class ConsumidorController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ConsumidorRepository consumidorRepository;

    @Autowired
    PrestadorRepository prestadorRepository;

//    @GetMapping()
//    public ModelAndView lista(){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("/lista");
//        return mv;
//    }


    @GetMapping("/cadastroConsumidor")
    public ModelAndView novoConsumidor(){
    	
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/cadastroConsumidor");
        return mv;
    }



    @PostMapping(path = "/cadastroConsumidor")
    public String salvarConsumidor(@Validated CadastroConsumidor cadastroConsumidor, BindingResult result) throws Exception {
        try{
            if(result.hasErrors()){
                return "consumidor/cadastroConsumidor";
            }
            if(usuarioRepository.findByEmail(cadastroConsumidor.getEmail()) != null){
                throw new EmailExistsException("Email já cadastrado: " + cadastroConsumidor.getEmail());
            }

            cadastroConsumidor.setSenha(Util.md5(cadastroConsumidor.getSenha()));

        } catch (NoSuchAlgorithmException e){

            throw new CriptoExitsException("Erro na criptografia da senha");

        }
        
        Usuario usuario = new Usuario(cadastroConsumidor.getNome(), cadastroConsumidor.getEmail(), cadastroConsumidor.getSenha(), cadastroConsumidor.getTelefone(), RoleType.CONSUMIDOR);
        Consumidor consumidor = new Consumidor(cadastroConsumidor.getEndereco(), usuario);

        System.out.println(consumidor);

        consumidorRepository.save(consumidor);
        return "/index";
    
    }
}