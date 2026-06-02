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
    @FXML private TableColumn<DesenhoDTO, Integer> colAnoLancamento; // Alterado para Integer para bater com o DTO
    @FXML private TableColumn<DesenhoDTO, String> colProtagonista;
    @FXML private TableColumn<DesenhoDTO, String> colCriador;

    @FXML
    private void carregarDesenhos() {
        DesenhoDAO objDesenhoDAO = new DesenhoDAO();
        ArrayList<DesenhoDTO> listaDesenhos = objDesenhoDAO.selecionarDesenho();
        tblDesenho.setItems(FXCollections.observableArrayList(listaDesenhos));
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        // Validação básica: Não permite salvar sem nome
        if (txtNome.getText().trim().isEmpty()) {
            System.out.println("O nome do desenho é obrigatório.");
            return;
        }

        String nome = txtNome.getText();
        String protagonista = txtProtagonista.getText();
        String criador = txtCriador.getText();

        // Trata o ano de lançamento para evitar o erro NumberFormatException se estiver vazio
        int anoLancamento = 0;
        if (!txtAnoLancamento.getText().trim().isEmpty()) {
            try {
                anoLancamento = Integer.parseInt(txtAnoLancamento.getText().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ano de lançamento inválido. Digite apenas números.");
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
    }

    @FXML
    private void btnAtualizarAction() {
        DesenhoDTO desenhodto = tblDesenho.getSelectionModel().getSelectedItem();

        if (desenhodto != null) {
            // Garante que estamos passando o ID correto que veio da tela/banco
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
                System.out.println("Erro ao converter dados numéricos para a edição.");
            }
        } else {
            System.out.println("Selecione um desenho na tabela para editar.");
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
        } else {
            System.out.println("Selecione um desenho na tabela para deletar.");
        }
    }

    @FXML
    private void initialize() {
        // Vincula as colunas da TableView com os atributos declarados na sua classe DesenhoDTO
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colAnoLancamento.setCellValueFactory(new PropertyValueFactory<>("anoLancamento"));
        colProtagonista.setCellValueFactory(new PropertyValueFactory<>("protagonista"));
        colCriador.setCellValueFactory(new PropertyValueFactory<>("criador"));

        carregarDesenhos();
        System.out.println("FXML loaded successfully!");
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