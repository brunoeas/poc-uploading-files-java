package br.com.pocuploadingfiles.converter;

import javax.enterprise.context.RequestScoped;

import br.com.pocuploadingfiles.dto.ArquivoDTO;
import br.com.pocuploadingfiles.orm.Arquivo;

/**
 * Converter do Arquivo
 */
@RequestScoped
public class ArquivoConverter extends AbstractConverter<Arquivo, ArquivoDTO> {

    /**
     * Converte um DTO para o formato ORM.
     */
    @Override
    public Arquivo dtoToOrm(final ArquivoDTO dto, final Arquivo orm) {

        orm.setIdArquivo(dto.getIdArquivo());
        orm.setNmArquivo(dto.getNmArquivo());

        return orm;
    }

    /**
     * Converte um ORM para o formato DTO.
     */
    @Override
    public ArquivoDTO ormToDto(final Arquivo orm, final ArquivoDTO dto) {

        dto.setIdArquivo(orm.getIdArquivo());
        dto.setNmArquivo(orm.getNmArquivo());

        return dto;
    }

    /**
     * @return Retorna uma nova instância do ORM.
     */
    @Override
    public Arquivo getOrmNewInstance() {
        return new Arquivo();
    }

    /**
     * @return Retorna uma nova instância do DTO.
     */
    @Override
    public ArquivoDTO getDtoNewInstance() {
        return new ArquivoDTO();
    }

}