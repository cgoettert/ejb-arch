package br.com.kopp.sistrak.skepi.servicos.usuario;

import br.com.kopp.sistrak.skepi.comum.exception.SkepyException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cgoettert
 */
@Local
public interface UsuarioServicoLocal {

    public UsuarioDto get(Integer id) throws SkepyException;
    
    public Integer count() throws SkepyException;;

    public List<UsuarioDto> getAll() throws SkepyException;;
    
}
