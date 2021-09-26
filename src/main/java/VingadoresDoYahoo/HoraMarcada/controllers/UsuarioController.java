package VingadoresDoYahoo.HoraMarcada.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import VingadoresDoYahoo.HoraMarcada.models.Agendamento;
import VingadoresDoYahoo.HoraMarcada.models.Prestador;
import VingadoresDoYahoo.HoraMarcada.models.Usuario;
import VingadoresDoYahoo.HoraMarcada.repositories.AgendamentoRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.ConsumidorRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.PrestadorRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.UsuarioRepository;


@Controller
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ConsumidorRepository consumidorRepository;

    @Autowired
    PrestadorRepository prestadorRepository;

    @Autowired
    AgendamentoRepository agendamentoRepository;

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
/*
    @GetMapping("/perfilPrestador")
    public static ModelAndView perfilPrestador(ModelMap model, @AuthenticationPrincipal Usuario usuario){
        System.out.println(usuario);
        model.addAttribute("usuario", usuario);
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("perfilPrestador");
        return mv;
    }

    @GetMapping("/avaliacoes")
    public static ModelAndView avaliacoes(ModelMap model, @AuthenticationPrincipal Usuario usuario){
        System.out.println(usuario);
        model.addAttribute("usuario", usuario);
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("avaliacoes");
        return mv;
    }
    */

    @GetMapping("/avaliacoes")
    public ModelAndView avaliacoes(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("avaliacoes");
    	return mv;
    }

    @GetMapping("/agendamentos")
    public ModelAndView agendamentos(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("agendamentos");
    	return mv;
    }

    @GetMapping("/meuPerfil")
    public ModelAndView perfilCorreto(ModelMap model, @AuthenticationPrincipal Usuario usuario){
        ModelAndView mv = new ModelAndView();
        if(usuario.getRole().name() == "CONSUMIDOR"){
            model.addAttribute("usuario", usuario);
            mv.setViewName("perfilConsumidor");
        }else{
            Optional<Prestador> prestadorOptional = prestadorRepository.findByUsuarioId(usuario.getId());
            model.addAttribute("prestador", prestadorOptional.get());
            model.addAttribute("usuario", usuario);
            mv.setViewName("perfilPrestador");
        }
        return mv;
    }

    @GetMapping("/novoAgendamento")
    public ModelAndView novoAgendamentos(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("formAgendamentos");
    	return mv;
    }

    @PostMapping("/salvarAgendamento")
    public ModelAndView salvarAgendamento(@Valid CadastroAgendamento cadastroAgendamento, BindingResult br) throws Exception {
        ModelAndView mv = new ModelAndView("/novoAgendamento");
        if(br.hasErrors()){
            return mv;
        }
        
        Agendamento agendamento = new Agendamento(null,cadastroAgendamento.getCliente(),cadastroAgendamento.getData(),cadastroAgendamento.getModalidade(),cadastroAgendamento.getEndereco(), null);
        System.out.println(agendamento);
        agendamentoRepository.save(agendamento);

        ModelAndView mb = new ModelAndView("/agendamentos");
        return mb;
    }
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
*/