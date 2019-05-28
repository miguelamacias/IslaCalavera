package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IslaController {
	//Variables de instancia
	Dado[] dados;
	Tirada tirada;
	ImageView[] imagenes;
	Boolean[] dadosMarcados;
	CheckBox[] checkboxes;
	
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
	private CheckBox chkDado1;
	
	@FXML
	private CheckBox chkDado2;
	
	@FXML
	private CheckBox chkDado3;
	
	@FXML
	private CheckBox chkDado4;
	
	@FXML
	private CheckBox chkDado5;
	
	@FXML
	private CheckBox chkDado6;
	
	@FXML
	private CheckBox chkDado7;
	
	@FXML
	private CheckBox chkDado8;
	
	
	@FXML
	void initialize() {
		//Inicializa las variables de instancia.
		dados = new Dado[9];
		tirada = new Tirada();
		imagenes = new ImageView[9];
		checkboxes = new CheckBox[9];
		inicializarArrays();
		
		//Crea la array de los 8 dados del juego
		for (int i = 1; i < dados.length; i++) {
			dados[i] = new Dado();
		}	
	}
	
	@FXML
	void tirar(ActionEvent event) {
		marcarDados();
		
		//Realiza una tirada y calcula los puntos obtenidos
		int puntos = tirada.calcularPuntuacion(tirada.obtenerTirada(dados, dadosMarcados));
		resultado.setText(String.valueOf(puntos));
		
		//Establece las imágenes correspondientes de cada dado.
		for (int i = 1; i < dados.length; i++) {
			String rutaImagen = String.format("/res/%s.png", dados[i].toString());
			Image imagen = new Image(rutaImagen);
			imagenes[i].setImage(imagen);
		  }
	}
	
	private void marcarDados() {
		dadosMarcados[1] = chkDado1.isSelected();
		dadosMarcados[2] = chkDado2.isSelected();
		dadosMarcados[3] = chkDado3.isSelected();
		dadosMarcados[4] = chkDado4.isSelected();
		dadosMarcados[5] = chkDado5.isSelected();
		dadosMarcados[6] = chkDado6.isSelected();
		dadosMarcados[7] = chkDado7.isSelected();
		dadosMarcados[8] = chkDado8.isSelected();
	}
	
	private void inicializarArrays() {
		//Crea una array con todos los imageviews de los dados
		imagenes[1] = img1;
		imagenes[2] = img2;
		imagenes[3] = img3;
		imagenes[4] = img4;
		imagenes[5] = img5;
		imagenes[6] = img6;
		imagenes[7] = img7;
		imagenes[8] = img8;
		
		//Crea la array de dados a jugar
		dadosMarcados = new Boolean[9];
		dadosMarcados[1] = true;
		dadosMarcados[2] = true;
		dadosMarcados[3] = true;
		dadosMarcados[4] = true;
		dadosMarcados[5] = true;
		dadosMarcados[6] = true;
		dadosMarcados[7] = true;
		dadosMarcados[8] = true;	
		
		//Crea la array de checkboxes
		
		checkboxes[1] = chkDado1;
		checkboxes[2] = chkDado2;
		checkboxes[3] = chkDado3;
		checkboxes[4] = chkDado4;
		checkboxes[5] = chkDado5;
		checkboxes[6] = chkDado6;
		checkboxes[7] = chkDado7;
		checkboxes[8] = chkDado8;
	}
	
}
