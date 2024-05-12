import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

abstract class Utilizador implements Serializable{

  private String codigoUtilizador;
  private String tipoUtilizador;
  private String nome;
  private String morada;
  private String email;
  private double fcm; // informação relativa à sua frequência cardíaca média
  private int peso;
  private ArrayList<Atividade> atividadesAgendadas;
  private ArrayList<Atividade> atividadesRealizadas;
  private ArrayList<PlanoTreino> planosAgendados;
  private ArrayList<PlanoTreino> planosRealizados;

  public Utilizador() {
    this.codigoUtilizador = "";
    this.tipoUtilizador = "";
    this.nome = "";
    this.morada = "";
    this.email = "";
    this.fcm = 60.00;
    this.peso = 0;
    this.atividadesAgendadas = new ArrayList<>();
    this.atividadesRealizadas = new ArrayList<>();
    this.planosAgendados = new ArrayList<>();
    this.planosRealizados = new ArrayList<>();
  }

  public Utilizador(
    String codigoUtilizador,
    String tipoUtilizador,
    String nome,
    String morada,
    String email,
    double fcm,
    int peso
  ) {
    this.codigoUtilizador = codigoUtilizador;
    this.tipoUtilizador = tipoUtilizador;
    this.nome = nome;
    this.morada = morada;
    this.email = email;
    this.fcm = fcm;
    this.peso = peso;
    this.atividadesAgendadas = new ArrayList<>();
    this.atividadesRealizadas = new ArrayList<>();
    this.planosAgendados = new ArrayList<>();
    this.planosRealizados = new ArrayList<>();
  }

  public Utilizador(Utilizador utilizador) {
    this.codigoUtilizador = utilizador.getCodigoUtilizador();
    this.tipoUtilizador = utilizador.getTipoUtilizador();
    this.nome = utilizador.getNome();
    this.morada = utilizador.getMorada();
    this.email = utilizador.getEmail();
    this.fcm = utilizador.getfcm();
    this.peso = utilizador.getPeso();
    this.atividadesAgendadas = utilizador.getAtividadesAgendadas();
    this.atividadesRealizadas = utilizador.getAtividadesRealizadas();
    this.planosAgendados = utilizador.getPlanosAgendados();
    this.planosRealizados = utilizador.getPlanosRealizados();
  }

  public String getCodigoUtilizador() {
    return this.codigoUtilizador;
  }

  public String getTipoUtilizador() {
    return this.tipoUtilizador;
  }

  public String getNome() {
    return this.nome;
  }

  public String getMorada() {
    return this.morada;
  }

  public String getEmail() {
    return this.email;
  }

  public double getfcm() {
    return this.fcm;
  }

  public int getPeso() {
    return this.peso;
  }

  public ArrayList<Atividade> getAtividadesAgendadas() {
    return this.atividadesAgendadas;
  }

  public ArrayList<Atividade> getAtividadesRealizadas() {
    return this.atividadesRealizadas;
  }

  public ArrayList<PlanoTreino> getPlanosAgendados() {
    return this.planosAgendados;
  }

  public ArrayList<PlanoTreino> getPlanosRealizados() {
    return this.planosRealizados;
  }

  public void setPlanosAgendados(ArrayList<PlanoTreino> planos) {
    this.planosAgendados = new ArrayList<PlanoTreino>(planos);
  }

  public void setPlanosRealizados(ArrayList<PlanoTreino> planos) {
    this.planosRealizados = new ArrayList<PlanoTreino>(planos);
  }

  public void setAtividadesAgendadas(ArrayList<Atividade> atividadesAgendadas) {
    this.atividadesAgendadas = new ArrayList<Atividade>(atividadesAgendadas);
  }

  public void setAtividadesRealizadas(
    ArrayList<Atividade> atividadesRealizadas
  ) {
    this.atividadesRealizadas = new ArrayList<Atividade>(atividadesRealizadas);
  }

  public void setCodigoUtilizador(String codigoUtilizador) {
    this.codigoUtilizador = codigoUtilizador;
  }

  public void setTipoUtilizador(String tipoUtilizador) {
    this.tipoUtilizador = tipoUtilizador;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setMorada(String morada) {
    this.morada = morada;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setfcm(double fcm) {
    this.fcm = fcm;
  }

  public void setPeso(int peso) {
    this.peso = peso;
  }

  @Override
  public String toString() {
    return (
      "Código: " +
      this.getCodigoUtilizador() +
      "\nTipo: " +
      this.getTipoUtilizador() +
      "\nNome: " +
      this.getNome() +
      "\nMorada: " +
      this.getMorada() +
      "\nEmail: " +
      this.getEmail() +
      "\nFrequência cardíaca média: " +
      this.getfcm() +
      " bpm\nPeso" +
      this.getPeso() +
      " kg."
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof Utilizador)) return false;
    Utilizador utilizador = (Utilizador) o;

    return (
      this.getCodigoUtilizador().equals(utilizador.getCodigoUtilizador()) &&
      this.getTipoUtilizador().equals(utilizador.getTipoUtilizador()) &&
      this.getMorada().equals(utilizador.getMorada()) &&
      this.getEmail().equals(utilizador.getEmail()) &&
      this.getNome().equals(utilizador.getNome()) &&
      this.getfcm() == utilizador.getfcm() &&
      this.getPeso() == utilizador.getPeso() &&
      this.getAtividadesAgendadas()
        .equals(utilizador.getAtividadesAgendadas()) &&
      this.getAtividadesRealizadas()
        .equals(utilizador.getAtividadesRealizadas()) &&
      this.getPlanosAgendados().equals(utilizador.getPlanosAgendados()) &&
      this.getPlanosRealizados().equals(utilizador.getPlanosRealizados())
    );
  }

  abstract double calculaFatorCalorico();

  public void addAtividade(Atividade atividade) {
    this.atividadesAgendadas.add(atividade);
  }

  public void addPlano(PlanoTreino plano) {
    this.planosAgendados.add(plano);
  }

  public String AtividadesAgendadasUtilizadorToString() {
    if (this.getAtividadesAgendadas().isEmpty()) {
      return (
        "O utilizador " +
        this.getNome() +
        " não tem nenhuma atividade agendada.\n"
      );
    } else return (
      "O utilizador " +
      this.getNome() +
      " tem as seguintes atividades agendadas associadas: \n" +
      this.AtividadesAgendadasToString()
    );
  }

  public String AtividadesAgendadasToString() {
    StringBuilder sb = new StringBuilder();
    int x = 0;
    for (Atividade atividade : this.getAtividadesAgendadas()) {
      x++;
      sb.append("Atividade " + x + ": ");
      sb.append(atividade.toString() + "\n");
    }
    return sb.toString();
  }

  public String AtividadesRealizadasUtilizadorToString() {
    if (this.getAtividadesRealizadas().isEmpty()) {
      return (
        "O utilizador " + this.getNome() + " não realizou nenhuma atividade.\n"
      );
    } else return (
      "O utilizador " +
      this.getNome() +
      " realizou as seguintes atividades: \n" +
      this.AtividadesRealizadasToString()
    );
  }

  public String AtividadesRealizadasToString() {
    StringBuilder sb = new StringBuilder();
    int x = 0;
    for (Atividade atividade : this.getAtividadesRealizadas()) {
      x++;
      sb.append("Atividade " + x + ": ");
      sb.append(atividade.toString() + "\n");
    }
    return sb.toString();
  }

  public String PlanosAgendadosToString() {
    StringBuilder sb = new StringBuilder();
    int x = 0;
    for (PlanoTreino plano : this.getPlanosAgendados()) {
      x++;
      sb.append("Plano " + x + ": ");
      sb.append(plano.toString() + "\n");
    }
    return sb.toString();
  }

  public String PlanosAgendadosUtilizadorToString() {
    if (this.getPlanosAgendados().isEmpty()) {
      return (
        "O utilizador " +
        this.getNome() +
        " não tem nenhum plano de treino agendado"
      );
    }
    return (
      "O utilizador " +
      this.getNome() +
      " tem os seguintes Planos agendados: \n" +
      this.PlanosAgendadosToString()
    );
  }

  public String PlanosRealizadosToString() {
    StringBuilder sb = new StringBuilder();
    int x = 0;
    for (PlanoTreino plano : this.getPlanosRealizados()) {
      x++;
      sb.append("Plano " + x + ": ");
      sb.append(plano.toString() + "\n");
    }
    return sb.toString();
  }

  public String PlanosRealizadosUtilizadorToString() {
    if (this.getPlanosRealizados().isEmpty()) {
      return (
        "O utilizador " +
        this.getNome() +
        " não realizou nenhum plano de treino"
      );
    }
    return (
      "O utilizador " +
      this.getNome() +
      " realizou os seguintes Planos: \n" +
      this.PlanosRealizadosToString()
    );
  }

  public int getCaloriasGastas() {
    int n = 0;
    for (Atividade atividade : this.getAtividadesRealizadas()) {
      n += atividade.getGastoCalorico();
    }
    return n;
  }

  public int getCaloriasGastasIntervalo(LocalDateTime inicio, LocalDateTime fim) {
    int n = 0;
    for (Atividade atividade : this.getAtividadesRealizadas()) {
      if(inicio.compareTo(atividade.getInicio()) <= 0 && atividade.getFim().compareTo(fim) <= 0){
        n += atividade.getGastoCalorico();
      }
    }
    return n;
  }

  public int getnAtividadesRealizadas() {
    return this.atividadesRealizadas.size();
  }

  public int getnAtividadesRealizadasIntervalo(LocalDateTime inicio, LocalDateTime fim) {
    int n = 0;
    for (Atividade atividade : this.getAtividadesRealizadas()) {
      if(inicio.compareTo(atividade.getInicio()) <= 0 && atividade.getFim().compareTo(fim) <= 0){
        n++;
      }
    }
    return n;
  }

  public int getkmsRealizados() {
    int n = 0;
    for (Atividade atividade : this.getAtividadesRealizadas()) {
      if(atividade.getClasseAtividade().equals("Canoagem")){
        Canoagem canoagem = (Canoagem) atividade;
        n += canoagem.getDistanciaTotal();
      } else if(atividade.getClasseAtividade().equals("MountainBike")){
        MountainBike mountainBike = (MountainBike) atividade;
        n += mountainBike.getDistancia();
      }
    }
    return n;
  }

  public int getkmsRealizadosIntervalo(LocalDateTime inicio, LocalDateTime fim) {
    int n = 0;
    for (Atividade atividade : this.getAtividadesRealizadas()) {
      if(inicio.compareTo(atividade.getInicio()) <= 0 && atividade.getFim().compareTo(fim) <= 0){
        if(atividade.getClasseAtividade().equals("Canoagem")){
          Canoagem canoagem = (Canoagem) atividade;
          n += canoagem.getDistanciaTotal();
        } else if(atividade.getClasseAtividade().equals("MountainBike")){
          MountainBike mountainBike = (MountainBike) atividade;
          n += mountainBike.getDistancia();
        }
      }
    }
    return n;
  }

  public String kmsRealizadosToString() {
    int n = this.getkmsRealizados();
    return ("O utlizador " + this.getNome() + " percorreu " + n + " kms");
  }

  public String kmsRealizadosIntervaloToString(LocalDateTime inicio, LocalDateTime fim) {
    int n = this.getkmsRealizadosIntervalo(inicio, fim);
    return ("O utlizador " + this.getNome() + " percorreu " + n + " kms entre "
     + inicio.toString() + " e " + fim.toString());
  }

  public int getMetrosAltimetria() {
    int n = 0;
    for (Atividade atividade : this.getAtividadesRealizadas()) {
      if(atividade.getClasseAtividade().equals("MountainBike")){
        MountainBike mountainBike = (MountainBike) atividade;
        n += mountainBike.getAltura();
      }
    }

    return n;
  }

  public int getMetrosAltimetriaIntervalo(LocalDateTime inicio, LocalDateTime fim) {
    int n = 0;
    for (Atividade atividade : this.getAtividadesRealizadas()) {
      if(inicio.compareTo(atividade.getInicio()) <= 0 && atividade.getFim().compareTo(fim) <= 0){
        if(atividade.getClasseAtividade().equals("MountainBike")){
          MountainBike mountainBike = (MountainBike) atividade;
          n += mountainBike.getAltura();
        }
      }
    }

    return n;
  }

  public String metrosAltimetriaToString() {
    int n = this.getMetrosAltimetria();
    return ("O utlizador " + this.getNome() + " totalizou " + n + " metros de altimetria");
  }

  public String metrosAltimetriaIntervaloToString(LocalDateTime inicio, LocalDateTime fim) {
    int n = this.getMetrosAltimetriaIntervalo(inicio, fim);
    return ("O utlizador " + this.getNome() + " totalizou " + n + " metros de altimetria entre "
     + inicio.toString() + " e " + fim.toString());
  }

  public Object getPlanoTreinoMaisDispendiosoUtilizador() {
    PlanoTreino planoTreino = null;
    if (this.getPlanosRealizados().size() != 0){
      planoTreino = this.getPlanosRealizados().getFirst();
    } else if (this.getPlanosAgendados().size() != 0){
      planoTreino = this.getPlanosAgendados().getFirst();
    } else {
      return planoTreino;
    }
    for (PlanoTreino pt : this.getPlanosRealizados()) {
      if(pt.getDispendioCalorias() > planoTreino.getDispendioCalorias()){
        planoTreino = pt;
      }
    }

    for (PlanoTreino pt : this.getPlanosAgendados()) {
      if(pt.getDispendioCalorias() > planoTreino.getDispendioCalorias()){
        planoTreino = pt;
      }
    }
    return planoTreino;
  }

  public ArrayList<Atividade> getAtividades() {
    ArrayList<Atividade> atividades = new ArrayList<>(this.getAtividadesRealizadas());
    for (Atividade atividade : this.getAtividadesAgendadas()){
      atividades.add(atividade);
    }
    return atividades;
  }

  public String AtividadesToString() {
    StringBuilder sb = new StringBuilder();
    int x = 0;
    for (Atividade atividade : this.getAtividades()) {
      x++;
      sb.append("Atividade " + x + ": ");
      sb.append(atividade.toString() + "\n");
    }
    return sb.toString();
  }

  public String getAtividadesToString() {

    if (this.getAtividades().isEmpty()) {
      return (
        "O utilizador " + this.getNome() + " não tem nenhuma atividade.\n"
      );
    } else return (
      "O utilizador " +
      this.getNome() +
      " tem as seguintes atividades: \n" +
      this.AtividadesToString()
    );
  }
}