package VingadoresDoYahoo.HoraMarcada.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import VingadoresDoYahoo.HoraMarcada.models.Agendamento;
import VingadoresDoYahoo.HoraMarcada.models.Consumidor;
import VingadoresDoYahoo.HoraMarcada.models.Modalidade;
import VingadoresDoYahoo.HoraMarcada.models.Prestador;
import VingadoresDoYahoo.HoraMarcada.models.RoleType;
import VingadoresDoYahoo.HoraMarcada.models.Servico;
import VingadoresDoYahoo.HoraMarcada.models.Usuario;
import VingadoresDoYahoo.HoraMarcada.repositories.AgendamentoRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.ConsumidorRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.PrestadorRepository;
import VingadoresDoYahoo.HoraMarcada.repositories.ServicoRepository;
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

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /*
    @Autowired
    private ServiceUsuario usuarioService;
    */

    @GetMapping("/")
    public ModelAndView index(@RequestParam(required = false) String modalidade) {
        List<Servico> servicos = null;
        try {
            servicos = servicoRepository.findAllByModalidade(Modalidade.valueOf(modalidade));
        } catch (Exception e) {
            servicos = servicoRepository.findAll();
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("servicos", servicos);
        mv.addObject("modalidades", Modalidade.values());
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login(){
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

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
        
        Agendamento agendamento = new Agendamento(null,cadastroAgendamento.getNome(),cadastroAgendamento.getTelefone(),cadastroAgendamento.getData(),cadastroAgendamento.getModalidade(),cadastroAgendamento.getEndereco(), null);
        System.out.println(agendamento);
        agendamentoRepository.save(agendamento);

        ModelAndView mb = new ModelAndView("/agendamentos");
        return mb;
    }

    @GetMapping("/cadastroPrestador")
    public ModelAndView novoPrestador(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("cadastroPrestador");
    	return mv;
    }
    
    @PostMapping("/cadastroPrestador")
    public ModelAndView salvarPrestador(@Valid CadastroPrestador cadastroPrestador, BindingResult br) throws Exception {
        ModelAndView mv = new ModelAndView("/cadastroPrestador");
        if(br.hasErrors()){
            return mv;
        }
        if(usuarioRepository.findByEmail(cadastroPrestador.getEmail()) != null){
            mv.addObject("mensagem","E-mail já cadastrado");
            return mv;
        }

        cadastroPrestador.setSenha(passwordEncoder.encode(cadastroPrestador.getSenha()));
        
        Usuario usuario = new Usuario(null, cadastroPrestador.getNome(), cadastroPrestador.getEmail(), cadastroPrestador.getSenha(), cadastroPrestador.getTelefone(), RoleType.PRESTADOR);
        Prestador prestador = new Prestador(cadastroPrestador.getEndereco(),cadastroPrestador.getBairro(), usuario);
        System.out.println(prestador);

        prestadorRepository.save(prestador);
        
        return login();
    }

    @GetMapping("/cadastroConsumidor")
    public ModelAndView novoConsumidor(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("cadastroConsumidor");
        return mv;
    }

    @PostMapping("/cadastroConsumidor")
    public ModelAndView salvarConsumidor(@Valid CadastroConsumidor cadastroConsumidor, BindingResult br) throws Exception {
        ModelAndView mv = new ModelAndView("/cadastroConsumidor");
        if(br.hasErrors()){
            return mv;
        }
        if(usuarioRepository.findByEmail(cadastroConsumidor.getEmail()) != null){
            mv.addObject("mensagem","E-mail já cadastrado");
            return mv;
        }

        cadastroConsumidor.setSenha(passwordEncoder.encode(cadastroConsumidor.getSenha()));
        
        Usuario usuario = new Usuario(null, cadastroConsumidor.getNome(), cadastroConsumidor.getEmail(), cadastroConsumidor.getSenha(), cadastroConsumidor.getTelefone(), RoleType.CONSUMIDOR);
        Consumidor consumidor = new Consumidor(cadastroConsumidor.getEndereco(), usuario);

        System.out.println(consumidor);

        consumidorRepository.save(consumidor);

        return login();
    }
}