package bsi.springWeb.trabalhop2.repository;

import bsi.springWeb.trabalhop2.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ProdutoRepository extends  JpaRepository<Produto, Long>{
    public List <Produto> findByNomeContainingIgnoreCase(@RequestParam String nome);
}
