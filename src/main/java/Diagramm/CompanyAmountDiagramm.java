package Diagramm;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;

public class CompanyAmountDiagramm extends JFrame
{
    private static final long serialVersionUID = 1L;
    PieDataset dataset;
    JFreeChart chart;
    private ObjectOutputStream coos;
    private ObjectInputStream cois;
    PieSectionLabelGenerator pslg = null;
    public CompanyAmountDiagramm(final String title,ObjectOutputStream coos,ObjectInputStream cois)
    {
        super(title);
        this.coos=coos;
        this.cois=cois;
        dataset = Dataset.createPieDataset(coos,cois);
        chart = createChart(dataset);

        // Размещение диаграммы в панели
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
        setContentPane(chartPanel);
    }
    private JFreeChart createChart(final PieDataset dataset)
    {
        chart = ChartFactory.createPieChart(
                "Диаграмма количества компаний пользователей",
                dataset,
                true,
                true,
                false);
        PiePlot plot = (PiePlot) chart.getPlot();
        pslg = new StandardPieSectionLabelGenerator("{0} = {1}шт.",
                NumberFormat.getNumberInstance(),
                NumberFormat.getPercentInstance());
        plot.setLabelGenerator(pslg);
        plot.setLabelGap(0.02);
       // plot.setExplodePercent(Dataset.SECTIONS[3], 0.20);
        return chart;
    }
    public static void main(final String[] args)
    {

    }
}