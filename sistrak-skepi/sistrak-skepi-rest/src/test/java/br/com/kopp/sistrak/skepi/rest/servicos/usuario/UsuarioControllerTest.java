package br.com.kopp.sistrak.skepi.rest.servicos.usuario;

import br.com.kopp.sistrak.skepi.rest.servicos.usuario.UsuarioController;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import br.com.kopp.framework.message.KoppMessage;
import br.com.kopp.sistrak.skepi.servicos.usuario.UsuarioDto;
import br.com.kopp.sistrak.skepi.comum.exception.SkepyException;
import br.com.kopp.sistrak.skepi.servicos.usuario.UsuarioServicoLocal;

public class UsuarioControllerTest extends JerseyTest {

    @Mock
    private UsuarioServicoLocal usuarioLocal;

    @Mock
    private KoppMessage skepyMessage;

    @Override
    protected Application configure() {
        MockitoAnnotations.initMocks(this);

        return new ResourceConfig()
                .register(new UsuarioController(usuarioLocal, skepyMessage));
    }

    @Test
    public void usuarioListTest() throws SkepyException {
        // given
        List<UsuarioDto> list = new ArrayList<>();
        list.add(gerarUsuarioDto());
        doReturn(list).when(usuarioLocal).getAll();

        // when
        Response response = target("usuario/").request().get(Response.class);

        // then
        list = getFromResponse(response, "usuarios");
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void usuarioGetId10Test() throws SkepyException {
        // given
        doReturn(gerarUsuarioDto()).when(usuarioLocal).get(anyInt());

        // when
        Response response = target("usuario/10").request().get(Response.class);

        // then
        Map<String, Object> map = getFromResponse(response, "UsuarioDto");
        Assert.assertEquals("Bond", map.get("nome"));
    }

    private <T> T getFromResponse(Response response, String key) {
        @SuppressWarnings("rawtypes")
        Map map = response.readEntity(Map.class);
        @SuppressWarnings("unchecked")
        T obj = (T) map.get(key);

        return obj;
    }

    private UsuarioDto gerarUsuarioDto() {
        return UsuarioDto.builder()
                .nome("Bond")
                .build();
    }

}
