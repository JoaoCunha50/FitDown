import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Objects;

public class MountainBike extends Atividade {

  private String local;
  private int distancia;
  private int altura;
  private String tipoTerreno;
  private String equipamento;

  public MountainBike() {
    super();
    this.local = "";
    this.distancia = 0;
    this.altura = 0;
    this.tipoTerreno = "";
    this.equipamento = "";
  }

  public MountainBike(
    LocalDateTime inicio,
    LocalDateTime fim,
    int duracao,
    double freq,
    String local,
    int distancia,
    int altura,
    String tipoTerreno,
    String equipamento
  ) {
    super(inicio, fim, duracao, freq, "MountainBike");
    this.local = local;
    this.distancia = distancia;
    this.altura = altura;
    this.tipoTerreno = tipoTerreno;
    this.equipamento = equipamento;
    this.setDificuldade(isMountainBikeHard());
  }

  public MountainBike(MountainBike exercicio) {
    super(exercicio);
    this.local = exercicio.getLocal();
    this.distancia = exercicio.getDistancia();
    this.altura = exercicio.getAltura();
    this.tipoTerreno = exercicio.getTipoTerreno();
    this.equipamento = exercicio.getEquipamento();
    this.setDificuldade(isMountainBikeHard());
  }

  public String getLocal() {
    return this.local;
  }

  public void setLocal(String local) {
    this.local = local;
  }

  public int getDistancia() {
    return this.distancia;
  }

  public int getAltura() {
    return this.altura;
  }

  public void setAltura(int altura) {
    this.altura = altura;
  }

  public void setDistancia(int distancia) {
    this.distancia = distancia;
  }

  public String getTipoTerreno() {
    return this.tipoTerreno;
  }

  public void setTipoTerreno(String terreno) {
    this.tipoTerreno = terreno;
  }

  public String getEquipamento() {
    return this.equipamento;
  }

  public void setEquipamento(String equipamento) {
    this.equipamento = equipamento;
  }

  @Override
  public String toString() {
    DecimalFormat df = new DecimalFormat("#.##");
    String freq = df.format(this.getGastoCalorico());
    return (
      "Mountain Bike;" +
      super.toString() +
      "Distancia: " +
      this.getDistancia() +
      " km;\nAltura: " +
      this.getAltura() +
      "m;\nLocal: ;" +
      this.getLocal() +
      ";\nTipo de Terreno: " +
      this.getTipoTerreno() +
      ";\nEquipamento: " +
      this.getEquipamento() +
      ";\nGasto Calorico: " +
      freq +
      " Kcal.\n"
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    MountainBike exercicio = (MountainBike) o;
    return (
      distancia == exercicio.distancia &&
      altura == exercicio.altura &&
      Objects.equals(local, exercicio.local) &&
      Objects.equals(tipoTerreno, exercicio.tipoTerreno) &&
      Objects.equals(equipamento, exercicio.equipamento)
    );
  }


  public boolean isMountainBikeHard(){
    int altura = this.getAltura();
    int distancia = this.getDistancia();
    return (altura >= 500 && distancia >= 10);
  }

  @Override
  public MountainBike clone() {
    return new MountainBike(this);
  }

  public double calculaCalorias(Utilizador utilizador) {
    double calorias = 0;
    double fator = utilizador.calculaFatorCalorico();
    calorias = fator * (0.4 * (this.getDistancia() + this.getAltura()));
    this.setGastoCalorico(calorias);
    return calorias;
  }
}
