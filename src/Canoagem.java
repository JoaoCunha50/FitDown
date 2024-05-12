import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Objects;

public class Canoagem extends Atividade {

  private String local;
  private int distanciaTotal;
  private int velocidadeVento;
  private int profundidadeMedia;

  public Canoagem() {
    super();
    this.local = "";
    this.distanciaTotal = 0;
    this.velocidadeVento = 0;
    this.profundidadeMedia = 0;
  }

  public Canoagem(
    LocalDateTime inicio,
    LocalDateTime fim,
    int duracao,
    double freq,
    String local,
    int distanciaTotal,
    int velocidadeVento,
    int profundidadeMedia
  ) {
    super(inicio, fim, duracao, freq, "Canoagem");
    this.local = local;
    this.distanciaTotal = distanciaTotal;
    this.velocidadeVento = velocidadeVento;
    this.profundidadeMedia = profundidadeMedia;
    this.setDificuldade(isCanoagemHard());
  }

  public Canoagem(Canoagem exercicio) {
    super(exercicio);
    this.local = exercicio.getLocal();
    this.distanciaTotal = exercicio.getDistanciaTotal();
    this.velocidadeVento = exercicio.getvelocidadeVento();
    this.profundidadeMedia = exercicio.getProfundidadeMedia();
    this.setDificuldade(isCanoagemHard());
  }

  public String getLocal() {
    return this.local;
  }

  public void setLocal(String local) {
    this.local = local;
  }

  public int getDistanciaTotal() {
    return this.distanciaTotal;
  }

  public void setDistanciaTotal(int distancia) {
    this.distanciaTotal = distancia;
  }

  public int getvelocidadeVento() {
    return this.velocidadeVento;
  }

  public void setvelocidadeVento(int velocidade) {
    this.velocidadeVento = velocidade;
  }

  public int getProfundidadeMedia() {
    return this.profundidadeMedia;
  }

  public void setProfundidadeMedia(int profundidade) {
    this.profundidadeMedia = profundidade;
  }

  @Override
  public String toString() {
    DecimalFormat df = new DecimalFormat("#.##");
    String freq = df.format(this.getGastoCalorico());
    return (
      "Canoagem;" +
      super.toString() +
      "Local: " +
      this.getLocal() +
      ";\nDistancia: " +
      this.getDistanciaTotal() +
      " km;\nVelocidade do vento: " +
      this.getvelocidadeVento() +
      " km/h;\nProfundidade: " +
      this.getProfundidadeMedia() +
      " m;\nGasto calorico: " +
      freq +
      " Kcal.\n"
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Canoagem exercicio = (Canoagem) o;
    return (
      distanciaTotal == exercicio.distanciaTotal &&
      velocidadeVento == exercicio.velocidadeVento &&
      profundidadeMedia == exercicio.profundidadeMedia &&
      Objects.equals(local, exercicio.local) 
    );
  }

  public boolean isCanoagemHard() {
    int distancia = this.getDistanciaTotal();
    int velVento = this.getvelocidadeVento();
    int profundidade = this.getProfundidadeMedia();

    return (distancia >= 10 && velVento >= 18 && profundidade >= 3);
  }

  @Override
  public Canoagem clone() {
    return new Canoagem(this);
  }

  public double calculaCalorias(Utilizador utilizador) {
    double calorias = 0;
    double fator = utilizador.calculaFatorCalorico();
    calorias = fator * (0.8 * this.getDistanciaTotal());
    this.setGastoCalorico(calorias);
    return calorias;
  }
}
