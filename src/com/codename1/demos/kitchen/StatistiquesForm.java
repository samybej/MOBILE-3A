/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.demos.kitchen;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.BarChart;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import services.ServiceOffre;

/**
 *
 * @author Asus
 */
public class StatistiquesForm extends Form {
    
    Font smallFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_SMALL, Font.STYLE_PLAIN);
    
      protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
    renderer.setAxisTitleTextSize(smallFont.getHeight()/2);
    renderer.setChartTitleTextFont(smallFont);
    renderer.setLabelsTextSize(smallFont.getHeight()/2);
    renderer.setLegendTextSize(smallFont.getHeight()/2);
    int length = colors.length;
    for (int i = 0; i < length; i++) {
      XYSeriesRenderer r = new XYSeriesRenderer();
      r.setColor(colors[i]);
      renderer.addSeriesRenderer(r);
    }
    return renderer;
  }
      
      protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
      String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
      int labelsColor) {
    renderer.setChartTitle(title);
    renderer.setXTitle(xTitle);
    renderer.setYTitle(yTitle);
    renderer.setXAxisMin(xMin);
    renderer.setXAxisMax(xMax);
    renderer.setYAxisMin(yMin);
    renderer.setYAxisMax(yMax);
    renderer.setAxesColor(axesColor);
    renderer.setLabelsColor(labelsColor);
  }
      
      protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    int length = titles.length;
    for (int i = 0; i < length; i++) {
      CategorySeries series = new CategorySeries(titles[i]);
      double[] v = values.get(i);
      int seriesLength = v.length;
      for (int k = 0; k < seriesLength; k++) {
        series.add(v[k]);
      }
      dataset.addSeries(series.toXYSeries());
    }
    return dataset;
  }
        
    
    public StatistiquesForm() {
        String[] titles = new String[] { "nombre do covoiturages / jour ", };
        ArrayList<Double> list = new ArrayList<>();
        
        list = ServiceOffre.getInstance().getStatistiques();
        System.out.println(list.get(1));
    List<double[]> values = new ArrayList<double[]>();
    values.add(new double[] { list.get(0)*1000.0, list.get(1)*1000.0, list.get(2)*1000.0, list.get(3)*1000.0
            , list.get(4)*1000.0, list.get(5)*1000.0, list.get(6)*1000.0
            , list.get(7)*1000.0, list.get(8)*1000.0, list.get(9)*1000.0,
        list.get(10)*1000.0, list.get(11)*1000.0, list.get(12)*1000.0, list.get(13)*1000.0
    ,list.get(14)*1000.0,list.get(15)*1000.0,list.get(16)*1000.0,list.get(17)*1000.0,list.get(18)*1000.0
    ,list.get(19)*1000.0,list.get(20)*1000.0,list.get(21)*1000.0,list.get(22)*1000.0,list.get(23)*1000.0
    ,list.get(24)*1000.0,list.get(25)*1000.0,list.get(26)*1000.0,list.get(27)*1000.0,list.get(28)*1000.0
    ,list.get(29)*1000.0});
    
    int[] colors = new int[] { ColorUtil.CYAN, ColorUtil.YELLOW };
    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
    renderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
    setChartSettings(renderer, "Statistiques Covoiturages", "numero du jour", "Nombre de covoiturages", 0.5,
        12.5, 0, 24000, ColorUtil.GRAY, ColorUtil.LTGRAY);
    renderer.setXLabels(1);
    renderer.setYLabels(10);
    renderer.addXTextLabel(1, "1");
    renderer.addXTextLabel(8, "8");
    renderer.addXTextLabel(16, "15");
    renderer.addXTextLabel(24, "24");
    renderer.addXTextLabel(30, "30");
   // renderer.addXTextLabel(12, "12");
    int length = renderer.getSeriesRendererCount();
    for (int i = 0; i < length; i++) {
      XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
      seriesRenderer.setDisplayChartValues(true);
    }
    
    BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
        BarChart.Type.DEFAULT);
    Component c = new ChartComponent(chart);
    c.getStyle().setBgColor(0x0);
      Form f = new Form("");
      f.setLayout(new BorderLayout());
      f.addComponent(BorderLayout.CENTER, c);
      
      f.show();
    
    }
}
