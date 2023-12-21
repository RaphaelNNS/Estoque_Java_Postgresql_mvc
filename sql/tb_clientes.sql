-- Table: public.tb_clientes

-- DROP TABLE IF EXISTS public.tb_clientes;

CREATE TABLE IF NOT EXISTS public.tb_clientes
(
    clienteid integer NOT NULL DEFAULT 'nextval('tb_clientes_clienteid_seq'::regclass)',
    clientenome character varying(56) COLLATE pg_catalog."default" NOT NULL,
    clienteendereco character varying(200) COLLATE pg_catalog."default" NOT NULL,
    clientecidade character varying(30) COLLATE pg_catalog."default" NOT NULL,
    clienteestado character varying(30) COLLATE pg_catalog."default" NOT NULL,
    clientetelefone character varying(20) COLLATE pg_catalog."default",
    clientecpf character varying(11) COLLATE pg_catalog."default",
    CONSTRAINT tb_clientes_pkey PRIMARY KEY (clienteid)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tb_clientes
    OWNER to postgres;