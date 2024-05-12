import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controlador {

  public static void insereUtilizador(
    int menu,
    String register,
    Utilizadores utilizadores
  ) {
    utilizadores.setUtilizador(Parsing.parserUtilizador(register, menu));
  }

  public static void insereAtividade(
    int menu,
    String register,
    Atividades atividades,
    Utilizador utilizador
  ) {
    Atividade atividade_aux = null;

    if (menu == 1) {
      atividade_aux = Parsing.parserDeadlift(register, utilizador);
    } else if (menu == 2) {
      atividade_aux = Parsing.parserMountainBike(register, utilizador);
    } else if (menu == 3) {
      atividade_aux = Parsing.parserBurpees(register, utilizador);
    } else if (menu == 4) {
      atividade_aux = Parsing.parserCanoagem(register, utilizador);
    }
    if (atividade_aux == null) {
      return;
    }
    atividade_aux.calculaCalorias(utilizador);
    if (atividade_aux instanceof Deadlift) {
      boolean dif = ((Deadlift) atividade_aux).isDeadliftHard(utilizador);
      atividade_aux.setDificuldade(dif);
    }
    if (!atividades.getAtividades().contains(atividade_aux)) {
      atividades.setAtividade(atividade_aux);
    }

    utilizador.addAtividade(atividade_aux);
    System.out.println(
      "Atividade inserida com sucesso! Pressione ENTER para prosseguir...\n"
    );
  }

  public static void inserePlanos(
    int menu,
    String register,
    Atividades atividades,
    Utilizador utilizador,
    PlanoTreino plano
  ) {
    Atividade atividade_aux = null;

    if (menu == 1) {
      atividade_aux = Parsing.parserDeadlift(register, utilizador);
    } else if (menu == 2) {
      atividade_aux = Parsing.parserMountainBike(register, utilizador);
    } else if (menu == 3) {
      atividade_aux = Parsing.parserBurpees(register, utilizador);
    } else if (menu == 4) {
      atividade_aux = Parsing.parserCanoagem(register, utilizador);
    }

    if (atividade_aux == null) {
      return;
    }

    atividade_aux.calculaCalorias(utilizador);
    if (atividade_aux instanceof Deadlift) {
      boolean dif = ((Deadlift) atividade_aux).isDeadliftHard(utilizador);
      atividade_aux.setDificuldade(dif);
    }
    plano.setData(atividade_aux.getInicio().toLocalDate());
    plano.setAtividadePlano(atividade_aux);
    if (!atividades.getAtividades().contains(atividade_aux)) {
      atividades.setAtividade(atividade_aux);
    }
    utilizador.addAtividade(atividade_aux);
  }

  public static void geraPlano(
    String chave,
    boolean isHard,
    Utilizador utilizador,
    Atividades atividades,
    LocalDateTime dataEstado
  ) {
    String[] parts = chave.split(";");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "dd/MM/yyyy HH:mm"
    );

    int n_atividadesDiarias = Integer.parseInt(parts[0]);
    int n_atividadesDistintas = Integer.parseInt(parts[1]);
    int repetidos = Integer.parseInt(parts[2]);
    int min_calorias = Integer.parseInt(parts[3]);
    LocalDateTime data = LocalDateTime.parse(parts[4], formatter);

    if (n_atividadesDiarias > 3) {
      System.out.println(
        "\nO máximo de atividades diárias é 3 , por favor tente novamente ...\n"
      );
      return;
    }
    if (data.isBefore(dataEstado)) {
      System.out.println(
        "\nA data introduzida é anterior à data do sistema, por favor introduza uma data posterior!"
      );
      return;
    }
    int i = 0;
    while (repetidos > 0) {
      PlanoTreino plano = new PlanoTreino();
      plano.geraPlanoTreino(
        isHard,
        n_atividadesDiarias,
        n_atividadesDistintas,
        min_calorias,
        atividades,
        utilizador,
        data.plusDays(i)
      );
      System.out.println(plano.toString());
      utilizador.addPlano(plano);
      repetidos--;
      i += 2;
    }
    System.out.println("Plano Gerado com sucesso!...\n");
  }
}
