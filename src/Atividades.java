import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Atividades implements Serializable {

  private List<Atividade> atividades;

  public Atividades() {
    this.atividades = new ArrayList<>();
  }

  public void setAtividade(Atividade atividade) {
    if (atividade == null) return;
    this.atividades.add(atividade);
  }

  public void setAtividades(List<Atividade> atividades) {
    for (Atividade atividade : atividades) {
      this.atividades.add(atividade);
    }
  }

  public List<Atividade> getAtividades() {
    List<Atividade> result = new ArrayList<>();
    Iterator<Atividade> iterator = this.atividades.iterator();

    while (iterator.hasNext()) {
      Atividade entry = iterator.next();
      result.add(entry);
    }
    return result;
  }

  public static boolean checkMaxActivitiesPerDay( // retorna false se houver mais que 3 atividades no dia atual
    Utilizador atividadesAgendadas,
    LocalDateTime dataComparacao
  ) {
    Iterator<Atividade> iterator = atividadesAgendadas
      .getAtividadesAgendadas()
      .iterator();
    int counter = 0;
    while (iterator.hasNext()) {
      Atividade atividade = iterator.next();
      if (
        atividade.getFim().toLocalDate().isEqual(dataComparacao.toLocalDate())
      ) {
        counter++;
      }
    }
    return counter <= 3;
  }

  public static boolean checkHardActivityDayBefore( // retorna true se existir uma atividade "HARD" no dia anterior
    Utilizador utilizador,
    LocalDateTime dataComparacao
  ) {
    ArrayList<Atividade> atividadesRealizadas = utilizador.getAtividadesRealizadas();
    LocalDate diaAnterior = dataComparacao.toLocalDate().minusDays(1);

    List<Atividade> atividadesDiaAnterior = new ArrayList<>();

    Iterator<Atividade> iterator = atividadesRealizadas.iterator();
    while (iterator.hasNext()) {
      Atividade atividade = iterator.next();
      if (atividade.getFim().toLocalDate().isEqual(diaAnterior)) {
        atividadesDiaAnterior.add(atividade);
      }
    }

    for (Atividade atividade : atividadesDiaAnterior) {
      if (atividade.getDificuldade()) {
        return true;
      }
    }
    return false;
  }

  public static boolean checkHardActivityDayFollowed( // retorna true se existir pelo menos uma atividade "HARD" no dia atual e no dia anterior.
    Utilizador utilizador,
    LocalDateTime dataComparacao
  ) {
    ArrayList<Atividade> atividadesAgendadas = utilizador.getAtividadesAgendadas();

    List<Atividade> atividadesDia = new ArrayList<>();

    Iterator<Atividade> iterator = atividadesAgendadas.iterator();
    while (iterator.hasNext()) {
      Atividade atividade = iterator.next();
      if (
        atividade.getFim().toLocalDate().isEqual(dataComparacao.toLocalDate())
      ) {
        atividadesDia.add(atividade);
      }
    }

    boolean atividadeDificilDiaAtual = atividadesDia
      .stream()
      .anyMatch(Atividade::getDificuldade);

    return (
      checkHardActivityDayBefore(utilizador, dataComparacao) &&
      atividadeDificilDiaAtual
    );
  }

  public static boolean checkHardActivitiesPlan(ArrayList<Atividade> atividades) {
    int counter = 0;
    Iterator<Atividade> iterator = atividades.iterator();
    while(iterator.hasNext()) {
      Atividade atividade = iterator.next();
      if (atividade.getDificuldade()) {
        counter++;
      }
    }
    return counter < 2;
  }

  public static boolean checkAtividadesDistintas(
    int numDistintas,
    List<Atividade> atividades
  ) {
    int counter = 0;

    Iterator<Atividade> iterator = atividades.iterator();
    ArrayList<String> classes = new ArrayList<>();

    while (iterator.hasNext()) {
      Atividade atividade = iterator.next();
      if (!(classes.contains(atividade.getClasseAtividade().toLowerCase()))) {
        classes.add(atividade.getClasseAtividade().toLowerCase());
      }
    }

    counter = classes.size();

    return counter <= numDistintas;
  }

  public String tipoAtividadeMaisRealizada() {
    int n1 = 0, n2 = 0, n3 = 0, n4 = 0;
    String result = "";

    for (Atividade atividade : this.atividades) {
        if (atividade instanceof Deadlift) {
            n1++;
        } else if (atividade instanceof Canoagem) {
            n2++;
        } else if (atividade instanceof MountainBike) {
            n3++;
        } else if (atividade instanceof Burpees) {
            n4++;
        }
    }

    int n = Math.max(n1, Math.max(n2, Math.max(n3, n4)));
    StringBuilder sb = new StringBuilder();

    if (n == n1) {
        sb.append("Série de repetições com peso");
    }
    if (n == n2) {
        if (sb.length() > 0) sb.append(" e ");
        sb.append("Distância");
    }
    if (n == n3) {
        if (sb.length() > 0) sb.append(" e ");
        sb.append("Distância e altimetria");
    }
    if (n == n4) {
        if (sb.length() > 0) sb.append(" e ");
        sb.append("Série de repetições");
    }

    result = sb.toString();
    return result;
}


  @Override
  public String toString() {
    return this.atividades.toString();
  }
}
