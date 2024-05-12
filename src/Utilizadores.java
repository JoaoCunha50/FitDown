import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Utilizadores implements Serializable {

  private Map<String, Utilizador> mapUtilizadores;

  public Utilizadores() {
    this.mapUtilizadores = new HashMap<>();
  }

  public Utilizadores(Utilizadores utilizadores) {
    this.mapUtilizadores = utilizadores.getUtilizadores();
  }

  public void setUtilizador(Utilizador utilizador) {
    this.mapUtilizadores.put(
        utilizador.getEmail(),
        utilizador
      );
  }

  public Map<String, Utilizador> getUtilizadores() {
    Map<String, Utilizador> result = new HashMap<>();
    Iterator<Map.Entry<String, Utilizador>> iterator =
      this.mapUtilizadores.entrySet().iterator();

    while (iterator.hasNext()) {
      Map.Entry<String, Utilizador> entry = iterator.next();
      result.put(entry.getKey(), entry.getValue());
    }

    return result;
  }

  public void setUtilizadores(Map<String, Utilizador> utilizadores) {
    // Adiciona ou atualiza os utilizadores
    for (Map.Entry<String, Utilizador> entry : utilizadores.entrySet()) {
      this.mapUtilizadores.put(entry.getKey(), entry.getValue());
    }
  }

  public Utilizador getUtilizadorbyChave(String code) {
    if (this.mapUtilizadores.containsKey(code)) {
      return this.mapUtilizadores.get(code);
    }
    return null;
  }

  public boolean verificarEmail(String email) {
    Iterator<Utilizador> iterator = this.getUtilizadores().values().iterator();
    
    while (iterator.hasNext()) {
        Utilizador utilizador = iterator.next();
        if (utilizador.getEmail().equals(email)) {
            return true;
        }
    }
    return false;
  }

  public Utilizador maisCaloriasGastas() {
    Iterator<Utilizador> iterator = this.getUtilizadores().values().iterator();

    Utilizador utilizador = iterator.next();
    Utilizador user = utilizador;

    while (iterator.hasNext()) {
        utilizador = iterator.next();
        if (utilizador.getCaloriasGastas() > user.getCaloriasGastas()) {
            user = utilizador;
        }
    }
    return user;
  }

  public Utilizador maisCaloriasGastasIntervalo(LocalDateTime inicio, LocalDateTime fim) {
    Iterator<Utilizador> iterator = this.getUtilizadores().values().iterator();

    Utilizador utilizador = iterator.next();
    Utilizador user = utilizador;

    while (iterator.hasNext()) {
        utilizador = iterator.next();
        if (utilizador.getCaloriasGastasIntervalo(inicio, fim) > user.getCaloriasGastasIntervalo(inicio, fim)) {
            user = utilizador;
        }
    }
    return user;
  }

  public String maisCaloriasGastasToString(){
    Utilizador utilizador = this.maisCaloriasGastas();
    if(utilizador.getnAtividadesRealizadas() == 0) {
      return ("Nenhum utilizador gastou calorias");
    }
    return ("O utilizador que mais calorias gastou foi " + utilizador.getNome());
  }

  public String maisCaloriasGastasIntervaloToString(LocalDateTime inicio, LocalDateTime fim){
    Utilizador utilizador = this.maisCaloriasGastasIntervalo(inicio, fim);
    if(utilizador.getnAtividadesRealizadasIntervalo(inicio, fim) == 0) {
      return ("Nenhum utilizador gastou calorias entre " + inicio.toString() + " e " + fim.toString());
    }
    return ("O utilizador que mais calorias gastou entre " + inicio.toString() + " e " + fim.toString() + " foi " + utilizador.getNome());
  }

  public Utilizador maisAtividadesRealizadas() {
    Iterator<Utilizador> iterator = this.getUtilizadores().values().iterator();

    Utilizador utilizador = iterator.next();
    Utilizador user = utilizador;

    while (iterator.hasNext()) {
        utilizador = iterator.next();
        if (utilizador.getnAtividadesRealizadas() > user.getnAtividadesRealizadas()) {
            user = utilizador;
        }
    }
    return user;
  }

  public Utilizador maisAtividadesRealizadasIntervalo(LocalDateTime inicio, LocalDateTime fim) {
    Iterator<Utilizador> iterator = this.getUtilizadores().values().iterator();

    Utilizador utilizador = iterator.next();
    Utilizador user = utilizador;

    while (iterator.hasNext()) {
        utilizador = iterator.next();
        if (utilizador.getnAtividadesRealizadasIntervalo(inicio, fim) > user.getnAtividadesRealizadasIntervalo(inicio, fim)) {
            user = utilizador;
        }
    }
    return user;
  }

  public String maisAtividadesRealizadasToString(){
    Utilizador utilizador = this.maisAtividadesRealizadas();
    if(utilizador.getnAtividadesRealizadas() == 0) {
      return ("Nenhum utilizador realizou atividades");
    }
    return ("O utilizador que mais atividades realizou foi " + utilizador.getNome());
  }

  public String maisAtividadesRealizadasIntervaloToString(LocalDateTime inicio, LocalDateTime fim){
    Utilizador utilizador = this.maisAtividadesRealizadasIntervalo(inicio, fim);
    if(utilizador.getnAtividadesRealizadasIntervalo(inicio, fim) == 0) {
      return ("Nenhum utilizador realizou atividades entre " + inicio.toString() + " e " + fim.toString());
    }
    return ("O utilizador que mais atividades realizou entre " + inicio.toString() + " e " + fim.toString() + " foi " + utilizador.getNome());
  }

  public Object getPlanoTreinoMaisDispendioso() {
    PlanoTreino planoTreino = null;
    PlanoTreino aux = null;

    Iterator<Utilizador> iterator = this.getUtilizadores().values().iterator();

    while (iterator.hasNext()) {
      Utilizador utilizador = iterator.next();
      if (utilizador.getPlanoTreinoMaisDispendiosoUtilizador() != null) {
        planoTreino = (PlanoTreino) utilizador.getPlanoTreinoMaisDispendiosoUtilizador();
        break;
      }
    }

    while (iterator.hasNext()) {
      Utilizador utilizador = iterator.next();
      if (utilizador.getPlanoTreinoMaisDispendiosoUtilizador() != null){
        aux = (PlanoTreino) utilizador.getPlanoTreinoMaisDispendiosoUtilizador();
        if (aux.getDispendioCalorias() > planoTreino.getDispendioCalorias()) {
          planoTreino = aux;
        }
      }
    }

    return planoTreino;
  }

  public String getPlanoTreinoMaisDispendiosoToString(){
    PlanoTreino planoTreino = (PlanoTreino) this.getPlanoTreinoMaisDispendioso();
    if (planoTreino == null) {
      return ("Nenhum utilizador registou planos de treino");
    }
    return ("O plano de treino mais exigente em função do dispêndio de calorias é:\n" + planoTreino.toString());
  }

  public Utilizadores clone() {
    return new Utilizadores(this);
  }
}
