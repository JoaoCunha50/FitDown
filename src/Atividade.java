import java.time.LocalDateTime;
import java.io.*;
import java.text.DecimalFormat;

abstract class Atividade implements Serializable{

  private LocalDateTime inicio;
  private LocalDateTime fim;
  private int duracao;
  private boolean isHard;
  private double freqCardiaca;
  private String classeAtividade;
  private double gastoCalorico;

  public Atividade() {
    this.inicio = null;
    this.fim = null;
    this.duracao = 0;
    this.isHard = false;
    this.freqCardiaca = 0;
    this.classeAtividade = "";
    this.gastoCalorico = 0;
  }

  public Atividade(
    LocalDateTime inicio,
    LocalDateTime fim,
    int duracao,
    double freqCardiaca,
    String atividade
  ) {
    this.inicio = inicio;
    this.fim = fim;
    this.duracao = duracao;
    this.freqCardiaca = freqCardiaca;
    this.classeAtividade = atividade;
    this.gastoCalorico = 0;
  }

  public Atividade(Atividade o) {
    this.inicio = o.getInicio();
    this.fim = o.getFim();
    this.duracao = o.getDuracao();
    this.isHard = o.getDificuldade();
    this.freqCardiaca = o.getFreqCardiaca();
    this.classeAtividade = o.getClasseAtividade();
    this.gastoCalorico = o.getGastoCalorico();
  }

  public LocalDateTime getInicio() {
    return this.inicio;
  }

  public LocalDateTime getFim() {
    return this.fim;
  }

  public int getDuracao() {
    return this.duracao;
  }

  public boolean getDificuldade() {
    return this.isHard;
  }

  public double getFreqCardiaca() {
    return this.freqCardiaca;
  }

  public String getClasseAtividade() {
    return this.classeAtividade;
  }

  public double getGastoCalorico() {
    return this.gastoCalorico;
  }

  public void setGastoCalorico(double calorias){
    this.gastoCalorico = calorias;
  }

  public void setInicio(LocalDateTime inicio) {
    this.inicio = inicio;
  }

  public void setFim(LocalDateTime fim) {
    this.fim = fim;
  }

  public void setDuracao(int duracao) {
    this.duracao = duracao;
  }

  public void setDificuldade(boolean isHard) {
    this.isHard = isHard;
  }

  public void setFreqCardiaca(double freq) {
    this.freqCardiaca = freq;
  }

  public void setClasseAtividade(String classe) {
    this.classeAtividade = classe;
  }

@Override
  public String toString() {
    DecimalFormat df = new DecimalFormat("#.##");
    String freq = df.format(this.getFreqCardiaca());
    return (
      "Início: " +
      Datas.dataToString(this.getInicio()) +
      ";\nFim: " +
      Datas.dataToString(this.getFim()) +
      ";\nDuração: " +
      this.getDuracao() +
      " min;\nDificuldade: " + 
      this.DificuldadeToString() +
      ";\nFrequência cardíaca média durante atividade: " +
      freq +  
      " bpm;\n"
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof Atividade)) return false;
    Atividade atividade = (Atividade) o;

    return (
      this.getDuracao() == atividade.getDuracao() &&
      this.getInicio().equals(atividade.getInicio()) &&
      this.getFim().equals(atividade.getFim()) &&
      this.getDificuldade() == atividade.getDificuldade() &&
      this.getFreqCardiaca() == atividade.getFreqCardiaca() &&
      this.getClasseAtividade().equals(atividade.getClasseAtividade())
    );
  }

  abstract double calculaCalorias(Utilizador utilizador);

  public String DificuldadeToString(){
    if(this.getDificuldade()) return ("Díficil");
    return ("Fácil");
  }
}