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
//import org.springframework.web.bind.annotation.PutMapping;
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
    public ModelAndView avaliacoes(ModelMap model, @AuthenticationPrincipal Usuario usuario){
    	ModelAndView mv = new ModelAndView();
        model.addAttribute("usuario", usuario);
    	mv.setViewName("avaliacoes");
    	return mv;
    }

    @GetMapping("/agendamentos")
    public ModelAndView agendamentos(ModelMap model, @AuthenticationPrincipal Usuario usuario){
    	ModelAndView mv = new ModelAndView();

        List<Agendamento> agendamento = agendamentoRepository.findByUsuarioId(usuario.getId());
            
        mv.addObject("agendamento", agendamento);
        model.addAttribute("usuario", usuario);
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
            Prestador prestador = prestadorOptional.get();

            List<Servico> servico = servicoRepository.findByPrestadorId(prestador.getId());
            
            mv.addObject("servico", servico);
            model.addAttribute("prestador", prestadorOptional.get());
            model.addAttribute("usuario", usuario);
            mv.setViewName("perfilPrestador");
        }
        return mv;
    }
    
    /*
    @GetMapping("/atualizarPerfil/prestador")
    public ModelAndView atualizarPerfilP(){
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("atualizarPerfilPrestador");
        return mv;
    }

    @PutMapping("/atualizarPerfil/prestador")
    public ModelAndView atualizarPerfil(@Valid CadastroPrestador cadastroPrestador, @AuthenticationPrincipal Usuario usuario, BindingResult br) throws Exception {
    	ModelAndView mv = new ModelAndView("/meuPerfil");
    	if (br.hasErrors()) {
    		return mv;
    	}	    

    	Optional<Prestador> prestadorOptional = prestadorRepository.findByUsuarioId(usuario.getId());
    	Prestador prestador = prestadorOptional.get();
    	cadastroPrestador.setSenha(passwordEncoder.encode(cadastroPrestador.getSenha()));

        Usuario usuario1 = new Usuario(null, cadastroPrestador.getNome(), cadastroPrestador.getEmail(), cadastroPrestador.getSenha(), cadastroPrestador.getTelefone(), RoleType.PRESTADOR);
          
        prestador.setEndereco(cadastroPrestador.getEndereco());
        prestador.setBairro(cadastroPrestador.getBairro());
        prestador.setUsuario(usuario1);  

        System.out.println(prestador);

        prestadorRepository.save(prestador);
        return mv;
    }

    @GetMapping("/atualizarPerfil/consumidor")
    public ModelAndView atualizarPerfilC(){
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("atualizarPerfilPrestador");
        return mv;
    }

    @PutMapping("/atualizarPerfil/consumidor")
    public ModelAndView atualizarPerfil(@Valid CadastroConsumidor cadastroConsumidor, @AuthenticationPrincipal Usuario usuario, BindingResult br) throws Exception {
	ModelAndView mv = new ModelAndView("/meuPerfil");
	if (br.hasErrors()) {
		return mv;
	}	

    Optional<Consumidor> consumidorOptional = consumidorRepository.findById(usuario.getId());
    Consumidor consumidor = consumidorOptional.get();

    cadastroConsumidor.setSenha(passwordEncoder.encode(cadastroConsumidor.getSenha()));
        
    Usuario usuario1 = new Usuario(null, cadastroConsumidor.getNome(), cadastroConsumidor.getEmail(), cadastroConsumidor.getSenha(), cadastroConsumidor.getTelefone(), RoleType.CONSUMIDOR);;
        
    consumidor.setEndereco(cadastroConsumidor.getEndereco());
    consumidor.setUsuario(usuario1);
    consumidorRepository.save(consumidor);

    System.out.println(consumidor);
    return mv;
    }
    */

    @GetMapping("/novoAgendamento")
    public ModelAndView novoAgendamentos( @AuthenticationPrincipal Usuario usuario){
    	ModelAndView mv = new ModelAndView();
        mv.addObject("usuario", usuario);
    	mv.setViewName("formAgendamento");
    	return mv;
    }

    @PostMapping("/salvarAgendamento")
    public ModelAndView salvarAgendamento(@Valid CadastroAgendamento cadastroAgendamento, BindingResult br, @AuthenticationPrincipal Usuario usuario) throws Exception {
        ModelAndView mv = new ModelAndView("/novoAgendamento");
        if(br.hasErrors()){
            return mv;
        }
        
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getId());

        Agendamento agendamento = new Agendamento(cadastroAgendamento.getNome(),cadastroAgendamento.getTelefone(),cadastroAgendamento.getData(),cadastroAgendamento.getModalidade(),cadastroAgendamento.getEndereco(), usuarioOptional.get());
        System.out.println(agendamento);
        agendamentoRepository.save(agendamento);

        return new ModelAndView("redirect:/agendamentos");
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
    
    /*
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(HttpRequest request) {
    	
    }
    
    @PutMapping("atualizarPerfilPrestador")
    public ModelAndView atualizarPerfilPrestador(@RequestParam)
    */

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