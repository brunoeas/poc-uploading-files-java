package br.com.pocuploadingfiles.converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe abstrata para definir os métodos padrões de um converter entre ORM e
 * DTO.
 *
 * @param <O> Objeto ORM.
 * @param <D> Objeto DTO.
 * @author Diego Maehler
 */
public abstract class AbstractConverter<O, D> {

    /**
     * Converte um DTO para o formato ORM.
     */
    public abstract O dtoToOrm(D dto, O orm);

    /**
     * Converte um ORM para o formato DTO.
     */
    public abstract D ormToDto(O orm, D dto);

    /**
     * @return Retorna uma nova instância do ORM.
     */
    public abstract O getOrmNewInstance();

    /**
     * @return Retorna uma nova instância do DTO.
     */
    public abstract D getDtoNewInstance();

    /**
     * Converte um DTO para o formato ORM.
     */
    public O dtoToOrm(final D dto) {
        if (dto == null) {
            return null;
        }

        final O orm = this.getOrmNewInstance();
        this.dtoToOrm(dto, orm);

        return orm;
    }

    /**
     * Converte um ORM para o formato DTO.
     */
    public D ormToDto(final O orm) {
        if (orm == null) {
            return null;
        }

        final D dto = this.getDtoNewInstance();
        this.ormToDto(orm, dto);

        return dto;
    }

    /**
     * Converte uma lista de DTO para uma lista de ORM.
     */
    public List<O> dtoListToOrmList(final List<D> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::dtoToOrm).collect(Collectors.toList());
    }

    /**
     * Converte uma lista de ORM para uma lista de DTO.
     */
    public List<D> ormListToDtoList(final List<O> ormList) {
        if (ormList == null) {
            return null;
        }
        return ormList.stream().map(this::ormToDto).collect(Collectors.toList());
    }

}