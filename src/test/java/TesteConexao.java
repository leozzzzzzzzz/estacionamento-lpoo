/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import br.edu.ifsul.cc.lpoo.estacionamento.lpoo_sistemaestacionamentoifsul.dao.PersistenciaJPA;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteConexao {
    
    PersistenciaJPA jpa = new PersistenciaJPA();
    
    public TesteConexao() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        if(jpa.conexaoAberta()) {
            System.out.println("Conexão aberta");
        } else {
            System.out.println("Erro ao abrir conexão");
        }
    }
    
    @After
    public void tearDown() {
        jpa.fecharConexao();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testeConexao() {
        System.out.println("Conexão realizada com sucesso");
    }
}
