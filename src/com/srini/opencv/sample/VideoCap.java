//http://www.codeproject.com/Tips/719878/How-to-Use-OpenCV-with-Java-under-Eclipse-IDE

package com.srini.opencv.sample;

//importing required - first
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
        
public class VideoCap {
    public static void main (String args[]){
    	
    	//Secondly, we need to load the native library. This command must be executed once per Java process to using native OpenCV methods. If it does not get used, it throws an exception like "UnsatisfiedLink errors."
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	
    	//The following statement states that the camera variable is initialized with VideoCapture with 0 "Zero" parameter. It means that zero is first device ID. It accesses the first web camera. Video Camera is also initialized with video file, just pass video file path.
    	//VideoCapture is moved to org.opencv.videoio.VideoCapture from org.opencv.highgui.VideoCapture
    	VideoCapture camera = new VideoCapture(0);
    	
    	//( or ) VideoCapture camera = new VideoCapture("F\\video.avi"); 

    	//isOpened function is used to check if the binding of the class to a video source that means device index or video source file path was successful or not to use.
    	if(!camera.isOpened()){
    		System.out.println("Error");
    	}
    	else {
    		//Mat class with two data parts, first the matrix header which contains information such as the size of the matrix and second a pointer to the matrix contains the pixel values. The matrix header size is constant.
    		Mat frame = new Mat();
    		
    		//Following is an infinite loop till the frame is not obtained. 
    		//If frame has been obtained, it breaks the loop. 
    		//The following loop prints the frame height and width, it also writes the frame to file and break the loop.
    	    while(true){
    	    	if (camera.read(frame)){
    	    		System.out.println("Frame Obtained");
    	    		System.out.println("Captured Frame Width " + 
    	    		frame.width() + " Height " + frame.height());
    	    		
    	    		//imwrite is a static method from the Imgcodecs - open cv 3.x (Highgui class - open cv 2.x). 
    	    		//This method is used to write an image to a file. In the following frame variable is written to camera.jpg file.
    	    		Imgcodecs.imwrite("camera.jpg", frame);
    	    		System.out.println("OK");
    	    		break;
    	    	}
    	    }	
    	}

    	//For closing the video, we need to call its release function.
    	camera.release();
    }
}   