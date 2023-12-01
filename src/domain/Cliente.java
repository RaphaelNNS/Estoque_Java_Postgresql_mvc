package domain;

import java.util.Objects;

public class Cliente {

    private long id;
    private String nome;
    private String endereco;
    private String cidade;
    private String estado;
    private String telefone;
    private String  cpf;

    public Cliente() {

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Cliente(String nome, String endereco, String cidade, String estado, String telefone, String  cpf) {
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return  Objects.equals(nome, cliente.nome) && Objects.equals(endereco, cliente.endereco) && Objects.equals(cidade, cliente.cidade) && Objects.equals(estado, cliente.estado) && Objects.equals(telefone, cliente.telefone) && Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, endereco, cidade, estado, telefone, cpf);
    }

    public String toString() {
        return "Nome : "+this.nome+
                "\nEndereço: "+this.endereco+
                "\nCidade: "+this.cidade+
                "\nEstado: "+this.estado+
                "\nTelefone: "+this.telefone+
                "\nCPF: " +this.cpf+"\n";
    }

}
