#i ran this on my mac using this command in terminal i cd'd into this folder and then
# python watermark_test.py --overlay overlay.png --input input --output output


from imutils import paths
import numpy as np
import argparse
import cv2
import os

# construct the argument parse and parse the arguments
ap = argparse.ArgumentParser()
ap.add_argument("-ov", "--overlay", required=True,
	help="path to watermark image")
ap.add_argument("-i", "--input", required=True,
	help="path to the input directory of images")
ap.add_argument("-o", "--output", required=True,
	help="path to the output directory")
ap.add_argument("-a", "--alpha", type=float, default=0.25,
	help="alpha transparency of the overlay")

args = vars(ap.parse_args())

# load the watermark image, 
watermark = cv2.imread(args["overlay"], cv2.IMREAD_UNCHANGED)
(wH, wW) = watermark.shape[:2]


for imagePath in paths.list_images(args["input"]):
	# load the input image, then add an extra dimension to the image (i.e., the alpha transparency)e
	image = cv2.imread(imagePath)
	(h, w) = image.shape[:2]
	image = np.dstack([image, np.ones((h, w), dtype="uint8") * 255])
	
	overlay = np.zeros((h, w, 4), dtype="uint8")
	overlay[h - wH - 10:h - 10, w - wW - 10:w - 10] = watermark

	# blend the two images together using transparent overlays
	output = image.copy()
	cv2.addWeighted(overlay, args["alpha"], output, 1.0, 0, output)

	# write the output image to disk
	filename = imagePath[imagePath.rfind(os.path.sep) + 1:]
	p = os.path.sep.join((args["output"], filename))
	cv2.imwrite(p, output)