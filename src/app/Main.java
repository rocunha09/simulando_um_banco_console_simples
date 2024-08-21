package app;

import java.util.Scanner;

import services.ContaServiceInMemory;
import util.Console;
import view.Menu;

public class Main {

	public static void main(String[] args) {
		int clear = 500;
		int option = -1;
		Scanner scan = new Scanner(System.in);
		Menu.defineContaService(new ContaServiceInMemory());
		
		while(true) {
				if(option == -1) {
					option = Menu.menuPrincipal(scan);
				}
				
				if(option == 0) {
					Console.clearTerminal(clear);
					Menu.encerrarAplicacao();
					Console.sleep(2);
					Console.clearTerminal(clear);
					scan.close();
					System.exit(0);
				}

				if(option == 1) {
					option = Menu.criarConta(scan);
				
				}
				
				if(option == 2) {
					option = Menu.consultarSaldo(scan);
				
				}
				
				if(option == 3) {
					option = Menu.depositar(scan);
				
				}
				
				if(option == 4) {
					option = Menu.sacar(scan);
				
				}
				
				if(option < 0 || option > 4) {
					Console.clearTerminal(clear);
					System.out.println("Opção inválida, digite novamente...");
					option = -1;
					Console.sleep(2);
					Console.clearTerminal(clear);
				}
			
		} 

	}
	
}
