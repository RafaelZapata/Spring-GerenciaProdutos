package bsi.springWeb.trabalhop2.repository;

import bsi.springWeb.trabalhop2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Usuario findByLogin(String login);
    public Usuario findByLoginContainingIgnoreCase(String login);
}
