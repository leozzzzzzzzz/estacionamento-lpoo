package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_modelo")
public class Modelo implements Serializable {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;
    
    private Marca marca;
    
    @Column(name = "descr", length = 100, nullable = false)
    private String descricao;
    

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString()
    {
        return marca + descricao;
    }
}
