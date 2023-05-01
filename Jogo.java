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
           
            if (!cartasRodada.isEmpty()) {
                Comparator<Carta> comparadorCartas = Comparator.comparing(Carta::getNaipe)
                        .thenComparing(carta -> -Carta.VALORES.indexOf(carta.getValor()));
                
             Collections.sort(cartasRodada, comparadorCartas);
             
             Jogador vencedor = jogadores.stream()
                     .filter(jogador -> jogador.getMao().contains(cartasRodada.get(cartasRodada.size() - 1)))
                     .findFirst()
                     .orElseThrow(() -> new RuntimeException("Não encontrou o vencedor da rodada"));

             System.out.println(vencedor.getNome() + " venceu a rodada com " + cartasRodada.get(cartasRodada.size() - 1));   
            
             List<Carta> cartasVencedor = cartasRodada.subList(0, 3);
             vencedor.receberCarta(cartasVencedor.get(0));
             vencedor.receberCarta(cartasVencedor.get(1));
             vencedor.receberCarta(cartasVencedor.get(2));
         }
     }
