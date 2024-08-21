package Interfaces;

import java.math.BigDecimal;
import java.util.HashMap;

import models.Conta;

public interface IContaService {

	void criarConta(Conta c);
	
	HashMap<Long, Conta>listarContas();

    Conta buscarContaPorCpf(String cpf);

    void depositar(Long numeroConta, BigDecimal valor) throws Exception;

    void sacar(Long numeroConta, BigDecimal valor) throws Exception;

    BigDecimal consultarSaldo(Long numeroConta) throws Exception;

    void fecharConta(Long numeroConta) throws Exception;
	
}
