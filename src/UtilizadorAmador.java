import java.io.Serializable;

public class UtilizadorAmador extends Utilizador implements Serializable {

  public UtilizadorAmador() {
    super("", "Amador", "", "", "", 60.00, 0);
  }

  public UtilizadorAmador(
    String codigoUtilizador,
    String tipoUtilizador,
    String nome,
    String morada,
    String email,
    double fcm,
    int peso
  ) {
    super(codigoUtilizador, tipoUtilizador, nome, morada, email, fcm, peso);
  }

  public UtilizadorAmador(Utilizador utilizador) {
    super(utilizador);
  }

  public UtilizadorAmador clone() {
    return new UtilizadorAmador(this);
  }

  public double calculaFatorCalorico() {
    return (1.0 * (this.getfcm() / 60.00));
  }
}
