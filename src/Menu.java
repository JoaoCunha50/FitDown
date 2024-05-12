import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.*;

public class Menu {

  public static void printLogo() {
    // Código ANSI para mudar a cor do texto para vermelho
    String amareloEscuro = "\u001B[33m";
    String azulCiano = "\u001B[36m";

    // Código ANSI para resetar a cor do texto para a cor padrão
    String resetColor = "\u001B[0m";

    StringBuilder sb = new StringBuilder();
    sb.append(amareloEscuro);
    sb.append(
      "███████ ██ ████████     ██████   ██████  ██     ██ ███    ██ \n"
    );
    sb.append(resetColor);
    sb.append(
      "██      ██    ██        ██   ██ ██    ██ ██     ██ ████   ██ \n"
    );
    sb.append(
      "█████   ██    ██        ██   ██ ██    ██ ██  █  ██ ██ ██  ██ \n"
    );
    sb.append(
      "██      ██    ██        ██   ██ ██    ██ ██ ███ ██ ██  ██ ██ \n"
    );
    sb.append(amareloEscuro);
    sb.append(
      "██      ██    ██        ██████   ██████   ███ ███  ██   ████ \n"
    );

    sb.append(resetColor);
    System.out.println(sb.toString());
  }

  public static int MenuInicio(LocalDateTime dataSistema) {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder(
      "             -----------MENU INICIAL-----------\n\n"
    );
    sb.append("1) Iniciar sessão.\n");
    sb.append("2) Registar novo utilizador.\n");
    sb.append("0) Sair.\n\n");
    sb
      .append("Data Atual: ")
      .append(
        dataSistema.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
      )
      .append("\n");
    sb.append("Selecione a opção pretendida: ");
    System.out.println(sb.toString());
    Scanner scanner = new Scanner(System.in);

    if (scanner.hasNextInt()) {
      return scanner.nextInt();
    } else {
      System.out.println("Por favor, escolha uma opção válida!");
      scanner.nextLine();
      System.out.println("Pressione Enter para continuar...");
      scanner.nextLine();
      return MenuInicio(dataSistema);
    }
  }

  public static int MenuUtilizador(Estado estado) {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder(
      "-----------MENU UTILIZADOR-----------\n\n"
    );
    sb.append("1) Registar nova Atividade.\n");
    sb.append("2) Registar novo Plano de Treino.\n");
    sb.append("3) Recomendação aleatória de plano de treino.\n");
    sb.append("4) Consultar Atividades.\n");
    sb.append("5) Consultar Planos de treino.\n");
    sb.append("6) Avançar data.\n");
    sb.append("7) Salvar.\n");
    sb.append("8) Estatísticas.\n");
    sb.append("9) Terminar Sessão.\n");
    sb.append("0) Sair.\n\n");
    sb
      .append("Data Atual: ")
      .append(
        estado
          .getDataEstado()
          .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
      )
      .append("\n");
    sb.append("Selecione a opção pretendida: ");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    if (scanner.hasNextInt()) {
      return scanner.nextInt();
    } else {
      System.out.println("Por favor, escolha uma opção válida!");
      scanner.nextLine();
      System.out.println("Pressione Enter para continuar...");
      scanner.nextLine();
      return MenuUtilizador(estado);
    }
  }

  public static void MenuErro() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder("Erro ao iniciar sessão\n\n");
    sb.append("Email inválido, tente novamente\n");
    System.out.println(sb.toString());
  }

  public static void clear() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static int registeroptions() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------REGISTAR NOVO UTILIZADOR------------").append("\n");
    sb.append("Como se deseja registar?\n\n");
    sb.append("1) Profissional\n");
    sb.append("2) Amador\n");
    sb.append("3) Ocasional\n");
    sb.append("0) Voltar\n").append("\n");
    sb.append("Introduza a opção pretendida: ");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    if (scanner.hasNextInt()) {
      return scanner.nextInt();
    } else {
      System.out.println("Por favor, escolha uma opção válida!");
      scanner.nextLine();
      System.out.println("Pressione Enter para continuar...");
      scanner.nextLine();
      return registeroptions();
    }
  }

  public static int registerAtividadeOptions() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------REGISTAR ATIVIDADE------------").append("\n");
    sb.append("Como deseja registar a atividade?\n\n");
    sb.append("1) Deadlift\n");
    sb.append("2) Mountain Bike\n");
    sb.append("3) Burpees\n");
    sb.append("4) Canoagem\n");
    sb.append("0) Voltar\n");
    sb.append("Introduza a opção pretendida: ");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);

    if (scanner.hasNextInt()) {
      return scanner.nextInt();
    } else {
      System.out.println("Por favor, escolha uma opção válida!");
      scanner.nextLine();
      System.out.println("Pressione Enter para continuar...");
      scanner.nextLine();
      return registerAtividadeOptions();
    }
  }

  public static String register() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------REGISTAR NOVO UTILIZADOR------------").append("\n");
    sb.append(
      "Introduza os seguintes dados com o formato <código>;<nome>;<morada>;<email>;<fcm>;<peso>"
    );
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();

    // Expressão regular para verificar o formato da entrada
    String formato = "\\d+;[^;]+;[^;]+;[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,};\\d+;\\d+";

    if (Pattern.matches(formato, input)) {
      return input;
    } else {
      System.out.println("Formato incorreto. Por favor, insira novamente.");
      System.out.println("Pressione Enter para continuar...");
      scanner.nextLine();
      return register();
    }
  }

  public static String registerAtividade(int tipoAtividade) {
    clear();
    if (tipoAtividade == 1) {
      printLogo();
      StringBuilder sb = new StringBuilder();
      sb.append("------------REGISTAR NOVO DEADLIFT------------").append("\n");
      sb.append(
        "Introduza os seguintes dados com o formato <Data e Hora de inicio>;<Data e Hora de fim>;<duração>;<freqCardiaca>;<Peso da barra>;<Peso Total>;<Numero de repetições>;<Numero de Sets>;<Descanso>"
      );
      System.out.println(sb.toString());
    }
    if (tipoAtividade == 2) {
      clear();
      printLogo();
      StringBuilder sb = new StringBuilder();
      sb.append("------------REGISTAR MOUNTAIN BIKE------------").append("\n");
      sb.append(
        "Introduza os seguintes dados com o formato <Data e Hora de inicio>;<Data e Hora de fim>;<duração>;<freqCardiaca>;<Local>;<distancia>;<altura>;<Tipo de Terreno>;<Equipamento>"
      );
      System.out.println(sb.toString());
    }
    if (tipoAtividade == 3) {
      clear();
      printLogo();
      StringBuilder sb = new StringBuilder();
      sb.append("------------REGISTAR BURPEES------------").append("\n");
      sb.append(
        "Introduza os seguintes dados com o formato <Data e Hora de inicio>;<Data e Hora de fim>;<duração>;<freqCardiaca>;<Numero de Repetições>;<Numero de Sets>;<Descanso>"
      );
      System.out.println(sb.toString());
    }
    if (tipoAtividade == 4) {
      clear();
      printLogo();
      StringBuilder sb = new StringBuilder();
      sb.append("------------REGISTAR CANOAGEM------------").append("\n");
      sb.append(
        "Introduza os seguintes dados com o formato <Data e Hora de inicio>;<Data e Hora de fim>;<duração>;<freqCardiaca>;<Local>;<Distancia Percorrida>;<Velocidade do Vento>;<Profundidade>"
      );
      System.out.println(sb.toString());
    }
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  public static int registerPlanoTreino() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------REGISTAR NOVO PLANO------------").append("\n");
    sb.append("1) Finalizar a introdução do plano de treino\n");
    sb.append("2) Introduzir atividades(mesma data): ");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    if (scanner.hasNextInt()) {
      return scanner.nextInt();
    } else {
      System.out.println("Por favor, escolha uma opção válida!");
      scanner.nextLine();
      System.out.println("Pressione Enter para continuar...");
      scanner.nextLine();
      return registerPlanoTreino();
    }
  }

  public static int registerPlanoTreinoOptions() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------REGISTAR NOVO PLANO------------").append("\n");
    sb.append("1) Quantas Atividades deseja registar no Plano (max 3)\n");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    if (scanner.hasNextInt()) {
      return scanner.nextInt();
    } else {
      System.out.println("Por favor, insira um número inteiro.");
      scanner.nextLine(); // Limpa o buffer do scanner
      return registerPlanoTreinoOptions(); // Chama a função novamente
    }
  }

  public static int login() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------LOGIN------------").append("\n");
    sb.append("1. Escreva seu Email").append("\n");
    sb.append("2. Voltar para o menu principal").append("\n\n");
    sb.append("Introduza a opção pretendida: ");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);

    if (scanner.hasNextInt()) {
      return scanner.nextInt();
    } else {
      System.out.println("Por favor, escolha uma opção válida!");
      scanner.nextLine();
      System.out.println("Pressione Enter para continuar...");
      scanner.nextLine();
      return login();
    }
  }

  public static int avancaData(LocalDateTime dataSistema) {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------MENU DATA------------").append("\n");
    sb
      .append("Data Atual: ")
      .append(
        dataSistema.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
      )
      .append("\n");
    sb.append("Escolha o tipo de avanço que deseja\n\n");
    sb.append("1) Avançar um dia\n");
    sb.append("2) Introduza a opção pretendida (dd/mm/yyyy HH:mm): ");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    if (scanner.hasNextInt()) {
      return scanner.nextInt();
    } else {
      System.out.println("Por favor, escolha uma opção válida!");
      scanner.nextLine();
      System.out.println("Pressione Enter para continuar...");
      scanner.nextLine();
      return avancaData(dataSistema);
    }
  }

  public static int consultarPlanos() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------CONSULTAR PLANOS------------").append("\n");
    sb.append("Que tipo de planos de treino deseja consultar?\n\n");
    sb.append("1) Planos de treino agendados: \n");
    sb.append("2) Planos de treino realizados: \n");
    sb.append("3) Voltar \n");
    sb.append("Introduza a opção pretendida: ");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    if (scanner.hasNextInt()) {
      return scanner.nextInt();
    } else {
      System.out.println("Por favor, escolha uma opção válida!");
      scanner.nextLine();
      System.out.println("Pressione Enter para continuar...");
      scanner.nextLine();
      return consultarPlanos();
    }
  }

  public static int consultarAtividades() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------CONSULTAR ATIVIDADES------------").append("\n");
    sb.append("Que tipo de atividades deseja consultar?\n\n");
    sb.append("1) Atividades agendadas: \n");
    sb.append("2) Atividades realizadas: \n");
    sb.append("3) Voltar \n");
    sb.append("Introduza a opção pretendida: ");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    if (scanner.hasNextInt()) {
      return scanner.nextInt();
    } else {
      System.out.println("Por favor, escolha uma opção válida!");
      scanner.nextLine();
      System.out.println("Pressione Enter para continuar...");
      scanner.nextLine();
      return consultarAtividades();
    }
  }

  public static boolean MenuisHard() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------GERAR PLANO TREINO------------").append("\n");
    sb.append("Deseja ter atividades Difíceis\n");
    sb.append("Introduza a opção pretendida(y/n): ");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    if (input.equals("y")) {
      return true;
    } else if (input.equals("n")) {
      return false;
    } else {
      System.out.println("Por favor, escolha uma opção válida!");
      System.out.println("Pressione Enter para continuar...");
      scanner.nextLine();
      return MenuisHard();
    }
  }

  public static String MenuGeraPlano() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------GERAR PLANO TREINO------------").append("\n");
    sb
      .append(
        "Introduza as suas preferencias <Nº máximo atividades por dia>;<Nº máximo de atividades distintas>;<Repeticão semanal do Plano>;<Consumo calórico mínimo>;<Data(dd/MM/yyyy HH:mm)>: "
      )
      .append("\n");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();

    // expressao que usamos para verificar o input!
    String formato = "\\d+;\\d+;\\d+;\\d+;\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}";

    if (Pattern.matches(formato, input)) {
      return input;
    } else {
      System.out.println("Formato incorreto. Por favor, insira novamente.");
      System.out.println("Pressione Enter para continuar...");
      scanner.nextLine();
      return MenuGeraPlano();
    }
  }

  public static int estatisticas() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------Estatísticas------------").append("\n");
    sb.append("Selecione a estatística pretendida\n\n");
    sb.append("1) Utilizador que mais calorias gastou. \n");
    sb.append("2) Utilizador que mais atividades realizou. \n");
    sb.append("3) Tipo de atividade mais realizada. \n");
    sb.append("4) Kms percorridos por utilizador. \n");
    sb.append("5) Metros de altimetria totalizados por um certo utilizador. \n");
    sb.append("6) Plano de treino mais exigente em função do dispêndio de calorias proposto. \n");
    sb.append("7) Listar as atividades de um utilizador. \n");
    sb.append("0) Voltar \n");
    sb.append("Introduza a opção pretendida: ");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }

  public static int estatistica1() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------Utilizador que mais calorias gastou------------").append("\n");
    sb.append("1) Utilizador que mais calorias gastou desde sempre\n");
    sb.append("2) Utilizador que mais calorias gastou num período ");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }

  public static int estatistica2() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------Utilizador que mais atividades realizou------------").append("\n");
    sb.append("1) Utilizador que mais atividades realizou desde sempre\n");
    sb.append("2) Utilizador que mais atividades realizou num período ");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }

  public static void estatistica3() {
    clear();
    printLogo();
    System.out.println("------------Tipo de Atividade mais Realizado------------\n");
  }

  public static int estatistica4() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------Kms realizados pelo Utilizador------------").append("\n");
    sb.append("1) Kms realizados pelo Utilizador desde sempre\n");
    sb.append("2) Kms realizados pelo Utilizador num período ");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }

  public static int estatistica5() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------Metros de altimetria totalizados pelo Utilizador------------").append("\n");
    sb.append("1) Metros de altimetria totalizados pelo Utilizador desde sempre\n");
    sb.append("2) Metros de altimetria totalizados pelo Utilizador num período ");
    System.out.println(sb.toString());

    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }

  public static void estatistica6() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------Plano de treino com maior gasto calorico------------").append("\n");
    System.out.println(sb.toString());
  }

  public static void estatistica7() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder();
    sb.append("------------Listar as atividades de um Utilizador------------").append("\n");
    System.out.println(sb.toString());
  }

  public static void MenuErroEstatisticas() {
    clear();
    printLogo();
    StringBuilder sb = new StringBuilder("Erro\n\n");
    sb.append("Email inválido, tente novamente\n");
    System.out.println(sb.toString());
  }
}
