import java.io.*;
import java.time.LocalDateTime;

public class Estado implements Serializable {

  private Utilizadores utilizadores;
  private Atividades atividades;
  private LocalDateTime data;

  public Estado() {
    this.atividades = new Atividades();
    this.utilizadores = new Utilizadores();
  }

  public Atividades getAtividadesEstado() {
    return this.atividades;
  }

  public Utilizadores getUtilizadoresEstado() {
    return this.utilizadores;
  }

  public LocalDateTime getDataEstado(){
    return this.data;
  }

  public void setDataEstado(LocalDateTime data){
    this.data = data;
  }

  public void saveObjectToFile(Estado e) throws IOException {
    ObjectOutputStream out = null;
    try {
      out = new ObjectOutputStream(new FileOutputStream("estado.obj"));
      out.writeObject(e);
      System.out.println("Objeto salvo com sucesso.");
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }
  }

  public Estado readObjectFromFile()
    throws IOException, ClassNotFoundException {
    ObjectInputStream in = null;
    Object obj = null;
    Estado e = null;
    try {
      in = new ObjectInputStream(new FileInputStream("estado.obj"));
      obj = in.readObject();
      e = (Estado) obj;
      System.out.println("Objeto lido com sucesso.");
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }
    return e;
  }

  public void loadEstado(Estado estado) {
    try {
      Estado e = readObjectFromFile();
      estado.getUtilizadoresEstado().setUtilizadores(
        e.getUtilizadoresEstado().getUtilizadores()
      );
      estado.getAtividadesEstado().setAtividades(e.getAtividadesEstado().getAtividades());
      estado.setDataEstado(e.getDataEstado());
    } catch (IOException | ClassNotFoundException e) {}
  }
}
