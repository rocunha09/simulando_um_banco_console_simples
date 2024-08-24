package app;

import java.util.Scanner;

import services.ContaServiceInMemory;
import util.Constantes;
import view.Menu;

public class Main {

	public static void main(String[] args) {
		int option = 0;
		Scanner scan = new Scanner(System.in);
		Menu.defineContaService(new ContaServiceInMemory());
		
		while(true) {
				if(option == 0) {
					option = Menu.menuPrincipal(scan);
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
				
				if(option == 5) {
					Menu.encerrarAplicacao(scan);
				}
				
				if(option < 0 || option > 5) {
					option = Menu.exibeMensagem(Constantes.MSG_01);
				}
				
		}
	}
}
