package bisca;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Jogo {
  private List<Jogador> jogadores;
  private Baralho baralho;
  public Jogo(List<String> nomesJogadores) {
   jogadores = nomesJogadores.stream().map(Jogador::new).collect(Collectors.toList());
   baralho = new Baralho();
		    }

	}



