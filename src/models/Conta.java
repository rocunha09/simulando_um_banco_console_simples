package models;

import java.math.BigDecimal;

/**
 * Conta pode ser abstraída e aprimorada com tipos diferentes de contas e etc.
 * neste caso será uma implementação simples.
 * 
 * há validações que podem ser feitas, tipoConta pode ser diferente e etc.
 */
public class Conta {
	private Long numeroConta;
	private String agencia;
	private String nome;
	private String cpf;
	private int tipoConta;
	private BigDecimal saldo;
	private int statusConta;

	public Conta() {
		super();
	}

	public Long getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Long numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public int getTipoConta() {
		return tipoConta;
	}
	
	public void setTipoConta(int tipoConta) {
		this.tipoConta = tipoConta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		
		if(saldo.compareTo(BigDecimal.ZERO) < 0)
			throw new IllegalArgumentException("valor não pode ser menor ou igual a zero...");
		
		this.saldo = saldo;
	}

	public int getStatusConta() {
		return statusConta;
	}

	public void setStatusConta(int statusConta) {
		this.statusConta = statusConta;
	}
	
	
}
