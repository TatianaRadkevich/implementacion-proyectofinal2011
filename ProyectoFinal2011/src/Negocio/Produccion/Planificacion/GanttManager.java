/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * ---------------
 * GanttDemo1.java
 * ---------------
 * (C) Copyright 2002-2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: GanttDemo1.java,v 1.12 2004/04/26 19:11:54 taqua Exp $
 *
 * Changes
 * -------
 * 06-Jun-2002 : Version 1 (DG);
 * 10-Oct-2002 : Modified to use DemoDatasetFactory (DG);
 * 10-Jan-2003 : Renamed GanttDemo --> GanttDemo1 (DG);
 * 16-Oct-2003 : Shifted dataset from DemoDatasetFactory to this class (DG);
 *
 */
package Negocio.Produccion.Planificacion;

import ZTest.*;
import Negocio.Produccion.DetallePlanProduccion;
import Negocio.Produccion.PlanProduccion;
import java.util.Calendar;
import java.util.Date;
//importante!!!!!!!!!!!  importen JFreeChart y JCommon
import javax.swing.JDialog;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a Gantt chart.
 * <P>
 * This demo is intended to show the conceptual approach rather than being a polished
 * implementation.
 *
 *
 */
public class GanttManager {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public static IntervalCategoryDataset createDataset(PlanProduccion plan) {
        final TaskSeries s1 = new TaskSeries("Planificado");
        final TaskSeries s2 = new TaskSeries("Real");
        for (DetallePlanProduccion dpp : plan.getDetallePlan()) {
            Date d1 = dpp.getFecHoraPrevistaInicio();
            Date d2 = dpp.getFecHoraPrevistaFin();
            String nombre = dpp.getEtapaProduccionEspecifica().getEtapaProduccion().getNombre();
            //nombre += "\nOperario: " + dpp.getEmpleado().getApellidoNombre();
            Task t = new Task(nombre, new SimpleTimePeriod(d1, d2));
            s1.add(t);

            if (dpp.getFecHoraRealInicio() != null || dpp.getFecHoraRealFin() != null) {
                d1 = dpp.getFecHoraRealInicio();
                d2 = dpp.getFecHoraRealFin();
                nombre = dpp.getEtapaProduccionEspecifica().getEtapaProduccion().getNombre();
                t = new Task(nombre, new SimpleTimePeriod(d1, d2));
                s2.add(t);
            }
        }

        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        collection.add(s2);

        return collection;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private static JFreeChart createChart(final IntervalCategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createGanttChart(
                "Gantt Chart Demo", // chart title
                "Task", // domain axis label
                "Date", // range axis label
                dataset, // data
                true, // include legend
                true, // tooltips
                false // urls
                );
//        chart.getCategoryPlot().getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
        return chart;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static ChartPanel getPnlGantt(PlanProduccion plan) {
        final IntervalCategoryDataset dataset = createDataset(plan);
        final JFreeChart chart = createChart(dataset);

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        return chartPanel;

    }

    public static ChartFrame getFrameGantt(String titulo, PlanProduccion plan, JDialog parent) {
        ChartFrame af = new ChartFrame(parent);
        final IntervalCategoryDataset dataset = createDataset(plan);
        final JFreeChart chart = createChart(dataset);

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        af.setTitle(titulo);
        af.setContentPane(chartPanel);
        af.pack();
        RefineryUtilities.centerFrameOnScreen(af);
        return af;

    }
}
