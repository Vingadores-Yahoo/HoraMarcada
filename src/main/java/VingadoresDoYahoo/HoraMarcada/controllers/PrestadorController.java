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
//import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
public class PrestadorController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ConsumidorRepository consumidorRepository;

    @Autowired
    PrestadorRepository prestadorRepository;

    //@GetMapping()
    //public String listaPrestadores(Model model) {
     //   return "lista";
    //}


    @GetMapping("/cadastroPrestador")
    public ModelAndView novoPrestador(){
        
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/cadastroPrestador");
    	return mv;
    }


    @PostMapping(path = "/cadastrarPrestador")
    public String salvarPrestador(@Validated CadastroPrestador cadastroPrestador, BindingResult result) throws Exception {
        try{
            if(result.hasErrors()){
                return "/cadastroPrestador";
            }
            if(usuarioRepository.findByEmail(cadastroPrestador.getEmail()) != null){
                throw new EmailExistsException("Email já cadastrado: " + cadastroPrestador.getEmail());
            }

            cadastroPrestador.setSenha(Util.md5(cadastroPrestador.getSenha()));

        } catch (NoSuchAlgorithmException e){

            throw new CriptoExitsException("Erro na criptografia da senha");

        }
        
        Usuario usuario = new Usuario(cadastroPrestador.getNome(), cadastroPrestador.getEmail(), cadastroPrestador.getSenha(), cadastroPrestador.getTelefone(), RoleType.PRESTADORSERVICO);
        Prestador prestador = new Prestador(cadastroPrestador.getEndereco(),cadastroPrestador.getBairro(),cadastroPrestador.getCidade(),cadastroPrestador.getEstado(), usuario);
        System.out.println(prestador);

        prestadorRepository.save(prestador);
        return "redirect: /index";
    
    }
} 