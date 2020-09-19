package br.alaminos.model;

public class Colaborador {
	private String nome_usuario;
	private String cpf;
	private String data_nascimento;
	private double valor;
	private String tipo;
	private String local_entrega;

	public Colaborador(String nome_usuario, String cPF, String data_nascimento, double valor, String tipo,
			String local_entrega) {
		this.nome_usuario = nome_usuario;
		cpf = cPF;
		this.data_nascimento = data_nascimento;
		this.valor = valor;
		this.tipo = tipo;
		this.local_entrega = local_entrega;
	}

	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cPF) {
		cpf = cPF;
	}

	public String getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String _tipo) {
		tipo = _tipo;
	}

	public String getLocal_entrega() {
		return local_entrega;
	}

	public void setLocal_entrega(String local_entrega) {
		this.local_entrega = local_entrega;
	}

}
