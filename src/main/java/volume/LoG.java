package volume;
import ij.*;

/**
 * This is a 2D Laplacian of Gaussian convolution kernel.
 *
 * LoG(x,y) = - 1 / (PI sigma^4) [1 - (x^2 + y^2) / 2 sigma^2] e ^ (-((x^2 + y^2)/2 sigma^2)))
 *
 *
 * @author: Michael Abramoff, (c) 1999-2003 Michael Abramoff. All rights reserved.
 *
 * Small print:
 * Permission to use, copy, modify and distribute this version of this software or any parts
 * of it and its documentation or any parts of it ("the software"), for any purpose is
 * hereby granted, provided that the above copyright notice and this permission notice
 * appear intact in all copies of the software and that you do not sell the software,
 * or include the software in a commercial package.
 * The release of this software into the public domain does not imply any obligation
 * on the part of the author to release future versions into the public domain.
 * The author is free to make upgraded or improved versions of the software available
 * for a fee or commercially only.
 * Commercial licensing of the software is available by contacting the author.
 * THE SOFTWARE IS PROVIDED "AS IS" AND WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS, IMPLIED OR OTHERWISE, INCLUDING WITHOUT LIMITATION, ANY
 * WARRANTY OF MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.
 */
public class LoG extends Kernel2D
{
        private double sigma;

        /**
        * Create a Laplacian of Gaussian kernel from standard deviation sigma.
        * @param sigma, the standard deviation of the associated probability function of the Gaussian function.
        */
        public LoG(double sigma)
        {
                this.sigma = sigma;
                if (sigma != 0)
                {
                        int width = (int) (6 * sigma + 1);
                        if (width % 2 == 0) width++;
                        halfwidth = width / 2;
                        k = new double[halfwidth*2+1][halfwidth*2+1];
                        for (int m = -halfwidth; m <= halfwidth; m++)
                        for (int l = -halfwidth; l <= halfwidth; l++)
                                k[m+halfwidth][l+halfwidth] = function(l, m);
                }
                else
                        halfwidth = 0;
        }
        public double getSigma() { return sigma; }
        /**
        * Compute Laplacian of Gaussian function at x,y.
        */
        protected double function(double x, double y)
        {
                return (- 1 / (Math.PI * Math.pow(sigma, 4))) * (1.0 - ((x*x + y*y)/ (2 * sigma * sigma)))
                                *
                        Math.exp(-((x*x + y*y) / (2 * sigma * sigma)));
        }
        public String toString() { return "Laplacian of Gaussian 2D "+sigma;  }
}
