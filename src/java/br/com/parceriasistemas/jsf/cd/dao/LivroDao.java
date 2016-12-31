package br.com.parceriasistemas.jsf.cd.dao;

import java.util.List;

import br.com.parceriasistemas.jsf.cd.model.Livro;

public class LivroDAO extends BaseDAO {

    public boolean incluir(Livro obj) {
        insert(obj);
        return true;
    }

    public boolean alterar(Livro obj) {
        update(obj);
        return true;
    }

    public boolean excluir(Livro obj) {
        delete(obj);
        return true;
    }

    public Livro localizarID(int id) {
        return (Livro)findById(Livro.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Livro> getLivros() {
        List<Livro> retorno = getListByCriteriaClass(Livro.class);
        return retorno;
    }
}
