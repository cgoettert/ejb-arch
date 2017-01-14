package br.com.kopp.impl.mapper;

import br.com.kopp.framework.mapper.KoppMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.enterprise.context.RequestScoped;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 *
 * @author cgoettert
 */
@RequestScoped
public class MapperDozer implements KoppMapper {

    @Override
    public <T, U> U map(final T source, final Class<U> destinationClass, Function<T, U> fun) {
        return new DozerBeanMapper().map(source, destinationClass);
    }

    @Override
    public <T, U> ArrayList<U> mapList(final List<T> source, final Class<U> destType, Function<T, U> fun) {
        final Mapper mapper = new DozerBeanMapper();
        final ArrayList<U> dest = new ArrayList<>();

        for (T element : source) {
            if (element == null) {
                continue;
            }
            dest.add(mapper.map(element, destType));
        }

        // finally remove all null values if any
        List<T> s1 = new ArrayList<T>();
        s1.add(null);
        dest.removeAll(s1);

        return dest;
    }

}
