package fr.polytech.dsl.dsl.execution.executors;

import fr.polytech.dsl.dsl.execution.Measure;
import fr.polytech.dsl.dsl.execution.executors.Executor;
import fr.polytech.dsl.dsl.model.structures.laws.InterpolateLaw.Point;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.util.ArrayList;
import java.util.List;

public class InterpolationExecutor extends Executor {
    private PolynomialSplineFunction function;
    private double max;
    private double offset;
    private double period;
    private double nextUnscaledVariable;

    public InterpolationExecutor(String name,
                                 long dateFrom,
                                 long duration,
                                 fr.polytech.dsl.dsl.model.structures.simulations.modifications.Noise noise,
                                 double samplingPeriod,
                                 double min,
                                 double max,
                                 double period,
                                 List<Point> listPoints){
        super(name, dateFrom, duration, noise, samplingPeriod);
        List<Point> listCopy = new ArrayList<>(listPoints);

        Point first = listCopy.get(0);
        Point last = listCopy.get(listPoints.size() - 1);

        // If the first point doesnt start exactly where the range of the function starts, add a point
        if (first.getX() > min){
            listCopy.add(0, new Point(min - (max - last.getX()), last.getY()));
        }

        // Add a point after the end of the range to have a nice and cool interpolation
        listCopy.add(new Point(max + first.getX(), first.getY()));
        
        double[] x = new double[listCopy.size()];
        double[] y = new double[listCopy.size()];

        for (int i = 0; i < listCopy.size(); ++i){
            x[i] = listCopy.get(i).getX();
            y[i] = listCopy.get(i).getY();
        }

        LinearInterpolator interpolator = new LinearInterpolator();
        function = interpolator.interpolate(x, y);

        this.max = max - min;
        this.offset = min;
        this.period = period;
        this.nextUnscaledVariable = 0;
    }

    @Override
    public Measure getNext() {
        nextUnscaledVariable %= period;
        double variable = (max * nextUnscaledVariable) / period;
        variable += offset;

        Measure measure = new Measure(lastTimeGet, (Integer)((int)function.value(variable)), name);

        nextUnscaledVariable += samplingPeriod;
        lastTimeGet += samplingPeriod;

        return measure;
    }

    @Override
    public boolean hasFinished() {
        return lastTimeGet >= dateFrom + duration;
    }
}
