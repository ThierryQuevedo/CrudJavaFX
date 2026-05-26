package com.template;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController
{
    @FXML private Button btnDeletar;
    @FXML private Button btnEditar;
    @FXML private Button btnSalvar;
    @FXML private Button btnLimpar;
    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private TextField txtAnoLancamento;
    @FXML private TextField txtProtagonista;
    @FXML private TextField txtCriador;
    @FXML private TableView<DesenhoDTO> tblDesenho;
    @FXML private TableColumn<DesenhoDTO,Integer> colId;
    @FXML private TableColumn<DesenhoDTO,String> colNome;
    @FXML private TableColumn<DesenhoDTO,String> colAnoLancamento;
    @FXML private TableColumn<DesenhoDTO,String> colProtagonista;
    @FXML private TableColumn<DesenhoDTO,String> colCriador;
    @FXML
    private void btnSalvarAction(ActionEvent event){
        int id = Integer.parseInt(txtId.getText());
        String nome = txtNome.getText();
        int anoLancamento = Integer.parseInt(txtAnoLancamento.getText());
        String protagonista = txtProtagonista.getText();
        String criador = txtCriador.getText();

    }
    @FXML
    private void btnLimparAction(ActionEvent event){
        txtId.setText("");
        txtAnoLancamento.setText("");
        txtProtagonista.setText("");
        txtNome.setText("");
        txtCriador.setText("");
    }
    @FXML
    private void initialize()
    {
        System.out.println("FXML loaded successfully!");
    }
}
