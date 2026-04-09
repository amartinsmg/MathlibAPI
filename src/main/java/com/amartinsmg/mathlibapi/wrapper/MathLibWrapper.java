package com.amartinsmg.mathlibapi.wrapper;

import com.amartinsmg.mathlibapi.schema.annotations.ApiFunction;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.LongByReference;

public class MathLibWrapper {

    private static final MathLibNative lib = MathLibNative.INSTANCE;

    @ApiFunction
    public static double triangleArea1(double base, double height) {
        return lib.triangleArea1(base, height);
    }

    @ApiFunction
    public static double triangleArea2(double sideA, double sideB, double angleGama) {
        return lib.triangleArea2(sideA, sideB, angleGama);
    }

    @ApiFunction
    public static double triangleArea3(double sideA, double sideB, double sideC) {
        return lib.triangleArea3(sideA, sideB, sideC);
    }

    @ApiFunction
    public static double squareArea(double side) {
        return lib.squareArea(side);
    }

    @ApiFunction
    public static double retangleArea(double width, double length) {
        return lib.retangleArea(width, length);
    }

    @ApiFunction
    public static double rhombusArea(double largerDiagonal, double smallerDiagonal) {
        return lib.rhombusArea(largerDiagonal, smallerDiagonal);
    }

    @ApiFunction
    public static double parallelogramArea1(double base, double height) {
        return lib.parallelogramArea1(base, height);
    }

    @ApiFunction
    public static double parallelogramArea2(double sideA, double sideB, double angleBeetweenAB) {
        return lib.parallelogramArea2(sideA, sideB, angleBeetweenAB);
    }

    @ApiFunction
    public static double trapezoidArea(double largerBase, double smallerBase, double height) {
        return lib.trapezoidArea(largerBase, smallerBase, height);
    }

    @ApiFunction
    public static double regPolygonArea(double side, int nOfSides) {
        return lib.regPolygonArea(side, nOfSides);
    }

    @ApiFunction
    public static double circleArea(double radius) {
        return lib.circleArea(radius);
    }

    @ApiFunction
    public static double circularSectorArea1(double angle, double radius) {
        return lib.circularSectorArea1(angle, radius);
    }

    @ApiFunction
    public static double circularSectorArea2(double arc, double radius) {
        return lib.circularSectorArea2(arc, radius);
    }

    @ApiFunction
    public static double ellipseArea(double semiMajorAxis, double semiMinorAxis) {
        return lib.ellipseArea(semiMajorAxis, semiMinorAxis);
    }

    @ApiFunction
    public static double cubeArea(double side) {
        return lib.cubeArea(side);
    }

    @ApiFunction
    public static double cuboidArea(double width, double length, double height) {
        return lib.cuboidArea(width, length, height);
    }

    @ApiFunction
    public static double prismArea(double baseArea, double basePerimeter, double height) {
        return lib.prismArea(baseArea, basePerimeter, height);
    }

    @ApiFunction
    public static double regularPrismArea(double baseSide, int nOfBaseSides, double height) {
        return lib.regularPrismArea(baseSide, nOfBaseSides, height);
    }

    @ApiFunction
    public static double pyramidArea(double baseArea, double basePerimeter, double slantHeight) {
        return lib.pyramidArea(baseArea, basePerimeter, slantHeight);
    }

    @ApiFunction
    public static double regPyramidArea(double baseSide, int nOfBaseSides, double heigth) {
        return lib.regPyramidArea(baseSide, nOfBaseSides, heigth);
    }

    @ApiFunction
    public static double cylinderArea(double baseRadius, double height) {
        return lib.cylinderArea(baseRadius, height);
    }

    @ApiFunction
    public static double coneArea(double baseRadius, double height) {
        return lib.coneArea(baseRadius, height);
    }

    @ApiFunction
    public static double sphereArea(double radius) {
        return lib.sphereArea(radius);
    }

    @ApiFunction
    public static boolean isArmstrong(long num) {
        return lib.isArmstrong(num);
    }

    @ApiFunction
    public static double logarithm(double num, double base) {
        return lib.logarithm(num, base);
    }

    @ApiFunction
    public static double nthRoot(double radicand, double degree) {
        return lib.nthRoot(radicand, degree);
    }

    @ApiFunction
    public static double roundTo(double num, int decimalPlaces) {
        return lib.roundTo(num, decimalPlaces);
    }

    public static long permutation(int num) {
        return lib.permutation(num);
    }

    public static long cyclePermutation(int num) {
        return lib.cyclePermutation(num);
    }

    public static long arrangement(int total, int selected) {
        return lib.arrangement(total, selected);
    }

    public static long combination(int total, int selected) {
        return lib.combination(total, selected);
    }

    public static double permutationlf(int num) {
        return lib.permutationlf(num);
    }

    public static double cyclePermutationlf(int num) {
        return lib.cyclePermutationlf(num);
    }

    public static double arrangementlf(int total, int selected) {
        return lib.arrangementlf(total, selected);
    }

    public static double combinationlf(int total, int selected) {
        return lib.combinationlf(total, selected);
    }

    public static long factorial(int num) {
        return lib.factorial(num);
    }

    public static double factoriallf(int num) {
        return lib.factoriallf(num);
    }

    @ApiFunction
    public static long gcd(long x, long y) {
        return lib.gcd(x, y);
    }

    @ApiFunction
    public static double deg2rad(double degrees) {
        return lib.deg2rad(degrees);
    }

    @ApiFunction
    public static double rad2deg(double radians) {
        return lib.rad2deg(radians);
    }

    @ApiFunction
    public static double distancePoints(double a_x, double a_y, double b_x, double b_y) {
        return lib.distancePoints(a_x, a_y, b_x, b_y);
    }

    @ApiFunction
    public static double[] midpointPoints(double a_x, double a_y, double b_x, double b_y) {
        Pointer ptr = lib.midpointPoints(a_x, a_y, b_x, b_y);

        double[] result = new double[2];

        result[0] = ptr.getDouble(0);
        result[1] = ptr.getDouble(8);

        lib.freeArray(ptr);

        return result;
    }

    @ApiFunction
    public static double slopeLine(double a_x, double a_y, double b_x, double b_y) {
        return lib.slopeLine(a_x, a_y, b_x, b_y);
    }

    @ApiFunction
    public static double inclinationLine(double a_x, double a_y, double b_x, double b_y) {
        return lib.inclinationLine(a_x, a_y, b_x, b_y);
    }

    @ApiFunction
    public static double lineYIntercept(double a_x, double a_y, double b_x, double b_y) {
        return lib.lineYIntercept(a_x, a_y, b_x, b_y);
    }

    @ApiFunction
    public static double distancePointLine(double inclination, double yIntercept, double p_x, double p_y) {
        return lib.distancePointLine(inclination, yIntercept, p_x, p_y);
    }

    @ApiFunction
    public static double circlePerimeter(double radius) {
        return lib.circlePerimeter(radius);
    }

    @ApiFunction
    public static int polygonDiagonals(int nOfSides) {
        return lib.polygonDiagonals(nOfSides);
    }

    @ApiFunction
    public static double convexPolySumIntAng(double nOfSides) {
        return lib.convexPolySumIntAng(nOfSides);
    }

    @ApiFunction
    public static double regPolygonIAng(int nOfSides) {
        return lib.regPolygonIAng(nOfSides);
    }

    @ApiFunction
    public static double convexPolyExtAngle(int nOfSides) {
        return lib.convexPolyExtAngle(nOfSides);
    }

    @ApiFunction
    public static double simpGrowth(double initial, double rate, double interval) {
        return lib.simpGrowth(initial, rate, interval);
    }

    @ApiFunction
    public static double simpGrowthRate(double initial, double final_, double interval) {
        return lib.simpGrowthRate(initial, final_, interval);
    }

    @ApiFunction
    public static double compGrowth(double initial, double rate, double interval) {
        return lib.compGrowth(initial, rate, interval);
    }

    @ApiFunction
    public static double compGrowthRate(double initial, double final_, double interval) {
        return lib.compGrowthRate(initial, final_, interval);
    }

    @ApiFunction
    public static boolean isHappy(long num) {
        return lib.isHappy(num);
    }

    @ApiFunction
    public static long lcm(long x, long y) {
        return lib.lcm(x, y);
    }

    @ApiFunction
    public static double nPercentOfX(double x, double n) {
        return lib.nPercentOfX(x, n);
    }

    @ApiFunction
    public static double nIsWhatPercentOfX(double x, double n) {
        return lib.nIsWhatPercentOfX(x, n);
    }

    @ApiFunction
    public static boolean isPerfect(long num) {
        return lib.isPerfect(num);
    }

    @ApiFunction
    public static long[] primeFactors(long num) {
        LongByReference sizeRef = new LongByReference();
        Pointer ptr = lib.primeFactors(num, sizeRef);
        int size = (int) sizeRef.getValue();

        long[] result = new long[size];

        for (int i = 0; i < size; i++) {
            result[i] = ptr.getLong(i * 8);
        }

        lib.freeArray(ptr);

        return result;
    }

    @ApiFunction
    public static boolean isPrime(long num) {
        return lib.isPrime(num);
    }

    @ApiFunction
    public static double binominal(int trials, double successProb, int success) {
        return lib.binominal(trials, successProb, success);
    }

    @ApiFunction
    public static double poisson(double lambda, int x) {
        return lib.poisson(lambda, x);
    }

    @ApiFunction
    public static double gaussianCDF(double mu, double stdDev, double x) {
        return lib.gaussianCDF(mu, stdDev, x);
    }

    @ApiFunction
    public static double mean(double[] arr) {
        return lib.mean(arr, arr.length);
    }

    @ApiFunction
    public static double trimmedMean(double[] arr, double percentage) {
        return lib.trimmedMean(arr, arr.length, percentage);
    }

    @ApiFunction
    public static double geometricMean(double[] arr) {
        return lib.geometricMean(arr, arr.length);
    }

    @ApiFunction
    public static double harmonicMean(double[] arr) {
        return lib.harmonicMean(arr, arr.length);
    }

    @ApiFunction
    public static double median(double[] arr) {
        return lib.median(arr, arr.length);
    }

    @ApiFunction
    public static double[] mode(double[] arr) {
        LongByReference sizeRef = new LongByReference();
        Pointer ptr = lib.mode(arr, arr.length, sizeRef);
        int size = (int) sizeRef.getValue();

        double[] result = new double[size];

        for (int i = 0; i < size; i++) {
            result[i] = ptr.getDouble(i * 8);
        }

        lib.freeArray(ptr);

        return result;
    }

    @ApiFunction
    public static double min(double[] arr) {
        return lib.min(arr, arr.length);
    }

    @ApiFunction
    public static double max(double[] arr) {
        return lib.max(arr, arr.length);
    }

    @ApiFunction
    public static double range(double[] arr) {
        return lib.range(arr, arr.length);
    }

    @ApiFunction
    public static double midrange(double[] arr) {
        return lib.midrange(arr, arr.length);
    }

    @ApiFunction
    public static double variance(double[] arr) {
        return lib.variance(arr, arr.length);
    }

    @ApiFunction
    public static double stdDev(double[] arr) {
        return lib.stdDev(arr, arr.length);
    }

    @ApiFunction
    public static double sampleVariance(double[] arr) {
        return lib.sampleVariance(arr, arr.length);
    }

    @ApiFunction
    public static double sampleStdDev(double[] arr) {
        return lib.sampleStdDev(arr, arr.length);
    }

    @ApiFunction
    public static double hypotenuse(double sideA, double sideB) {
        return lib.hypotenuse(sideA, sideB);
    }

    @ApiFunction
    public static double sideRTriangle(double hypotenuse, double sideA) {
        return lib.sideRTriangle(hypotenuse, sideA);
    }

    @ApiFunction
    public static double sideTriangleLC(double sideA, double sideB, double oppositeAng) {
        return lib.sideTriangleLC(sideA, sideB, oppositeAng);
    }

    @ApiFunction
    public static double angTriangleLC(double oppositeSide, double sideA, double sideB) {
        return lib.angTriangleLC(oppositeSide, sideA, sideB);
    }

    @ApiFunction
    public static double sideTriangleLS(double oppositeAng, double sideA, double oppositeAng2A) {
        return lib.sideTriangleLS(oppositeAng, sideA, oppositeAng2A);
    }

    @ApiFunction
    public static double angTriangleLS(double oppositeSide, double sideA, double oppositeAng2A) {
        return lib.angTriangleLS(oppositeSide, sideA, oppositeAng2A);
    }

    @ApiFunction
    public static double cubeVol(double side) {
        return lib.cubeVol(side);
    }

    @ApiFunction
    public static double cuboidVol(double length, double width, double height) {
        return lib.cuboidVol(length, width, height);
    }

    @ApiFunction
    public static double prismVol(double baseArea, double height) {
        return lib.prismVol(baseArea, height);
    }

    @ApiFunction
    public static double regularPrismVol(double baseSide, int nOfBaseSides, double height) {
        return lib.regularPrismVol(baseSide, nOfBaseSides, height);
    }

    @ApiFunction
    public static double pyramidVol(double baseArea, double height) {
        return lib.pyramidVol(baseArea, height);
    }

    @ApiFunction
    public static double regPyramidVol(double baseSide, int nOfBaseSides, double height) {
        return lib.regPyramidVol(baseSide, nOfBaseSides, height);
    }

    @ApiFunction
    public static double cylinderVol(double baseRadius, double height) {
        return lib.cylinderVol(baseRadius, height);
    }

    @ApiFunction
    public static double coneVol(double baseRadius, double height) {
        return lib.coneVol(baseRadius, height);
    }

    @ApiFunction
    public static double sphereVol(double radius) {
        return lib.sphereVol(radius);
    }

}
