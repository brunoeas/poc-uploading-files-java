package br.com.pocuploadingfiles.provider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 * Provider para requests que retornam respostas do tipo {@link ByteArrayOutputStream},
 * normalmente para requests de download de um arquivo
 *
 * @author Bruno Eduardo
 */
@Provider
public class OutputStreamWriter implements MessageBodyWriter<ByteArrayOutputStream> {

    /**
     * Valida se a request vai ou não usar este Provider para manipular a resposta dela
     */
    @Override
    public boolean isWriteable(final Class<?> type, final Type genericType, final Annotation[] annotations,
            final MediaType mediaType) {
        return ByteArrayOutputStream.class.equals(type);
    }

    /**
     * Escreve a resposta da request
     */
    @Override
    public void writeTo(final ByteArrayOutputStream byteArrayOutputStream, final Class<?> type, final Type genericType,
            final Annotation[] annotations, final MediaType mediaType, final MultivaluedMap<String, Object> httpHeaders,
            final OutputStream entityStream) throws IOException, WebApplicationException {
        httpHeaders.add("Content-Length", byteArrayOutputStream.toByteArray().length);
        byteArrayOutputStream.writeTo(entityStream);
    }

}