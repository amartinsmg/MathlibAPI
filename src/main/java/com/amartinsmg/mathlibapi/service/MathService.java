package com.amartinsmg.mathlibapi.service;

import com.amartinsmg.mathlibapi.schema.annotations.ApiFunction;
import com.amartinsmg.mathlibapi.wrapper.MathLibWrapper;

public class MathService {

    public static double triangleArea1(double base, double height) {
        return MathLibWrapper.triangleArea1(base, height);
    }

    public static double triangleArea2(double sideA, double sideB, double angleGama) {
        return MathLibWrapper.triangleArea2(sideA, sideB, angleGama);
    }

    public static double triangleArea3(double sideA, double sideB, double sideC) {
        return MathLibWrapper.triangleArea3(sideA, sideB, sideC);
    }

    public static double squareArea(double side) {
        return MathLibWrapper.squareArea(side);
    }

    public static double retangleArea(double width, double length) {
        return MathLibWrapper.retangleArea(width, length);
    }

    public static double rhombusArea(double largerDiagonal, double smallerDiagonal) {
        return MathLibWrapper.rhombusArea(largerDiagonal, smallerDiagonal);
    }

    public static double parallelogramArea1(double base, double height) {
        return MathLibWrapper.parallelogramArea1(base, height);
    }

    public static double parallelogramArea2(double sideA, double sideB, double angleBeetweenAB) {
        return MathLibWrapper.parallelogramArea2(sideA, sideB, angleBeetweenAB);
    }

    public static double trapezoidArea(double largerBase, double smallerBase, double height) {
        return MathLibWrapper.trapezoidArea(largerBase, smallerBase, height);
    }

    public static double regPolygonArea(double side, int nOfSides) {
        return MathLibWrapper.regPolygonArea(side, nOfSides);
    }

    public static double circleArea(double radius) {
        return MathLibWrapper.circleArea(radius);
    }

    public static double circularSectorArea1(double angle, double radius) {
        return MathLibWrapper.circularSectorArea1(angle, radius);
    }

    public static double circularSectorArea2(double arc, double radius) {
        return MathLibWrapper.circularSectorArea2(arc, radius);
    }

    public static double ellipseArea(double semiMajorAxis, double semiMinorAxis) {
        return MathLibWrapper.ellipseArea(semiMajorAxis, semiMinorAxis);
    }

    public static double cubeArea(double side) {
        return MathLibWrapper.cubeArea(side);
    }

    public static double cuboidArea(double width, double length, double height) {
        return MathLibWrapper.cuboidArea(width, length, height);
    }

    public static double prismArea(double baseArea, double basePerimeter, double height) {
        return MathLibWrapper.prismArea(baseArea, basePerimeter, height);
    }

    public static double regularPrismArea(double baseSide, int nOfBaseSides, double height) {
        return MathLibWrapper.regularPrismArea(baseSide, nOfBaseSides, height);
    }

    public static double pyramidArea(double baseArea, double basePerimeter, double slantHeight) {
        return MathLibWrapper.pyramidArea(baseArea, basePerimeter, slantHeight);
    }

    public static double regPyramidArea(double baseSide, int nOfBaseSides, double heigth) {
        return MathLibWrapper.regPyramidArea(baseSide, nOfBaseSides, heigth);
    }

    public static double cylinderArea(double baseRadius, double height) {
        return MathLibWrapper.cylinderArea(baseRadius, height);
    }

    public static double coneArea(double baseRadius, double height) {
        return MathLibWrapper.coneArea(baseRadius, height);
    }

    public static double sphereArea(double radius) {
        return MathLibWrapper.sphereArea(radius);
    }

    public static boolean isArmstrong(long num) {
        return MathLibWrapper.isArmstrong(num);
    }

    public static double logarithm(double num, double base) {
        return MathLibWrapper.logarithm(num, base);
    }

    public static double nthRoot(double radicand, double degree) {
        return MathLibWrapper.nthRoot(radicand, degree);
    }

    public static double roundTo(double num, int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("decimalPlaces must be >= 0");
        }
        return MathLibWrapper.roundTo(num, decimalPlaces);
    }

    @ApiFunction(name = "permutation")

    public static double permutationSmart(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("num must be >= 0");
        }
        if (num <= 20) {
            return (double) MathLibWrapper.permutation(num);
        }
        return MathLibWrapper.permutationlf(num);
    }

    @ApiFunction(name = "cyclePermutation")
    public static double cyclePermutationSmart(int num) {
        if (num <= 1) {
            throw new IllegalArgumentException("num must be > 1");
        }
        if (num <= 20) {
            return (double) MathLibWrapper.cyclePermutation(num);
        }
        return MathLibWrapper.cyclePermutationlf(num);
    }

    @ApiFunction(name = "arrangement")
    public static double arrangementSmart(int total, int selected) {
        if (total <= 0) {
            throw new IllegalArgumentException("total must be > 0");
        }
        if (selected < 0) {
            throw new IllegalArgumentException("selected must be >= 0");
        }
        if (selected > total) {
            throw new IllegalArgumentException("select must be <= total");
        }
        if (total <= 20) {
            return (double) MathLibWrapper.arrangement(total, selected);
        }
        return MathLibWrapper.arrangementlf(total, selected);
    }

    @ApiFunction(name = "combination")
    public static double combinationSmart(int total, int selected) {
        if (total <= 0) {
            throw new IllegalArgumentException("total must be > 0");
        }
        if (selected < 0) {
            throw new IllegalArgumentException("selected must be >= 0");
        }
        if (selected > total) {
            throw new IllegalArgumentException("select must be <= total");
        }
        if (total <= 20) {
            return (double) MathLibWrapper.combination(total, selected);
        }
        return MathLibWrapper.combinationlf(total, selected);
    }

    @ApiFunction(name = "factorial")
    public static double factorialSmart(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("num must be >= 0");
        }
        if (num <= 20) {
            return (double) MathLibWrapper.factorial(num);
        }
        return MathLibWrapper.factoriallf(num);
    }

    public static long gcd(long x, long y) {
        return MathLibWrapper.gcd(x, y);
    }

    public static double deg2rad(double degrees) {
        return MathLibWrapper.deg2rad(degrees);
    }

    public static double rad2deg(double radians) {
        return MathLibWrapper.rad2deg(radians);
    }

    public static double distancePoints(double a_x, double a_y, double b_x, double b_y) {
        return MathLibWrapper.distancePoints(a_x, a_y, b_x, b_y);
    }

    public static double[] midpointPoints(double a_x, double a_y, double b_x, double b_y) {
        return MathLibWrapper.midpointPoints(a_x, a_y, b_x, b_y);
    }

    public static double slopeLine(double a_x, double a_y, double b_x, double b_y) {
        return MathLibWrapper.slopeLine(a_x, a_y, b_x, b_y);
    }

    public static double inclinationLine(double a_x, double a_y, double b_x, double b_y) {
        return MathLibWrapper.inclinationLine(a_x, a_y, b_x, b_y);
    }

    public static double lineYIntercept(double a_x, double a_y, double b_x, double b_y) {
        return MathLibWrapper.lineYIntercept(a_x, a_y, b_x, b_y);
    }

    public static double distancePointLine(double inclination, double yIntercept, double p_x, double p_y) {
        return MathLibWrapper.distancePointLine(inclination, yIntercept, p_x, p_y);
    }

    public static double circlePerimeter(double radius) {
        return MathLibWrapper.circlePerimeter(radius);
    }

    public static int polygonDiagonals(int nOfSides) {
        return MathLibWrapper.polygonDiagonals(nOfSides);
    }

    public static double convexPolySumIntAng(double nOfSides) {
        return MathLibWrapper.convexPolySumIntAng(nOfSides);
    }

    public static double regPolygonIAng(int nOfSides) {
        return MathLibWrapper.regPolygonIAng(nOfSides);
    }

    public static double convexPolyExtAngle(int nOfSides) {
        return MathLibWrapper.convexPolyExtAngle(nOfSides);
    }

    public static double simpGrowth(double initial, double rate, double interval) {
        return MathLibWrapper.simpGrowth(initial, rate, interval);
    }

    public static double simpGrowthRate(double initial, double final_, double interval) {
        return MathLibWrapper.simpGrowthRate(initial, final_, interval);
    }

    public static double compGrowth(double initial, double rate, double interval) {
        return MathLibWrapper.compGrowth(initial, rate, interval);
    }

    public static double compGrowthRate(double initial, double final_, double interval) {
        return MathLibWrapper.compGrowthRate(initial, final_, interval);
    }

    public static boolean isHappy(long num) {
        return MathLibWrapper.isHappy(num);
    }

    public static long lcm(long x, long y) {
        return MathLibWrapper.lcm(x, y);
    }

    public static double nPercentOfX(double x, double n) {
        return MathLibWrapper.nPercentOfX(x, n);
    }

    public static double nIsWhatPercentOfX(double x, double n) {
        return MathLibWrapper.nIsWhatPercentOfX(x, n);
    }

    public static boolean isPerfect(long num) {
        return MathLibWrapper.isPerfect(num);
    }

    public static long[] primeFactors(long num) {
        return MathLibWrapper.primeFactors(num);

    }

    public static boolean isPrime(long num) {
        return MathLibWrapper.isPrime(num);
    }

    public static double binominal(int trials, double successProb, int success) {
        return MathLibWrapper.binominal(trials, successProb, success);
    }

    public static double poisson(double lambda, int x) {
        if (x < 0) {
            throw new IllegalArgumentException("x must be >= 0");
        }
        return MathLibWrapper.poisson(lambda, x);
    }

    public static double gaussianCDF(double mu, double stdDev, double x) {
        return MathLibWrapper.gaussianCDF(mu, stdDev, x);
    }

    public static double mean(double[] arr) {
        return MathLibWrapper.mean(arr);
    }

    public static double trimmedMean(double[] arr, double percentage) {
        return MathLibWrapper.trimmedMean(arr, percentage);
    }

    public static double geometricMean(double[] arr) {
        return MathLibWrapper.geometricMean(arr);
    }

    public static double harmonicMean(double[] arr) {
        return MathLibWrapper.harmonicMean(arr);
    }

    public static double median(double[] arr) {
        return MathLibWrapper.median(arr);
    }

    public static double[] mode(double[] arr) {
        return MathLibWrapper.mode(arr);
    }

    public static double min(double[] arr) {
        return MathLibWrapper.min(arr);
    }

    public static double max(double[] arr) {
        return MathLibWrapper.max(arr);
    }

    public static double range(double[] arr) {
        return MathLibWrapper.range(arr);
    }

    public static double midrange(double[] arr) {
        return MathLibWrapper.midrange(arr);
    }

    public static double variance(double[] arr) {
        return MathLibWrapper.variance(arr);
    }

    public static double stdDev(double[] arr) {
        return MathLibWrapper.stdDev(arr);
    }

    public static double sampleVariance(double[] arr) {
        return MathLibWrapper.sampleVariance(arr);
    }

    public static double sampleStdDev(double[] arr) {
        return MathLibWrapper.sampleStdDev(arr);
    }

    public static double hypotenuse(double sideA, double sideB) {
        return MathLibWrapper.hypotenuse(sideA, sideB);
    }

    public static double sideRTriangle(double hypotenuse, double sideA) {
        return MathLibWrapper.sideRTriangle(hypotenuse, sideA);
    }

    public static double sideTriangleLC(double sideA, double sideB, double oppositeAng) {
        return MathLibWrapper.sideTriangleLC(sideA, sideB, oppositeAng);
    }

    public static double angTriangleLC(double oppositeSide, double sideA, double sideB) {
        return MathLibWrapper.angTriangleLC(oppositeSide, sideA, sideB);
    }

    public static double sideTriangleLS(double oppositeAng, double sideA, double oppositeAng2A) {
        return MathLibWrapper.sideTriangleLS(oppositeAng, sideA, oppositeAng2A);
    }

    public static double angTriangleLS(double oppositeSide, double sideA, double oppositeAng2A) {
        return MathLibWrapper.angTriangleLS(oppositeSide, sideA, oppositeAng2A);
    }

    public static double cubeVol(double side) {
        return MathLibWrapper.cubeVol(side);
    }

    public static double cuboidVol(double length, double width, double height) {
        return MathLibWrapper.cuboidVol(length, width, height);
    }

    public static double prismVol(double baseArea, double height) {
        return MathLibWrapper.prismVol(baseArea, height);
    }

    public static double regularPrismVol(double baseSide, int nOfBaseSides, double height) {
        return MathLibWrapper.regularPrismVol(baseSide, nOfBaseSides, height);
    }

    public static double pyramidVol(double baseArea, double height) {
        return MathLibWrapper.pyramidVol(baseArea, height);
    }

    public static double regPyramidVol(double baseSide, int nOfBaseSides, double height) {
        return MathLibWrapper.regPyramidVol(baseSide, nOfBaseSides, height);
    }

    public static double cylinderVol(double baseRadius, double height) {
        return MathLibWrapper.cylinderVol(baseRadius, height);
    }

    public static double coneVol(double baseRadius, double height) {
        return MathLibWrapper.coneVol(baseRadius, height);
    }

    public static double sphereVol(double radius) {
        return MathLibWrapper.sphereVol(radius);
    }

}
