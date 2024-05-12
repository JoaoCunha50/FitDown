import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class Deadlift extends Atividade {

  private int pesoBarra;
  private int pesoTotal;
  private int numReps;
  private int numSets;
  private int descanso;

  public Deadlift() {
    super();
    this.pesoBarra = 0;
    this.pesoTotal = 0;
    this.numReps = 0;
    this.numSets = 0;
    this.descanso = 0;
  }

  public Deadlift(
    LocalDateTime inicio,
    LocalDateTime fim,
    int duracao,
    double freq,
    int pesoBarra,
    int pesoTotal,
    int numReps,
    int numSets,
    int descanso
  ) {
    super(inicio, fim, duracao, freq, "Deadlift");
    this.pesoBarra = pesoBarra;
    this.pesoTotal = pesoTotal;
    this.numReps = numReps;
    this.numSets = numSets;
    this.descanso = descanso;
  }

  public Deadlift(Deadlift exercicio) {
    super(exercicio);
    this.pesoBarra = exercicio.getPesoBarra();
    this.pesoTotal = exercicio.getPesoTotal();
    this.numReps = exercicio.getNumReps();
    this.numSets = exercicio.getNumSets();
    this.descanso = exercicio.getDescanso();
  }

  public int getPesoBarra() {
    return this.pesoBarra;
  }

  public void setPesoBarra(int pesobarra) {
    this.pesoBarra = pesobarra;
  }

  public int getPesoTotal() {
    return this.pesoTotal;
  }

  public void setPesoTotal(int pesototal) {
    this.pesoTotal = pesototal;
  }

  public int getNumReps() {
    return this.numReps;
  }

  public void setNumReps(int numreps) {
    this.numReps = numreps;
  }

  public int getNumSets() {
    return this.numSets;
  }

  public void setNumSets(int numsets) {
    this.numSets = numsets;
  }

  public int getDescanso() {
    return this.descanso;
  }

  public void setDescanso(int descanso) {
    this.descanso = descanso;
  }

  public int getPesoDiscos() {
    return this.getPesoTotal() - this.getPesoBarra();
  }

  @Override
  public String toString() {
    DecimalFormat df = new DecimalFormat("#.##");
    String freq = df.format(this.getGastoCalorico());
    return (
      "Deadlift;" +
      super.toString() +
      "Peso Barra: " +
      this.getPesoBarra() +
      " kg;\nPeso total: " +
      this.getPesoTotal() +
      " kg;\n" +
      this.getNumReps() +
      " repetições;\n" +
      this.getNumSets() +
      " sets;\nDescanso: " +
      this.getDescanso() +
      " min;\nGasto Calorico: " +
      freq +
      " Kcal.\n"
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Deadlift exercicio = (Deadlift) o;
    return (
      pesoBarra == exercicio.pesoBarra &&
      pesoTotal == exercicio.pesoTotal &&
      numReps == exercicio.numReps &&
      numSets == exercicio.numSets &&
      descanso == exercicio.descanso
    );
  }

  public boolean isDeadliftHard(Utilizador user) {
    int reps = this.getNumReps();
    int sets = this.getNumSets();
    int descanso = this.getDescanso();
    int pesoTotal = this.getPesoTotal();
    int pesoUser = user.getPeso();

    return (
      (reps >= 8 && sets >= 3 && descanso <= 3) || pesoTotal >= 1.5 * pesoUser
    );
  }

  @Override
  public Deadlift clone() {
    return new Deadlift(this);
  }

  public double calculaCalorias(Utilizador utilizador) {
    double calorias = 0;
    double fator = utilizador.calculaFatorCalorico();
    calorias =
      fator *
      (0.5 * (this.getNumReps() + this.getNumSets() + this.getPesoTotal()));

    this.setGastoCalorico(calorias);
    return calorias;
  }
}
