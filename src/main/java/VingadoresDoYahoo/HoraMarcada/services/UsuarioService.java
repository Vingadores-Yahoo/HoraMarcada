package VingadoresDoYahoo.HoraMarcada.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import VingadoresDoYahoo.HoraMarcada.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String email){
        return Optional.ofNullable(usuarioRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
    }
    
}
