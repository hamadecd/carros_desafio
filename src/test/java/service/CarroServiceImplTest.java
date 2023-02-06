package service;

/**
 * Enquanto o carro estiver desligado deve ser capaz de:
 *  - Ligar
 *  - Mostrar estado atual
 *
 * Enquanto o carro estiver ligado deve ser capaz de:
 * - Desligar
 * - Acelerar
 * - Frear
 * - Motrar estado atual
 *
 * Regras:
 *
 * - Só podemos realizar as ações como acelerar e frear com o carro ligado
 * - Só podemos desligar o carro quando ele parar totalmente (velocidadeAtual = 0)
 * - Não existe velocidade negativa
 * - O carro não pode passar de sua velocidade máxima
 * - O carro inicia desligado
 */

import model.Carro;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CarroServiceImplTest {

    CarroServiceImpl carroService;

    @Before
    public void setup() {
        carroService = new CarroServiceImpl();
    }

    @Test
    public void shouldTurnOnTheCar() {
        Carro carro = new Carro("Azul", "Fiat", "Uno", 150);

        carroService.ligar(carro);

        assertTrue(carro.isLigado());
    }

    @Test
    public void shouldTurnOffTheCar() {
        Carro carro = new Carro("Azul", "Fiat", "Uno", 150);

        carroService.ligar(carro);
        carroService.desligar(carro);

        assertFalse(carro.isLigado());
    }

    @Test
    public void shouldStartWithCarTurnOff() {
        Carro carro = new Carro("Azul", "Fiat", "Uno", 150);

        assertFalse(carro.isLigado());
    }

    @Test
    public void shouldBePossibleToAccelerateWithTheCarOn() {
        Carro carro = new Carro("Azul", "Fiat", "Uno", 150);

        carroService.ligar(carro);
        carroService.acelerar(carro, 110);
        carroService.acelerar(carro, 40);

        assertEquals(150, carro.getVelocidadeAtual());
    }

    @Test
    public void shouldNotBePossiblePassSpeedLimit() {
        Carro carro = new Carro("Azul", "Fiat", "Uno", 150);

        carroService.ligar(carro);
        carroService.acelerar(carro, 151);

        assertEquals(150, carro.getVelocidadeAtual());
    }

    @Test
    public void shouldNotExistNegativeVelocity() {
        Carro carro = new Carro("Azul", "Fiat", "Uno", 150);

        carroService.ligar(carro);
        carroService.acelerar(carro, 10);
        carroService.frear(carro, 20);

        assertTrue(carro.getVelocidadeAtual() == 0);
    }

    @Test
    public void shouldBePossibleToBrakeWithTheCarOn() {
        Carro carro = new Carro("Azul", "Fiat", "Uno", 150);

        carroService.ligar(carro);
        carroService.acelerar(carro, 150);
        carroService.frear(carro, 50);

        assertEquals(100, carro.getVelocidadeAtual());
    }

    @Test
    public void shouldOnlyBePossibleToTurnOffTheCarWhenTheSpeedIsEqualToZero() {
        Carro carro = new Carro("Azul", "Fiat", "Uno", 150);

        carroService.ligar(carro);
//        carroService.acelerar(carro, 80);
//        carroService.frear(carro, 80);
        carroService.desligar(carro);

        assertFalse(carro.isLigado());
    }

    @Test
    public void shouldShowCurrentStatusWithCarOn() {
        Carro carro = new Carro("Azul", "Fiat", "Uno", 150);

        carroService.ligar(carro);
        carroService.acelerar(carro, 10);

        assertEquals("Carro{cor='Azul', marca='Fiat', modelo='Uno', ligado=true, velocidadeAtual=10, velocidadeMaxima=150}", carroService.estadoAtual(carro));
    }

    @Test
    public void shouldShowCurrentStatusWithCarOff() {
        Carro carro = new Carro("Azul", "Fiat", "Uno", 150);

        assertEquals("Carro{cor='Azul', marca='Fiat', modelo='Uno', ligado=false, velocidadeAtual=0, velocidadeMaxima=150}", carroService.estadoAtual(carro));
    }

}
