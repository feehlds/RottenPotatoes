package Sessao;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import DataBase.Database;
import Entidades.Perfil;
import Entidades.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import Rotten.App;
public class Login {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inputid;

    @FXML
    private PasswordField inputpswd;

    @FXML
    private Button loginbtn;
    
    @FXML
    private Text welcome;

    @FXML
    private JFXButton btCadastrar;
   
    @FXML
    private Pane telaCad;

    @FXML
    private TextField emailC;

    @FXML
    private PasswordField senhaC;

    @FXML
    private TextField nomeC;

    @FXML
    private TextField sobrenomeC;

    @FXML
    private JFXButton btCadastrar2;
   
    @FXML
    private Text warning;

    
    @FXML
    void initialize() {
        assert inputid != null : "fx:id=\"inputemail\" was not injected: check your FXML file 'Untitled'.";
        assert inputpswd != null : "fx:id=\"inputpswd\" was not injected: check your FXML file 'Untitled'.";
        assert loginbtn != null : "fx:id=\"loginbtn\" was not injected: check your FXML file 'Untitled'.";
    	}  
    
    public void validateLogin() throws Exception {
    	String id = inputid.getText();
    	String pass = inputpswd.getText();
    	//App.db.usuarioRep.validarLogin();
    	Usuario user =  App.db.usuarioRep.validarLogin(id, pass);
    	if(user != null) {
    		UserScreenController uc = new UserScreenController();
    		uc.buildUserScreen(user);
    	}
    	else
    		welcome.setText("Dados Inválidos");
    }
    
    public void cadastrar(){
    	welcome.setVisible(false);
    	telaCad.setVisible(true);
	}
	
	public void salvar() {
		if(validadeText()) {
			Usuario user = new Usuario(emailC.getText(), senhaC.getText(), 0);
			App.db.usuarioRep.add(user);
			Perfil perfil = new Perfil(nomeC.getText(), sobrenomeC.getText(),user.getIdPerfil());
			App.db.perfilRep.add(perfil);
			warning.setText("Agora você é um Rotten Head!");
			emailC.clear();
			senhaC.clear();
			nomeC.clear();
			sobrenomeC.clear();
		}
		else
			warning.setText("Preencha todos os campos");
		

	}
	
	public boolean validadeText() {
		if(emailC.getText().isBlank() || emailC.getText().isEmpty() 
				|| senhaC.getText().isBlank() || senhaC.getText().isEmpty()
				|| nomeC.getText().isBlank() || nomeC.getText().isEmpty()
				|| sobrenomeC.getText().isBlank() || sobrenomeC.getText().isEmpty()) {
			return false;
		}
		else
			return true;
	}

}