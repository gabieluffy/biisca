import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Jogo {
    private List<Jogador> jogadores;
    private Baralho baralho;

    public Jogo(List<String> nomesJogadores) {
        jogadores = nomesJogadores.stream().map(Jogador::new).collect(Collectors.toList());
        baralho = new Baralho();
    }

    public void jogar() {
        baralho.embaralhar();

        for (Jogador jogador : jogadores) {
            jogador.receberCarta(baralho.comprarCarta());
        }

        for (int i = 0; i < 4; i++) {
            List<Carta> cartasRodada = jogadores.stream()
                    .map(jogador -> jogador.jogarCarta(i))
                    .filter(carta -> carta != null)
                    .collect(Collectors.toList());
