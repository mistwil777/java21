/*
 * Copyright (C) 2017 Andrea Binello ("andbin")
 *
 * This file is part of the "Java 8 Streams Demos" project and is licensed
 * under the MIT License. See one of the license files included in the root
 * of the project for the full text of the license.
 */

package net.andbin.streamsdemos.points.tool;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import net.andbin.streamsdemos.points.datamodel.Point2D;
import net.andbin.streamsdemos.points.datamodel.PointsData;

@SuppressWarnings("restriction")
public class PointsDataChart extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Points Data Chart");

        NumberAxis xAxis = new NumberAxis(-13, +13, 1);
        xAxis.setLabel("X");

        NumberAxis yAxis = new NumberAxis(-13, +13, 1);
        yAxis.setLabel("Y");

        XYChart.Series<Number,Number> series = new XYChart.Series<>();
        series.setName("Samples");

        for (Point2D point : PointsData.getSamples()) {
            series.getData().add(new XYChart.Data<>(point.getX(), point.getY()));
        }

        ScatterChart<Number,Number> chart = new ScatterChart<>(xAxis, yAxis);
        chart.setId("points-chart");
        chart.getData().add(series);

        Scene scene = new Scene(chart, 600, 600);
        scene.getStylesheets().add(getClass().getResource("points-chart.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
