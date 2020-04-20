CREATE TABLE arquivo (
    id_arquivo Serial PRIMARY KEY,
    nm_arquivo Text NOT NULL,
    bytes_arquivo bytea NOT NULL,
    dh_criacao TIMESTAMP WITH TIME ZONE NOT NULL
);