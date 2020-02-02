package bsi.springWeb.trabalhop2.repository;

import bsi.springWeb.trabalhop2.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends  JpaRepository<Produto, Long>{
    
}
