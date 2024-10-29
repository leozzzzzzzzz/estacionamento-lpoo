import br.edu.ifsul.cc.lpoo.estacionamento.lpoo_sistemaestacionamentoifsul.dao.PersistenciaJPA;
import model.EntradaSaida;
import model.Marca;
import model.Modelo;
import model.Pessoa;
import model.TipoMovimentacao;
import model.TipoVeiculo;
import model.Veiculo;
import model.VinculoPessoa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
        m.setDescricao("Uno de firma com escada em cima");
        m.setMarca(Marca.FIAT);
        Veiculo v = new Veiculo();
        v.setModelo(m);
        v.setPlaca("AAA1234");
        v.setCor("BRANCO");
        v.setTipoVeiculo(TipoVeiculo.CARRO);
        
        Pessoa p = new Pessoa();
        p.setNome("Robson");
        p.setEmail("robson@gmail.com");
        p.setFone("190");
        p.setVinculoPessoa(VinculoPessoa.VISITANTE);
        v.setProprietario(p);
        
        EntradaSaida es = new EntradaSaida(TipoMovimentacao.SAIDA, v);
        try{
            jpa.persist(m);
            jpa.persist(v);
            jpa.persist(p);
            jpa.persist(es);
        } catch(Exception e) {
            System.err.println("Erro ao persistir modelo: "+m);
        }
    }
}
