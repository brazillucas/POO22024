import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BrasileiraoIFTest {

    private BrasileiraoIF brasileirao;
    private Equipe[] equipes;

    @BeforeEach
    public void setUp() {
        brasileirao = new BrasileiraoIF();
        equipes = new Equipe[20];
    }

    @Test
    public void testCadastrarPartida() {
        // Setup mock input for cadastrarPartida
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextInt()).thenReturn(1, 2, 3, 4, 5, 6);
        when(mockScanner.nextLine()).thenReturn("Equipe1", "Equipe2");

        equipes[0] = new Equipe("Equipe1");
        equipes[1] = new Equipe("Equipe2");

        BrasileiraoIF.scan = mockScanner;
        brasileirao.cadastrarPartida(equipes);

        assertEquals(3, equipes[0].getGolsPro());
        assertEquals(4, equipes[1].getGolsPro());

        // Reset the Scanner
        BrasileiraoIF.scan = new Scanner(System.in);
    }

    @Test
    public void testMostrarLider() {
        // Setup mock input for mostrarLider
        equipes[0] = new Equipe("Equipe1");
        equipes[1] = new Equipe("Equipe2");
        equipes[0].setPontos(10);
        equipes[1].setPontos(15);

        brasileirao.mostrarLider(equipes);

        // Verify that the team with the highest points is displayed
        assertEquals("Equipe2", brasileirao.mostrarLider(equipes));
    }

    @Test
    public void testListarG4() {
        // Setup mock input for listarG4
        for (int i = 0; i < 4; i++) {
            equipes[i] = new Equipe("Equipe" + (i + 1));
        }

        brasileirao.listarG4(equipes);

        for (int i = 0; i < 4; i++) {
            assertEquals("Equipe" + (i + 1), equipes[i].getNome());
        }
    }

    @Test
    public void testListarZ4() {
        // Setup mock input for listarZ4
        for (int i = 0; i < 20; i++) {
            equipes[i] = new Equipe("Equipe" + (i + 1));
        }

        brasileirao.listarZ4(equipes);

        for (int i = 16; i < 20; i++) {
            assertEquals("Equipe" + (i + 1), equipes[i].getNome());
        }
    }

    @Test
    public void testCadastrarEquipe() {
        // Setup mock input for cadastrarEquipe
        when(mockScanner.nextLine()).thenReturn("EquipeNova");

        brasileirao.cadastrarEquipe(equipes);

        assertEquals("EquipeNova", equipes[0].getNome());
    }
}