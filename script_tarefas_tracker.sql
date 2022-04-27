-- Database: tarefas_tracker

-- DROP DATABASE IF EXISTS tarefas_tracker;

CREATE DATABASE tarefas_tracker
    WITH
    OWNER = demo
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
-- Table: public.tarefas

-- DROP TABLE IF EXISTS public.tarefas;

CREATE TABLE IF NOT EXISTS public.tarefas
(
    titulo character varying(50) COLLATE pg_catalog."default" NOT NULL,
    descricao character varying(200) COLLATE pg_catalog."default" NOT NULL,
    responsavel character varying(50) COLLATE pg_catalog."default" NOT NULL,
    prioridade character varying(20) COLLATE pg_catalog."default" NOT NULL,
    situacao character varying(20) COLLATE pg_catalog."default" NOT NULL,
    deadline character varying(12) COLLATE pg_catalog."default",
    id_tarefa integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 )
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tarefas
    OWNER to demo;
