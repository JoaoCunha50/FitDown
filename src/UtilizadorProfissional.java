import java.io.Serializable;

public class UtilizadorProfissional extends Utilizador implements Serializable{

  public UtilizadorProfissional() {
    super("", "Profissional", "", "", "", 60.00, 0);
  }

  public UtilizadorProfissional(
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

  public UtilizadorProfissional(Utilizador utilizador) {
    super(utilizador);
  }

  public UtilizadorProfissional clone() {
    return new UtilizadorProfissional(this);
  }

  public double calculaFatorCalorico() {
    return (1.2 * (this.getfcm() / 60.00));
  }
}
