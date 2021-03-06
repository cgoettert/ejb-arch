package br.com.kopp.sistrak.skepi.servicos.origem;

import br.com.kopp.framework.ejb.KoppEJB;
import br.com.kopp.sistrak.skepi.comum.exception.SkepyException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import org.mybatis.cdi.Mapper;

/**
 *
 * @author cgoettert
 */
@Stateless
public class OrigemServicoEjb extends KoppEJB implements OrigemServicoLocal {

    @Inject
    @Mapper
    private UsuarioOrigemMapper mapper;
    
    @Override
    public List<OrigemUsuarioDto> combo() throws SkepyException {
        List<UsuarioOrigem> lista = mapper.selectByExample(new UsuarioOrigemExample());
        
        return getMapper()
                .comFunction(UsuarioOrigemConversor.obterConversorUsuarioOrigem())
                .converterLista(lista);
    }

}
