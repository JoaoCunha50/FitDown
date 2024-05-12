import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parsing {

  public static Utilizador parserUtilizador(String chave, int tipo) {
    String tipoUser = null;

    String[] parts = chave.split(";");
    String codigoUtilizador = parts[0];
    String nome = parts[1].trim();
    String morada = parts[2].trim();
    String email = parts[3].trim();
    Double fcm = Double.parseDouble(parts[4]);
    int peso = Integer.parseInt(parts[5]);

    if (tipo == 1) {
      tipoUser = "PROFISSIONAL";
      UtilizadorProfissional utilizador = new UtilizadorProfissional(
        codigoUtilizador,
        tipoUser,
        nome,
        morada,
        email,
        fcm,
        peso
      );
      return utilizador;
    } else if (tipo == 2) {
      tipoUser = "AMADOR";
      UtilizadorAmador utilizador = new UtilizadorAmador(
        codigoUtilizador,
        tipoUser,
        nome,
        morada,
        email,
        fcm,
        peso
      );
      return utilizador;
    } else if (tipo == 3) {
      tipoUser = "OCASIONAL";
      UtilizadorOcasional utilizador = new UtilizadorOcasional(
        codigoUtilizador,
        tipoUser,
        nome,
        morada,
        email,
        fcm,
        peso
      );
      return utilizador;
    }
    return null;
  }

  public static Deadlift parserDeadlift(String chave, Utilizador utilizador) {
    String[] parts = chave.split(";");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "dd/MM/yyyy HH:mm"
    );
    try {
      Deadlift atividade = new Deadlift(
        LocalDateTime.parse(parts[0], formatter),
        LocalDateTime.parse(parts[1], formatter),
        Integer.parseInt(parts[2]),
        Double.parseDouble(parts[3]),
        Integer.parseInt(parts[4]),
        Integer.parseInt(parts[5]),
        Integer.parseInt(parts[6]),
        Integer.parseInt(parts[7]),
        Integer.parseInt(parts[8])
      );
      return atividade;
    } catch (
      DateTimeParseException
      | NumberFormatException
      | ArrayIndexOutOfBoundsException e
    ) {
      System.err.println(
        "Erro ao fazer o parsing da atividade: " +
        e.getMessage() +
        "\n" +
        "Pressione uma tecla para prosseguir"
      );
      return null;
    }
  }

  public static MountainBike parserMountainBike(
    String chave,
    Utilizador utilizador
  ) {
    String[] parts = chave.split(";");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "dd/MM/yyyy HH:mm"
    );
    try {
      MountainBike atividade = new MountainBike(
        LocalDateTime.parse(parts[0], formatter),
        LocalDateTime.parse(parts[1], formatter),
        Integer.parseInt(parts[2]),
        Double.parseDouble(parts[3]),
        parts[4],
        Integer.parseInt(parts[5]),
        Integer.parseInt(parts[6]),
        parts[7],
        parts[8]
      );
      return atividade;
    } catch (
      DateTimeParseException
      | NumberFormatException
      | ArrayIndexOutOfBoundsException e
    ) {
      System.err.println(
        "Erro ao fazer o parsing da atividade: " +
        e.getMessage() +
        "\n" +
        "Pressione uma tecla para prosseguir"
      );
      return null;
    }
  }

  public static Burpees parserBurpees(String chave, Utilizador utilizador) {
    String[] parts = chave.split(";");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "dd/MM/yyyy HH:mm"
    );

    try {
      Burpees atividade = new Burpees(
        LocalDateTime.parse(parts[0], formatter),
        LocalDateTime.parse(parts[1], formatter),
        Integer.parseInt(parts[2]),
        Double.parseDouble(parts[3]),
        Integer.parseInt(parts[4]),
        Integer.parseInt(parts[5]),
        Integer.parseInt(parts[6])
      );
      return atividade;
    } catch (
      DateTimeParseException
      | NumberFormatException
      | ArrayIndexOutOfBoundsException e
    ) {
      System.err.println(
        "Erro ao fazer o parsing da atividade: " +
        e.getMessage() +
        "\n" +
        "Pressione uma tecla para prosseguir"
      );
      return null;
    }
  }

  public static Canoagem parserCanoagem(String chave, Utilizador utilizador) {
    String[] parts = chave.split(";");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "dd/MM/yyyy HH:mm"
    );
    try {
      Canoagem atividade = new Canoagem(
        LocalDateTime.parse(parts[0], formatter),
        LocalDateTime.parse(parts[1], formatter),
        Integer.parseInt(parts[2]),
        Double.parseDouble(parts[3]),
        parts[4],
        Integer.parseInt(parts[5]),
        Integer.parseInt(parts[6]),
        Integer.parseInt(parts[7])
      );
      return atividade;
    } catch (
      DateTimeParseException
      | NumberFormatException
      | ArrayIndexOutOfBoundsException e
    ) {
      System.err.println(
        "Erro ao fazer o parsing da atividade: " +
        e.getMessage() +
        "\n" +
        "Pressione uma tecla para prosseguir"
      );
      return null;
    }
  }

  public static List<Atividade> parseAtividadesInput(
    Atividades atividades,
    Utilizador utilizador
  ) {
    List<Atividade> atividades_aux = new ArrayList<>();
    try {
      File input = new File("input/activities.txt");
      Scanner reader = new Scanner(input);
      while (reader.hasNextLine()) {
        String line = reader.nextLine();
        String[] parts = line.split(";");
        String activitiesData = String.join(
          ";",
          Arrays.copyOfRange(parts, 1, parts.length)
        );
        Atividade atividade = null;

        String nomeAtividade = parts[0].toLowerCase();
        if (nomeAtividade.equals("deadlift")) {
          atividade = parserDeadlift(activitiesData, utilizador);
        } else if (nomeAtividade.equals("burpees")) {
          atividade = parserBurpees(activitiesData, utilizador);
        } else if (nomeAtividade.equals("canoagem")) {
          atividade = parserCanoagem(activitiesData, utilizador);
        } else if (nomeAtividade.equals("mountainbike")) {
          atividade = parserMountainBike(activitiesData, utilizador);
        }
        if(!atividades.getAtividades().contains(atividade)){
            atividades_aux.add(atividade);
        }
      }
      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println("Ocorreu um erro ao abrir o arquivo.");
      e.printStackTrace();
    } catch (NumberFormatException e) {
      System.out.println("Erro de formato numérico: " + e.getMessage());
      e.printStackTrace();
    }

    return atividades_aux;
  }
}
