/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import br.edu.ifsul.cc.lpoo.estacionamento.lpoo_sistemaestacionamentoifsul.dao.PersistenciaJPA;
import model.Marca;
import model.Modelo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20231PF.CC0030
 */
public class TestePersistencia {
    
    PersistenciaJPA jpa = new PersistenciaJPA();
    
    
    
    public TestePersistencia() {
    }
    
    @Before
    public void setUp() {
        jpa.conexaoAberta();
    }
    
    @After
    public void tearDown() {
        jpa.fecharConexao();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testePersistencia() {
        Modelo m = new Modelo();
        m.setDescricao("fox");
        m.setMarca(Marca.VOLKSWAGEN);
        Modelo m2 = new Modelo();
        m2.setDescricao("uno com escada em cima");
        m2.setMarca(Marca.FIAT);
        
        try{
            jpa.persist(m);
            jpa.persist(m2);
        } catch(Exception e) {
            System.err.println("Erro ao persistir modelo: "+m);
        }
    }
}
