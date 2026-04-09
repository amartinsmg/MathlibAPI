package com.amartinsmg.mathlibapi.wrapper;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.LongByReference;

public interface MathLibNative extends Library {

    MathLibNative INSTANCE = Native.load("mathlib", MathLibNative.class);

    void freeArray(Pointer ptr);

    double triangleArea1(double base, double height);

    double triangleArea2(double sideA, double sideB, double angleGama);

    double triangleArea3(double sideA, double sideB, double sideC);

    double squareArea(double side);

    double retangleArea(double width, double length);

    double rhombusArea(double largerDiagonal, double smallerDiagonal);

    double parallelogramArea1(double base, double height);

    double parallelogramArea2(double sideA, double sideB, double angleBeetweenAB);

    double trapezoidArea(double largerBase, double smallerBase, double height);

    double regPolygonArea(double side, int nOfSides);

    double circleArea(double radius);

    double circularSectorArea1(double angle, double radius);

    double circularSectorArea2(double arc, double radius);

    double ellipseArea(double semiMajorAxis, double semiMinorAxis);

    double cubeArea(double side);

    double cuboidArea(double width, double length, double height);

    double prismArea(double baseArea, double basePerimeter, double height);

    double regularPrismArea(double baseSide, int nOfBaseSides, double height);

    double pyramidArea(double baseArea, double basePerimeter, double slantHeight);

    double regPyramidArea(double baseSide, int nOfBaseSides, double height);

    double cylinderArea(double baseRadius, double height);

    double coneArea(double baseRadius, double height);

    double sphereArea(double radius);

    boolean isArmstrong(long num);

    double logarithm(double num, double base);

    double nthRoot(double radicand, double degree);

    double roundTo(double num, int decimalPlaces);

    long permutation(int num);

    long cyclePermutation(int num);

    long arrangement(int total, int selected);

    long combination(int total, int selected);

    double permutationlf(int num);

    double cyclePermutationlf(int num);

    double arrangementlf(int total, int selected);

    double combinationlf(int total, int selected);

    long factorial(int num);

    double factoriallf(int num);

    long gcd(long x, long y);

    double deg2rad(double degrees);

    double rad2deg(double radians);

    double distancePoints(double aX, double aY, double bX, double bY);

    Pointer midpointPoints(double aX, double aY, double bX, double bY);

    double slopeLine(double aX, double aY, double bX, double bY);

    double inclinationLine(double aX, double aY, double bX, double bY);

    double lineYIntercept(double aX, double aY, double bX, double bY);

    double distancePointLine(double inclination, double yIntercept, double pX, double pY);

    double circlePerimeter(double radius);

    int polygonDiagonals(int nOfSides);

    double convexPolySumIntAng(double nOfSides);

    double regPolygonIAng(int nOfSides);

    double convexPolyExtAngle(int nOfSides);

    double simpGrowth(double initial, double rate, double interval);

    double simpGrowthRate(double initial, double final_, double interval);

    double compGrowth(double initial, double rate, double interval);

    double compGrowthRate(double initial, double final_, double interval);

    boolean isHappy(long num);

    long lcm(long x, long y);

    double nPercentOfX(double x, double n);

    double nIsWhatPercentOfX(double x, double n);

    boolean isPerfect(long num);

    Pointer primeFactors(long num, LongByReference size);

    boolean isPrime(long num);

    double binominal(int trials, double successProb, int success);

    double poisson(double lambda, long x);

    double gaussianCDF(double mu, double stdDev, double x);

    double mean(double[] arr, long length);

    double trimmedMean(double[] arr, long length, double percentage);

    double geometricMean(double[] arr, long length);

    double harmonicMean(double[] arr, long length);

    double median(double[] arr, long length);

    Pointer mode(double[] arr, long length, LongByReference size);

    double min(double[] arr, long length);

    double max(double[] arr, long length);

    double range(double[] arr, long length);

    double midrange(double[] arr, long length);

    double variance(double[] arr, long length);

    double stdDev(double[] arr, long length);

    double sampleVariance(double[] arr, long length);

    double sampleStdDev(double[] arr, long length);

    double hypotenuse(double sideA, double sideB);

    double sideRTriangle(double hypotenuse, double sideA);

    double sideTriangleLC(double sideA, double sideB, double oppositeAng);

    double angTriangleLC(double oppositeSide, double sideA, double sideB);

    double sideTriangleLS(double oppositeAng, double sideA, double oppositeAng2A);

    double angTriangleLS(double oppositeSide, double sideA, double oppositeAng2A);

    double cubeVol(double side);

    double cuboidVol(double length, double width, double height);

    double prismVol(double baseArea, double height);

    double regularPrismVol(double baseSide, int nOfBaseSides, double height);

    double pyramidVol(double baseArea, double height);

    double regPyramidVol(double baseSide, int nOfBaseSides, double height);

    double cylinderVol(double baseRadius, double height);

    double coneVol(double baseRadius, double height);

    double sphereVol(double radius);
}
