package Interfaces;

import java.math.BigDecimal;
import java.util.HashMap;

import models.Conta;

public interface IContaService {

	Long criarConta(Conta c);
	
	HashMap<Long, Conta>listarContas();

    Conta buscarContaPorCpf(String cpf);

    boolean depositar(Long numeroConta, BigDecimal valor) throws Exception;

    boolean sacar(Long numeroConta, BigDecimal valor) throws Exception;

    BigDecimal consultarSaldo(Long numeroConta) throws Exception;

    void fecharConta(Long numeroConta) throws Exception;
	
    boolean validarConta(Long numeroConta) throws Exception;
}
