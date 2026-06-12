package com.template;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MainController {

    @FXML private Button btnDeletar;
    @FXML private Button btnAtualizar;
    @FXML private Button btnSalvar;
    @FXML private Button btnLimpar;
    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private TextField txtAnoLancamento;
    @FXML private TextField txtProtagonista;
    @FXML private TextField txtCriador;
    @FXML private TableView<DesenhoDTO> tblDesenho;
    @FXML private TableColumn<DesenhoDTO, Integer> colId;
    @FXML private TableColumn<DesenhoDTO, String> colNome;
    @FXML private TableColumn<DesenhoDTO, Integer> colAnoLancamento;
    @FXML private TableColumn<DesenhoDTO, String> colProtagonista;
    @FXML private TableColumn<DesenhoDTO, String> colCriador;

    @FXML
    private void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colAnoLancamento.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        colProtagonista.setCellValueFactory(new PropertyValueFactory<>("protagonista"));
        colCriador.setCellValueFactory(new PropertyValueFactory<>("criador"));


        btnAtualizar.disableProperty().bind(tblDesenho.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(tblDesenho.getSelectionModel().selectedItemProperty().isNull());

        carregarDesenhos();
    }

    @FXML
    private void carregarDesenhos() {
        DesenhoDAO objDesenhoDAO = new DesenhoDAO();
        ArrayList<DesenhoDTO> listaDesenhos = objDesenhoDAO.selecionarDesenho();
        tblDesenho.setItems(FXCollections.observableArrayList(listaDesenhos));
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        if (txtNome.getText().trim().isEmpty()) {
            return;
        }

        String nome = txtNome.getText();
        String protagonista = txtProtagonista.getText();
        String criador = txtCriador.getText();

        int anoLancamento = 0;
        if (!txtAnoLancamento.getText().trim().isEmpty()) {
            try {
                anoLancamento = Integer.parseInt(txtAnoLancamento.getText().trim());
            } catch (NumberFormatException e) {
                return;
            }
        }



        DesenhoDTO objdesenhodto = new DesenhoDTO();
        objdesenhodto.setNome(nome);
        objdesenhodto.setAnoLancamento(anoLancamento);
        objdesenhodto.setCriador(criador);
        objdesenhodto.setProtagonista(protagonista);

        DesenhoDAO objdesenhodao = new DesenhoDAO();
        objdesenhodao.cadastrarDesenho(objdesenhodto);

        carregarDesenhos();
        btnLimparAction(null);
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtId.clear();
        txtNome.clear();
        txtAnoLancamento.clear();
        txtProtagonista.clear();
        txtCriador.clear();
        tblDesenho.getSelectionModel().clearSelection();
    }

    @FXML
    private void btnAtualizarAction() {
        DesenhoDTO desenhodto = tblDesenho.getSelectionModel().getSelectedItem();

        if (desenhodto != null) {
            try {
                desenhodto.setId(Integer.parseInt(txtId.getText()));
                desenhodto.setNome(txtNome.getText());
                desenhodto.setProtagonista(txtProtagonista.getText());
                desenhodto.setCriador(txtCriador.getText());

                if (!txtAnoLancamento.getText().trim().isEmpty()) {
                    desenhodto.setAnoLancamento(Integer.parseInt(txtAnoLancamento.getText().trim()));
                }

                DesenhoDAO objdesenhodao = new DesenhoDAO();
                objdesenhodao.atualizarDesenho(desenhodto);

                carregarDesenhos();
                btnLimparAction(null);
            } catch (NumberFormatException e) {
            }
        }
    }

    @FXML
    private void btnDeletarAction() {
        DesenhoDTO desenhodto = tblDesenho.getSelectionModel().getSelectedItem();

        if (desenhodto != null) {
            DesenhoDAO objdesenhodao = new DesenhoDAO();
            objdesenhodao.deletarDesenho(desenhodto);

            carregarDesenhos();
            btnLimparAction(null);
        }
    }

    @FXML
    private void carregarCampos() {
        DesenhoDTO desenhoDTO = tblDesenho.getSelectionModel().getSelectedItem();

        if (desenhoDTO != null) {
            txtId.setText(String.valueOf(desenhoDTO.getId()));
            txtNome.setText(desenhoDTO.getNome());
            txtAnoLancamento.setText(String.valueOf(desenhoDTO.getAnoLancamento()));
            txtProtagonista.setText(desenhoDTO.getProtagonista());
            txtCriador.setText(desenhoDTO.getCriador());
        }
    }
}