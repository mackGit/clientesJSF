package br.com.parceriasistemas.jsf.cd.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "cidade")
@SequenceGenerator(name="SEQ_CIDADE", sequenceName = "SEQ_CIDADE")
public class Cidade implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CIDADE")
    @Column(name = "idCidade", length = 20, updatable = false)
    private Integer idCidade;
    
    @Column(name = "nomeCidade")
    @Size(max = 150)
    @NotEmpty
    private String nomeCidade;
    
    @Column(name = "estadoCidade")
    @Size(min = 2, max = 2)
    @NotEmpty
    private String estadoCidade;

    
    
    public Cidade() {
    }

    public Cidade(Integer idCidade) {
        this.idCidade = idCidade;
    }
    
    
    public Cidade(Integer idCidade, String nomeCidade, String estadoCidade) {
        this.idCidade = idCidade;
        this.nomeCidade = nomeCidade;
        this.estadoCidade = estadoCidade;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.idCidade);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cidade other = (Cidade) obj;
        if (idCidade == null) {
            if (other.idCidade != null)
                return false;
        } else if (!idCidade.equals(other.idCidade))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cidade{" + "idCidade=" + idCidade + ", nomeCidade=" + nomeCidade + '}';
    }
    
    
    
    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getEstadoCidade() {
        return estadoCidade;
    }

    public void setEstadoCidade(String estadoCidade) {
        this.estadoCidade = estadoCidade;
    }    
}
