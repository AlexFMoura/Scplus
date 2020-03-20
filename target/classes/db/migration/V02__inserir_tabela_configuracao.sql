  CREATE TABLE configuracao (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    banco VARCHAR(25) NOT NULL,
    caminhoBanco VARCHAR(120) NOT NULL,
    porta INT NOT NULL,
    nomeBanco VARCHAR(50) NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    senha VARCHAR(120) NOT NULL,
    ativo BOOLEAN DEFAULT true NOT NULL,
    data_cadastro DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;