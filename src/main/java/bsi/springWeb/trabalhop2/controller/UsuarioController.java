package bsi.springWeb.trabalhop2.controller;

import bsi.springWeb.trabalhop2.model.Permissao;
import bsi.springWeb.trabalhop2.model.Usuario;
import bsi.springWeb.trabalhop2.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repo;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("lista", repo.findAll());
        return "listarUsuario";
    }
    
    @GetMapping("/{id}")
    public String getOne(@PathVariable("id") Long id,Model model){
        model.addAttribute(repo.findById(id));
        return "listarUsuario";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        repo.deleteById(id);
        return "redirect:../../usuarios";
    }
    
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model){
        Optional<Usuario> user = repo.findById(id);
        if(user.isPresent()){
            model.addAttribute("usuario", user.get());
            return "cadastrarUsuario";
        }else{
            return "redirect:../../usuarios";
        }
    }
    
    @GetMapping("/cadastrar")
    public String cadastrar(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute(usuario);
        return "cadastrarUsuario";
    }
    
    @PostMapping("/cadastrar")
    public String save(@Valid @ModelAttribute Usuario usuario, BindingResult bindingResult, @RequestParam("permissao") String permissao, Model model){
        
        usuario.setPermissoes(new ArrayList<>());
        
        if(permissao.contains("ADMIN")){
            usuario.getPermissoes().add(new Permissao("ADMIN", Long.valueOf(1)));
        }
        
        usuario.getPermissoes().add(new Permissao("COMUM", Long.valueOf(2)));
        
        if(bindingResult.hasErrors())
            return "cadastrar_usuario";
        
        BCryptPasswordEncoder p = new BCryptPasswordEncoder();
        usuario.setSenha(p.encode(usuario.getSenha()));
        
        repo.save(usuario);
        
        return "redirect:../usuarios";
    }
    
    @PostMapping("/pesquisar")
    public String findByName(@RequestParam("campoBusca") String campoBusca, Model model){
        model.addAttribute("lista", repo.findByLoginContainingIgnoreCase(campoBusca));
        return "listarUsuario";
    }
}
