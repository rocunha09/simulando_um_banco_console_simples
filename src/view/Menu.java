package view;

import java.math.BigDecimal;
import java.util.Scanner;

import Interfaces.IContaService;
import models.Conta;
import util.Console;
import util.Constantes;

public class Menu {
	private static IContaService contaService;
	
	public static void defineContaService(IContaService cs) {
		contaService = cs;
	}
	
	public static int menuPrincipal(Scanner scan) {
		StringBuilder str = new StringBuilder();
		
		str.append("-------------------\n");
		str.append("Bem Vind@ ao banco digital dio! selecione a opção desejada:\n");
		str.append("\n");
		str.append("1. Criar Conta\n");
		str.append("2. Consultar Saldo\n");
		str.append("3. Realizar Depósito\n");
		str.append("4. Realizar Saque\n");
		str.append("5. Sair\n");
		str.append("-------------------\n");
		str.append("\n \n");
		System.out.println(str);
		
		return scan.nextInt();
	}
	
	public static int criarConta(Scanner scan) {
		int option = 0;
		Conta c = new Conta();
		
		Console.clearTerminal(Constantes.CLEAR);
		scan.nextLine();
		
		System.out.println("-------------------\n");
		System.out.println("Criar Conta:\n");
		System.out.println("   - Passo 1: Informe seu nome completo.\n");
		String nome = scan.nextLine();
		if(nome.equals("") || nome == null)
			return exibeMensagem(Constantes.MSG_02);
		c.setNome(nome);
		
		System.out.println("   - Passo 2: Informe seu CPF (somente números).\n");
		String cpf = scan.nextLine();
		if(cpf.equals("") || cpf == null)
			return exibeMensagem(Constantes.MSG_03);
		c.setCpf(cpf);
		
		System.out.println("   - Passo 3: Escolha o tipo de conta (1 = Corrente ou 2 = Poupança). \n");
		int tipoConta = scan.nextInt();
		scan.nextLine();
		if(tipoConta < 1 && tipoConta > 2)
			return exibeMensagem(Constantes.MSG_04);;
		c.setTipoConta(tipoConta);
		
		Long contaCriada = contaService.criarConta(c);
		if(contaCriada < 0)
			return exibeMensagem(Constantes.MSG_05);
		exibeMensagem(Constantes.MSG_10);

		System.out.println("O numero da sua conta é: " + contaCriada);
		System.out.println("Pressione qualquer tecla para continuar...");
		scan.nextLine();
		Console.clearTerminal(Constantes.CLEAR);
		return option;
    }

	public static int consultarSaldo(Scanner scan) {
		int option = 0;
		StringBuilder str = new StringBuilder();
		
		Console.clearTerminal(Constantes.CLEAR);
		scan.nextLine();
		
		str.append("-------------------\n");
        str.append("Consultar Saldo:\n");
        str.append("   - Passo 1: Informe o número da sua conta: \n");
        System.out.println(str);
        String numeroConta = scan.nextLine();
        try {
        	Long.parseLong(numeroConta);
        	if(Long.valueOf(numeroConta) < 1)
        		return exibeMensagem(Constantes.MSG_06);
        }
        catch(NumberFormatException e) {
        	return exibeMensagem(Constantes.MSG_06);
        }
		 
		str = null;
		str = new StringBuilder();
        str.append("   - Saldo disponível: ");
        
        String saldo = null;
		try {
			if (contaService.validarConta(Long.valueOf(numeroConta)))
				saldo = contaService.consultarSaldo(Long.valueOf(numeroConta)).toString();
		}
		catch (Exception e) {
			return exibeMensagem(Constantes.MSG_06);
		}
        
        str.append(saldo + "\n");
        str.append("-------------------\n");
        System.out.println(str);
        System.out.println("Pressione qualquer tecla para continuar...");
		scan.nextLine();
		Console.clearTerminal(Constantes.CLEAR);
        return option;
	}

	public static int depositar(Scanner scan) {
        int option = 0;
		StringBuilder str = new StringBuilder();
		
		Console.clearTerminal(Constantes.CLEAR);
		scan.nextLine();
		
		str.append("-------------------\n");
        str.append("Depositar:\n");
        str.append("   - Passo 1: Informe o número da sua conta: \n");
        System.out.println(str);
        String numeroConta = scan.nextLine();
        try {
        	Long.parseLong(numeroConta);
        	if(Long.valueOf(numeroConta) < 1)
        		return exibeMensagem(Constantes.MSG_06);
        }
        catch(NumberFormatException e) {
        	return exibeMensagem(Constantes.MSG_06);
        }
        
        System.out.println("   - Passo 2: Informe o valor que deseja depositar.");
        String valor = scan.nextLine();
        try {
        	BigDecimal val = new BigDecimal(valor);
        	if(val.compareTo(BigDecimal.ZERO) <= 0)
        		return exibeMensagem(Constantes.MSG_07);
        }
        catch(NumberFormatException e) {
        	return exibeMensagem(Constantes.MSG_08);
        }
        
        System.out.println("   - Valor a ser depositado: " + valor + "\n");
        System.out.println("   - Pressione 1 para continuar ou 2 para cancelar e retornar ao menu inicial.");
        String opt = scan.nextLine();
        if(opt.equals("2"))
        	return exibeMensagem(Constantes.MSG_11);
        
        if(!opt.equals("1"))
        	return exibeMensagem(Constantes.MSG_09);
        
		try {
			if (contaService.validarConta(Long.valueOf(numeroConta)))
				contaService.depositar(Long.valueOf(numeroConta), new BigDecimal(valor));
		}
		catch (Exception e) {
			return exibeMensagem(Constantes.MSG_06);
		}
        
		exibeMensagem(Constantes.MSG_12);
        System.out.println("Pressione qualquer tecla para continuar...");
		scan.nextLine();
		Console.clearTerminal(Constantes.CLEAR);
        return option;
	}

	public static int sacar(Scanner scan) {
		 int option = 0;
			StringBuilder str = new StringBuilder();
			
			Console.clearTerminal(Constantes.CLEAR);
			scan.nextLine();
			
			str.append("-------------------\n");
	        str.append("Sacar:\n");
	        str.append("   - Passo 1: Informe o número da sua conta: \n");
	        System.out.println(str);
	        String numeroConta = scan.nextLine();
	        try {
	        	Long.parseLong(numeroConta);
	        	if(Long.valueOf(numeroConta) < 1)
	        		return exibeMensagem(Constantes.MSG_06);
	        }
	        catch(NumberFormatException e) {
	        	return exibeMensagem(Constantes.MSG_06);
	        }
	        
	        System.out.println("   - Passo 2: Informe o valor que deseja sacar.");
	        String valor = scan.nextLine();
	        try {
	        	BigDecimal val = new BigDecimal(valor);
	        	if(val.compareTo(BigDecimal.ZERO) <= 0)
	        		return exibeMensagem(Constantes.MSG_07);
	        }
	        catch(NumberFormatException e) {
	        	return exibeMensagem(Constantes.MSG_08);
	        }
	        
	        System.out.println("   - Valor a ser sacado: " + valor + "\n");
	        System.out.println("   - Pressione 1 para continuar ou 2 para cancelar e retornar ao menu inicial.");
	        String opt = scan.nextLine();
	        if(opt.equals("2"))
	        	return exibeMensagem(Constantes.MSG_11);
	        
	        if(!opt.equals("1"))
	        	return exibeMensagem(Constantes.MSG_09);
	        
			try {
				if (contaService.validarConta(Long.valueOf(numeroConta)))
					contaService.sacar(Long.valueOf(numeroConta), new BigDecimal(valor));
			}
			catch (Exception e) {
				return exibeMensagem(Constantes.MSG_06);
			}
	        
			exibeMensagem(Constantes.MSG_13);
	        System.out.println("Pressione qualquer tecla para continuar...");
			scan.nextLine();
			Console.clearTerminal(Constantes.CLEAR);
	        return option;
	}

	public static void encerrarAplicacao(Scanner scan) {
		Console.sleep(1);
		Console.clearTerminal(Constantes.CLEAR);
		StringBuilder str = new StringBuilder();
        str.append("-------------------\n");
        str.append("\n");
        str.append("Saindo da conta e Encerrando aplicação...\n");
        str.append("\n");
        str.append("-------------------\n");
        System.out.println(str.toString());
        Console.sleep(2);
        Console.clearTerminal(Constantes.CLEAR);
        scan.close();
		System.exit(0);
	}
	
	public static int exibeMensagem(String msg) {
		Console.clearTerminal(Constantes.CLEAR);
		System.out.println(msg);
		Console.sleep(2);
		Console.clearTerminal(Constantes.CLEAR);
		return 0;
	}
	
}
