//modified for opencv 3.0 https://blog.openshift.com/day-12-opencv-face-detection-for-java-developers/

package com.srini.opencv.sample;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**The code shown below does the following :

*   It loads the native OpenCV library so that we can use it using Java API.

*   We create an instance of CascadeClassifier passing it the name of the file from which the classifier is loaded.

*   Next we convert the image to a format which the Java API will accept using the Imgcodecs class  (earlier from Highgui). Mat is the OpenCV C++ n-dimensional dense array class.

*   Then we call the detectMultiScale method on the classifier passing it the image and MatOfRect object. After processing, the MatOfRect will have face detections.

*   We iterate over all the face detections and mark the image with rectangles using the Imgproc class (earlier from Core)

*   Finally, we write the image to the output.png file.
*   
*   $make -j4 (This is required for linux - to generate java open cv jar) 
*/
public class FaceDetector {
 
    public static void main(String[] args) {
 
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\nRunning FaceDetector");
 		
 
        System.out.println(System.getProperty("user.dir")+"\\haarcascade_frontalface_alt.xml");
        CascadeClassifier faceDetector = new CascadeClassifier(System.getProperty("user.dir")+"\\haarcascade_frontalface_alt.xml");
        Mat image = 
                Imgcodecs.imread(System.getProperty("user.dir")+"\\[000030].jpg");		
 
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
 
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
 
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }
 
        String filename = "ouput.png";
        System.out.println(String.format("Writing %s", filename));
        Imgcodecs.imwrite(filename, image);
    }
}