package VingadoresDoYahoo.HoraMarcada.controllers;

import org.springframework.stereotype.Controller;


import VingadoresDoYahoo.HoraMarcada.repositories.*;

/*
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpSession;
import VingadoresDoYahoo.HoraMarcada.services.ServiceUsuario;
import VingadoresDoYahoo.HoraMarcada.util.Util;
import VingadoresDoYahoo.HoraMarcada.exceptions.ServiceExc;
import VingadoresDoYahoo.HoraMarcada.models.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
*/

import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Controller
//@RequestMapping("/home")
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ConsumidorRepository consumidorRepository;

    @Autowired
    PrestadorRepository prestadorRepository;

    /*
    @Autowired
    private ServiceUsuario usuarioService;
    */

    @GetMapping("/")
    public static ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/login")
    public static ModelAndView login(){
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/perfil")
    public static ModelAndView perfil(){
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("lista");
        return mv;
    }

/*
    @PostMapping("/logar")
    public ModelAndView login(@Validated Usuario usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceExc {
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", new Usuario());
        if(br.hasErrors()){
            mv.setViewName("/");
        }

        Usuario userLogin = usuarioService.loginUser(usuario.getEmail(), Util.md5(usuario.getSenha()));
        if(userLogin == null){
            mv.addObject("msg", "Usuário não econtrado.");
        }else{
            session.setAttribute("usuarioLogado", userLogin);
            return index();
        }
        return mv;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login");
        return mv;
    }
*/

}