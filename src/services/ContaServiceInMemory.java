package services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import Interfaces.IContaService;
import models.Conta;
import util.Constantes;
import view.Menu;

public class ContaServiceInMemory implements IContaService{

	private static HashMap<Long, Conta> contasCC = new HashMap<>();
	private static HashMap<Long, Conta> contasPP = new HashMap<>();
	private static HashMap<Long, Conta> contas;
	
	@Override
	public Long criarConta(Conta c) {
		Long numeroConta = 0L;
		c.setAgencia(Integer.valueOf((int) (Math.random() * 100 + 42)).toString());
		c.setStatusConta(1);
		c.setSaldo(BigDecimal.ZERO);

		if(c.getTipoConta() == 1) {
			numeroConta = contasCC.size() > 0 ? contasCC.size() : 1024L + 1; 
			c.setNumeroConta(numeroConta);
			contasCC.put(numeroConta, c);
		}
		
		if(c.getTipoConta() == 2) {
			numeroConta = contasPP.size() > 0 ? contasPP.size() : (1024L * 1024L) + 1; 
			c.setNumeroConta(numeroConta);
			contasPP.put(numeroConta, c);
		}
		return c.getNumeroConta();
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
	
	public boolean validarConta(Long numeroConta) throws Exception {
		Conta c = listarContas().get(numeroConta);
		if(c == null)
			return false;
		return true;
	}

	@Override
	public boolean depositar(Long numeroConta, BigDecimal valor) throws Exception {
		Conta c = listarContas().get(numeroConta);
		
		if(valor.compareTo(BigDecimal.ZERO) <= 0)
			return false;
		
		c.setSaldo(c.getSaldo().add(valor));
		return true;
	}

	@Override
	public boolean sacar(Long numeroConta, BigDecimal valor) throws Exception {
		Conta c = listarContas().get(numeroConta);
				
		if (c.getSaldo().compareTo(BigDecimal.ZERO) <= 0 || c.getSaldo().compareTo(valor) < 0)
		    throw new Exception("Saldo Insuficiente...");
		
		c.setSaldo(c.getSaldo().subtract(valor));
		return true;
		
	}

	@Override
	public BigDecimal consultarSaldo(Long numeroConta) throws Exception {
		return listarContas().get(numeroConta).getSaldo();
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
