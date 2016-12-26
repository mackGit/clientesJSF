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

@Entity
@Table(name = "livro")
@SequenceGenerator(name="SEQ_LIVRO", sequenceName = "SEQ_LIVRO")
public class Livro implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_LIVRO")
    @Column(name = "id", length = 20, updatable = false)
    private Integer id;
    
    @Column(name = "titulo")
    @Size(max = 150)
    private String titulo;
    
    @Column(name = "autor")
    @Size(max = 150)
    private String autor;
    
    public Livro() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Livro other = (Livro) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

}
