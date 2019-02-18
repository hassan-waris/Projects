import gab.opencv.*;
import processing.video.*;
import java.awt.*;
import org.openkinect.processing.*;
import org.openkinect.tests.*;
import org.openkinect.freenect.*;


Kinect kinect;
Capture video;
OpenCV opencv;

boolean ir = false;

void setup() {
  size(1280, 960);
  kinect = new Kinect(this);
  kinect.initVideo();
  kinect.enableIR(ir);
  opencv = new OpenCV(this, 1280/2, 960/2);
  opencv.loadCascade("haarcascade_cars.xml"); 
}

void draw() {
  scale(2);
  opencv.loadImage(kinect.getVideoImage());
  image(kinect.getVideoImage(), 0, 0 );
  noFill();
  stroke(0, 255, 0);
  strokeWeight(2);
  Rectangle[] car = opencv.detect();
  println(car.length);

  for (int i = 0; i < car.length; i++) {
    println(car[i].x + "," + car[i].y);
    rect(car[i].x, car[i].y, car[i].width, car[i].height);
  }
}

void captureEvent(Capture c) {
  c.read();
}


void keyPressed() {
  if (key == 'i') {
    ir = !ir;
    kinect.enableIR(ir);
  }
}
