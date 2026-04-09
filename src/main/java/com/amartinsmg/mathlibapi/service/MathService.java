package com.amartinsmg.mathlibapi.service;

import com.amartinsmg.mathlibapi.schema.annotations.ApiFunction;
import com.amartinsmg.mathlibapi.schema.annotations.ApiParam;
import com.amartinsmg.mathlibapi.wrapper.MathLibWrapper;

public class MathService {

    @ApiFunction(name = "triangle-area-1", description = "Calculates triangle area using base and height")
    public static double triangleArea1(double base, double height) {
        return MathLibWrapper.triangleArea1(base, height);
    }

    @ApiFunction(name = "triangle-area-2", description = "Calculates triangle area two sides and the angle between them")
    public static double triangleArea2(
            @ApiParam(name = "side-a") double sideA,
            @ApiParam(name = "side-b") double sideB,
            @ApiParam(name = "angle-between-a-and-b") double angleGama) {
        return MathLibWrapper.triangleArea2(sideA, sideB, angleGama);
    }

    @ApiFunction(name = "triangle-area-3", description = "Calculates triangle area using three sides (Heron's formula)")
    public static double triangleArea3(
            @ApiParam(name = "side-a") double sideA,
            @ApiParam(name = "side-b") double sideB,
            @ApiParam(name = "side-c") double sideC) {
        return MathLibWrapper.triangleArea3(sideA, sideB, sideC);
    }

    @ApiFunction(name = "square-area", description = "Calculates square area")
    public static double squareArea(double side) {
        return MathLibWrapper.squareArea(side);
    }

    @ApiFunction(name = "rectangle-area", description = "Calculates rectangle area")
    public static double retangleArea(double width, double length) {
        return MathLibWrapper.retangleArea(width, length);
    }

    @ApiFunction(name = "rhombus-area", description = "Calculates rhombus area")
    public static double rhombusArea(
            @ApiParam(name = "larger-diagonal") double largerDiagonal,
            @ApiParam(name = "smaller-diagonal") double smallerDiagonal) {
        return MathLibWrapper.rhombusArea(largerDiagonal, smallerDiagonal);
    }

    @ApiFunction(name = "parallelogram-area-1", description = "Calculates parallelogram area using base and height")
    public static double parallelogramArea1(double base, double height) {
        return MathLibWrapper.parallelogramArea1(base, height);
    }

    @ApiFunction(name = "parallelogram-area-2", description = "Calculates parallelogram area using two sides and the angle between them")
    public static double parallelogramArea2(
            @ApiParam(name = "side-a") double sideA,
            @ApiParam(name = "side-b") double sideB,
            @ApiParam(name = "angle-between-a-and-b") double angleBeetweenAB) {
        return MathLibWrapper.parallelogramArea2(sideA, sideB, angleBeetweenAB);
    }

    @ApiFunction(name = "trapezoid-area", description = "Calculates trapezoid area")
    public static double trapezoidArea(
            @ApiParam(name = "larger-base") double largerBase,
            @ApiParam(name = "smaller-base") double smallerBase,
            double height) {
        return MathLibWrapper.trapezoidArea(largerBase, smallerBase, height);
    }

    @ApiFunction(name = "regular-polygon-area", description = "Calculates regular polygon area")
    public static double regPolygonArea(
            double side,
            @ApiParam(name = "number-of-sides") int nOfSides) {
        return MathLibWrapper.regPolygonArea(side, nOfSides);
    }

    @ApiFunction(name = "circle-area", description = "Calculates circle area")
    public static double circleArea(double radius) {
        return MathLibWrapper.circleArea(radius);
    }

    @ApiFunction(name = "circular-sector-area-1", description = "Calculates circular sector area using angle and radius")
    public static double circularSectorArea1(double angle, double radius) {
        return MathLibWrapper.circularSectorArea1(angle, radius);
    }

    @ApiFunction(name = "circular-sector-area-2", description = "Calculates circular sector area using arc length and radius")
    public static double circularSectorArea2(
            @ApiParam(name = "arc-length") double arc,
            double radius) {
        return MathLibWrapper.circularSectorArea2(arc, radius);
    }

    @ApiFunction(name = "ellipse-area", description = "Calculates ellipse area")
    public static double ellipseArea(
            @ApiParam(name = "semi-major-axis") double semiMajorAxis,
            @ApiParam(name = "semi-minor-axis") double semiMinorAxis) {
        return MathLibWrapper.ellipseArea(semiMajorAxis, semiMinorAxis);
    }

    @ApiFunction(name = "cube-area", description = "Calculates cube surface area")
    public static double cubeArea(double side) {
        return MathLibWrapper.cubeArea(side);
    }

    @ApiFunction(name = "cuboid-area", description = "Calculates cuboid surface area")
    public static double cuboidArea(double width, double length, double height) {
        return MathLibWrapper.cuboidArea(width, length, height);
    }

    @ApiFunction(name = "prism-area", description = "Calculates prism surface area")
    public static double prismArea(
            @ApiParam(name = "base-area") double baseArea,
            @ApiParam(name = "base-perimeter") double basePerimeter,
            double height) {
        return MathLibWrapper.prismArea(baseArea, basePerimeter, height);
    }

    @ApiFunction(name = "regular-prism-area", description = "Calculates regular prism surface area")
    public static double regularPrismArea(
            @ApiParam(name = "base-side") double baseSide,
            @ApiParam(name = "number-of-base-sides") int nOfBaseSides,
            double height) {
        return MathLibWrapper.regularPrismArea(baseSide, nOfBaseSides, height);
    }

    @ApiFunction(name = "pyramid-area", description = "Calculates pyramid surface area")
    public static double pyramidArea(
            @ApiParam(name = "base-area") double baseArea,
            @ApiParam(name = "base-perimeter") double basePerimeter,
            @ApiParam(name = "slant-height") double slantHeight) {
        return MathLibWrapper.pyramidArea(baseArea, basePerimeter, slantHeight);
    }

    @ApiFunction(name = "regular-pyramid-area", description = "Calculates regular pyramid surface area")
    public static double regPyramidArea(
            @ApiParam(name = "base-side") double baseSide,
            @ApiParam(name = "number-of-base-sides") int nOfBaseSides,
            double height) {
        return MathLibWrapper.regPyramidArea(baseSide, nOfBaseSides, height);
    }

    @ApiFunction(name = "cylinder-area", description = "Calculates cylinder surface area")
    public static double cylinderArea(
            @ApiParam(name = "base-radius") double baseRadius,
            double height) {
        return MathLibWrapper.cylinderArea(baseRadius, height);
    }

    @ApiFunction(name = "cone-area", description = "Calculates cone surface area")
    public static double coneArea(
            @ApiParam(name = "base-radius") double baseRadius,
            double height) {
        return MathLibWrapper.coneArea(baseRadius, height);
    }

    @ApiFunction(name = "sphere-area", description = "Calculates sphere surface area")
    public static double sphereArea(double radius) {
        return MathLibWrapper.sphereArea(radius);
    }

    @ApiFunction(name = "is-armstrong", description = "Checks if a number is an Armstrong number")
    public static boolean isArmstrong(long num) {
        return MathLibWrapper.isArmstrong(num);
    }

    @ApiFunction(name = "logarithm", description = "Calculates the logarithm of a number with a given base")
    public static double logarithm(double num, double base) {
        return MathLibWrapper.logarithm(num, base);
    }

    @ApiFunction(name = "nth-root", description = "Calculates the nth root of a number")
    public static double nthRoot(double radicand, double degree) {
        return MathLibWrapper.nthRoot(radicand, degree);
    }

    @ApiFunction(name = "round-to", description = "Rounds a number to a specified number of decimal places")
    public static double roundTo(
            double num,
            @ApiParam(name = "decimal-places") int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("decimalPlaces must be >= 0");
        }
        return MathLibWrapper.roundTo(num, decimalPlaces);
    }

    @ApiFunction(name = "permutation", description = "Calculates the permutation of a number")
    public static double permutationSmart(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("num must be >= 0");
        }
        if (num <= 20) {
            return (double) MathLibWrapper.permutation(num);
        }
        return MathLibWrapper.permutationlf(num);
    }

    @ApiFunction(name = "cycle-permutation", description = "Calculates the cycle permutation of a number")
    public static double cyclePermutationSmart(int num) {
        if (num <= 1) {
            throw new IllegalArgumentException("num must be > 1");
        }
        if (num <= 20) {
            return (double) MathLibWrapper.cyclePermutation(num);
        }
        return MathLibWrapper.cyclePermutationlf(num);
    }

    @ApiFunction(name = "arrangement", description = "Calculates the arrangement of selecting items from a total")
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

    @ApiFunction(name = "combination", description = "Calculates the combination of selecting items from a total")
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

    @ApiFunction(name = "factorial", description = "Calculates the factorial of a number")
    public static double factorialSmart(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("num must be >= 0");
        }
        if (num <= 20) {
            return (double) MathLibWrapper.factorial(num);
        }
        return MathLibWrapper.factoriallf(num);
    }

    @ApiFunction(name = "gcd", description = "Calculates the greatest common divisor of two numbers")
    public static long gcd(long x, long y) {
        return MathLibWrapper.gcd(x, y);
    }

    @ApiFunction(name = "deg-to-rad", description = "Converts degrees to radians")
    public static double deg2rad(double degrees) {
        return MathLibWrapper.deg2rad(degrees);
    }

    @ApiFunction(name = "rad-to-deg", description = "Converts radians to degrees")
    public static double rad2deg(double radians) {
        return MathLibWrapper.rad2deg(radians);
    }

    @ApiFunction(name = "distance-points", description = "Calculates the distance between two points")
    public static double distancePoints(
            @ApiParam(name = "a-x") double aX,
            @ApiParam(name = "a-y") double aY,
            @ApiParam(name = "b-x") double bX,
            @ApiParam(name = "b-y") double bY) {
        return MathLibWrapper.distancePoints(aX, aY, bX, bY);
    }

    @ApiFunction(name = "midpoint-points", description = "Calculates the midpoint between two points")
    public static double[] midpointPoints(
            @ApiParam(name = "a-x") double aX,
            @ApiParam(name = "a-y") double aY,
            @ApiParam(name = "b-x") double bX,
            @ApiParam(name = "b-y") double bY) {
        return MathLibWrapper.midpointPoints(aX, aY, bX, bY);
    }

    @ApiFunction(name = "slope-line", description = "Calculates the slope of a line passing through two points")
    public static double slopeLine(
            @ApiParam(name = "a-x") double aX,
            @ApiParam(name = "a-y") double aY,
            @ApiParam(name = "b-x") double bX,
            @ApiParam(name = "b-y") double bY) {
        return MathLibWrapper.slopeLine(aX, aY, bX, bY);
    }

    @ApiFunction(name = "inclination-line", description = "Calculates the inclination angle of a line")
    public static double inclinationLine(
            @ApiParam(name = "a-x") double aX,
            @ApiParam(name = "a-y") double aY,
            @ApiParam(name = "b-x") double bX,
            @ApiParam(name = "b-y") double bY) {
        return MathLibWrapper.inclinationLine(aX, aY, bX, bY);
    }

    @ApiFunction(name = "line-y-intercept", description = "Calculates the y-intercept of a line")
    public static double lineYIntercept(
            @ApiParam(name = "a-x") double aX,
            @ApiParam(name = "a-y") double aY,
            @ApiParam(name = "b-x") double bX,
            @ApiParam(name = "b-y") double bY) {
        return MathLibWrapper.lineYIntercept(aX, aY, bX, bY);
    }

    @ApiFunction(name = "distance-point-line", description = "Calculates the distance from a point to a line")
    public static double distancePointLine(
            double inclination,
            @ApiParam(name = "y-intercept") double yIntercept,
            @ApiParam(name = "p-x") double pX,
            @ApiParam(name = "p-y") double pY) {
        return MathLibWrapper.distancePointLine(inclination, yIntercept, pX, pY);
    }

    @ApiFunction(name = "circle-perimeter", description = "Calculates the perimeter of a circle")
    public static double circlePerimeter(double radius) {
        return MathLibWrapper.circlePerimeter(radius);
    }

    @ApiFunction(name = "polygon-diagonals", description = "Calculates the number of diagonals in a polygon")
    public static int polygonDiagonals(@ApiParam(name = "number-of-sides") int nOfSides) {
        return MathLibWrapper.polygonDiagonals(nOfSides);
    }

    @ApiFunction(name = "convex-polygon-sum-interior-angles", description = "Calculates the sum of interior angles of a convex polygon")
    public static double convexPolySumIntAng(@ApiParam(name = "number-of-sides") double nOfSides) {
        return MathLibWrapper.convexPolySumIntAng(nOfSides);
    }

    @ApiFunction(name = "regular-polygon-interior-angle", description = "Calculates the interior angle of a regular polygon")
    public static double regPolygonIAng(@ApiParam(name = "number-of-sides") int nOfSides) {
        return MathLibWrapper.regPolygonIAng(nOfSides);
    }

    @ApiFunction(name = "convex-polygon-exterior-angle", description = "Calculates the exterior angle of a convex polygon")
    public static double convexPolyExtAngle(@ApiParam(name = "number-of-sides") int nOfSides) {
        return MathLibWrapper.convexPolyExtAngle(nOfSides);
    }

    @ApiFunction(name = "simple-growth", description = "Calculates simple growth")
    public static double simpGrowth(double initial, double rate, double interval) {
        return MathLibWrapper.simpGrowth(initial, rate, interval);
    }

    @ApiFunction(name = "simple-growth-rate", description = "Calculates the simple growth rate")
    public static double simpGrowthRate(
            double initial,
            @ApiParam(name = "final") double final_,
            double interval) {
        return MathLibWrapper.simpGrowthRate(initial, final_, interval);
    }

    @ApiFunction(name = "compound-growth", description = "Calculates compound growth")
    public static double compGrowth(double initial, double rate, double interval) {
        return MathLibWrapper.compGrowth(initial, rate, interval);
    }

    @ApiFunction(name = "compound-growth-rate", description = "Calculates the compound growth rate")
    public static double compGrowthRate(
            double initial,
            @ApiParam(name = "final") double final_,
            double interval) {
        return MathLibWrapper.compGrowthRate(initial, final_, interval);
    }

    @ApiFunction(name = "is-happy", description = "Checks if a number is a happy number")
    public static boolean isHappy(long num) {
        return MathLibWrapper.isHappy(num);
    }

    @ApiFunction(name = "lcm", description = "Calculates the least common multiple of two numbers")
    public static long lcm(long x, long y) {
        return MathLibWrapper.lcm(x, y);
    }

    @ApiFunction(name = "n-percent-of-x", description = "Calculates n percent of x")
    public static double nPercentOfX(double x, double n) {
        return MathLibWrapper.nPercentOfX(x, n);
    }

    @ApiFunction(name = "n-is-what-percent-of-x", description = "Calculates what percentage n is of x")
    public static double nIsWhatPercentOfX(double x, double n) {
        return MathLibWrapper.nIsWhatPercentOfX(x, n);
    }

    @ApiFunction(name = "is-perfect", description = "Checks if a number is a perfect number")
    public static boolean isPerfect(long num) {
        return MathLibWrapper.isPerfect(num);
    }

    @ApiFunction(name = "prime-factors", description = "Calculates the prime factors of a number")
    public static long[] primeFactors(long num) {
        return MathLibWrapper.primeFactors(num);
    }

    @ApiFunction(name = "is-prime", description = "Checks if a number is a prime number")
    public static boolean isPrime(long num) {
        return MathLibWrapper.isPrime(num);
    }

    @ApiFunction(name = "binominal-distribution-probability", description = "Calculates the binomial distribution probability")
    public static double binominal(
            int trials,
            @ApiParam(name = "success-prob") double successProb,
            int success) {
        return MathLibWrapper.binominal(trials, successProb, success);
    }

    @ApiFunction(name = "poisson-distribution-probability", description = "Calculates the Poisson distribution probability")
    public static double poisson(double lambda, int x) {
        if (x < 0) {
            throw new IllegalArgumentException("x must be >= 0");
        }
        return MathLibWrapper.poisson(lambda, x);
    }

    @ApiFunction(name = "gaussian-cumlative-distribution", description = "Calculates the Gaussian Cumulative Distribution Function")
    public static double gaussianCDF(
            double mu,
            @ApiParam(name = "std-dev") double stdDev,
            double x) {
        return MathLibWrapper.gaussianCDF(mu, stdDev, x);
    }

    @ApiFunction(name = "mean", description = "Calculates the arithmetic mean of a dataset")
    public static double mean(@ApiParam(name = "dataset") double[] arr) {
        return MathLibWrapper.mean(arr);
    }

    @ApiFunction(name = "trimmed-mean", description = "Calculates the trimmed mean of a dataset")
    public static double trimmedMean(
            @ApiParam(name = "dataset") double[] arr,
            double percentage) {
        return MathLibWrapper.trimmedMean(arr, percentage);
    }

    @ApiFunction(name = "geometric-mean", description = "Calculates the geometric mean of a dataset")
    public static double geometricMean(@ApiParam(name = "dataset") double[] arr) {
        return MathLibWrapper.geometricMean(arr);
    }

    @ApiFunction(name = "harmonic-mean", description = "Calculates the harmonic mean of a dataset")
    public static double harmonicMean(@ApiParam(name = "dataset") double[] arr) {
        return MathLibWrapper.harmonicMean(arr);
    }

    @ApiFunction(name = "median", description = "Calculates the median of a dataset")
    public static double median(@ApiParam(name = "dataset") double[] arr) {
        return MathLibWrapper.median(arr);
    }

    @ApiFunction(name = "mode", description = "Calculates the mode of a dataset")
    public static double[] mode(@ApiParam(name = "dataset") double[] arr) {
        return MathLibWrapper.mode(arr);
    }

    @ApiFunction(name = "min", description = "Finds the minimum value in a dataset")
    public static double min(@ApiParam(name = "dataset") double[] arr) {
        return MathLibWrapper.min(arr);
    }

    @ApiFunction(name = "max", description = "Finds the maximum value in a dataset")
    public static double max(@ApiParam(name = "dataset") double[] arr) {
        return MathLibWrapper.max(arr);
    }

    @ApiFunction(name = "range", description = "Calculates the range of a dataset")
    public static double range(@ApiParam(name = "dataset") double[] arr) {
        return MathLibWrapper.range(arr);
    }

    @ApiFunction(name = "midrange", description = "Calculates the midrange of a dataset")
    public static double midrange(@ApiParam(name = "dataset") double[] arr) {
        return MathLibWrapper.midrange(arr);
    }

    @ApiFunction(name = "variance", description = "Calculates the variance of a dataset")
    public static double variance(@ApiParam(name = "dataset") double[] arr) {
        return MathLibWrapper.variance(arr);
    }

    @ApiFunction(name = "std-dev", description = "Calculates the standard deviation of a dataset")
    public static double stdDev(@ApiParam(name = "dataset") double[] arr) {
        return MathLibWrapper.stdDev(arr);
    }

    @ApiFunction(name = "sample-variance", description = "Calculates the sample variance of a dataset")
    public static double sampleVariance(@ApiParam(name = "dataset") double[] arr) {
        return MathLibWrapper.sampleVariance(arr);
    }

    @ApiFunction(name = "sample-std-dev", description = "Calculates the sample standard deviation of a dataset")
    public static double sampleStdDev(@ApiParam(name = "dataset") double[] arr) {
        return MathLibWrapper.sampleStdDev(arr);
    }

    @ApiFunction(name = "hypotenuse", description = "Calculates the hypotenuse of a right triangle")
    public static double hypotenuse(
            @ApiParam(name = "side-a") double sideA,
            @ApiParam(name = "side-b") double sideB
    ) {
        return MathLibWrapper.hypotenuse(sideA, sideB);
    }

    @ApiFunction(name = "side-right-triangle", description = "Calculates a side of a right triangle")
    public static double sideRTriangle(
            double hypotenuse,
            @ApiParam(name = "side-a") double sideA
    ) {
        return MathLibWrapper.sideRTriangle(hypotenuse, sideA);
    }

    @ApiFunction(name = "side-triangle-law-of-cos", description = "Calculates a triangle side using the Law of Cosines")
    public static double sideTriangleLC(
            @ApiParam(name = "side-a") double sideA,
            @ApiParam(name = "side-b") double sideB,
            @ApiParam(name = "opposite-ang") double oppositeAng) {
        return MathLibWrapper.sideTriangleLC(sideA, sideB, oppositeAng);
    }

    @ApiFunction(name = "ang-triangle-law-of-cos", description = "Calculates a triangle angle using the Law of Cosines")
    public static double angTriangleLC(
            @ApiParam(name = "opposite-side") double oppositeSide,
            @ApiParam(name = "side-a") double sideA,
            @ApiParam(name = "side-b") double sideB) {
        return MathLibWrapper.angTriangleLC(oppositeSide, sideA, sideB);
    }

    @ApiFunction(name = "side-triangle-law-of-sin", description = "Calculates a triangle side using the Law of Sines")
    public static double sideTriangleLS(
            @ApiParam(name = "opposite-ang") double oppositeAng,
            @ApiParam(name = "side-a") double sideA,
            @ApiParam(name = "opposite-ang-a") double oppositeAng2A) {
        return MathLibWrapper.sideTriangleLS(oppositeAng, sideA, oppositeAng2A);
    }

    @ApiFunction(name = "ang-triangle-law-of-sin", description = "Calculates a triangle angle using the Law of Sines")
    public static double angTriangleLS(
            @ApiParam(name = "opposite-side") double oppositeSide,
            @ApiParam(name = "side-a") double sideA,
            @ApiParam(name = "opposite-ang-a") double oppositeAng2A) {
        return MathLibWrapper.angTriangleLS(oppositeSide, sideA, oppositeAng2A);
    }

    @ApiFunction(name = "cube-volume", description = "Calculates the volume of a cube")
    public static double cubeVol(double side) {
        return MathLibWrapper.cubeVol(side);
    }

    @ApiFunction(name = "cuboid-volume", description = "Calculates the volume of a cuboid")
    public static double cuboidVol(double length, double width, double height) {
        return MathLibWrapper.cuboidVol(length, width, height);
    }

    @ApiFunction(name = "prism-volume", description = "Calculates the volume of a prism")
    public static double prismVol(
            @ApiParam(name = "base-area") double baseArea,
            double height) {
        return MathLibWrapper.prismVol(baseArea, height);
    }

    @ApiFunction(name = "regular-prism-volume", description = "Calculates the volume of a regular prism")
    public static double regularPrismVol(
            @ApiParam(name = "base-side") double baseSide,
            @ApiParam(name = "number-of-base-sides") int nOfBaseSides,
            double height) {
        return MathLibWrapper.regularPrismVol(baseSide, nOfBaseSides, height);
    }

    @ApiFunction(name = "pyramid-volume", description = "Calculates the volume of a pyramid")
    public static double pyramidVol(
            @ApiParam(name = "base-area") double baseArea,
            double height) {
        return MathLibWrapper.pyramidVol(baseArea, height);
    }

    @ApiFunction(name = "regular-pyramid-volume", description = "Calculates the volume of a regular pyramid")
    public static double regPyramidVol(
            @ApiParam(name = "base-side") double baseSide,
            @ApiParam(name = "number-of-base-sides") int nOfBaseSides,
            double height) {
        return MathLibWrapper.regPyramidVol(baseSide, nOfBaseSides, height);
    }

    @ApiFunction(name = "cylinder-volume", description = "Calculates the volume of a cylinder")
    public static double cylinderVol(
            @ApiParam(name = "base-radius") double baseRadius,
            double height) {
        return MathLibWrapper.cylinderVol(baseRadius, height);
    }

    @ApiFunction(name = "cone-volume", description = "Calculates the volume of a cone")
    public static double coneVol(
            @ApiParam(name = "base-radius") double baseRadius,
            double height) {
        return MathLibWrapper.coneVol(baseRadius, height);
    }

    @ApiFunction(name = "sphere-volume", description = "Calculates the volume of a sphere")
    public static double sphereVol(double radius) {
        return MathLibWrapper.sphereVol(radius);
    }

}
