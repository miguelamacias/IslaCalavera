package application;

import java.util.Arrays;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

//TODO refactorizar la clase completa. Es un desastre de código
public class IslaController {
	//Variables de instancia
	private Dado[] dados;
	private Tirada tirada;
	private ImageView[] imagenes;
	private Boolean[] dadosMarcados;
	private CheckBox[] checkboxes;
	private boolean[] dadosBloqueados;
	private int ronda;
	private SingletonBD baseDatos;
	private Carta carta;
	
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
	private TextFlow areaPuntuaciones;
	
	@FXML
	private ScrollPane scrollPuntuaciones;
	
	@FXML
	private ImageView cartaImageView;
	
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
		carta = new Carta();
		
		//Saca una carta y la muestra
		String rutaImagen = String.format("/res/CARTA_%s.png", carta.sacarCarta());
		Image imagen = new Image(rutaImagen);
		cartaImageView.setImage(imagen);
		
		//Carga y configura la tabla de puntuaciones
		Text cabeceraTabla = new Text(String.format("%-16s%-19s%s", "Nombre", "Puntuación", "Fecha"));
		cabeceraTabla.setId("cabeceratabla");
		cabeceraTabla.setFont(Font.font("Consolas", FontWeight.BOLD, 20));
		scrollPuntuaciones.setVbarPolicy(ScrollBarPolicy.NEVER);
		areaPuntuaciones.getChildren().addAll(cabeceraTabla, baseDatos.getPuntuacionesText());
		
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
		int puntos = tirada.calcularPuntuacion(tirada.obtenerTirada(dados, dadosMarcados), carta.getValor());
		
		if (puntos > 0) { //Si es una puntuación normal
			resultado.setText(String.valueOf(puntos)); 
		} else if (puntos < 0 && carta.toString().equals("COFRE")) { //Si salen 3 calaveras pero tiene un cofre que guarda la puntuación
			int puntosCofre = tirada.calcularPuntuacionCofre((tirada.obtenerTiradaCofre(dados, dadosMarcados)));
			resultado.setText(String.valueOf(puntosCofre));
		} else { //Si salen 3 calaveras y no tiene cofre.
			resultado.setText("0");
		}
		
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
			Dado.cambiarEstadoDado(chkDado1);
			Dado.efectosEstadoDado(img1, chkDado1);
			break;
		case "img2":
			Dado.cambiarEstadoDado(chkDado2);
			Dado.efectosEstadoDado(img2, chkDado2);
			break;
		case "img3":
			Dado.cambiarEstadoDado(chkDado3);
			Dado.efectosEstadoDado(img3, chkDado3);
			break;
		case "img4":
			Dado.cambiarEstadoDado(chkDado4);
			Dado.efectosEstadoDado(img4, chkDado4);
			break;
		case "img5":
			Dado.cambiarEstadoDado(chkDado5);
			Dado.efectosEstadoDado(img5, chkDado5);
			break;
		case "img6":
			Dado.cambiarEstadoDado(chkDado6);
			Dado.efectosEstadoDado(img6, chkDado6);
			break;
		case "img7":
			Dado.cambiarEstadoDado(chkDado7);
			Dado.efectosEstadoDado(img7, chkDado7);
			break;
		case "img8":
			Dado.cambiarEstadoDado(chkDado8);
			Dado.efectosEstadoDado(img8, chkDado8);
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
		//se suma la puntuación de la ronda al total
		int acumuladoInt = Integer.parseInt(acumulado.getText());
		acumuladoInt += Integer.parseInt(resultado.getText());
		acumulado.setText(String.valueOf(acumuladoInt));
		
		
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
				imagenes[i].setImage(new Image("res/dado_vacio.jpg"));
				checkboxes[i].setSelected(true);
			}
			
			//Saca una nueva carta y la muestra
			String rutaImagen = String.format("/res/CARTA_%s.png", carta.sacarCarta());
			Image imagen = new Image(rutaImagen);
			cartaImageView.setImage(imagen);
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
		//Mostrar la tabla con la puntuacion actualizada
		areaPuntuaciones.getChildren().clear();
		Text cabeceraTabla = new Text(String.format("%-16s%-19s%s", "Nombre", "Puntuación", "Fecha"));
		cabeceraTabla.setId("cabeceratabla");
		cabeceraTabla.setFont(Font.font("Consolas", FontWeight.BOLD, 20));
		areaPuntuaciones.getChildren().addAll(cabeceraTabla, baseDatos.getPuntuacionesText());
		
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
