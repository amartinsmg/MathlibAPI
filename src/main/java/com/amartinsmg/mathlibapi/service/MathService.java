package com.amartinsmg.mathlibapi.service;

import com.amartinsmg.mathlibapi.schema.annotations.ApiFunction;
import com.amartinsmg.mathlibapi.wrapper.MathLibWrapper;

public class MathService {

    @ApiFunction
    public static double triangleArea1(double base, double height) {
        return MathLibWrapper.triangleArea1(base, height);
    }

    @ApiFunction
    public static double triangleArea2(double sideA, double sideB, double angleGama) {
        return MathLibWrapper.triangleArea2(sideA, sideB, angleGama);
    }

    @ApiFunction
    public static double triangleArea3(double sideA, double sideB, double sideC) {
        return MathLibWrapper.triangleArea3(sideA, sideB, sideC);
    }

    @ApiFunction
    public static double squareArea(double side) {
        return MathLibWrapper.squareArea(side);
    }

    @ApiFunction
    public static double retangleArea(double width, double length) {
        return MathLibWrapper.retangleArea(width, length);
    }

    @ApiFunction
    public static double rhombusArea(double largerDiagonal, double smallerDiagonal) {
        return MathLibWrapper.rhombusArea(largerDiagonal, smallerDiagonal);
    }

    @ApiFunction
    public static double parallelogramArea1(double base, double height) {
        return MathLibWrapper.parallelogramArea1(base, height);
    }

    @ApiFunction
    public static double parallelogramArea2(double sideA, double sideB, double angleBeetweenAB) {
        return MathLibWrapper.parallelogramArea2(sideA, sideB, angleBeetweenAB);
    }

    @ApiFunction
    public static double trapezoidArea(double largerBase, double smallerBase, double height) {
        return MathLibWrapper.trapezoidArea(largerBase, smallerBase, height);
    }

    @ApiFunction
    public static double regPolygonArea(double side, int nOfSides) {
        return MathLibWrapper.regPolygonArea(side, nOfSides);
    }

    @ApiFunction
    public static double circleArea(double radius) {
        return MathLibWrapper.circleArea(radius);
    }

    @ApiFunction
    public static double circularSectorArea1(double angle, double radius) {
        return MathLibWrapper.circularSectorArea1(angle, radius);
    }

    @ApiFunction
    public static double circularSectorArea2(double arc, double radius) {
        return MathLibWrapper.circularSectorArea2(arc, radius);
    }

    @ApiFunction
    public static double ellipseArea(double semiMajorAxis, double semiMinorAxis) {
        return MathLibWrapper.ellipseArea(semiMajorAxis, semiMinorAxis);
    }

    @ApiFunction
    public static double cubeArea(double side) {
        return MathLibWrapper.cubeArea(side);
    }

    @ApiFunction
    public static double cuboidArea(double width, double length, double height) {
        return MathLibWrapper.cuboidArea(width, length, height);
    }

    @ApiFunction
    public static double prismArea(double baseArea, double basePerimeter, double height) {
        return MathLibWrapper.prismArea(baseArea, basePerimeter, height);
    }

    @ApiFunction
    public static double regularPrismArea(double baseSide, int nOfBaseSides, double height) {
        return MathLibWrapper.regularPrismArea(baseSide, nOfBaseSides, height);
    }

    @ApiFunction
    public static double pyramidArea(double baseArea, double basePerimeter, double slantHeight) {
        return MathLibWrapper.pyramidArea(baseArea, basePerimeter, slantHeight);
    }

    @ApiFunction
    public static double regPyramidArea(double baseSide, int nOfBaseSides, double heigth) {
        return MathLibWrapper.regPyramidArea(baseSide, nOfBaseSides, heigth);
    }

    @ApiFunction
    public static double cylinderArea(double baseRadius, double height) {
        return MathLibWrapper.cylinderArea(baseRadius, height);
    }

    @ApiFunction
    public static double coneArea(double baseRadius, double height) {
        return MathLibWrapper.coneArea(baseRadius, height);
    }

    @ApiFunction
    public static double sphereArea(double radius) {
        return MathLibWrapper.sphereArea(radius);
    }

    @ApiFunction
    public static boolean isArmstrong(long num) {
        return MathLibWrapper.isArmstrong(num);
    }

    @ApiFunction
    public static double logarithm(double num, double base) {
        return MathLibWrapper.logarithm(num, base);
    }

    @ApiFunction
    public static double nthRoot(double radicand, double degree) {
        return MathLibWrapper.nthRoot(radicand, degree);
    }

    @ApiFunction
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

    @ApiFunction
    public static long gcd(long x, long y) {
        return MathLibWrapper.gcd(x, y);
    }

    @ApiFunction
    public static double deg2rad(double degrees) {
        return MathLibWrapper.deg2rad(degrees);
    }

    @ApiFunction
    public static double rad2deg(double radians) {
        return MathLibWrapper.rad2deg(radians);
    }

    @ApiFunction
    public static double distancePoints(double a_x, double a_y, double b_x, double b_y) {
        return MathLibWrapper.distancePoints(a_x, a_y, b_x, b_y);
    }

    @ApiFunction
    public static double[] midpointPoints(double a_x, double a_y, double b_x, double b_y) {
        return MathLibWrapper.midpointPoints(a_x, a_y, b_x, b_y);
    }

    @ApiFunction
    public static double slopeLine(double a_x, double a_y, double b_x, double b_y) {
        return MathLibWrapper.slopeLine(a_x, a_y, b_x, b_y);
    }

    @ApiFunction
    public static double inclinationLine(double a_x, double a_y, double b_x, double b_y) {
        return MathLibWrapper.inclinationLine(a_x, a_y, b_x, b_y);
    }

    @ApiFunction
    public static double lineYIntercept(double a_x, double a_y, double b_x, double b_y) {
        return MathLibWrapper.lineYIntercept(a_x, a_y, b_x, b_y);
    }

    @ApiFunction
    public static double distancePointLine(double inclination, double yIntercept, double p_x, double p_y) {
        return MathLibWrapper.distancePointLine(inclination, yIntercept, p_x, p_y);
    }

    @ApiFunction
    public static double circlePerimeter(double radius) {
        return MathLibWrapper.circlePerimeter(radius);
    }

    @ApiFunction
    public static int polygonDiagonals(int nOfSides) {
        return MathLibWrapper.polygonDiagonals(nOfSides);
    }

    @ApiFunction
    public static double convexPolySumIntAng(double nOfSides) {
        return MathLibWrapper.convexPolySumIntAng(nOfSides);
    }

    @ApiFunction
    public static double regPolygonIAng(int nOfSides) {
        return MathLibWrapper.regPolygonIAng(nOfSides);
    }

    @ApiFunction
    public static double convexPolyExtAngle(int nOfSides) {
        return MathLibWrapper.convexPolyExtAngle(nOfSides);
    }

    @ApiFunction
    public static double simpGrowth(double initial, double rate, double interval) {
        return MathLibWrapper.simpGrowth(initial, rate, interval);
    }

    @ApiFunction
    public static double simpGrowthRate(double initial, double final_, double interval) {
        return MathLibWrapper.simpGrowthRate(initial, final_, interval);
    }

    @ApiFunction
    public static double compGrowth(double initial, double rate, double interval) {
        return MathLibWrapper.compGrowth(initial, rate, interval);
    }

    @ApiFunction
    public static double compGrowthRate(double initial, double final_, double interval) {
        return MathLibWrapper.compGrowthRate(initial, final_, interval);
    }

    @ApiFunction
    public static boolean isHappy(long num) {
        return MathLibWrapper.isHappy(num);
    }

    @ApiFunction
    public static long lcm(long x, long y) {
        return MathLibWrapper.lcm(x, y);
    }

    @ApiFunction
    public static double nPercentOfX(double x, double n) {
        return MathLibWrapper.nPercentOfX(x, n);
    }

    @ApiFunction
    public static double nIsWhatPercentOfX(double x, double n) {
        return MathLibWrapper.nIsWhatPercentOfX(x, n);
    }

    @ApiFunction
    public static boolean isPerfect(long num) {
        return MathLibWrapper.isPerfect(num);
    }

    @ApiFunction
    public static long[] primeFactors(long num) {
        return MathLibWrapper.primeFactors(num);

    }

    @ApiFunction
    public static boolean isPrime(long num) {
        return MathLibWrapper.isPrime(num);
    }

    @ApiFunction
    public static double binominal(int trials, double successProb, int success) {
        return MathLibWrapper.binominal(trials, successProb, success);
    }

    @ApiFunction
    public static double poisson(double lambda, int x) {
        if (x < 0) {
            throw new IllegalArgumentException("x must be >= 0");
        }
        return MathLibWrapper.poisson(lambda, x);
    }

    @ApiFunction
    public static double gaussianCDF(double mu, double stdDev, double x) {
        return MathLibWrapper.gaussianCDF(mu, stdDev, x);
    }

    @ApiFunction
    public static double mean(double[] arr) {
        return MathLibWrapper.mean(arr);
    }

    @ApiFunction
    public static double trimmedMean(double[] arr, double percentage) {
        return MathLibWrapper.trimmedMean(arr, percentage);
    }

    @ApiFunction
    public static double geometricMean(double[] arr) {
        return MathLibWrapper.geometricMean(arr);
    }

    @ApiFunction
    public static double harmonicMean(double[] arr) {
        return MathLibWrapper.harmonicMean(arr);
    }

    @ApiFunction
    public static double median(double[] arr) {
        return MathLibWrapper.median(arr);
    }

    @ApiFunction
    public static double[] mode(double[] arr) {
        return MathLibWrapper.mode(arr);
    }

    @ApiFunction
    public static double min(double[] arr) {
        return MathLibWrapper.min(arr);
    }

    @ApiFunction
    public static double max(double[] arr) {
        return MathLibWrapper.max(arr);
    }

    @ApiFunction
    public static double range(double[] arr) {
        return MathLibWrapper.range(arr);
    }

    @ApiFunction
    public static double midrange(double[] arr) {
        return MathLibWrapper.midrange(arr);
    }

    @ApiFunction
    public static double variance(double[] arr) {
        return MathLibWrapper.variance(arr);
    }

    @ApiFunction
    public static double stdDev(double[] arr) {
        return MathLibWrapper.stdDev(arr);
    }

    @ApiFunction
    public static double sampleVariance(double[] arr) {
        return MathLibWrapper.sampleVariance(arr);
    }

    @ApiFunction
    public static double sampleStdDev(double[] arr) {
        return MathLibWrapper.sampleStdDev(arr);
    }

    @ApiFunction
    public static double hypotenuse(double sideA, double sideB) {
        return MathLibWrapper.hypotenuse(sideA, sideB);
    }

    @ApiFunction
    public static double sideRTriangle(double hypotenuse, double sideA) {
        return MathLibWrapper.sideRTriangle(hypotenuse, sideA);
    }

    @ApiFunction
    public static double sideTriangleLC(double sideA, double sideB, double oppositeAng) {
        return MathLibWrapper.sideTriangleLC(sideA, sideB, oppositeAng);
    }

    @ApiFunction
    public static double angTriangleLC(double oppositeSide, double sideA, double sideB) {
        return MathLibWrapper.angTriangleLC(oppositeSide, sideA, sideB);
    }

    @ApiFunction
    public static double sideTriangleLS(double oppositeAng, double sideA, double oppositeAng2A) {
        return MathLibWrapper.sideTriangleLS(oppositeAng, sideA, oppositeAng2A);
    }

    @ApiFunction
    public static double angTriangleLS(double oppositeSide, double sideA, double oppositeAng2A) {
        return MathLibWrapper.angTriangleLS(oppositeSide, sideA, oppositeAng2A);
    }

    @ApiFunction
    public static double cubeVol(double side) {
        return MathLibWrapper.cubeVol(side);
    }

    @ApiFunction
    public static double cuboidVol(double length, double width, double height) {
        return MathLibWrapper.cuboidVol(length, width, height);
    }

    @ApiFunction
    public static double prismVol(double baseArea, double height) {
        return MathLibWrapper.prismVol(baseArea, height);
    }

    @ApiFunction
    public static double regularPrismVol(double baseSide, int nOfBaseSides, double height) {
        return MathLibWrapper.regularPrismVol(baseSide, nOfBaseSides, height);
    }

    @ApiFunction
    public static double pyramidVol(double baseArea, double height) {
        return MathLibWrapper.pyramidVol(baseArea, height);
    }

    @ApiFunction
    public static double regPyramidVol(double baseSide, int nOfBaseSides, double height) {
        return MathLibWrapper.regPyramidVol(baseSide, nOfBaseSides, height);
    }

    @ApiFunction
    public static double cylinderVol(double baseRadius, double height) {
        return MathLibWrapper.cylinderVol(baseRadius, height);
    }

    @ApiFunction
    public static double coneVol(double baseRadius, double height) {
        return MathLibWrapper.coneVol(baseRadius, height);
    }

    @ApiFunction
    public static double sphereVol(double radius) {
        return MathLibWrapper.sphereVol(radius);
    }

}
