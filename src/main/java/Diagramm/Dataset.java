package Diagramm;

import models.*;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Dataset
{
    public static PieDataset createPieDataset(ObjectOutputStream coos, ObjectInputStream cois)
    {
        try {
            List<User>users=new ArrayList<User>() ;
            coos.writeObject("GetAllUsers");
            users=( List<User>)cois.readObject();
            DefaultPieDataset dataset = new DefaultPieDataset();
            List<Company>companies=new ArrayList<Company>() ;
            coos.writeObject("GetAllCompanies");
            companies=( List<Company>)cois.readObject();
            int amount;
            for (int i = 0; i < users.size(); i++)
            {
                amount=0;
                for(int j=0;j<companies.size();j++)
                {
                    if(users.get(i).getIdUser()==companies.get(j).getIdUser())
                        amount++;
                }
                System.out.println(users.get(i).getUsername());
                System.out.println(amount);
                dataset.setValue(users.get(i).getUsername(), amount);
            }
            return dataset;
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static String[] months={"Январь","Февраль","Март","Апрель","Май","Июнь","Июль","Август","Сентябрь","Октябрь","Ноябрь","Декабрь"};
    public static CategoryDataset createDatasetAgeOfCompany(ObjectOutputStream coos, ObjectInputStream cois)
    {
        try {
            DefaultCategoryDataset dataset=new DefaultCategoryDataset();
            final String series1 = "Возраст компаний";
            coos.writeObject("GetAllCompanies");
            List<Company>companies=new ArrayList<>();
            companies=(List<Company>)cois.readObject();
            Date nowDate=Date.valueOf("2023-12-01");
            for(int i=0;i<companies.size();i++)
            {
                LocalDate startLocalDate = companies.get(i).getYearFoundation().toLocalDate();
                LocalDate endLocalDate = nowDate.toLocalDate();
                Period period = Period.between(startLocalDate, endLocalDate);
                int years = period.getYears();
//                int months = period.getMonths();
//                int days = period.getDays();
                dataset.addValue(years, series1, companies.get(i).getCompanyName());
            }
            return dataset;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static CategoryDataset createDatasetEVAOfCompany(ObjectOutputStream coos, ObjectInputStream cois)
    {
        try {
            DefaultCategoryDataset dataset=new DefaultCategoryDataset();
            final String series1 = "EVA компаний";
            System.out.println("EVA diag getallcompanies? shown:");
            coos.writeObject("GetAllCompanies");
            List<Company>companies=new ArrayList<>();
            System.out.println("EVA diag getallcompanies! shown:");
            companies=(List<Company>)cois.readObject();
            coos.writeObject("FindEvaFinances");
            System.out.println("EVA diag findevafinances? shown:");
            List<Finances>finances=new ArrayList<>();
            finances=(List<Finances>)cois.readObject();
            System.out.println("EVA diag findevafinances! shown:");

            for(int i=0;i<companies.size();i++) {
                System.out.println("EVA diag findevafinances! shown: i="+i);
                for (int j = finances.size()-1; j >=0; j--) {
                    System.out.println("EVA diag findevafinances! shown: j="+j);
                    if (companies.get(i).getIdCompany() == finances.get(j).getIdCompany()) {
                        System.out.println("EVA shown:");
                        dataset.addValue(finances.get(j).getEVAforPeriod(), String.valueOf(companies.get(i).getIdCompany()), companies.get(i).getCompanyName());
                        break;
                    }
                }
            }
            return dataset;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static CategoryDataset createDatasetPVOfCompany(ObjectOutputStream coos, ObjectInputStream cois)
    {
        try {
            DefaultCategoryDataset dataset=new DefaultCategoryDataset();
            final String series1 = "PV компаний";
            System.out.println("6666");
            coos.writeObject("GetAllCompanies");
            List<Company>companies=new ArrayList<>();
            System.out.println("6666");
            companies=(List<Company>)cois.readObject();
//            coos.writeObject("FindEvaFinances");
            System.out.println("6666");
//            List<ResultedCompanyNetworth>rcns=new ArrayList<>();
//            rcns = (List<ResultedCompanyNetworth>)cois.readObject();
            System.out.println("6666");
            for(int i=0;i<companies.size();i++) {
                System.out.println("PV diag RCN shown: i=" + i);
                coos.writeObject("FindAllRCNbyCompanyId");
                System.out.println("6666");
                List<ResultedCompanyNetworth> rcns = new ArrayList<>();
                System.out.println(rcns.size());
                rcns = (List<ResultedCompanyNetworth>) cois.readObject();
                System.out.println(rcns.size());
                if (rcns.size() != 0) {
                    System.out.println(rcns.size());
                    System.out.println(rcns.get(rcns.size() - 1).getPVValue());
                    System.out.println(String.valueOf(companies.get(i).getIdCompany()));
                    System.out.println(companies.get(i).getCompanyName());
                    dataset.addValue(rcns.get(rcns.size() - 1).getPVValue(), String.valueOf(companies.get(i).getIdCompany()), companies.get(i).getCompanyName());
                }
            }
            return dataset;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}