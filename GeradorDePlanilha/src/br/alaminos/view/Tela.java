package br.alaminos.view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.alaminos.control.CtrlTela;

public class Tela implements ActionListener {

	private JFrame janela;
	private JButton gerar;
	private JLabel lblqtdColaboradores;
	private JTextField tfqtdColaboradores;
	private JLabel lblLocalEntrega;
	private JTextField tfLocalEntrega;
	private JRadioButton jrFilial;
	private JRadioButton jrPostoDeTrabalho;
	private ButtonGroup grupoBotoes;
	private CtrlTela ctrltela;

	public void abrir() {
		instanciaComponents();
		adicionaImagens();
		dimensionaComponents();
		adicionaBordaComponents();
		adicionaComponents();
		adicionaEscutador();
		configuraJanela();

	}

	@Override
	public void actionPerformed(ActionEvent o) {
		Object e = o.getSource();
		ctrltela = new CtrlTela();
		if (e.equals(jrFilial)) {
			ctrltela.setTipoEntrega("FI");

		} else if (e.equals(jrPostoDeTrabalho)) {
			ctrltela.setTipoEntrega("PT");

		} else if (e.equals(gerar)) {
			ctrltela.carregamento(ctrltela.criarExcel(tfqtdColaboradores.getText(), tfLocalEntrega.getText(),
					ctrltela.getTipoEntrega(), grupoBotoes));
		}
	}

	public void instanciaComponents() {
		janela = new JFrame();
		lblqtdColaboradores = new JLabel("Nº de Colaboradores");
		tfqtdColaboradores = new JTextField();
		lblLocalEntrega = new JLabel("Local de Entrega");
		tfLocalEntrega = new JTextField();
		gerar = new JButton("Gerar");
		jrFilial = new JRadioButton("Filial");
		jrFilial.setActionCommand("Filial");
		jrFilial.setSelected(true);
		jrPostoDeTrabalho = new JRadioButton("Posto de Trabalho");
		jrPostoDeTrabalho.setActionCommand("Posto de Trabalho");
		grupoBotoes = new ButtonGroup();

	}

	public void adicionaImagens() {
		gerar.setIcon(new ImageIcon(getClass().getResource("/br/alaminos/image/Export.png")));
		janela.setIconImage(
				Toolkit.getDefaultToolkit().getImage(getClass().getResource("/br/alaminos/image/icon.png")));

	}

	public void dimensionaComponents() {
		lblqtdColaboradores.setBounds(10, 10, 150, 30);
		tfqtdColaboradores.setBounds(155, 10, 150, 30);
		lblLocalEntrega.setBounds(10, 50, 150, 30);
		tfLocalEntrega.setBounds(155, 50, 150, 30);

		jrFilial.setBounds(60, 80, 50, 30);
		jrPostoDeTrabalho.setBounds(115, 80, 150, 30);
		gerar.setBounds(105, 120, 130, 30);

	}

	public void adicionaBordaComponents() {
		tfqtdColaboradores.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tfLocalEntrega.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	public void adicionaComponents() {
		janela.getContentPane().add(lblqtdColaboradores);
		janela.getContentPane().add(tfqtdColaboradores);
		janela.getContentPane().add(lblLocalEntrega);
		janela.getContentPane().add(tfLocalEntrega);
		janela.getContentPane().add(gerar);
		janela.getContentPane().add(jrFilial);
		janela.getContentPane().add(jrPostoDeTrabalho);
		adicionaNoGrupoDeBotoes();
	}

	public void adicionaNoGrupoDeBotoes() {
		grupoBotoes.add(jrFilial);
		grupoBotoes.add(jrPostoDeTrabalho);
	}

	public void adicionaEscutador() {
		jrFilial.addActionListener(this);
		jrPostoDeTrabalho.addActionListener(this);
		gerar.addActionListener(this);
	}

	public void configuraJanela() {
		janela.setTitle("Importar colaborador");
		janela.setSize(330, 200);
		janela.setLayout(null);
		janela.setLocationRelativeTo(null);
		janela.setResizable(false);
		janela.setVisible(true);
	}

}
