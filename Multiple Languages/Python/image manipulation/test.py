import numpy
import cv2
import argparse

# constructing the argument parsers
ap = argparse.ArgumentParser()
ap.add_argument("-i", "--input", required=True,
	help="path to input image")
ap.add_argument("-o", "--output", required=True,
	help="path to output image")
#ap.add_argument("-ov", '--overlay', required=True,
#	help="path to overlay image")
args = vars(ap.parse_args())


# load the input image from disk
img = cv2.imread(args['input'])

# convert the image to grayscale
gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
cv2.imwrite(args["output"], gray)
cv2.waitKey(0)
cv2.destroyAllWindows()