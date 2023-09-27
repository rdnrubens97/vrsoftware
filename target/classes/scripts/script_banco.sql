-- Cria o banco de dados vrsoftware
CREATE DATABASE vrsoftware;

-- Cria o usuário postgres com a senha 'root'
DO $$BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = 'postgres') THEN
        CREATE USER postgres WITH PASSWORD 'root';
    END IF;
END$$;

-- Concede todas as permissões ao usuário postgres para o banco de dados vrsoftware
GRANT ALL PRIVILEGES ON DATABASE vrsoftware TO postgres;

