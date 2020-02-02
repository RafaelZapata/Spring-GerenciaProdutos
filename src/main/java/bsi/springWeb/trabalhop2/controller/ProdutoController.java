package bsi.springWeb.trabalhop2.controller;

import bsi.springWeb.trabalhop2.model.Produto;
import bsi.springWeb.trabalhop2.repository.ProdutoRepository;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository repo;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("lista", repo.findAll());
        return "listar";
    }
    
    @GetMapping("/cadastrar")
    public String cadastrar(Model model){
        model.addAttribute("produto", new Produto());
        return "cadastrar";
    }
    
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model){
        Optional<Produto> findById = repo.findById(id);
        if(findById.isPresent()){
            model.addAttribute("produto", repo.findById(id).get());
            return "cadastrar";
        }else{
            return "redirect:../../produtos";
        }
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        repo.deleteById(id);
        return "redirect:/produtos";
    }
    
    @PostMapping("/cadastrar")
    public String save(@Valid @ModelAttribute Produto produto, BindingResult bindingResult,
            @RequestParam("file") MultipartFile file, Model model) {

        if (bindingResult.hasErrors()) {
            return "cadastrar";
        }
        
        if (file.isEmpty()) {
            model.addAttribute("msgFile", "Arquivo não carregado");
            return "cadastrar";
        } else if (!file.getContentType().equals(MediaType.IMAGE_JPEG_VALUE)) {
            model.addAttribute("msgFile", "Tipo de arquivo inválido");
            return "cadastrar";
        }
        try {
            String name = Calendar.getInstance().getTimeInMillis() + file.getOriginalFilename();
            file.transferTo(Paths.get("src/main/resources/img/" + name));
            produto.setImagem("/files/jpg/" + name);
            repo.save(produto);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:../produtos";
    }
    
    @PostMapping(path = "/pesquisar")
    public String findByName(@RequestParam("campoBusca") String campoBusca, Model model){
        model.addAttribute("lista", repo.findByNomeContainingIgnoreCase(campoBusca));
        return "listar";
    }
}
