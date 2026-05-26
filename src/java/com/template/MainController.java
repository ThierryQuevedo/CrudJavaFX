package com.template;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

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
    @FXML private void carregarDesenhos(){
        DesenhoDAO objDesenhoDAO = new DesenhoDAO();
        ArrayList<DesenhoDTO> listaDesenhos = objDesenhoDAO.selecionarDesenho();
        tblDesenho.setItems(FXCollections.observableArrayList(listaDesenhos));
    }
    @FXML private void btnSalvarAction(ActionEvent event){
        int id = Integer.parseInt(txtId.getText());
        String nome = txtNome.getText();
        int anoLancamento = Integer.parseInt(txtAnoLancamento.getText());
        String protagonista = txtProtagonista.getText();
        String criador = txtCriador.getText();

        DesenhoDTO objdesenhodto = new DesenhoDTO();
        objdesenhodto.setNome(nome);
        objdesenhodto.setAnoLancamento(anoLancamento);
        objdesenhodto.setCriador(criador);
        objdesenhodto.setProtagonista(protagonista);

        DesenhoDAO objdesenhodao = new DesenhoDAO();
        objdesenhodao.cadastrarDesenho(objdesenhodto);

        carregarDesenhos();
    }
    @FXML private void btnLimparAction(ActionEvent event){
        txtId.setText("");
        txtAnoLancamento.setText("");
        txtProtagonista.setText("");
        txtNome.setText("");
        txtCriador.setText("");
    }
    @FXML private void btnEditarAction(){
        DesenhoDTO desenhodto = tblDesenho.getSelectionModel().getSelectedItem();

        if (desenhodto != null) {
            desenhodto.setNome(txtNome.getText());
            desenhodto.setAnoLancamento(Integer.parseInt(txtAnoLancamento.getText()));
            desenhodto.setProtagonista(txtProtagonista.getText());
            desenhodto.setCriador(txtCriador.getText());


            DesenhoDAO objdesenhodao = new DesenhoDAO();
            objdesenhodao.atualizarDesenho(desenhodto);

            carregarDesenhos();
            btnLimparAction(null);
        }
    }
    @FXML private void btnDeletarAction(){
        DesenhoDTO desenhodto = tblDesenho.getSelectionModel().getSelectedItem();

        if (desenhodto != null) {
            DesenhoDAO objdesenhodao = new DesenhoDAO();
            objdesenhodao.deletarDesenho(desenhodto);

            carregarDesenhos();
            btnLimparAction(null);
        }
    }
    @FXML private void initialize()
    {
        System.out.println("FXML loaded successfully!");
    }
}
