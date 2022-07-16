package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDaoTest {
    private UsuarioDao dao;

    @Test
    public void testeBuscaDeUsuarioPeloUserName() {
        this.dao = new UsuarioDao();
        Usuario usuario = this.dao.buscarPorUsername("fulano");
        assertNotNull(usuario);
    }
}