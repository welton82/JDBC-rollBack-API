package testes;

import entity.Department;
import entity.Seller;
import model.dao.DaoSellerDepartment;

import javax.xml.namespace.QName;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        operacao(sc);
    }
    public static int menu(Scanner sc){
        System.out.println("0 - SAIR");
        System.out.println("1 - LISTAR");
        System.out.println("2 - CONSULTAR");
        System.out.println("3 - DELETAR");
        System.out.println("4 - INSERIR");
        System.out.println("5 - ALTERAR O SALARIO");

        System.out.print("Operacao: ");
        int op = sc.nextInt();
        sc.nextLine();
        return op;
    }
    static public void  operacao(Scanner sc) {
        int op = -1;
        DaoSellerDepartment dao = new DaoSellerDepartment();

        while(op != 0){
            op = menu(sc);
            switch (op){
                case 1:
                    System.out.println("============== LISTANDO DEPARTAMENTO ==============");
                    dao.getDepartment();
                    List<Seller> sel = dao.getSeller();
                    System.out.println("================= LISTANDO SELLER =================");
                    for(Seller s:sel){
                        System.out.println("Id: " + s.getId() + ", Name: " + s.getName() + ", Salary: " + s.getBaseSalary() + ", BirthDate: " + s.getBirthdate());
                        System.out.println("idDepartment: " + s.getDepartmentId() + ", Email: " + s.getEmail());
                        System.out.println("-----------------------------------------------------");
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.println("1 - Consultar Department");
                    System.out.println("2 - Consultar Seller");
                    System.out.print("Operação: ");
                    op = sc.nextInt();

                    switch (op){
                        case 1:
                            System.out.println("INFORME O ID DO DEPARTAMENTO: ");
                            op = sc.nextInt();
                            Department dp = dao.consultarDepartment(op);
                            System.out.println("Id: " + dp.getId());
                            System.out.println("NAME: " + dp.getName());
                            break;
                        case 2:
                            System.out.println("INFORME O ID DO SELLER: ");
                            op = sc.nextInt();
                            Seller se = dao.consultarSeller(op);
                            System.out.println("Id: " + se.getId());
                            System.out.println("Name: " + se.getName());
                            System.out.println("Birthdate: " + se.getBirthdate());
                            System.out.println("Base Salary: " + se.getBaseSalary());
                            System.out.println("Email: " + se.getEmail());
                            System.out.println("Id Department: " + se.getDepartmentId());
                            break;
                    }

                    break;
                case 3:
                    System.out.println("1 - DELETAR Department");
                    System.out.println("2 - DELETAR Seller");
                    System.out.print("Operação: ");
                    op = sc.nextInt();

                    switch (op){
                        case 1:
                            System.out.print("Informe o 'id' do Departamento para deletar: ");
                            op = sc.nextInt();
                            dao.deletarDepartment(op);
                            break;
                        case 2:
                            System.out.print("Informe o 'id' do Seller para deletar: ");
                            op = sc.nextInt();
                            dao.deletarSeller(op);
                            break;
                        default :
                            System.out.println("Invalida");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("1 - INSERIR Department");
                    System.out.println("2 - INSERIR Seller");
                    System.out.print("Operação: ");
                    op = sc.nextInt();
                    switch(op){
                        case 1:
                            System.out.print("Nome do Department: ");
                            String nome = sc.nextLine();
                            dao.insereDepartment(new Department(nome));
                            break;
                        case 2:
                            System.out.print("NOME DO SELLER: ");
                            nome = sc.nextLine();
                            System.out.print("Email: ");
                            String email = sc.nextLine();
                            System.out.print("Salary: ");
                            Double salary = sc.nextDouble();
                            System.out.print("Aniversario: ");
                            String data = sc.next();
                            System.out.print("Department id: ");
                            Integer dp = sc.nextInt();
                            dao.insereSeller(new Seller(nome,email, data,salary,dp));
                            break;
                    }
                    break;
                case 5:
                    System.out.print("infome o id1: ");
                    int id1 = sc.nextInt();
                    System.out.print("Novo salario: ");
                    Double sal1 = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Informe o id2: ");
                    int id2 = sc.nextInt();
                    System.out.print("Novo Salario: ");
                    Double sal2 = sc.nextDouble();
                    dao.alterarSalarioAutomaticamente1(id1,id2,sal1,sal2);
                    break;
                default:
                    System.out.println("INVALIDO");
                    break;
            }


        }
    }
}
