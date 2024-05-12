import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class Burpees extends Atividade {

  private int numReps;
  private int numSets;
  private int descanso;

  public Burpees() {
    super();
    this.numReps = 0;
    this.numSets = 0;
    this.descanso = 0;
  }

  public Burpees(
    LocalDateTime inicio,
    LocalDateTime fim,
    int duracao,
    double freq,
    int numReps,
    int numSets,
    int descanso
  ) {
    super(inicio, fim, duracao, freq, "Burpees");
    this.numReps = numReps;
    this.numSets = numSets;
    this.descanso = descanso;
    this.setDificuldade(isBurpeesHard());
  }

  public Burpees(Burpees exercicio) {
    super(exercicio);
    this.numReps = exercicio.getNumReps();
    this.numSets = exercicio.getNumSets();
    this.descanso = exercicio.getDescanso();
    this.setDificuldade(isBurpeesHard());
  }

  public int getNumReps() {
    return this.numReps;
  }

  public void setNumReps(int num) {
    this.numReps = num;
  }

  public int getNumSets() {
    return this.numSets;
  }

  public void setNumSets(int numSets) {
    this.numSets = numSets;
  }

  public int getDescanso() {
    return this.descanso;
  }

  public void setDescanso(int descanso) {
    this.descanso = descanso;
  }

  @Override
  public String toString() {
    DecimalFormat df = new DecimalFormat("#.##");
    String freq = df.format(this.getGastoCalorico());
    return (
      "Burpees;" +
      super.toString() +
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
    Burpees exercicio = (Burpees) o;
    return (
      numReps == exercicio.numReps &&
      numSets == exercicio.numSets &&
      descanso == exercicio.descanso
    );
  }

  public boolean isBurpeesHard() {
    int reps = this.getNumReps();
    int sets = this.getNumSets();
    int descanso = this.getDescanso();

    return (reps >= 20 && sets >= 4 && descanso <= 2);
  }

  @Override
  public Burpees clone() {
    return new Burpees(this);
  }

  public double calculaCalorias(Utilizador utilizador) {
    double calorias = 0;
    double fator = utilizador.calculaFatorCalorico();
    calorias = fator * (0.5 * this.getNumReps() * this.getNumSets());

    this.setGastoCalorico(calorias);
    return calorias;
  }
}
