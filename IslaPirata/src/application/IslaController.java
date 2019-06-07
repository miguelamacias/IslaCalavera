package application;



import java.util.Arrays;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
//TODO refactorizar la clase completa. Es un desastre de código
public class IslaController {
	//Variables de instancia
	Dado[] dados;
	Tirada tirada;
	ImageView[] imagenes;
	Boolean[] dadosMarcados;
	CheckBox[] checkboxes;
	boolean[] dadosBloqueados;
	int ronda;
	SingletonBD baseDatos;
	
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
	private Label rondaLbl;
	
	@FXML
	private Button nuevaRonda;
	
	@FXML
	private Label acumulado;
	
	@FXML
	private TextArea areaPuntuaciones;
	
	@FXML
	void initialize() {
		//Inicializa las variables de instancia.
		dados = new Dado[9];
		tirada = new Tirada();
		imagenes = new ImageView[9];
		checkboxes = new CheckBox[9];
		dadosBloqueados = new boolean[9];
		baseDatos = SingletonBD.getInstance();
		ronda = 1;
		acumulado.setText("0");
		inicializarArrays();
		
		areaPuntuaciones.setText(baseDatos.getPuntuaciones());
		
		//Crea la array de los 8 dados del juego
		for (int i = 1; i < dados.length; i++) {
			dados[i] = new Dado();
		}	
	}
	
	@FXML
	void tirar(ActionEvent event) {
		seleccionarDadosMarcados();
		controlCalaveras();
		
		//Realiza una tirada y calcula los puntos obtenidos
		int puntos = tirada.calcularPuntuacion(tirada.obtenerTirada(dados, dadosMarcados));
		resultado.setText(String.valueOf(puntos));
		
		//Establece las imágenes correspondientes de cada dado.
		for (int i = 1; i < dados.length; i++) {
			String rutaImagen = String.format("/res/%s.png", dados[i].toString());
			Image imagen = new Image(rutaImagen);
			imagenes[i].setImage(imagen);
			
			//bloquea las calaveras
			if (dados[i].getValorCara() == 1) {
				dadosBloqueados[i] = true;
				imagenes[i].setEffect(new ColorAdjust(0,0,-0.4,0));
			}
		  }
		
		evitarTiradaIndividual();
		
		//termina el juego por calaveras
		if (puntos == -1) {
			tirar.setDisable(true);
		}
	}
	
	@FXML
	private void controlCalaveras() {
		//Control de calaveras
		for (int i = 0; i < dadosBloqueados.length; i++) {
			if (dadosBloqueados[i]) {
				dadosMarcados[i] = false; 
			}
		}

	}
	
	@FXML
	private void seleccionarDados(MouseEvent event) {
		ImageView origen = (ImageView) event.getSource();
		
		switch (origen.getId()) {
		case "img1":
			cambiarEstadoDado(chkDado1);
			efectosEstadoDado(img1, chkDado1);
			break;
		case "img2":
			cambiarEstadoDado(chkDado2);
			efectosEstadoDado(img2, chkDado2);
			break;
		case "img3":
			cambiarEstadoDado(chkDado3);
			efectosEstadoDado(img3, chkDado3);
			break;
		case "img4":
			cambiarEstadoDado(chkDado4);
			efectosEstadoDado(img4, chkDado4);
			break;
		case "img5":
			cambiarEstadoDado(chkDado5);
			efectosEstadoDado(img5, chkDado5);
			break;
		case "img6":
			cambiarEstadoDado(chkDado6);
			efectosEstadoDado(img6, chkDado6);
			break;
		case "img7":
			cambiarEstadoDado(chkDado7);
			efectosEstadoDado(img7, chkDado7);
			break;
		case "img8":
			cambiarEstadoDado(chkDado8);
			efectosEstadoDado(img8, chkDado8);
			break;

		default:
			break;
		}
		
		evitarTiradaIndividual();
		
		
	}
	
	@FXML
	private void evitarTiradaIndividual() {
		seleccionarDadosMarcados();
		controlCalaveras();
		
		int checkMarcados = 0;
		
		for (int i = 1; i < dadosMarcados.length; i++) {
			if (dadosMarcados[i]) {
				checkMarcados++;
			}
		}
		
		if (checkMarcados < 2) {
			tirar.setDisable(true);
		} else {
			tirar.setDisable(false);
		}
	}
	
	@FXML
	private void nuevaRonda(ActionEvent event) {
		//Si la puntuación no es -1 (calaveras) se suma al total
		if (Integer.parseInt(resultado.getText()) != -1) {
			int acumuladoInt = Integer.parseInt(acumulado.getText());
			acumuladoInt += Integer.parseInt(resultado.getText());
			acumulado.setText(String.valueOf(acumuladoInt));
		}
		
		if (ronda < 10) {
			//Cambia el número de ronda
			rondaLbl.setText(String.valueOf(++ronda));
			//reinicia los datos de la tirada
			Arrays.fill(dadosBloqueados, false);
			Arrays.fill(dadosMarcados, true);
			resultado.setText("0");
			//Activa el boton de jugar
			tirar.setDisable(false);
			//Desactiva los efectos de los dados y reinicia la imagen
			for (int i = 1; i < imagenes.length; i++) {
				imagenes[i].setEffect(new ColorAdjust(0, 0, 0, 0));
				imagenes[i].setImage(new Image("/res/1Calavera.png"));
				checkboxes[i].setSelected(true);
			}
		} else {
			terminarJuego();
		}
		
	}
	
	private void terminarJuego() {
		nuevaRonda.setDisable(true);
		tirar.setDisable(true);
		int acumuladoInt = Integer.parseInt(acumulado.getText());

		TextInputDialog mensajeFinal = new TextInputDialog();
		mensajeFinal.setTitle("Guardar puntuación");
		mensajeFinal.setHeaderText("¡Fin del juego! Has terminado las 10 rondas.");
		mensajeFinal.setContentText("Introduce un nombre para guardar la puntuación:");
		Optional<String> nombre = mensajeFinal.showAndWait();
		
		if (nombre.isPresent()) {
			baseDatos.guardarPuntuacionBD(nombre.get(), acumuladoInt);
		}
		areaPuntuaciones.setText(baseDatos.getPuntuaciones());
		
	}
	
	private void cambiarEstadoDado(CheckBox chck) {
		if (chck.isSelected()) {
			chck.setSelected(false);
		} else {
			chck.setSelected(true);
		}
	}
	
	private void efectosEstadoDado(ImageView img, CheckBox chck) {
		ColorAdjust oscurecer = new ColorAdjust(0,0,-0.5,0);
		ColorAdjust aclarar = new ColorAdjust(0,0,0,0);
		
		if (chck.isSelected()) {
			img.setEffect(aclarar);
		} else {
			img.setEffect(oscurecer);
		}
	}
	
	private void seleccionarDadosMarcados() {
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
		
		//Inicilaiza la array que controla los dados que juegan
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
