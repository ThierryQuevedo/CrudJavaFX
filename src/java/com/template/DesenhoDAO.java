package com.template;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.*;

import static java.util.logging.Logger.getLogger;


public class DesenhoDAO {
    private static final Logger logger = getLogger(DesenhoDAO.class.getName());

    public ArrayList<DesenhoDTO> selecionarDesenho() {
        ArrayList<DesenhoDTO> ListaDesenhos = new ArrayList<>();
        String sql = "select * from desenho";

        try (Connection conexao = new Conexao().conectaBD();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                DesenhoDTO desenho = new DesenhoDTO();
                desenho.setId(resultado.getInt("id"));
                desenho.setNome(resultado.getString("nome"));
                desenho.setAnoLancamento(resultado.getInt("ano_lancamento"));
                desenho.setProtagonista(resultado.getString("protagonista"));
                desenho.setCriador(resultado.getString("criador"));
                ListaDesenhos.add(desenho);
            }

        } catch (SQLException erro) {
            logger.log(Level.SEVERE, "Erro ao listar desenho",erro);
        }
        return ListaDesenhos;
    }

    public void cadastrarDesenho(DesenhoDTO desenho) {
        String sql = "insert into desenho (nome, ano_lancamento, protagonista, criador) values (?, ?, ?, ?)";

        try (Connection conexao = new Conexao().conectaBD();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, desenho.getNome());
            comando.setInt(2, desenho.getAnoLancamento());
            comando.setString(3, desenho.getProtagonista());
            comando.setString(4, desenho.getCriador());

            comando.execute();


        } catch (SQLException erro) {
            logger.log(Level.SEVERE, "Erro ao cadastrar desenho",erro);
        }
    }

    public void atualizarDesenho(DesenhoDTO desenho) {
        String sql = "update desenho set nome = ?, ano_lancamento = ?, protagonista = ?, criador = ? where id = ?";

        try (Connection conexao = new Conexao().conectaBD();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, desenho.getNome());
            comando.setInt(2, desenho.getAnoLancamento());
            comando.setString(3, desenho.getProtagonista());
            comando.setString(4, desenho.getCriador());
            comando.setInt(5, desenho.getId());

            comando.execute();

        } catch (SQLException erro) {
            logger.log(Level.SEVERE, "Erro ao atualizar desenho",erro);
        }
    }

    public void deletarDesenho(DesenhoDTO desenho) {
        String sql = "DELETE FROM desenho WHERE id = ?";

        try (Connection conexao = new Conexao().conectaBD();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, desenho.getId());
            comando.execute();

        } catch (SQLException erro) {
            logger.log(Level.SEVERE, "Erro ao deletar desenho",erro);
        }
    }
}