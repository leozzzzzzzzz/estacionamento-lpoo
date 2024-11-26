package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_veiculo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public class Veiculo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String placa;
    private String cor;
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo; 
    
    //Relações:
    @OneToMany(mappedBy = "veiculo")
    List<EntradaSaida> listaMovimentacoes;
    
    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "veiculo_proprietario")
    private Pessoa proprietario;
    
    
    public Veiculo() {
        listaMovimentacoes = new ArrayList<>();
    }

    public Veiculo(String placa, TipoVeiculo tipoVeiculo) {
        this.placa = placa;
        this.tipoVeiculo = tipoVeiculo;
        listaMovimentacoes = new ArrayList<>();
    }
    
    
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }
    
    public void addMovimentacao(EntradaSaida movimentacao){
        listaMovimentacoes.add(movimentacao);
    }

    public Pessoa getProprietario() {
        return proprietario;
    }

    public void setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario;
    }
    
    @Override
    public String toString(){
        
        return "Placa: " + placa
            + ", Proprietário: " + (proprietario != null ? proprietario.getNome() : "Desconhecido") 
            + ", Oficial: " + ((this instanceof VeiculoOficial) ? "Sim" : "Não");
    }
}
