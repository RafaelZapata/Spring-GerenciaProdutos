package bsi.springWeb.trabalhop2.security;

import bsi.springWeb.trabalhop2.model.Permissao;
import bsi.springWeb.trabalhop2.model.Usuario;
import bsi.springWeb.trabalhop2.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class MeuUsuarioService implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String login){
        Usuario user = repo.findByLogin(login);
        return new User(user.getLogin(),
                        user.getSenha(),
                        getAuthorities(user.getPermissoes()));
    }
    
    private List<GrantedAuthority> getAuthorities(List<Permissao> permissoes){
        List<GrantedAuthority> lista =  new ArrayList<>();
        for(Permissao p : permissoes){
            lista.add(new SimpleGrantedAuthority("ROLE_"+p.getNome()));
        }
        return lista;
    }
    
}