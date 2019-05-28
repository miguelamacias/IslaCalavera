package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IslaController {
	//Variables de instancia
	Dado[] dados;
	Tirada tirada;
	ImageView[] imagenes;
	
	//Declaración de variables para el SceneBuilder
	@FXML
	private ImageView img1;
	
	@FXML
	private ImageView img2;
	
	@FXML
	private ImageView img3;
	
	@FXML
	private ImageView img4;
	
	@FXML
	private ImageView img5;
	
	@FXML
	private ImageView img6;
	
	@FXML
	private ImageView img7;
	
	@FXML
	private ImageView img8;
	
	@FXML
	private Button tirar;
	
	@FXML
	private Label resultado;
	
	
	
	@FXML
	void initialize() {
		//Inicializa las variables de instancia.
		dados = new Dado[9];
		tirada = new Tirada();
		imagenes = new ImageView[9];
		crearArrayImageViews();
		
		//Crea la array de los 8 dados del juego
		for (int i = 1; i < dados.length; i++) {
			dados[i] = new Dado();
		}
		
		
	}
	
	@FXML
	void tirar(ActionEvent event) {
		//Realiza una tirada y calcula los puntos obtenidos
		int puntos = tirada.calcularPuntuacion(tirada.obtenerTirada(dados));
		resultado.setText(String.valueOf(puntos));
		
		//Establece las imágenes correspondientes de cada dado.
		for (int i = 1; i < dados.length; i++) {
			String rutaImagen = String.format("/res/%s.png", dados[i].toString());
			Image imagen = new Image(rutaImagen);
			imagenes[i].setImage(imagen);
		  }	
	}
	
	private void crearArrayImageViews() {
		//Crea una array con todos los imageviews de los dados
		imagenes[1] = img1;
		imagenes[2] = img2;
		imagenes[3] = img3;
		imagenes[4] = img4;
		imagenes[5] = img5;
		imagenes[6] = img6;
		imagenes[7] = img7;
		imagenes[8] = img8;
		
	}
	
}
