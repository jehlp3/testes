package Java.industria;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Funcionario extends Pessoa {

    private List<Funcionario> funcionarioList;

    private BigDecimal salario;
    private String funcao;

    public Funcionario() {
        this.funcionarioList = new ArrayList<>();
    }

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void adicionarFuncionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        Funcionario funcionario = new Funcionario(nome, dataNascimento, salario, funcao);
        this.funcionarioList.add(funcionario);
    }

    public void removerFuncionario(String nome) {
        List<Funcionario> funcionariosParaRemover = new ArrayList<>();
        if (!funcionarioList.isEmpty()) {
            for (Funcionario f : funcionarioList) {
                if (f.getNome().equalsIgnoreCase(nome)) {
                    funcionariosParaRemover.add(f);
                }
            }
            funcionarioList.removeAll(funcionariosParaRemover);
        } else {
            System.out.println("Não há funcionários cadastrados");
        }
        System.out.println("O(a) funcionário(a) " + nome + " foi removido.");
    }

    public static String formatarDataNascimento(LocalDate dataNascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataNascimento.format(formatter);
    }

    public static String formatarValores(BigDecimal salario) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        return df.format(salario);
    }

    public void exibirFuncionarios() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Funcionários: ");
        if (!funcionarioList.isEmpty()) {
            System.out.println(this.funcionarioList);
        } else {
            System.out.println("Não há funcionários cadastrados");
        }
    }

    public void atualizarSalario(BigDecimal percentualAumento) {
        if (!funcionarioList.isEmpty()) {
            for (Funcionario f : funcionarioList) {
                BigDecimal salarioAtual = f.getSalario();
                BigDecimal aumento = salarioAtual.multiply(percentualAumento).divide(BigDecimal.valueOf(100));
                BigDecimal novoSalario = salarioAtual.add(aumento);
                f.setSalario(novoSalario);
            }
        } else {
            System.out.println("Não há funcionários cadastrados");
        }
        System.out.println("Os salários foram atualizados em " + percentualAumento + "%.");
    }

    public Map<String, List<Funcionario>> agruparPorFuncao() {
        Map<String, List<Funcionario>> mapaFuncionariosPorFuncao = new HashMap<>();
        for (Funcionario f : funcionarioList) {
            String funcao = f.getFuncao();
            if (!mapaFuncionariosPorFuncao.containsKey(funcao)) {
                mapaFuncionariosPorFuncao.put(funcao, new ArrayList<>());
            }
            mapaFuncionariosPorFuncao.get(funcao).add(f);
        }
        return mapaFuncionariosPorFuncao;
    }

    public void listarFuncionariosPorFuncao() {
        Map<String, List<Funcionario>> funcionariosPorFuncao = agruparPorFuncao();
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.println(f);
            }
        }
    }

    public List<Funcionario> listarAniversariantes(int[] mesesAniversario) {
        List<Funcionario> funcionariosAniversariantes = new ArrayList<>();
        if (!funcionarioList.isEmpty()) {
            for (Funcionario f : funcionarioList) {
                int mes = f.getDataNascimento().getMonthValue();
                for (int mesAniversario : mesesAniversario) {
                    if (mes == mesAniversario) {
                        funcionariosAniversariantes.add(f);
                    }
                }
            }
        } else {
            System.out.println("Não há funcionários cadastrados");
        }
        System.out.println("Aniversariantes: ");
        return funcionariosAniversariantes;
    }


    public void imprimirFuncionarioMaisVelho() {
        if (!funcionarioList.isEmpty()) {
            Funcionario funcionarioMaisVelho = funcionarioList.get(0);
            for (Funcionario f : funcionarioList) {
                if (f.getDataNascimento().isBefore(funcionarioMaisVelho.getDataNascimento())) {
                    funcionarioMaisVelho = f;
                }
            }
            LocalDate dataAtual = LocalDate.now();
            int idade = dataAtual.getYear() - funcionarioMaisVelho.getDataNascimento().getYear();
            if (dataAtual.getDayOfYear() < funcionarioMaisVelho.getDataNascimento().getDayOfYear()) {
                idade--;
            }
            System.out.println("O(a) funcionário(a) mais velho(a) é: " + funcionarioMaisVelho.getNome() + " " + idade);
        } else {
            System.out.println("Não há funcionários cadastrados");
        }
    }

    public List<Funcionario> ordenarFuncionariosOrdemAlfabetica() {
        System.out.println("Funcionários em ordem alfabética: ");
        List<Funcionario> funcionariosOrdemAlfabetica = new ArrayList<>(funcionarioList);
        if (!funcionarioList.isEmpty()) {
            for (Funcionario f : funcionarioList) {
                Collections.sort(funcionariosOrdemAlfabetica, new Comparator<Funcionario>() {
                    @Override
                    public int compare(Funcionario f1, Funcionario f2) {
                        return f1.getNome().compareToIgnoreCase(f2.getNome());
                    }
                });
            }
        } else {
            System.out.println("Não há funcionários cadastrados");
        }
        return funcionariosOrdemAlfabetica;
    }

    public String somarSalariosFuncionarios() {
        BigDecimal somaSalarios = new BigDecimal(0);
        if (!funcionarioList.isEmpty()) {
            for (Funcionario f : funcionarioList) {
                somaSalarios = somaSalarios.add(f.getSalario());
            }
        } else {
            System.out.println("Não há funcionários cadastrados");
        }
        String somaFormatada = formatarValores(somaSalarios);
        System.out.println("Soma dos salários: ");
        return somaFormatada;
    }

    public List<Funcionario> calcularSalariosMinimosPorFuncionario(float salMinCorrente) {
        System.out.println("Salários mínimos por funcionário: ");
        List<Funcionario> salMinPorFuncionario = new ArrayList<>(funcionarioList);
        if (!funcionarioList.isEmpty()) {
            for (Funcionario f : funcionarioList) {
                BigDecimal salarioFuncionario = f.getSalario();
                BigDecimal salarioMinimo = BigDecimal.valueOf(salMinCorrente);
                BigDecimal calculoSalMinPorFuncionario = salarioFuncionario.divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
                String percentualFormatado = formatarValores(calculoSalMinPorFuncionario);
                System.out.println(f.getNome() + " " + percentualFormatado);
            }
        } else {
            System.out.println("Não há funcionários cadastrados");
        }
        return salMinPorFuncionario;
    }

    @Override
    public String toString() {
        return
                "Nome: " + getNome() +
                        "; Data de Nascimento: " + formatarDataNascimento(getDataNascimento()) +
                        "; Salário: " + formatarValores(salario) +
                        "; Função: " + funcao +
                        "; \n";
    }

}

