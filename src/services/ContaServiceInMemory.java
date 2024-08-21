package services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import Interfaces.IContaService;
import models.Conta;

public class ContaServiceInMemory implements IContaService{

	private static HashMap<Long, Conta> contasCC = new HashMap<>();
	private static HashMap<Long, Conta> contasPP = new HashMap<>();
	private static HashMap<Long, Conta> contas;
	
	@Override
	public void criarConta(Conta c) {
		Long numeroConta = 0L;
		c.setAgencia(Integer.valueOf((int) (Math.random() * 100 + 42)).toString());
		c.setStatusConta(1);

		if(c.getTipoConta() == 1) {
			numeroConta = contasCC.size() > 0 ? contasCC.size() : 1024L + 1; 
			c.setNumeroConta(numeroConta);
			contasCC.put(numeroConta, c);
			return;
		}
		
		if(c.getTipoConta() == 2) {
			numeroConta = contasPP.size() > 0 ? contasPP.size() : (1024L * 1024L) + 1; 
			c.setNumeroConta(numeroConta);
			contasPP.put(numeroConta, c);
			return;
		}
		
	}
		
	@Override
	public HashMap<Long, Conta> listarContas() {
		contas = new HashMap<>(contasCC);
		contas.putAll(contasPP);
		return contas;
	}

	@Override
	public Conta buscarContaPorCpf(String cpf) {
		
		for(Map.Entry<Long, Conta> e : contas.entrySet()) {
			Conta c = e.getValue();
			if(c.getCpf().equals(cpf))
				return c;
		}
		return null;
	}

	@Override
	public void depositar(Long numeroConta, BigDecimal valor) throws Exception {
		Conta c = listarContas().get(numeroConta);
		if(c == null)
			throw new Exception("Conta não Encontrada...");
		
		if(valor.compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException("valor não pode ser menor ou igual a zero...");
		
		c.setSaldo(c.getSaldo().add(valor));
	}

	@Override
	public void sacar(Long numeroConta, BigDecimal valor) throws Exception {
		Conta c = listarContas().get(numeroConta);
		if(c == null)
			throw new Exception("Conta não Encontrada...");
		
		if(valor.compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException("valor não pode ser menor ou igual a zero...");
		
		if(c.getSaldo().compareTo(BigDecimal.ZERO) <= 0)
			throw new Exception("Saldo Insuficiente...");
		
		c.setSaldo(c.getSaldo().subtract(valor));
		
	}

	@Override
	public BigDecimal consultarSaldo(Long numeroConta) throws Exception {
		Conta c = listarContas().get(numeroConta);
		if(c == null)
			throw new Exception("Conta não Encontrada...");
		
		
		return c.getSaldo();
	}

	@Override
	public void fecharConta(Long numeroConta) throws Exception {
		Conta c = listarContas().get(numeroConta);
		if(c == null)
			throw new Exception("Conta não Encontrada...");
		
		if(c.getSaldo().compareTo(BigDecimal.ZERO) > 1)
			throw new Exception("Conta não pode se encerrada, saldo maior que o permitido...");
		
		c.setStatusConta(0);
		
	}

}
