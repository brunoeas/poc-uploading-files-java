package br.com.pocuploadingfiles.orm;

import java.time.ZonedDateTime;
import javax.persistence.*;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 * ORM do Usuário
 *
 * @author Bruno Eduardo <bruno.soares@kepha.com.br>
 */
@Entity
@Table(name = "arquivo")
public class Arquivo extends PanacheEntityBase {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arquivo")
    private Integer idArquivo;

    /**
     * Nome
     */
    @Column(name = "nm_arquivo", nullable = false)
    private String nmArquivo;

    /**
     * Bytes do arquivo
     */
    @Column(name = "bytes_arquivo", nullable = false)
    private byte[] bytesArquivo;

    /**
     * Data e hora da criação
     */
    @Column(name = "dh_criacao", nullable = false)
    private ZonedDateTime dhCriacao;

    public Integer getIdArquivo() {
        return this.idArquivo;
    }

    public void setIdArquivo(final Integer idArquivo) {
        this.idArquivo = idArquivo;
    }

    public String getNmArquivo() {
        return this.nmArquivo;
    }

    public void setNmArquivo(final String nmArquivo) {
        this.nmArquivo = nmArquivo;
    }

    public byte[] getBytesArquivo() {
        return this.bytesArquivo;
    }

    public void setBytesArquivo(final byte[] bytesArquivo) {
        this.bytesArquivo = bytesArquivo;
    }

    public ZonedDateTime getDhCriacao() {
        return this.dhCriacao;
    }

    public void setDhCriacao(final ZonedDateTime dhCriacao) {
        this.dhCriacao = dhCriacao;
    }

}