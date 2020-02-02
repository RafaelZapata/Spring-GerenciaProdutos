package bsi.springWeb.trabalhop2.repository;

import bsi.springWeb.trabalhop2.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
    
}
