package com.amartinsmg.mathlibapi.wrapper;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.LongByReference;

public class MathLibWrapper {

    private static final MathLibNative lib = MathLibNative.INSTANCE;

    public static double triangleArea1(double base, double height) {
        return lib.triangleArea1(base, height);
    }

    public static double triangleArea2(double sideA, double sideB, double angleGama) {
        return lib.triangleArea2(sideA, sideB, angleGama);
    }

    public static double triangleArea3(double sideA, double sideB, double sideC) {
        return lib.triangleArea3(sideA, sideB, sideC);
    }

    public static double squareArea(double side) {
        return lib.squareArea(side);
    }

    public static double retangleArea(double width, double length) {
        return lib.retangleArea(width, length);
    }

    public static double rhombusArea(double largerDiagonal, double smallerDiagonal) {
        return lib.rhombusArea(largerDiagonal, smallerDiagonal);
    }

    public static double parallelogramArea1(double base, double height) {
        return lib.parallelogramArea1(base, height);
    }

    public static double parallelogramArea2(double sideA, double sideB, double angleBeetweenAB) {
        return lib.parallelogramArea2(sideA, sideB, angleBeetweenAB);
    }

    public static double trapezoidArea(double largerBase, double smallerBase, double height) {
        return lib.trapezoidArea(largerBase, smallerBase, height);
    }

    public static double regPolygonArea(double side, int nOfSides) {
        return lib.regPolygonArea(side, nOfSides);
    }

    public static double circleArea(double radius) {
        return lib.circleArea(radius);
    }

    public static double circularSectorArea1(double angle, double radius) {
        return lib.circularSectorArea1(angle, radius);
    }

    public static double circularSectorArea2(double arc, double radius) {
        return lib.circularSectorArea2(arc, radius);
    }

    public static double ellipseArea(double semiMajorAxis, double semiMinorAxis) {
        return lib.ellipseArea(semiMajorAxis, semiMinorAxis);
    }

    public static double cubeArea(double side) {
        return lib.cubeArea(side);
    }

    public static double cuboidArea(double width, double length, double height) {
        return lib.cuboidArea(width, length, height);
    }

    public static double prismArea(double baseArea, double basePerimeter, double height) {
        return lib.prismArea(baseArea, basePerimeter, height);
    }

    public static double regularPrismArea(double baseSide, int nOfBaseSides, double height) {
        return lib.regularPrismArea(baseSide, nOfBaseSides, height);
    }

    public static double pyramidArea(double baseArea, double basePerimeter, double slantHeight) {
        return lib.pyramidArea(baseArea, basePerimeter, slantHeight);
    }

    public static double regPyramidArea(double baseSide, int nOfBaseSides, double heigth) {
        return lib.regPyramidArea(baseSide, nOfBaseSides, heigth);
    }

    public static double cylinderArea(double baseRadius, double height) {
        return lib.cylinderArea(baseRadius, height);
    }

    public static double coneArea(double baseRadius, double height) {
        return lib.coneArea(baseRadius, height);
    }

    public static double sphereArea(double radius) {
        return lib.sphereArea(radius);
    }

    public static boolean isArmstrong(long num) {
        return lib.isArmstrong(num);
    }

    public static double logarithm(double num, double base) {
        return lib.logarithm(num, base);
    }

    public static double nthRoot(double radicand, double degree) {
        return lib.nthRoot(radicand, degree);
    }

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

    public static long gcd(long x, long y) {
        return lib.gcd(x, y);
    }

    public static double deg2rad(double degrees) {
        return lib.deg2rad(degrees);
    }

    public static double rad2deg(double radians) {
        return lib.rad2deg(radians);
    }

    public static double distancePoints(double a_x, double a_y, double b_x, double b_y) {
        return lib.distancePoints(a_x, a_y, b_x, b_y);
    }

    public static double[] midpointPoints(double a_x, double a_y, double b_x, double b_y) {
        Pointer ptr = lib.midpointPoints(a_x, a_y, b_x, b_y);

        double[] result = new double[2];

        result[0] = ptr.getDouble(0);
        result[1] = ptr.getDouble(8);

        lib.freeArray(ptr);

        return result;
    }

    public static double slopeLine(double a_x, double a_y, double b_x, double b_y) {
        return lib.slopeLine(a_x, a_y, b_x, b_y);
    }

    public static double inclinationLine(double a_x, double a_y, double b_x, double b_y) {
        return lib.inclinationLine(a_x, a_y, b_x, b_y);
    }

    public static double lineYIntercept(double a_x, double a_y, double b_x, double b_y) {
        return lib.lineYIntercept(a_x, a_y, b_x, b_y);
    }

    public static double distancePointLine(double inclination, double yIntercept, double p_x, double p_y) {
        return lib.distancePointLine(inclination, yIntercept, p_x, p_y);
    }

    public static double circlePerimeter(double radius) {
        return lib.circlePerimeter(radius);
    }

    public static int polygonDiagonals(int nOfSides) {
        return lib.polygonDiagonals(nOfSides);
    }

    public static double convexPolySumIntAng(double nOfSides) {
        return lib.convexPolySumIntAng(nOfSides);
    }

    public static double regPolygonIAng(int nOfSides) {
        return lib.regPolygonIAng(nOfSides);
    }

    public static double convexPolyExtAngle(int nOfSides) {
        return lib.convexPolyExtAngle(nOfSides);
    }

    public static double simpGrowth(double initial, double rate, double interval) {
        return lib.simpGrowth(initial, rate, interval);
    }

    public static double simpGrowthRate(double initial, double final_, double interval) {
        return lib.simpGrowthRate(initial, final_, interval);
    }

    public static double compGrowth(double initial, double rate, double interval) {
        return lib.compGrowth(initial, rate, interval);
    }

    public static double compGrowthRate(double initial, double final_, double interval) {
        return lib.compGrowthRate(initial, final_, interval);
    }

    public static boolean isHappy(long num) {
        return lib.isHappy(num);
    }

    public static long lcm(long x, long y) {
        return lib.lcm(x, y);
    }

    public static double nPercentOfX(double x, double n) {
        return lib.nPercentOfX(x, n);
    }

    public static double nIsWhatPercentOfX(double x, double n) {
        return lib.nIsWhatPercentOfX(x, n);
    }

    public static boolean isPerfect(long num) {
        return lib.isPerfect(num);
    }

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

    public static boolean isPrime(long num) {
        return lib.isPrime(num);
    }

    public static double binominal(int trials, double successProb, int success) {
        return lib.binominal(trials, successProb, success);
    }

    public static double poisson(double lambda, int x) {
        return lib.poisson(lambda, x);
    }

    public static double gaussianCDF(double mu, double stdDev, double x) {
        return lib.gaussianCDF(mu, stdDev, x);
    }

    public static double mean(double[] arr) {
        return lib.mean(arr, arr.length);
    }

    public static double trimmedMean(double[] arr, double percentage) {
        return lib.trimmedMean(arr, arr.length, percentage);
    }

    public static double geometricMean(double[] arr) {
        return lib.geometricMean(arr, arr.length);
    }

    public static double harmonicMean(double[] arr) {
        return lib.harmonicMean(arr, arr.length);
    }

    public static double median(double[] arr) {
        return lib.median(arr, arr.length);
    }

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

    public static double min(double[] arr) {
        return lib.min(arr, arr.length);
    }

    public static double max(double[] arr) {
        return lib.max(arr, arr.length);
    }

    public static double range(double[] arr) {
        return lib.range(arr, arr.length);
    }

    public static double midrange(double[] arr) {
        return lib.midrange(arr, arr.length);
    }

    public static double variance(double[] arr) {
        return lib.variance(arr, arr.length);
    }

    public static double stdDev(double[] arr) {
        return lib.stdDev(arr, arr.length);
    }

    public static double sampleVariance(double[] arr) {
        return lib.sampleVariance(arr, arr.length);
    }

    public static double sampleStdDev(double[] arr) {
        return lib.sampleStdDev(arr, arr.length);
    }

    public static double hypotenuse(double sideA, double sideB) {
        return lib.hypotenuse(sideA, sideB);
    }

    public static double sideRTriangle(double hypotenuse, double sideA) {
        return lib.sideRTriangle(hypotenuse, sideA);
    }

    public static double sideTriangleLC(double sideA, double sideB, double oppositeAng) {
        return lib.sideTriangleLC(sideA, sideB, oppositeAng);
    }

    public static double angTriangleLC(double oppositeSide, double sideA, double sideB) {
        return lib.angTriangleLC(oppositeSide, sideA, sideB);
    }

    public static double sideTriangleLS(double oppositeAng, double sideA, double oppositeAng2A) {
        return lib.sideTriangleLS(oppositeAng, sideA, oppositeAng2A);
    }

    public static double angTriangleLS(double oppositeSide, double sideA, double oppositeAng2A) {
        return lib.angTriangleLS(oppositeSide, sideA, oppositeAng2A);
    }

    public static double cubeVol(double side) {
        return lib.cubeVol(side);
    }

    public static double cuboidVol(double length, double width, double height) {
        return lib.cuboidVol(length, width, height);
    }

    public static double prismVol(double baseArea, double height) {
        return lib.prismVol(baseArea, height);
    }

    public static double regularPrismVol(double baseSide, int nOfBaseSides, double height) {
        return lib.regularPrismVol(baseSide, nOfBaseSides, height);
    }

    public static double pyramidVol(double baseArea, double height) {
        return lib.pyramidVol(baseArea, height);
    }

    public static double regPyramidVol(double baseSide, int nOfBaseSides, double height) {
        return lib.regPyramidVol(baseSide, nOfBaseSides, height);
    }

    public static double cylinderVol(double baseRadius, double height) {
        return lib.cylinderVol(baseRadius, height);
    }

    public static double coneVol(double baseRadius, double height) {
        return lib.coneVol(baseRadius, height);
    }

    public static double sphereVol(double radius) {
        return lib.sphereVol(radius);
    }

}
