package Rotten;
import DataBase.Database;

import Entidades.*;
import Repositorios.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
	 
	public static Database db;
	
	@Override
	public void start(Stage primaryStage) {
			try {
				//URL url = new File("src/Sessao/login.src").toURL();
				Parent root = FXMLLoader.load(getClass().getResource("/Sessao/Login.fxml"));
				
				Scene scene = new Scene(root);
				
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	public static void main(String[] args) throws Exception {
		db = Database.runDb();
        launch(args);
       
       
        // ADICIONAR REPOSITÓRIO DE PERFIL, E COLOCAR PRA QUANDO DELETER USUARIO DELETAR PERFIL
    }
}
