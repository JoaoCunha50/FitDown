import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class Datas {

  public static String dataToString(LocalDateTime data) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    return data.format(format);
  }

  public static boolean jaPassou(
    LocalDateTime dataAtividade,
    LocalDateTime dataSistema
  ) {
    return dataSistema.isAfter(dataAtividade);
  }

  /* Esta função irá retornar TRUE caso a data do sistema seja posterior à data da atividade.
   * Isto é, se a atividade já tiver passado
   */

  public static void verificaAtividades(
    Utilizador utilizador,
    LocalDateTime dataSistema
  ) {
    ArrayList<Atividade> atividadesAgendadas = utilizador.getAtividadesAgendadas();
    ArrayList<Atividade> atividadesRealizadas = utilizador.getAtividadesRealizadas();

    Iterator<Atividade> iterator = atividadesAgendadas.iterator();
    while (iterator.hasNext()) {
      Atividade atividade = iterator.next();
      if (jaPassou(atividade.getFim(), dataSistema)) {
        iterator.remove();
        atividadesRealizadas.add(atividade);
      }
    }

    utilizador.setAtividadesAgendadas(atividadesAgendadas);
    utilizador.setAtividadesRealizadas(atividadesRealizadas);
  }

  public static boolean jaPassouData(LocalDate dataAtividade, LocalDate dataSistema){
    return dataSistema.isAfter(dataAtividade);
  }

  public static void verificaPlanosTreino(Utilizador utilizador,LocalDateTime dataSistema){
    ArrayList<PlanoTreino> planosAgendados = utilizador.getPlanosAgendados();
    ArrayList<PlanoTreino> planosRealizados = utilizador.getPlanosRealizados();

    Iterator<PlanoTreino> iterator = planosAgendados.iterator();
    while (iterator.hasNext()) {
      PlanoTreino plano = iterator.next();

      if (jaPassouData(plano.getData(), dataSistema.toLocalDate())) {
        iterator.remove();
        planosRealizados.add(plano);
      }
    }
    utilizador.setPlanosAgendados(planosAgendados);
    utilizador.setPlanosRealizados(planosRealizados);
  }

  public static void verificaAtividadesPlanos(Utilizador utilizador,LocalDateTime dataSistema){
    verificaAtividades(utilizador, dataSistema);
    verificaPlanosTreino(utilizador, dataSistema);
  }
}
