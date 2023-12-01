package File;

import models.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextFileWriter {
    public static void writeAllCompaniesToFile(List<Company>companies) {
        try (FileWriter writer = new FileWriter("Companies.txt", false)) {
            for(Company company:companies) {
                writer.write(company.getCompanyName() + " " + company.getDepartament() + " " +
                        company.getYearFoundation() + " " +
                        company.getAdress() + " " +
                        company.getOwner() + " " +
                        company.getUNP());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }//USE CASE #7 Запись в файл1. Запись компаний +-
    //dataset +
    public static void writeAllUsersToFile(List<User>users) {
        try (FileWriter writer = new FileWriter("Users.txt", false)) {
            for(User user:users) {
                writer.write(user.getIdUser() + " " +
                        user.getUsername() + " " +
                        user.getEmail() + " \n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    } //USE CASE #8 Запись в файл2. юзеров +-
    public static void writeCalculationsToFile(List<EVAcounting> EVAcountings) {
        try (FileWriter writer = new FileWriter("Calculations.txt", false)) {
            for(EVAcounting EVAcounting: EVAcountings) {
                writer.write("\nID = "+ EVAcounting.getIdEvacounting() + " |\t Inv=" + EVAcounting.getCapitalInv() +
                        "  ds=" + EVAcounting.getDsPercentage() + " | " +
                        "  dd=" + EVAcounting.getDdPercentage() + " | " +
                        "  Rs=" + EVAcounting.getRsValuePercentage() + " | " +
                        "  Rd=" + EVAcounting.getRdValuePercentage() + " | " +
                        "  WACC=" + EVAcounting.getWaccPercentage() + " | " +
                        "  EBIT=" + EVAcounting.getEBIT() + " | " +
                        "  Amortization=" + EVAcounting.getAmortization() + " | " +
                        "\n => for id("+EVAcounting.getIdEvacounting()+"):  "+EVAcounting.getNOPAT()+", " + EVAcounting.getEVA());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }//USE CASE #9 Записать рассчёты в файл3. +-
    public static void writeEVAtoFile(List<Finances>finances) {

        try (FileWriter writer = new FileWriter("EVAs.txt", false)) {
            for (Finances finance : finances) {
                String years = "лет";
                if (finance.getPeriodEVA() == 1) years = "год";
                if (finance.getPeriodEVA() == 2 || finance.getPeriodEVA() == 3 || finance.getPeriodEVA() == 4)
                    years = "года";
                writer.write("За период (" + finance.getPeriodEVA() + ") " + years + " в компании <<" + finance.getCompany().getCompanyName() + ">> EVA = " +
                        finance.getEVAforPeriod() + " \n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }//USE CASE #15 Записать в файл4. EVA компаний +-

    public static void writePVtoFile(List<ResultedCompanyNetworth>rcns) {

        try (FileWriter writer = new FileWriter("PVs.txt", false)) {
            for(ResultedCompanyNetworth rcn:rcns) {
                        String years = "-летний";
                        if(rcn.getPeriod() == 1) years = "-годний";
                writer.write( "Стоимость компании <<"+rcn.getEvacounting().getFinances().getCompany().getCompanyName()+">> за "+rcn.getPeriod() + years + " период составляет: "+rcn.getPVValue()+" y.e \n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }//USE CASE #16 Записать в файл5. Стоимость компаний +-
}