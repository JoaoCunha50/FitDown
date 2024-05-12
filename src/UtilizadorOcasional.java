import java.io.Serializable;

public class UtilizadorOcasional extends Utilizador implements Serializable{

  public UtilizadorOcasional() {
    super("", "Ocasional", "", "", "", 60.00, 0);
  }

  public UtilizadorOcasional(
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

  public UtilizadorOcasional(Utilizador utilizador){
    super(utilizador);
  }

  public UtilizadorOcasional clone() {
    return new UtilizadorOcasional(this);
  }

  public double calculaFatorCalorico() {
    return (0.8 * (this.getfcm() / 60.00));
  }
}
