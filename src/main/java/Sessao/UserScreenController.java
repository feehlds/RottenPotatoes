package Sessao;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import Entidades.Filme;
import Entidades.Perfil;
import Entidades.Usuario;
import Rotten.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

public class UserScreenController implements Initializable {
    @FXML
    private AnchorPane profilePane;
    
    @FXML
    private Text userName = null;
    @FXML
    private JFXButton btProfile;
    @FXML
    private JFXButton btAmigos;
    @FXML
    private JFXButton btFavoritos;
    @FXML
    private JFXButton btBuscar;
    @FXML
    private JFXButton btSair;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private Text welcome;
   
    // INICIO PANE DE PERFIL
    @FXML
    private AnchorPane perfilPane;
    @FXML
    private Text perfilUserName;
    @FXML
    private JFXTextField inNome;
    @FXML
    private JFXButton btNome;
    @FXML
    private Text perfilSobrenome;
    @FXML
    private JFXTextField inSobrenome;
    @FXML
    private JFXButton btSobrenome;
    @FXML
    private Text warning;
    @FXML
    private Text perfilEmail;
    @FXML
    private JFXTextField inEmail;
    @FXML
    private JFXButton btEmail;
    @FXML
    private JFXPasswordField inOldPass;
    @FXML
    private JFXPasswordField inNewPass;
    @FXML
    private JFXButton btMudarSenha;
    @FXML
    private JFXButton btExcluir;

    //COMEÇO FAVORITOS PANE
    @FXML
    private AnchorPane filmesPane;
    @FXML
    private ImageView img1;
    @FXML
    private Text f1;
    @FXML
    private ImageView img2;
    @FXML
    private Text f2;
    @FXML
    private Text msg;
    
    
    
	private AnchorPane ativo; 
    
	private static Usuario user = null;
	private Iterator<Filme> i;
	
	public void buildUserScreen(Usuario user1) throws IOException {
		user = user1;
		Parent root = FXMLLoader.load(getClass().getResource("/Sessao/UserScreen.fxml"));
		Scene scene = new Scene(root);
		App.changeScene(scene);
	}
	
	public void mudarAba(ActionEvent event){
		JFXButton botaoClicado = (JFXButton)event.getSource();
		switch(botaoClicado.getId()){
		case "btProfile" :
			atualizarPerfilPane();
		break;
		
		case "btAmigos" :
			ativo.setVisible(false);
		break;
		
		case "btFavoritos" :
			atualizarFavoritosPane();
		break;
		
		case "btBuscar" :
			ativo.setVisible(false);
		break;
		}
	}
	
	
	public void sair() throws IOException {
		user = null;
		Parent root = FXMLLoader.load(getClass().getResource("/Sessao/Login.fxml"));
		App.changeScene(new Scene(root));
	}
	
	public void initialize(Usuario user) {
		
		
	}
	
	public void atualizarDados(ActionEvent event) {
		JFXButton b = (JFXButton)event.getSource();
		
		switch(b.getId()) {
		case "btNome" :
			if(!inNome.getText().isBlank() && !inNome.getText().isEmpty()) {
				App.db.perfilRep.updateSomethingString(user.getPerfil(), inNome.getText(), "nome");
				atualizarPerfilPane();
				warning.setText("Atualizado");
			}
			else
				warning.setText("Nome vazio");
		break;
		
		case "btSobrenome":
			if(!inSobrenome.getText().isBlank() && !inSobrenome.getText().isEmpty()) {
				App.db.perfilRep.updateSomethingString(user.getPerfil(), inSobrenome.getText(), "sobrenome");
				atualizarPerfilPane();
				warning.setText("Atualizado");
			}
			else
				warning.setText("Sobrenome vazio");
		break;
		}
	}
	
	public void updateUser(ActionEvent event){
		JFXButton b = (JFXButton)event.getSource();
		switch(b.getId()) {
		case "btEmail":
			if(!inEmail.getText().isEmpty() && !inEmail.getText().isBlank()) {
				App.db.usuarioRep.updateSomethingString(user, inEmail.getText(), "email");
				atualizarPerfilPane();
				warning.setText("Atualizado");
			}
			else {
				warning.setText("Digite algo");
			}
			
		break;
		case "btMudarSenha":
			if(!inOldPass.getText().isBlank() && !inOldPass.getText().isEmpty()
					&& !inNewPass.getText().isEmpty() && !inNewPass.getText().isBlank()) {
				
				if(inOldPass.getText().equals(user.getPswd())){
					App.db.usuarioRep.updateSomethingString(user, inNewPass.getText(), "senha");
					warning.setText("Senha alterada");
				}
				else {
					warning.setText("Senha antiga errada");
				}
			}
			else {
				warning.setText("Preencha todos os campos");
			}
		break;
		}
	}
	
	public void excluirConta(ActionEvent event) throws IOException {
		if(!warning.getText().equals("Tem certeza?")) {
			warning.setText("Tem certeza?");
			btExcluir.setText("Sim!");
		}
		else if(warning.getText().equals("Tem certeza?")){
			warning.setText("Mas tudo que passamos juntos?");
			btExcluir.setText("Pai tá online");
			App.db.usuarioRep.remove(user);
			sair();
		}
	}
	
	public void atualizarPerfilPane() {
		ativo.setVisible(false);
		ativo = perfilPane;
		ativo.setVisible(true);
		perfilUserName.setText(user.getPerfil().getName());
		perfilSobrenome.setText(user.getPerfil().getSobrenome());
		perfilEmail.setText(user.getEmail());
		this.userName.setText(""+user.getPerfil().getName()+" "+user.getPerfil().getSobrenome());
		
	}
	
	public void atualizarFavoritosPane() {
		ativo.setVisible(false);
		ativo = filmesPane;
		ativo.setVisible(true);
		if(user.getPerfil().getFilmesFavoritos().size() > 0) {
			i = user.getPerfil().getFilmesFavoritos().iterator();
			if(i.hasNext()) {
				Filme filme = (Filme)i.next();
				Image img = new Image(img(filme));
				img1.setImage(img);
				f1.setText(filme.getNome());
				if(i.hasNext()) {
					Filme filme2 = (Filme)i.next();
					Image img1 = new Image(img(filme2));
					img2.setImage(img1);
					f2.setText(filme2.getNome());
				}
			}
		}
		else {
			msg.setText("Você ainda não tem nenhum");
		}
	}
	
	public void nextFav() {
			if(i.hasNext()) {
				Filme filme = (Filme)i.next();
				Image img = new Image(img(filme));
				img1.setImage(img);
				f1.setText(filme.getNome());
				if(i.hasNext()) {
					Filme filme2 = (Filme)i.next();
					Image img1 = new Image(img(filme2));
					img2.setImage(img1);
					f2.setText(filme2.getNome());
				}
				else {
					img2.setImage(null);;
					f2.setText("");
				}
			}
		else {
			atualizarFavoritosPane();
		}
	}
	
	public String img(Filme filme) {
		return App.db.filmeImg.getUrl(App.db.filmeRep.getId(filme));
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.userName.setText(""+user.getPerfil().getName()+" "+user.getPerfil().getSobrenome());
		this.ativo = contentPane;
	}
	
}
