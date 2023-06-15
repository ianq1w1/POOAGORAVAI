package org.exercicio.banco.template.model;

import java.math.BigDecimal;
import java.util.List;

public interface IConta {
	public void depositar(BigDecimal valor);
	public void sacar(BigDecimal quantia);
	public void transferir(IConta dest, BigDecimal valor);
	public boolean isStatus();
	public Integer getNumeroConta();
	public BigDecimal getSaldo();
	public void setISaldo(BigDecimal saldo);
	public List <RegistroTransacao> getTransacoes();
	public void getSaldo(BigDecimal saldo);
}
