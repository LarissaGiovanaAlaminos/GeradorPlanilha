package br.alaminos.control;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

import br.alaminos.utils.StringValidation;

public class CtrlTela {

	CtrlGeraExcel gerarExcel;

	private static String tipoEntrega = "FI";
	private JProgressBar barra;
	JFrame janela_carregamento;

	public String criarExcel(String qtdColaboradores, String localEntrega, String tipoEntrega,
			ButtonGroup grupoBotoes) {
		gerarExcel = new CtrlGeraExcel();

		try {
			if (qtdColaboradores.equals("") || localEntrega.equals("") || getTipoEntrega().equals("")) {
				JOptionPane.showMessageDialog(null, "Possuem campos vazios. " + "\nVerifique os valores digitados");
				return "Erro";
			} else if (!StringValidation.soContemNumeros(qtdColaboradores)) {
				JOptionPane.showMessageDialog(null, "Verifique a quantidade de colaboradores desejada!");
				return "Erro";
			}

			else {
				if (grupoBotoes.getSelection().getActionCommand().equals("Filial")) {
					setTipoEntrega("FI");
				} else {
					setTipoEntrega("PT");
				}
			}

			if (!StringValidation.soContemNumeros(qtdColaboradores)) {
				return "Verifique a quantidade de colaboradores desejada!";
			} else if (tipoEntrega.equals("")) {
				return "Selecione um local de entrega";
			} else if (localEntrega.equals("")) {
				return "Digite o código do local de entrega";
			} else {
				String resultado = gerarExcel.create(qtdColaboradores, localEntrega, tipoEntrega);
				if (resultado.equals("Arquivo Excel criado com sucesso!")) {
					return "Planilha gerada com sucesso!";
				} else if (resultado.equals("Arquivo não encontrado!")) {
					return "Arquivo não encontrado!";
				} else if (resultado.equals("Erro na edição do arquivo!")) {
					return "Erro na edição do arquivo!";
				}
			}

		} catch (NullPointerException erro) {
			JOptionPane.showMessageDialog(null, "Possuem campos vazios. " + "\nVerifique os valores digitados");
			return "Erro";
		}

		return "Erro";
	}

	public void carregamento(String mensagem) {
		janela_carregamento = new JFrame();
		barra = new JProgressBar();
		if (mensagem.equals("Erro"))
			return;

		janela_carregamento.setTitle("Aviso de carregamento");
		janela_carregamento.setLayout(null);
		janela_carregamento.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janela_carregamento.setSize(280, 85);
		janela_carregamento.setLocationRelativeTo(null);
		janela_carregamento.setVisible(true);

		barra.setBounds(30, 10, 200, 30);
		barra.setStringPainted(true);
		barra.setValue(50);
		barra.setMaximum(1000);
		barra.setForeground(new Color(50, 200, 50));
		janela_carregamento.add(barra);
		new Temporizador(mensagem).start();
	}

	public class Temporizador extends Thread {
		String mensagem;

		public Temporizador(String mensagem) {
			this.mensagem = mensagem;
		}

		public void run() {
			while (barra.getValue() < 1000) {
				try {
					sleep(30);
					barra.setValue(barra.getValue() + 10);
				} catch (InterruptedException erro) {
					erro.printStackTrace();
				}

			}
			//janela_carregamento.setVisible(false);
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}

	public String getTipoEntrega() {
		return tipoEntrega;
	}

	public void setTipoEntrega(String tipoEntrega) {
		CtrlTela.tipoEntrega = tipoEntrega;
	}

}