package fr.polytech.dsl.dsl.execution.executors;

import fr.polytech.dsl.dsl.execution.Measure;
import fr.polytech.dsl.dsl.execution.executors.Executor;
import fr.polytech.dsl.dsl.model.structures.laws.InterpolateLaw.Point;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

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

        double[] x = new double[listPoints.size()];
        double[] y = new double[listPoints.size()];

        for (int i = 0; i < listPoints.size(); ++i){
            x[i] = listPoints.get(i).getX();
            y[i] = listPoints.get(i).getY();
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

        Measure measure = new Measure(lastTimeGet, function.value(variable), name);

        nextUnscaledVariable += samplingPeriod;
        lastTimeGet += samplingPeriod;

        return measure;
    }

    @Override
    public boolean hasFinished() {
        return lastTimeGet >= dateFrom + duration;
    }
}
