package view;

import java.util.Scanner;

import Interfaces.IContaService;
import models.Conta;
import util.Console;

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
		int option = -2;
		Conta c = new Conta();

		System.out.println("-------------------\n");
		System.out.println("Criar Conta:\n");
		System.out.println("   - Passo 1: Informe seu nome completo.\n");
		String nome = scan.nextLine();
		if(nome.equals("") || nome == null) {
			System.out.println("\n\nNome inválido...");
			System.out.println("Voltando ao menu principal.\n");
			Console.sleep(3);
			return option;
		}
		c.setNome(nome);
		
		System.out.println("   - Passo 2: Informe seu CPF (somente números).\n");
		String cpf = scan.nextLine();
		if(cpf.equals("") || cpf == null) {
			System.out.println("\n\nCPF inválido...");
			System.out.println("Voltando ao menu principal.\n");
			Console.sleep(3);
			return option;
		}
		c.setCpf(cpf);
		
		System.out.println("   - Passo 3: Escolha o tipo de conta (1 = Corrente ou 2 = Poupança). \n");
		int tipoConta = scan.nextInt();
		scan.nextLine();
		if(tipoConta < 1 && tipoConta > 2) {
			System.out.println("\n\nTipo da Conta inválido...");
			System.out.println("Voltando ao menu principal.\n");
			Console.sleep(3);
			return option;
		}
		c.setTipoConta(tipoConta);
		
		contaService.criarConta(c);
		
		return option;
    }

	public static int consultarSaldo(Scanner scan) {
		int option = 0;
		StringBuilder str = new StringBuilder();
		
		str.append("-------------------\n");
        str.append("Consultar Saldo:\n");
        str.append("   - Passo 1: Informe o número da sua conta.\n");
        Long numeroConta = Long.valueOf(scan.next());
        scan.nextLine();
        		
        str.append("   - Saldo disponível: ");
        
        String saldo = null;
		try {
			saldo = contaService.consultarSaldo(numeroConta).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        str.append(saldo + "\n");
        str.append("-------------------\n");
        System.out.println(str);
        return option;
	}

	public static int depositar(Scanner scan) {
		int option = 0;
		StringBuilder str = new StringBuilder();
		
		str.append("-------------------\n");
        str.append("Realizar Depósito:\n");
        str.append("   - Passo 1: Informe o número da sua conta.\n");
        str.append("   - Passo 2: Informe o valor que deseja depositar.\n");
        str.append("   - Nota: O valor será creditado imediatamente na sua conta.\n");
        str.append("-------------------\n");
        System.out.println(str.toString());
        return option;
	}

	public static int sacar(Scanner scan) {
		int option = 0;
		StringBuilder str = new StringBuilder();
		
        str.append("-------------------\n");
        str.append("Realizar Saque:\n");
        str.append("   - Passo 1: Informe o número da sua conta.\n");
        str.append("   - Passo 2: Informe o valor que deseja sacar.\n");
        str.append("   - Nota: Certifique-se de que o saldo é suficiente para realizar o saque.\n");
        str.append("-------------------\n");
        System.out.println(str.toString());
        return option;
	}

	public static void encerrarAplicacao() {
		StringBuilder str = new StringBuilder();
        str.append("-------------------\n");
        str.append("\n");
        str.append("Saindo da conta e Encerrando aplicação...\n");
        str.append("\n");
        str.append("-------------------\n");
        System.out.println(str.toString());
	}
	
	
}
