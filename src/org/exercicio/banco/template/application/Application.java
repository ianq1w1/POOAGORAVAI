package org.exercicio.banco.template.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.exercicio.banco.template.model.Cliente;
import org.exercicio.banco.template.model.ContaCorrente;
import org.exercicio.banco.template.model.ContaPoupanca;
import org.exercicio.banco.template.persistence.PersistenciaEmArquivo;

public class Application {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Bem-vindo ao banco! O que você gostaria de fazer?");
			System.out.println("1. Cadastrar novo cliente");
			System.out.println("2. Selecionar cliente existente");
			System.out.println("3. Sair");
			System.out.println("4. Listar todos os clientes cadastrados");
			System.out.println("5. Remover cliente");
			

			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				System.out.println("Digite o nome do cliente:");
				String nome = scanner.nextLine();
				System.out.println("Digite o CPF do cliente:");
				String cpf = scanner.nextLine();
				Cliente cliente = new Cliente(cpf, nome);
				PersistenciaEmArquivo.getInstance().salvarCliente(cliente);
				break;

			case 2:
				System.out.println("Digite o CPF do cliente:");
				cpf = scanner.next();

				cliente = PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf);

				if (cliente == null) {
					System.out.println("Cliente não encontrado");
					break;
				}
				

				System.out.println("Cliente selecionado: " + cliente.getNome());
				System.out.println("\nO que você gostaria de fazer?\n");
				System.out.println("1. Criar nova conta");
				System.out.println("2. Ver informações das contas");
				System.out.println("3. Realizar Deposito");
				System.out.println("4. Realizar Saque");
				System.out.println("5. Realizar Transferencia");

				opcao = scanner.nextInt();
				scanner.nextLine();

				switch (opcao) {
				case 1:
					System.out.println("Qual tipo de conta deseja criar?");
					System.out.println("1. Conta corrente");
					System.out.println("2. Conta poupança");
					
					opcao = scanner.nextInt();
					scanner.nextLine();
				
					switch (opcao) {
					case 1:
						ContaCorrente contaC = new ContaCorrente();
						cliente.adicionarConta(contaC);
						PersistenciaEmArquivo.getInstance().atualizarClienteCadastro(cliente);
						System.out.println("Conta corrente criada com sucesso!");
						break;					
					
					case 2:
						ContaPoupanca contaP = new ContaPoupanca();
						cliente.adicionarConta(contaP);
						PersistenciaEmArquivo.getInstance().atualizarClienteCadastro(cliente);
						System.out.println("Conta criada com sucesso!");
						break;					
					}

				case 2:
					if (cliente.getContas().size() == 0) {
						System.err.println("Nao ha contas associada a este cliente.");
					} else {
						for (ContaCorrente c : cliente.getContas()) {
							System.out.println(c);
						}
					}else {
						for (ContaPoupanca c : cliente.getContas()) {
							System.out.println(c);
						}
					}
					break;
				case 3:
					if (cliente.getContas().size() == 0) {
						System.err.println("Nao ha contas associada a este cliente.");
					} else {
						for (ContaCorrente c : cliente.getContas()) {
							System.out.println(c);
						}
					} else {
						for (ContaPoupanca c : cliente.getContas()) {
							System.out.println(c);
						}
					}
					System.out.println("Em qual conta deseja realizar o deposito?");
					int opcaoContaDeposito = 0;
					double quantia = 0.0;
					opcaoContaDeposito = scanner.nextInt();
					System.out.println("Insira o valor da quantia a ser depositada: ");
					quantia = scanner.nextDouble();
					ContaCorrente temp = cliente.localizarContaNumero(opcaoContaDeposito);
					if (temp != null) {
						temp.depositar(new BigDecimal(quantia));
						PersistenciaEmArquivo.getInstance().atualizarClienteCadastro(cliente);
					}
					break;
				default:
					System.out.println("Opção inválida");
					break;
				}
				break;
			case 3:
				System.out.println("Até logo!");
				System.exit(0);
			
			case 4:
				PersistenciaEmArquivo.getInstance().listarClientes();
			        break;
			case 5:
				System.out.println("Digite o CPF do cliente:");
				cpf = scanner.next();
				PersistenciaEmArquivo.getInstance().removerCliente(cpf);
				break;
			
			default:
				System.out.println("Opção inválida");
				break;
			}
		}
	}
}
