/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.estacionamento.lpoo_sistemaestacionamentoifsul.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Pessoa;
import model.Veiculo;
import model.Modelo;
import model.VinculoPessoa;

public class PersistenciaJPA implements InterfaceBD{
    public EntityManager entity;
    public EntityManagerFactory factory;
    
    public PersistenciaJPA() {
        factory = Persistence.createEntityManagerFactory("pu_lpoo_estacionamento");
               
        entity = factory.createEntityManager();
    }

    @Override
    public Boolean conexaoAberta() {
        return entity.isOpen();
    }

    @Override
    public void fecharConexao() {
        entity.close();
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void persist(Object o) throws Exception {

        entity = getEntityManager();
        try {
            entity.getTransaction().begin();
            if (!entity.contains(o)) {
                o = entity.merge(o);
            } else {
                entity.persist(o);
            }
            entity.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro ao persistir: "+e);
            if (entity.getTransaction().isActive()) {
                entity.getTransaction().rollback();
            }
        }
    }


    @Override
    public void remover(Object o) throws Exception {
        entity = getEntityManager();
        try {
            entity.getTransaction().begin();
            if (!entity.contains(o)) {
                o = entity.merge(o);
            }
            entity.remove(o);
            entity.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao remover item: "+e);
            if (entity.getTransaction().isActive()) {
                entity.getTransaction().rollback();
            }
        }
        
    }
    
    public EntityManager getEntityManager() {
        if(entity == null || !entity.isOpen()) {
            entity = factory.createEntityManager();
        }
        return entity;
    }
    
    //funções para listar dados
    
    public List<Pessoa> getPessoas(){
        entity = getEntityManager();
        try {
            TypedQuery<Pessoa> query = entity.createQuery("select p from Pessoa p", Pessoa.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro ao buscar pessoas: " + e);
            return null;
        }
    }
    
    public List<Pessoa> getPessoasPorNomeEVinculo(String nome, VinculoPessoa vinculo) {
        EntityManager em = getEntityManager();
        if (vinculo!=null){
            String query = "SELECT p FROM Pessoa p WHERE p.nome LIKE :nome AND p.vinculoPessoa = :vinculo";
            return em.createQuery(query, Pessoa.class)
                    .setParameter("nome", "%" + nome + "%")
                    .setParameter("vinculo", vinculo)
                    .getResultList();
        } else{
            String query = "SELECT p FROM Pessoa p WHERE p.nome LIKE :nome";
            return em.createQuery(query, Pessoa.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        }
    }
    
    public List<Veiculo> getVeiculos() {
        entity = getEntityManager();
        try {
            TypedQuery<Veiculo> query = entity.createQuery("Select v from Veiculo v", Veiculo.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro: " + e);
            return null;
        }
    }
    public List<Veiculo> getVeiculos(String placa) {
        entity = getEntityManager();
        try {
            TypedQuery<Veiculo> query = entity.createQuery("Select v from Veiculo v where lower(v.placa) LIKE :p", Veiculo.class);
            query.setParameter("p", "%" + placa.toLowerCase() + "%");
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro: " + e);
            return null;
        }
    }
    public List<Veiculo> getVeiculos(boolean oficial) {
        entity = getEntityManager();
        try {
            String q = "Select v from Veiculo v ";
            if (oficial) {
                q += "where tipo_veiculo = 'OFICIAL'";
            }
            TypedQuery<Veiculo> query = entity.createQuery(q, Veiculo.class);        
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro: " + e);
            return null;
        }
    }
    
        public List<Pessoa> getPessoas(String nome) {
        entity = getEntityManager();

        try {
            TypedQuery<Pessoa> query = entity.createQuery("Select p from Pessoa p where lower(p.nome) LIKE :n", Pessoa.class);
            query.setParameter("n", "%" + nome.toLowerCase() + "%");
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro: " + e);
            return null;
        }
    }
    public List<Modelo> getModelos() {
        entity = getEntityManager();
        try {
            TypedQuery<Modelo> query = entity.createQuery("Select m from Modelo m", Modelo.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Erro: " + e);
        }
        return null;
    }
}
