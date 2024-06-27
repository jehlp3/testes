package Java.industria;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Funcionario funcionario = new Funcionario();

        funcionario.adicionarFuncionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador");
        funcionario.adicionarFuncionario("Joao", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador");
        funcionario.adicionarFuncionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador");
        funcionario.adicionarFuncionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor");
        funcionario.adicionarFuncionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista");
        funcionario.adicionarFuncionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador");
        funcionario.adicionarFuncionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador");
        funcionario.adicionarFuncionario("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), "Gerente");
        funcionario.adicionarFuncionario("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista");
        funcionario.adicionarFuncionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente");

        funcionario.exibirFuncionarios();

        funcionario.removerFuncionario("Joao");

        funcionario.exibirFuncionarios();

        BigDecimal percentualAumento = BigDecimal.valueOf(10);
        funcionario.atualizarSalario(percentualAumento);

        funcionario.exibirFuncionarios();

        funcionario.listarFuncionariosPorFuncao();

        int[] mesesAniversario = {10, 12};
        System.out.println(funcionario.listarAniversariantes(mesesAniversario));

        funcionario.imprimirFuncionarioMaisVelho();

        System.out.println(funcionario.ordenarFuncionariosOrdemAlfabetica());

        System.out.println(funcionario.somarSalariosFuncionarios());

        funcionario.calcularSalariosMinimosPorFuncionario(1212);
    }
}

