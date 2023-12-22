-- Table: public.tb_produto

-- DROP TABLE IF EXISTS public.tb_produto;

CREATE TABLE IF NOT EXISTS public.tb_produto
(
    id serial,
    codigo character varying(10) COLLATE pg_catalog."default" NOT NULL,
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    descricao character varying(100) COLLATE pg_catalog."default" NOT NULL,
    valor numeric(10,2) NOT NULL,
    CONSTRAINT pk_id_produto PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tb_produto
    OWNER to postgres;