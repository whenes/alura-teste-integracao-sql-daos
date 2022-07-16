package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDaoTest {
    private UsuarioDao dao;
    private EntityManager em;

    @BeforeEach
    public void beforeEach() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    public void deveriaEncontrarUsuarioCadastrado() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@fulano.com.br")
                .comSenha("12345678")
                .criar();
        em.persist(usuario);

        Usuario usuarioEncontrado = this.dao.buscarPorUsername(usuario.getNome());
        assertNotNull(usuarioEncontrado);
    }
    @Test
    public void naoDeveriaEncontrarUsuarioNaoCadastrado() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@fulano.com.br")
                .comSenha("12345678")
                .criar();
        em.persist(usuario);
        assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername("Beltrano"));
    }

    @Test
    public void deveriaRemoverUmUsuario() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@fulano.com.br")
                .comSenha("12345678")
                .criar();
        em.persist(usuario);
        dao.deletar(usuario);
        assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername("fulano"));
    }
}