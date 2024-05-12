import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class PlanoTreino implements Serializable {

  private ArrayList<Atividade> treino;
  private LocalDate data;

  public PlanoTreino() {
    this.treino = new ArrayList<>();
    this.data = null;
  }

  public PlanoTreino(ArrayList<Atividade> treino, LocalDate data) {
    this.treino = new ArrayList<>(treino);
    this.data = data;
  }

  public PlanoTreino(PlanoTreino plano) {
    this.treino = plano.getTreino();
    this.data = plano.getData();
  }

  public ArrayList<Atividade> getTreino() {
    ArrayList<Atividade> result = new ArrayList<>(this.treino);
    return result;
  }

  public LocalDate getData() {
    return this.data;
  }

  public void setData(LocalDate data) {
    this.data = data;
  }

  public void setTreino(ArrayList<Atividade> treino) {
    this.treino = new ArrayList<>(treino);
  }

  public void setAtividadePlano(Atividade atividade){
    this.treino.add(atividade); 
  }

  public PlanoTreino geraPlanoTreino( //Falta fazer para quando o utilziador nao quer atividades hard
    boolean isHard,
    int n_atividadesDiarias,
    int n_atividadesDistintas,
    int min_calorias,
    Atividades atividades,
    Utilizador utilizador,
    LocalDateTime data
  ) {
    ArrayList<Atividade> atividades_aux = new ArrayList<>();
    int calorias = 0;
    do {
        for (Atividade atividade : atividades.getAtividades()) {
          if (isHard) {
            if (
              atividades_aux.size() < n_atividadesDiarias &&
              !(Atividades.checkHardActivityDayBefore(utilizador, data)) &&
              !(Atividades.checkHardActivityDayFollowed(utilizador, data))
            ) {
              atividades_aux.add(atividade);
              atividade.calculaCalorias(utilizador);
              calorias += atividade.getGastoCalorico();
            }
            if (
              !Atividades.checkHardActivitiesPlan(atividades_aux) ||
              (calorias < min_calorias && atividades_aux.size() == n_atividadesDiarias)
            ) {
              Random random = new Random();
              int index = random.nextInt(atividades_aux.size() - 1);
              calorias -= atividade.getGastoCalorico();
              atividades_aux.remove(index);
            }
            if (calorias > min_calorias) break;
          } else {
            if (!(atividade.getDificuldade())) {
              atividades_aux.add(atividade);
              atividade.calculaCalorias(utilizador);
              calorias += atividade.getGastoCalorico();
            } else continue;
            if (
              !Atividades.checkHardActivitiesPlan(atividades_aux) ||
              (calorias < min_calorias && atividades_aux.size() == n_atividadesDiarias)
            ) {
              Random random = new Random();
              int index = random.nextInt(atividades_aux.size() - 1);
              calorias -= atividade.getGastoCalorico();
              atividades_aux.remove(index);
            }
            if (calorias > min_calorias) break;
          }
        }
    } while (calorias < min_calorias);
    this.setDataAtividades(data, atividades_aux);
    this.setData(data.toLocalDate());
    this.setTreino(atividades_aux);
    return this;
  }

  public void setDataAtividades(
    LocalDateTime data,
    ArrayList<Atividade> atividades
  ) {
    for (Atividade atividade : atividades) {
      atividade.setInicio(data);
      atividade.setFim(
        atividade.getInicio().plusMinutes(atividade.getDuracao())
      );
      data = atividade.getFim();
    }
  }

  public int getDispendioCalorias(){
    int n = 0;
    for (Atividade atividade : this.getTreino()) {
      n += atividade.getGastoCalorico();
    }
    return n;
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return (
      "Este plano de treino que deverá ser realizado em " +
      this.getData().format(formatter) +
      " é constituído por:\n" +
      this.getTreino().toString() +
      "\n"
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof PlanoTreino)) return false;
    PlanoTreino treino = (PlanoTreino) o;

    return (
      this.getTreino().equals(treino.getTreino()) &&
      this.getData().equals(treino.getData())
    );
  }

  @Override
  public PlanoTreino clone() {
    return new PlanoTreino(this);
  }
}
