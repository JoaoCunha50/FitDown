import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Interactive implements Serializable {

  private static Utilizador utilizadorLogado;

  public static void start(String[] args) {
    Scanner scanner = new Scanner(System.in);
    PlanoTreino plano = new PlanoTreino();
    Estado estado = new Estado();
    estado.loadEstado(estado);
    estado
      .getAtividadesEstado()
      .setAtividades(
        Parsing.parseAtividadesInput(
          estado.getAtividadesEstado(),
          utilizadorLogado
        )
      );
    if (estado.getDataEstado() == null) {
      estado.setDataEstado(LocalDateTime.of(2024, 04, 11, 0, 0));
    }

    int opcao;
    int op;
    boolean sair = false;

    while (!sair) {
      opcao = Menu.MenuInicio(estado.getDataEstado());

      switch (opcao) {
        case 1:
          utilizadorLogado = controlaMenuLogin(estado);
          if (utilizadorLogado == null) {
            break;
          }
          int controla = 0;
          while (controla != 8) {
            op = Menu.MenuUtilizador(estado);
            controla = controlaMenuUtilizador(op, estado.getAtividadesEstado(), plano, estado);
          }
          break;
        case 2:
          op = Menu.registeroptions();
          if (op == 0) {
            break;
          }
          String create = Menu.register();
          Controlador.insereUtilizador(
            op,
            create,
            estado.getUtilizadoresEstado()
          );
          System.out.println(
            "Registro realizado com sucesso! Pressione ENTER para prosseguir...\n"
          );
          scanner.nextLine();
          break;
        case 0:
          sair = true;
          System.out.println("Saindo...");
          break;
        default:
          System.out.println(
            "\n\nOpção Inválida! Pressione ENTER para prosseguir...\n"
          );
          scanner.nextLine();

          break;
      }
    }
    scanner.close();
  }

  public static Utilizador controlaMenuLogin(Estado estado) {
    String input;
    int op;
    Scanner scanner = new Scanner(System.in);
    boolean emailValido = false;
    while (!emailValido) {
      op = Menu.login();
      if (op == 1) {
        System.out.println("\nIntroduza o seu email");
        System.out.print("Email: ");
        input = scanner.nextLine();

        if (estado.getUtilizadoresEstado().verificarEmail(input)) {
          System.out.println();
          System.out.println(
            "Login realizado com sucesso! Pressione ENTER para prosseguir...\n"
          );
          scanner.nextLine();
          emailValido = true;
          return estado.getUtilizadoresEstado().getUtilizadorbyChave(input);
        } else {
          Menu.MenuErro();
          System.out.println("Pressione ENTER para prosseguir...\n");
          scanner.nextLine();
        }
      } else if (op == 2) {
        return null;
      } else {
        Menu.MenuErro();
        System.out.println("Pressione ENTER para prosseguir...\n");
        scanner.nextLine();
      }
    }
    return null;
  }

  public static int controlaMenuUtilizador(
    int opcao,
    Atividades atividades,
    PlanoTreino plano,
    Estado estado
  ) {
    int op;
    String create2;
    Scanner scanner = new Scanner(System.in);
    switch (opcao) {
      case 1:
        op = Menu.registerAtividadeOptions();
        if (op == 0) break;
        create2 = Menu.registerAtividade(op);
        Controlador.insereAtividade(
          op,
          create2,
          estado.getAtividadesEstado(),
          utilizadorLogado
        );
        scanner.nextLine();
        break;
      case 2:
        op = Menu.registerPlanoTreino();
        if (op == 2) {
          int n_atividades = Menu.registerPlanoTreinoOptions();
          if (n_atividades > 3) {
            System.out.println(
              "\nO máximo de atividades diárias é 3 , por favor tente novamente ...\n"
            );
            break;
          }
          while (n_atividades > 0) {
            op = Menu.registerAtividadeOptions();
            if (op == 0) {
              break;
            }
            n_atividades--;
            create2 = Menu.registerAtividade(op);
            Controlador.inserePlanos(
              op,
              create2,
              estado.getAtividadesEstado(),
              utilizadorLogado,
              plano
            );
            System.out.println(
              "\n\nAtividade inserida com sucesso! Pressione ENTER para prosseguir...\n"
            );
            scanner.nextLine();
          }

          if (n_atividades == 0 && !(plano.getTreino().isEmpty())) {
            utilizadorLogado.addPlano(plano);
            System.out.println(
              "\n\nPlano adicionado com sucesso! Pressione ENTER para prosseguir...\n"
            );
            System.out.println("Pressione ENTER para prosseguir...\n");
            scanner.nextLine();
          }
        }
        break;
      case 3:
        boolean isHard = Menu.MenuisHard();
        create2 = Menu.MenuGeraPlano();
        Controlador.geraPlano(
          create2,
          isHard,
          utilizadorLogado,
          estado.getAtividadesEstado(),
          estado.getDataEstado()
        );
        scanner.nextLine();
        break;
      case 4:
        op = Menu.consultarAtividades();
        if (op == 1) {
          Datas.verificaAtividadesPlanos(
            utilizadorLogado,
            estado.getDataEstado()
          );
          System.out.println(
            utilizadorLogado.AtividadesAgendadasUtilizadorToString()
          );
          System.out.println("Pressione ENTER para prosseguir...\n");
          scanner.nextLine();
          break;
        } else if (op == 2) {
          Datas.verificaAtividadesPlanos(
            utilizadorLogado,
            estado.getDataEstado()
          );
          System.out.println(
            utilizadorLogado.AtividadesRealizadasUtilizadorToString()
          );
          System.out.println("Pressione ENTER para prosseguir...\n");
          scanner.nextLine();
          break;
        }
        if (op == 3) {
          break;
        } else if (op != 3) {
          System.out.println("A opção selecionada é inválida! \n");
          System.out.println("Pressione ENTER para prosseguir...\n");
          scanner.nextLine();
          break;
        }
      case 5:
        op = Menu.consultarPlanos();
        if (op == 1) {
          Datas.verificaAtividadesPlanos(
            utilizadorLogado,
            estado.getDataEstado()
          );
          System.out.println(
            utilizadorLogado.PlanosAgendadosUtilizadorToString()
          );
          System.out.println("Pressione ENTER para prosseguir...\n");
          scanner.nextLine();
          break;
        } else if (op == 2) {
          Datas.verificaAtividadesPlanos(
            utilizadorLogado,
            estado.getDataEstado()
          );
          System.out.println(
            utilizadorLogado.PlanosRealizadosUtilizadorToString()
          );
          System.out.println("Pressione ENTER para prosseguir...\n");
          scanner.nextLine();
          break;
        }
        if (op == 3) {
          break;
        } else if (op != 3) {
          System.out.println("A opção selecionada é inválida! \n");
          System.out.println("Pressione ENTER para prosseguir...\n");
          scanner.nextLine();
          break;
        }
      case 6:
        op = Menu.avancaData(estado.getDataEstado());
        if (op == 1) {
          estado.setDataEstado(estado.getDataEstado().plusDays(1));
          System.out.println("Pressione ENTER para prosseguir...\n");
          scanner.nextLine();
          break;
        } else if (op == 2) {
          System.out.print("\nData:");
          String dataIntroduzida = scanner.nextLine();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "dd/MM/yyyy HH:mm"
          );
          try {
            LocalDateTime dataNova = LocalDateTime.parse(
              dataIntroduzida,
              formatter
            );
            if (dataNova.isBefore(estado.getDataEstado())) {
              System.out.println(
                "\nA data introduzida é anterior à data do sistema, por favor introduza uma data posterior!"
              );
            } else {
              estado.setDataEstado(dataNova);
              System.out.println(estado.getDataEstado());
            }
          } catch (DateTimeParseException e) {
            System.out.println(
              "\nA data introduzida é inválida, por favor tente novamente!"
            );
          }
        } else {
          System.out.println("Opção inválida.");
        }
        System.out.println("Pressione ENTER para prosseguir...\n");
        scanner.nextLine();
        break;
      case 7:
        try {
          estado.getUtilizadoresEstado().getUtilizadores().remove(utilizadorLogado.getEmail());
          estado.getUtilizadoresEstado().setUtilizador(utilizadorLogado);
          estado.saveObjectToFile(estado);
        } catch (IOException e) {
          e.printStackTrace();
        }
        System.out.println(
          "\nSessão guardada com sucesso! PRESS Enter para prosseguir...\n"
        );
        scanner.nextLine();
        break;
      case 8:
        op = Menu.estatisticas();
        switch (op) {
          case 0:
            break;
          case 1:
            op = Menu.estatistica1();
            if (op == 1){
              System.out.println("\n" + estado.getUtilizadoresEstado().maisCaloriasGastasToString());
              System.out.println("\nPressione ENTER para prosseguir...\n");
              scanner.nextLine();
              break;
            } else if (op == 2){
              System.out.print("\nData de Início(dd/MM/yyyy HH:mm):");
              String dataInicio = scanner.nextLine();
              System.out.print("\nData de Fim(dd/MM/yyyy HH:mm):");
              String dataFim = scanner.nextLine();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "dd/MM/yyyy HH:mm");
              LocalDateTime inicio = LocalDateTime.parse(dataInicio, formatter);
              LocalDateTime fim = LocalDateTime.parse(dataFim, formatter);
              System.out.println("\n" + estado.getUtilizadoresEstado().maisCaloriasGastasIntervaloToString(inicio, fim));
              System.out.println("\nPressione ENTER para prosseguir...\n");
              scanner.nextLine();
            }
            break;
          case 2:
            op = Menu.estatistica2();
            if (op == 1){
              System.out.println("\n" + estado.getUtilizadoresEstado().maisAtividadesRealizadasToString());
              System.out.println("\nPressione ENTER para prosseguir...\n");
              scanner.nextLine();
              break;
            } else if (op == 2){
              System.out.print("\nData de Início(dd/MM/yyyy HH:mm):");
              String dataInicio = scanner.nextLine();
              System.out.print("\nData de Fim(dd/MM/yyyy HH:mm):");
              String dataFim = scanner.nextLine();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "dd/MM/yyyy HH:mm");
              LocalDateTime inicio = LocalDateTime.parse(dataInicio, formatter);
              LocalDateTime fim = LocalDateTime.parse(dataFim, formatter);
              System.out.println("\n" + estado.getUtilizadoresEstado().maisAtividadesRealizadasIntervaloToString(inicio, fim));
              System.out.println("\nPressione ENTER para prosseguir...\n");
              scanner.nextLine();
            }
            break;
          case 3:
            Menu.estatistica3();
            System.out.println(estado.getAtividadesEstado().tipoAtividadeMaisRealizada());
            System.out.println("\nPressione ENTER para prosseguir...\n");
            scanner.nextLine();
            break;
          case 4:
            op = Menu.estatistica4();
            if (op == 1){
              System.out.println("\n" + utilizadorLogado.kmsRealizadosToString());
              System.out.println("\nPressione ENTER para prosseguir...\n");
              scanner.nextLine();
              break;
            } else if (op == 2){
              System.out.print("\nData de Início(dd/MM/yyyy HH:mm):");
              String dataInicio = scanner.nextLine();
              System.out.print("\nData de Fim(dd/MM/yyyy HH:mm):");
              String dataFim = scanner.nextLine();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "dd/MM/yyyy HH:mm");
              LocalDateTime inicio = LocalDateTime.parse(dataInicio, formatter);
              LocalDateTime fim = LocalDateTime.parse(dataFim, formatter);
              System.out.println("\n" + utilizadorLogado.kmsRealizadosIntervaloToString(inicio, fim));
              System.out.println("\nPressione ENTER para prosseguir...\n");
              scanner.nextLine();
            }
            break;
          case 5:
            op = Menu.estatistica5();
            if (op == 1){
              System.out.println("\n" + utilizadorLogado.metrosAltimetriaToString());
              System.out.println("\nPressione ENTER para prosseguir...\n");
              scanner.nextLine();
              break;
            } else if (op == 2){
              System.out.print("\nData de Início(dd/MM/yyyy HH:mm):");
              String dataInicio = scanner.nextLine();
              System.out.print("\nData de Fim(dd/MM/yyyy HH:mm):");
              String dataFim = scanner.nextLine();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "dd/MM/yyyy HH:mm");
              LocalDateTime inicio = LocalDateTime.parse(dataInicio, formatter);
              LocalDateTime fim = LocalDateTime.parse(dataFim, formatter);
              System.out.println("\n" + utilizadorLogado.metrosAltimetriaIntervaloToString(inicio, fim));
              System.out.println("\nPressione ENTER para prosseguir...\n");
              scanner.nextLine();
            }
            break;
          case 6:
            Menu.estatistica6();
            System.out.println(estado.getUtilizadoresEstado().getPlanoTreinoMaisDispendiosoToString());
            System.out.println("\nPressione ENTER para prosseguir...\n");
            scanner.nextLine();
            break;
          case 7:
            Menu.estatistica7();
            System.out.println("Introduza o email do Utilizador:");
            String emailUtilizador = scanner.nextLine();
            if (estado.getUtilizadoresEstado().verificarEmail(emailUtilizador)) {
              System.out.println(estado.getUtilizadoresEstado().getUtilizadorbyChave(emailUtilizador).getAtividadesToString());
            } else {
              Menu.MenuErroEstatisticas();
            }
            //System.out.println(estado.getUtilizadoresEstado().getUtilizadorbyChave(emailUtilizador).getAtividadesToString());
            System.out.println("\nPressione ENTER para prosseguir...\n");
            scanner.nextLine();
            break;
          default:
            System.out.println("Opção inválida.");
            break;
          }
        break;
      case 9:
        System.out.println("\nTem a certeza que quer terminar sessão?(y/n)\n");
        String resposta = scanner.next();
        if (resposta.equalsIgnoreCase("y")) {
          utilizadorLogado = null;
          return 8;
        }
        break;
      case 0:
        System.out.println("Saindo da aplicação!\n");
        // scanner.nextLine();
        System.exit(0);
        break;
      default:
        System.out.println("Opção inválida.");
        break;
    }
    return 0;
  }
}
